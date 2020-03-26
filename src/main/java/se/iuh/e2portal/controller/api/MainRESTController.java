package se.iuh.e2portal.controller.api;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import se.iuh.e2portal.config.jwt.JwtTokenProvider;
import se.iuh.e2portal.model.*;
import se.iuh.e2portal.payload.LoginRequest;
import se.iuh.e2portal.payload.LoginResponse;
import se.iuh.e2portal.repository.UserAccountRepository;
import se.iuh.e2portal.service.StudentService;

import javax.naming.AuthenticationException;
import javax.validation.Valid;
import javax.xml.ws.Response;

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
    @GetMapping("/random")
    @ResponseBody
    public String randomNumber(@RequestParam(value = "range", required = false) Integer value) {
        if (value == null) {
            return "Random number is : " + new Random().nextInt(DEFAULT_RANGE);
        }
        return "Random number is : " + new Random().nextInt(value);
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getId(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        System.out.println(authentication);
//        System.out.println("Principal :" + authentication.getPrincipal());
        String jwt = tokenProvider.generateToken(authentication.getPrincipal().toString());
        return new LoginResponse(jwt);
    }
}