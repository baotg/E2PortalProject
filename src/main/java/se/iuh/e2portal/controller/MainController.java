package se.iuh.e2portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        return "layout/mainlayout";
    }
    @GetMapping("/home")
    public String home(Model model) {
    	String s = "This is text";
    	model.addAttribute("msg", s);
    	return "home";
    }
  
}
