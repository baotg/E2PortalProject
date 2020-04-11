package se.iuh.e2portal.controller;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import se.iuh.e2portal.component.ModuleClassReader;
import se.iuh.e2portal.component.TimeTableReader;
import se.iuh.e2portal.model.*;
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
    @GetMapping("")
    public String getTimeTables(@PageableDefault(size = 10) Pageable pageable, Model model) {
        model.addAttribute("timetables",timeTableService.findAll());
        return "timetable";
    }
    @PostMapping("/import")
    public String mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {
        Workbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        ModuleClass moduleClass = timeTableReader.getModuleClass(sheet);
        Lecturer lecturer = timeTableReader.getModuleClass(sheet).getLecturer();
        if(!lecturerService.existsById(lecturer.getId())){
            lecturerService.save(lecturer);
        }
        if(!moduleClassService.findById(moduleClass.getModuleClassId()).isPresent()){
            moduleClassService.save(moduleClass);
        }
        else {
            moduleClass = moduleClassService.findById(moduleClass.getModuleClassId()).get();
        }
        List<TimeTable> timeTableList = timeTableReader.getListTimeTable(sheet,moduleClass);
        timeTableService.saveAll(timeTableList);
        return "redirect:/timetable";
    }
}
