package com.pro.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pro.constants.ConstantMessage;
import com.pro.dto.ResponseMessageDto;
import com.pro.entity.Files;
import com.pro.repo.FilesRepo;

@RestController
public class FilesController {

	@Autowired
	FilesRepo filesRepo;

	@PostMapping(ConstantMessage.SINGLEFILEINPUT)
	public ResponseEntity<ResponseMessageDto> insertFile(@RequestParam MultipartFile file) throws IOException {
		Files files = new Files();
		files.setFileName(file.getOriginalFilename());
		files.setFileType(file.getContentType());
		files.setData(file.getBytes());

		Files save = filesRepo.save(files);
		if (save != null) {
			return ResponseEntity.status(HttpURLConnection.HTTP_CREATED).body(new ResponseMessageDto(
					ConstantMessage.SUCCESS, " Files Inserted Successfully", file.getOriginalFilename()));

		} else {
			return ResponseEntity.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.body(new ResponseMessageDto(ConstantMessage.FAILED, "File insertion failed"));
		}
	}

	@PostMapping(ConstantMessage.MULTIFILEINPUT)
	public ResponseEntity<Stream<Object>> createMultipleFile(@RequestParam MultipartFile[] files) throws IOException {
	    Stream<Object> map = Arrays.stream(files).map(s -> {
	        try {
	            return this.insertFile(s);
	        } catch (Exception e) {
	            return "files upload failed" + e.getLocalizedMessage();
	        }
	    });
	    return ResponseEntity.ok(map);
	}


}
