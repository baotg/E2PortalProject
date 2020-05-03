package se.iuh.e2portal.controller;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iuh.e2portal.component.TimeTableReader;
import se.iuh.e2portal.config.Message;
import se.iuh.e2portal.model.*;
import se.iuh.e2portal.service.ExcelFileHandlerService;
import se.iuh.e2portal.service.LecturerService;
import se.iuh.e2portal.service.ModuleClassService;
import se.iuh.e2portal.service.TimeTableService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/timetable")
public class TimeTableController {
	
    @Autowired
    private TimeTableReader timeTableReader;
    @Autowired
    private ModuleClassService moduleClassService;
    @Autowired
    private TimeTableService timeTableService;
    @Autowired
    private LecturerService lecturerService;
    @Autowired
	private ExcelFileHandlerService excelFileHandlerService;
    
    @GetMapping("")
    public String getTimeTables(@PageableDefault(size = 10) Pageable pageable, Model model) {
        model.addAttribute("timetables",timeTableService.findAll());
        return "time-table";
    }
   
    @GetMapping("/import")
    public String mapReadExcelDatatoDB(Model model) throws IOException {
    	if(excelFileHandlerService.getInputStream()==null)
			return "redirect:/";
    	@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(excelFileHandlerService.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        boolean validate = timeTableReader.validdateFile(sheet);
		if(!validate) {
			Message msg = Message.FILE_NOT_CORRECT;
			model.addAttribute("msg", msg.getMessage());
			model.addAttribute("timetables",timeTableService.findAll());
			return "time-table";
		}
        ModuleClass moduleClass = timeTableReader.getModuleClass(sheet);
        List<TimeTable> timeTableList = timeTableReader.getListTimeTable(sheet,moduleClass);
        excelFileHandlerService.setTimeTables(timeTableList);
        model.addAttribute("timeTables", timeTableList);
       
        return "time-table-preview";

    }
    
    @GetMapping("/import/save")
	public String saveAll(){
    	List<TimeTable> timeTables = excelFileHandlerService.getTimeTables();
    	if(timeTables.isEmpty()||timeTables == null)
    		return "redirect:/";
    	ModuleClass moduleClass = timeTables.get(0).getModuleClass();
        Lecturer lecturer = moduleClass.getLecturer();
        if(!lecturerService.existsById(lecturer.getId())){
            lecturerService.save(lecturer);
        }
        if(!moduleClassService.findById(moduleClass.getModuleClassId()).isPresent()){
            moduleClassService.save(moduleClass);
        }
        else {
            moduleClass = moduleClassService.findById(moduleClass.getModuleClassId()).get();
        }
        timeTableService.saveAll(timeTables);
		excelFileHandlerService.getTimeTables().clear();
		return "redirect:/timetable";
	}
    
	@GetMapping("/import/cancel")
	public String cancelImport() {
		excelFileHandlerService.getTimeTables().clear();
		return "redirect:/timetable";
	}
}
