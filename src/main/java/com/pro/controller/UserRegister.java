package com.pro.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.constants.ConstantMessage;
import com.pro.dto.ResponseMessageDto;
import com.pro.dto.UserLoginDto;
import com.pro.dto.UserRegistrationDto;
import com.pro.entity.UserRegistration;
import com.pro.service.UserRegistrationService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UserRegisterController Controller", description = "Register Create, read, update and delete")

@RestController
public class UserRegister {

	@Autowired
	private UserRegistrationService userRegistrationService;

	@ApiResponses({ @ApiResponse(responseCode = "201", description = "User Created Successfull"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })

	@PostMapping(ConstantMessage.USERREGISTRATION)
	public ResponseEntity<ResponseMessageDto> register(@RequestBody UserRegistrationDto userRegistrationDto) {

		try {
			if (userRegistrationDto == null || userRegistrationDto.getEmail() == null
					|| userRegistrationDto.getEmail().isEmpty() || userRegistrationDto.getPassword() == null
					|| userRegistrationDto.getFirstName() == null || userRegistrationDto.getFirstName().isEmpty()
					|| userRegistrationDto.getLastName().isBlank() || userRegistrationDto.getPassword().isBlank()) {
				return ResponseEntity.status(HttpURLConnection.HTTP_BAD_REQUEST)
						.body(new ResponseMessageDto(ConstantMessage.FAILED, " some inserted data is inappropriate"));

			}
			UserRegistration register = userRegistrationService.register(userRegistrationDto);
			if (register != null) {
				return ResponseEntity.status(HttpURLConnection.HTTP_OK).body(new ResponseMessageDto(
						ConstantMessage.SUCCESS, " Make My Trip Registration Successfully ", register));
			} else {
				return ResponseEntity.status(HttpURLConnection.HTTP_BAD_REQUEST)
						.body(new ResponseMessageDto(ConstantMessage.FAILED, "Make My Trip Registration Failed"));
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpURLConnection.HTTP_INTERNAL_ERROR)
					.body(new ResponseMessageDto(ConstantMessage.FAILURE, " getting error during inserting data "));
		}

	}

	@PostMapping(ConstantMessage.USERLOGIN)
	public ResponseEntity<ResponseMessageDto> userLogin(@RequestBody UserLoginDto userLoginDto) {
		try {
			if (userLoginDto.getEmail().isBlank() || userLoginDto.getEmail().isEmpty()
					|| userLoginDto.getEmail() == null || userLoginDto.getPassword().isEmpty()
					|| userLoginDto.getPassword().isBlank() || userLoginDto.getPassword() == null) {
				return ResponseEntity.status(HttpURLConnection.HTTP_PRECON_FAILED).body(new ResponseMessageDto(
						ConstantMessage.FAILED, "You Have Entered Some Data Blank Or Empty , Try again"));

			}
			UserRegistration byEmail = userRegistrationService.findByEmail(userLoginDto);
			if (byEmail != null) {
				return ResponseEntity.status(HttpURLConnection.HTTP_OK)
						.body(new ResponseMessageDto(ConstantMessage.SUCCESS, " Welcome to Make My Trip", byEmail));
			} else {
				return ResponseEntity.status(HttpURLConnection.HTTP_NOT_FOUND)
						.body(new ResponseMessageDto(ConstantMessage.FAILED, " No User Found"));
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpURLConnection.HTTP_INTERNAL_ERROR)
					.body(new ResponseMessageDto(ConstantMessage.FAILURE, " Login Failed Due To Server Error"));
		}
	}

	@PostMapping(ConstantMessage.USERLOGINMULTIPART)
	public ResponseEntity<ResponseMessageDto> makeMyTripRegistartion(@RequestParam String jsonData,
			@RequestParam MultipartFile[] files) {
		try {
			UserRegistrationDto userRegistrationDto = new ObjectMapper().readValue(jsonData, UserRegistrationDto.class);

			UserRegistration multipartFiles = userRegistrationService
					.inserUserRegisterWithMultipartFiles(userRegistrationDto, files);
			if (multipartFiles != null) {
				return ResponseEntity.status(HttpURLConnection.HTTP_CREATED)
						.body(new ResponseMessageDto(ConstantMessage.SUCCESS,
								"Make My Trip user Registartion with Multipart Files saved Successfully"));
			} else {
				return ResponseEntity.status(HttpURLConnection.HTTP_CONFLICT).body(new ResponseMessageDto(
						ConstantMessage.FAILED, "Make My Trip user Registartion with Multipart Files Saving failed"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.body(new ResponseMessageDto(ConstantMessage.FAILURE, "Internal Server Eroor"));
		}
	}

}
