package se.iuh.e2portal.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.iuh.e2portal.model.Student;
import se.iuh.e2portal.service.StudentService;

@RestController
@RequestMapping("/api/profile")
public class UserController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Student> viewProfile(@RequestParam(value = "id", required = true) String id){
        Student student = studentService.findById(id);
        return ResponseEntity.ok().body(student);
    }
}
