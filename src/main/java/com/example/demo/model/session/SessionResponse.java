package com.example.demo.model.session;

import com.example.demo.model.response.OperationResponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class SessionResponse extends OperationResponse {
	@ApiModelProperty(required = true, value = "")
	private SessionItem item;
}
