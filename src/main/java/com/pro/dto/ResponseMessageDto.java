package com.pro.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResponseMessageDto {

	private String statusCode;
	private String status;
	private Object data;
	private List<?> list;

	public ResponseMessageDto(String statusCode) {
		this.statusCode = statusCode;
	}

	public ResponseMessageDto(String statusCode, String status) {
		this.statusCode = statusCode;
		this.status = status;
	}

	public ResponseMessageDto(String statusCode, String status, Object data) {
		this.statusCode = statusCode;
		this.status = status;
		this.data = data;
	}

	public ResponseMessageDto(String statusCode, String status, Object data, List<?> list) {
		this.statusCode = statusCode;
		this.status = status;
		this.data = data;
		this.list = list;
	}
}
