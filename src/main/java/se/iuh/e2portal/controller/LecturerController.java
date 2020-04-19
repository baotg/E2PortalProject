package se.iuh.e2portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
}
