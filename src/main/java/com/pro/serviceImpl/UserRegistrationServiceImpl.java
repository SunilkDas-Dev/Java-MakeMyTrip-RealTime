package com.pro.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pro.dto.UserLoginDto;
import com.pro.dto.UserRegistrationDto;
import com.pro.entity.Files;
import com.pro.entity.UserRegistration;
import com.pro.repo.FilesRepo;
import com.pro.repo.UserRegistrationRepo;
import com.pro.service.UserRegistrationService;

import java.util.Base64;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	UserRegistrationRepo userRegistrationRepo;

	@Autowired
	FilesRepo filesRepo;

	@Override
	public UserRegistration register(UserRegistrationDto userRegistrationDto) {
		UserRegistration register = new UserRegistration();

		try {
			register.setFirstname(userRegistrationDto.getFirstName());
			register.setLastName(userRegistrationDto.getLastName());
			register.setEmail(userRegistrationDto.getEmail());
			register.setPassword(Base64.getEncoder().encodeToString(userRegistrationDto.getPassword().getBytes()));
			register.setMobileNumber(userRegistrationDto.getMobileNumber());
			UserRegistration save = userRegistrationRepo.save(register);
			return save;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return register;

	}

	@Override
	public UserRegistration findByEmail(UserLoginDto userLoginDto) {

	 UserRegistration byEmail = userRegistrationRepo.findByEmail(userLoginDto.getEmail());
		if (byEmail != null) {
			String password = new String(Base64.getDecoder().decode(byEmail.getPassword()));

			if (password.equals(userLoginDto.getPassword())) {

				return byEmail;
			}

		}
		return null;
	}

	@Override
	public UserRegistration inserUserRegisterWithMultipartFiles(UserRegistrationDto userRegistrationDto,
			MultipartFile[] files) {
		UserRegistration register = new UserRegistration();
		try {
			register.setFirstname(userRegistrationDto.getFirstName());
			register.setLastName(userRegistrationDto.getLastName());
			register.setEmail(userRegistrationDto.getEmail());
			register.setPassword(Base64.getEncoder().encodeToString(userRegistrationDto.getPassword().getBytes()));
			register.setMobileNumber(userRegistrationDto.getMobileNumber());
			userRegistrationRepo.save(register);
			if (files != null && files.length < 0) {
				for (MultipartFile file : files) {

					Files fl = new Files();
					fl.setFileName(file.getOriginalFilename());
					fl.setFileType(file.getContentType());
					fl.setData(file.getBytes());
					filesRepo.save(fl);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return register;
	}

}
