package se.iuh.e2portal.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import se.iuh.e2portal.model.Faculty;
import se.iuh.e2portal.service.FacultyService;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
	
	@Autowired
	private FacultyService facultyService;
	@GetMapping("")
	public String getMainClass(Model model, @Param("ajax")String ajax){
		model.addAttribute("faculties", facultyService.findAll());
		if(ajax!=null)
			return "faculty::faculty";
		return "faculty";
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editFaculty(@RequestParam("id") String id, Model model){
		Optional<Faculty> result = facultyService.findById(id);
		if(result.isPresent()){
			model.addAttribute("faculty", result.get());
			return "edit-faculty::edit-faculty";
		}
		return "redirect:/?ajax=true";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteFaculty(@RequestParam("id") String id, Model model){
		Optional<Faculty> result = facultyService.findById(id);
		if(result.isPresent()){
			facultyService.delete(result.get());
			model.addAttribute("faculties", facultyService.findAll());
			return "faculty::faculty-table";
		}
		return "redirect:/?ajax=true";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveFaculty(Faculty faculty, Model model){
		System.out.println(faculty.getFacultyId() + " " + faculty.getName());
		facultyService.save(faculty);
		model.addAttribute("faculties", facultyService.findAll());
		return "faculty::faculty-table";
	}

}
