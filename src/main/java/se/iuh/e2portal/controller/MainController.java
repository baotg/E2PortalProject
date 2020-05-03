package se.iuh.e2portal.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import se.iuh.e2portal.service.ExcelFileHandlerService;


@Controller
public class MainController {
	
	@Autowired
	private ExcelFileHandlerService excelFileHandlerService;
	
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
    
    @PostMapping("/upload")
   	public ResponseEntity<String> uploadFile(
   			@RequestParam("file") MultipartFile uploadfile) throws IOException {
   		if (uploadfile.isEmpty()) {
   			return new ResponseEntity<String>("empty", HttpStatus.OK);
   		}
   		else {
   			excelFileHandlerService.setInputStream(uploadfile.getInputStream());
   			return new ResponseEntity<String>("successful", HttpStatus.OK);
   		}
   	}
//    @PostMapping("/upload-student")
//   	public ResponseEntity<String> uploadFileStudent(
//   			@RequestParam("file-student") MultipartFile uploadfile) throws IOException {
//   		if (uploadfile.isEmpty()) {
//   			return new ResponseEntity<String>("empty", HttpStatus.OK);
//   		}
//   		else {
//   			excelFileHandlerService.setInputStream(uploadfile.getInputStream());
//   			return new ResponseEntity<String>("successful", HttpStatus.OK);
//   		}
//   	}
//    @PostMapping("/upload-module-class")
//   	public ResponseEntity<String> uploadFileModuleClass(
//   			@RequestParam("file-module-class") MultipartFile uploadfile) throws IOException {
//   		if (uploadfile.isEmpty()) {
//   			return new ResponseEntity<String>("empty", HttpStatus.OK);
//   		}
//   		else {
//   			excelFileHandlerService.setInputStream(uploadfile.getInputStream());
//   			return new ResponseEntity<String>("successful", HttpStatus.OK);
//   		}
//   	}
//    @PostMapping("/upload-grading-result")
//   	public ResponseEntity<String> uploadFileGradingResult(
//   			@RequestParam("file-grading-result") MultipartFile uploadfile) throws IOException {
//   		if (uploadfile.isEmpty()) {
//   			return new ResponseEntity<String>("empty", HttpStatus.OK);
//   		}
//   		else {
//   			excelFileHandlerService.setInputStream(uploadfile.getInputStream());
//   			return new ResponseEntity<String>("successful", HttpStatus.OK);
//   		}
//   	}
//    @PostMapping("/upload-time-table")
//   	public ResponseEntity<String> uploadFileTimeTable(
//   			@RequestParam("file-time-table") MultipartFile uploadfile) throws IOException {
//   		if (uploadfile.isEmpty()) {
//   			return new ResponseEntity<String>("empty", HttpStatus.OK);
//   		}
//   		else {
//   			excelFileHandlerService.setInputStream(uploadfile.getInputStream());
//   			return new ResponseEntity<String>("successful", HttpStatus.OK);
//   		}
//   	}
    
    
    @GetMapping("/download")
    public String download() {
    	return "excel-template-download";
    }
    
  
}
