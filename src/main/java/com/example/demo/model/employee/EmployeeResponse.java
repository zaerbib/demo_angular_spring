package com.example.demo.model.employee;

import java.util.List;

import com.example.demo.model.response.PageResponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeResponse extends PageResponse {
	 @ApiModelProperty(required = true, value = "")
	  private List<Employee> items;
}
