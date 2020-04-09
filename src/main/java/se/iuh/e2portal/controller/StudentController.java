package se.iuh.e2portal.controller;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.iuh.e2portal.component.StudentReader;
import se.iuh.e2portal.model.Faculty;
import se.iuh.e2portal.model.MainClass;
import se.iuh.e2portal.model.Student;
import se.iuh.e2portal.service.FacultyService;
import se.iuh.e2portal.service.MainClassService;
import se.iuh.e2portal.service.StudentService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private MainClassService mainClassService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private StudentReader studentReader;
//    @GetMapping("")
//    public String getMainClass(@PageableDefault(size = 10) Pageable pageable, Model model) {
//        Iterable<Student> page = studentService.findAll();
//        model.addAttribute("page", page);
//        return "student";
//    }
    @GetMapping("")
    public String getMainClass(Model model){
        model.addAttribute("listClass",mainClassService.findAll());
        return "student";
    }
    @GetMapping("/{id}")
    public String getStudent(@PathVariable("id") String id, Model model){
        Optional<MainClass> result = mainClassService.findById(id);
        if(result.isPresent()){
            List<Student> studentList = studentService.findByMainClass(result.get());
            model.addAttribute("studentList", studentList);
            return "studentdetail";
        }
        return "redirect:/student";
    }
    @PostMapping("/import")
    public String mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {
        Workbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        MainClass mainClass = studentReader.getMainClass(sheet);
        Faculty faculty = mainClass.getFaculty();
        if(!facultyService.findById(faculty.getFalcultyId()).isPresent()){
            facultyService.save(faculty);
        }
        else {
            faculty = facultyService.findById(faculty.getFalcultyId()).get();
            mainClass.setFaculty(faculty);
        }
        if(!mainClassService.findById(mainClass.getClassId()).isPresent()){
            mainClassService.save(mainClass);
        }
        else {
            mainClass = mainClassService.findById(mainClass.getClassId()).get();
        }
        List<Student> students = studentReader.getListStudent(sheet, mainClass);
        studentService.saveAll(students);
        return "redirect:/student";
    }
}
