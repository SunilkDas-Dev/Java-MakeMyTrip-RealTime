package com.pro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

	private String firstName;
	private String lastName;

	@NotBlank(message = "Name cannot be blank")
	@Schema(description = "User name", example = "email")
	private String email;
	@NotBlank(message = "Name cannot be blank")
	@Schema(description = "Please enter password", example = "password")
	private String password;
	private String mobileNumber;

}
