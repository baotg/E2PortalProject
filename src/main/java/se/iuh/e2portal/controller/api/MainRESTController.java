package se.iuh.e2portal.controller.api;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import se.iuh.e2portal.config.jwt.JwtTokenProvider;
import se.iuh.e2portal.model.GradingResult;
import se.iuh.e2portal.model.GradingResultPK;
import se.iuh.e2portal.model.Student;
import se.iuh.e2portal.payload.LoginRequest;
import se.iuh.e2portal.payload.LoginResponse;
import se.iuh.e2portal.service.StudentService;
import se.iuh.e2portal.service.GradingResultService;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class MainRESTController {
	
    private static final int DEFAULT_RANGE = 100;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentService studentService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private GradingResultService gradingResultService;
    
    @GetMapping("/random")
    @ResponseBody
    public String randomNumber(@RequestParam(value = "range", required = false) Integer value, Authentication authentication) {
        if (value == null) {
            return "Random number is : " + new Random().nextInt(DEFAULT_RANGE);
        }
        return "Random number is : " + new Random().nextInt(value) + " Check " + authentication.getPrincipal().toString();
    }
    
    @GetMapping("/gradingresult")
	public ResponseEntity<Object> getGradingResultByStudentIdAndModuleClassId(@RequestParam("studentId")String studentId, 
																			  @RequestParam("moduleClassId")String moduleClassId) {
		GradingResultPK id = new GradingResultPK();
		id.setModuleClass(moduleClassId);
		id.setStudent(studentId);
		Optional<GradingResult> gradingResult = gradingResultService.findById(id);
		if(gradingResult.isPresent())
			return new ResponseEntity<Object>(gradingResult.get(),HttpStatus.OK);
		return new ResponseEntity<Object>("not-found",HttpStatus.OK);
		
	}
    
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getId(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        System.out.println(authentication);
//        System.out.println("Principal :" + authentication.getPrincipal());
        String jwt = tokenProvider.generateToken(authentication.getPrincipal().toString());
        return new LoginResponse(jwt);
    }
    @GetMapping("/user/profile")
    ResponseEntity<Student> getStudent(){
        Optional<Student> optionalStudent = studentService.profile();
        if(!optionalStudent.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.ok(optionalStudent.get());
    }
//    @PostMapping(value = "/updatePassword")
//    public ResponseEntity<Object> changePassword(@Valid @RequestBody String newPassword){
//
//    }
}