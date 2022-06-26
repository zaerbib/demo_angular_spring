package com.example.demo.model.user;

import com.example.demo.model.response.OperationResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class UserResponse extends OperationResponse{
	private User data = new User();
}
