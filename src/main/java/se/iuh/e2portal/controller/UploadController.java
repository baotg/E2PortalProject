package se.iuh.e2portal.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import se.iuh.e2portal.service.ExcelFileHandlerService;
@Transactional
@RestController
public class UploadController {
	
	@Autowired
	private ExcelFileHandlerService excelFileHandlerService;	

	@PostMapping("upload/import")
	public ResponseEntity<String> uploadFile(
			@RequestParam("file") MultipartFile uploadfile) throws IOException {

		if (uploadfile.isEmpty()) {
			return new ResponseEntity<String>("emty", HttpStatus.OK);
		}
		else {
			excelFileHandlerService.setInputStream(uploadfile.getInputStream());
			System.out.println(uploadfile.getOriginalFilename() + " " + uploadfile.getSize());
			return new ResponseEntity<String>("successful", HttpStatus.OK);
		}


	}

}
