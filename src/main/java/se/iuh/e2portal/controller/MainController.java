package se.iuh.e2portal.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import se.iuh.e2portal.service.*;


@Controller
public class MainController {

	@Autowired
	private ExcelFileHandlerService excelFileHandlerService;
	@Autowired
	ModuleClassService moduleClassService;
	@Autowired
	MainClassService mainClassService;
	@Autowired
	GradingResultService gradingResultService;
	@Autowired
	AnnouncementService announcementService;
	@Autowired
	StudentService studentService;
	@Autowired
	LecturerService lecturerService;
	@Autowired
	FacultyService facultyService;

	@GetMapping(value = {"/home","/"})
	public String index(Model model, @Param("ajax")String ajax) {
		long totalClass = 0;
		long totalStudent = 0;
		long totalFaculty = 0;
		long totalAnnouncement = 0;
		long totalLecturer = lecturerService.count();
		totalClass = mainClassService.count() + moduleClassService.count();
		totalStudent = studentService.count();
		totalFaculty = facultyService.count();
		totalAnnouncement = announcementService.count();
		model.addAttribute("totalAnnouncement",totalAnnouncement);
		model.addAttribute("totalFaculty",totalFaculty);
		model.addAttribute("totalStudent",totalStudent);
		model.addAttribute("totalClass",totalClass);
		model.addAttribute("totalLecturer",totalLecturer);
		if(ajax!=null)
			return "home::home";
		
		return "home";
	}
	@GetMapping("/403")
	public String getAllAccount(){
		return "403";
	}
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile uploadfile) throws IOException {

		if (uploadfile.isEmpty()) {
			return new ResponseEntity<String>("empty", HttpStatus.OK);
		}
		else {
			String name = uploadfile.getOriginalFilename();
			String ext = name.substring(name.length()-4);
			if(!ext.equalsIgnoreCase("xlsx"))
				return new ResponseEntity<String>("notMatch", HttpStatus.OK);
			excelFileHandlerService.setInputStream(uploadfile.getInputStream());
			return new ResponseEntity<String>("successful", HttpStatus.OK);
		}
	}
	
	@GetMapping("/handle")
	public ResponseEntity<String> doReturn(){
		return new ResponseEntity<String>("notMatch", HttpStatus.OK);
	}
	
	@GetMapping("/download")
	public String download(@Param("ajax")String ajax) {
		if(ajax!=null)
			return "excel-template-download::excel-template";
		return "excel-template-download";
	}


}
