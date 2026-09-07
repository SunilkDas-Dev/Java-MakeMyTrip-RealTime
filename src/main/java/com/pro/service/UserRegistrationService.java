package com.pro.service;

import org.springframework.web.multipart.MultipartFile;

import com.pro.dto.UserLoginDto;
import com.pro.dto.UserRegistrationDto;
import com.pro.entity.UserRegistration;

public interface UserRegistrationService {

	UserRegistration register(UserRegistrationDto userRegistrationDto);

	UserRegistration findByEmail(UserLoginDto userLoginDto);

	UserRegistration inserUserRegisterWithMultipartFiles(UserRegistrationDto userRegistrationDto, MultipartFile[] files);

}
