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
import se.iuh.e2portal.service.ExcelFileHandlerService;


@Controller
public class MainController {

	@Autowired
	private ExcelFileHandlerService excelFileHandlerService;

	@GetMapping(value = {"/home","/"})
	public String index(Model model, @Param("ajax")String ajax) {
		String s = "This is text";
		model.addAttribute("msg", s);
		if(ajax!=null)
			return "home::home";
		return "home";
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
