package com.example.demo.model.customer;

import java.util.List;

import com.example.demo.model.response.PageResponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerResponse extends PageResponse {
	@ApiModelProperty(required = true, value = "")
	  private List<Customer> items;
}
