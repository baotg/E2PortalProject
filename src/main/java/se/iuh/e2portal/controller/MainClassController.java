package se.iuh.e2portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iuh.e2portal.service.MainClassService;

@Controller
@RequestMapping("/mainclass")
public class MainClassController {
    @Autowired
    MainClassService mainClassService;
    @GetMapping("")
    public String getMainClass(@PageableDefault(size = 10) Pageable pageable, Model model) {
        model.addAttribute("listClass",mainClassService.findAll());
        return "mainclass";
    }
}