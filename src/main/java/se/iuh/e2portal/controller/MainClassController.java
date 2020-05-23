package se.iuh.e2portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import se.iuh.e2portal.model.MainClass;
import se.iuh.e2portal.model.ModuleClass;
import se.iuh.e2portal.model.Student;
import se.iuh.e2portal.service.FacultyService;
import se.iuh.e2portal.service.MainClassService;

import java.util.Optional;

@Controller
@RequestMapping("/mainclass")
public class MainClassController {
	
    @Autowired
    MainClassService mainClassService;
    @Autowired
	private FacultyService facultyService;
    @GetMapping("")
    public String getMainClass(@PageableDefault(size = 10) Pageable pageable, Model model, @Param("ajax")String ajax) {
		model.addAttribute("faculties", facultyService.findAll());
       if (ajax!=null)
            return "main-class::main-class";
        return "main-class";
    }
    @GetMapping("/search")
   	public String getMainClasses(@RequestParam("id") String id, Model model) {
   		model.addAttribute("listClass", mainClassService.findByFacultyId(id));
   		return "main-class::main-class-table";
   	}
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editMainClass(@RequestParam("id") String id, Model model){
        Optional<MainClass> result = mainClassService.findById(id);
        if(result.isPresent()){
            model.addAttribute("mainClass", result.get());
            return "edit-main-class::edit-main-class";
        }
        return "main-class::main-class";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveMainClass(MainClass mainClass, Model model){
        mainClassService.save(mainClass);
        model.addAttribute("listClass", facultyService.findById(mainClass.getFaculty().getFacultyId()));
        return "main-class::main-class-table";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteMainClass(@RequestParam("id") String id, Model model){
        Optional<MainClass> result = mainClassService.findById(id);
        if(result.isPresent()){
            mainClassService.delete(result.get());
            model.addAttribute("listClass", mainClassService.findByFacultyId(result.get().getFaculty().getFacultyId()));
            return "main-class::main-class-table";
        }
        return "redirect:/";
    }
}
