package se.iuh.e2portal.controller;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.iuh.e2portal.component.GradingResultReader;
import se.iuh.e2portal.component.StudentReader;
import se.iuh.e2portal.model.*;
import se.iuh.e2portal.service.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/gradingresult")
public class GradingResultController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private MainClassService mainClassService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private StudentReader studentReader;
    @Autowired
    private GradingResultService gradingResultService;
    @Autowired
    private GradingResultReader gradingResultReader;
    @Autowired
    private ModuleClassService moduleClassService;

    @GetMapping("")
    public String getAll(Model model){
        model.addAttribute("gradingresults",gradingResultService.findAll());
        return "gradingresult";
    }
    @GetMapping("/{id}")
    public String getGradingResultByStudent(@PathVariable("id") String id, Model model){

        return "redirect:/";
    }
    @PostMapping("/import")
    public String mapReadExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile, Model model) throws IOException {

            Workbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            ModuleClass moduleClass = gradingResultReader.getModuleClass(sheet);
            if(!moduleClassService.existsById(moduleClass.getModuleClassId())){
                moduleClassService.save(moduleClass);
            }
            else {
                moduleClass = moduleClassService.findById(moduleClass.getModuleClassId()).get();
            }
            List<GradingResult> gradingResults = gradingResultReader.getListGradingResult(sheet,moduleClass);
            for(GradingResult gradingResult : gradingResults){
                if(!studentService.existsById(gradingResult.getStudent().getId()))
                    studentService.save(gradingResult.getStudent());
                else
                    gradingResult.setStudent(studentService.findById(gradingResult.getStudent().getId()).get());
            }
            gradingResultService.saveAll(gradingResults);
        return "redirect:/";
    }
}
