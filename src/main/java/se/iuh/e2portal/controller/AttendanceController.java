package se.iuh.e2portal.controller;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.iuh.e2portal.component.AttendanceReader;
import se.iuh.e2portal.component.GradingResultReader;
import se.iuh.e2portal.component.StudentReader;
import se.iuh.e2portal.model.Attendance;
import se.iuh.e2portal.model.ModuleClass;
import se.iuh.e2portal.service.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
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
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private AttendanceReader attendanceReader;

    @GetMapping("")
    public String getAll(Model model){
        model.addAttribute("attendances",attendanceService.findAll());
        return "attendance";
    }
    @GetMapping("/{id}")
    public String getAttendanceByStudent(@PathVariable("id") String id, Model model){

        return "redirect:/student";
    }
    @PostMapping("/import")
    public String mapReadExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile, Model model) throws IOException {
        Workbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        ModuleClass moduleClass = attendanceReader.getModuleClass(sheet);
        if(!moduleClassService.existsById(moduleClass.getModuleClassId())){
            moduleClassService.save(moduleClass);
        }
        else {
            moduleClass = moduleClassService.findById(moduleClass.getModuleClassId()).get();
        }
        List<Attendance> attendances = attendanceReader.getListAttendance(sheet,moduleClass);
        for(Attendance attendance : attendances){
            if(!studentService.existsById(attendance.getStudent().getId()))
                studentService.save(attendance.getStudent());
            else
                attendance.setStudent(studentService.findById(attendance.getStudent().getId()).get());
        }
        attendanceService.saveAll(attendances);
        return "redirect:/attendance";
    }
}
