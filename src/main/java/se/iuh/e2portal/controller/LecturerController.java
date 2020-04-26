package se.iuh.e2portal.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import se.iuh.e2portal.model.Lecturer;
import se.iuh.e2portal.service.LecturerService;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;
    
    @GetMapping("")
    public String getMainClass(Model model){
        model.addAttribute("lecturers",lecturerService.findAll());
        return "lecturer";
    }
    
    @GetMapping("/{id}")
	public String getLecturer(@PathVariable("id") String id, Model model){
		Optional<Lecturer> lecturer = lecturerService.findById(id);
		if(!lecturer.isPresent())
			return "redirect:/";
		model.addAttribute("lecturer", lecturer.get());
		return "view-lecturer";
	}
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editLecturer(@RequestParam("id") String id, Model model){
		Optional<Lecturer> result = lecturerService.findById(id);
		if(result.isPresent()){
			model.addAttribute("lecturer", result.get());
			return "edit-lecturer";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteLecturer(@RequestParam("id") String id, Model model){
		Optional<Lecturer> result = lecturerService.findById(id);
		if(result.isPresent()){
			lecturerService.delete(result.get());
			return "redirect:/lecturer";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveLecturer(Lecturer lecturer){
		lecturerService.save(lecturer);
		return "redirect:/lecturer";
	}
}
