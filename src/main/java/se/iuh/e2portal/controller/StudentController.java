package se.iuh.e2portal.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentController {
    @GetMapping("")
    public String getAnnouncements(@PageableDefault(size = 10) Pageable pageable, Model model) {
        return "student";
    }
}
