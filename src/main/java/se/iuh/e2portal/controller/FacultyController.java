package se.iuh.e2portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
