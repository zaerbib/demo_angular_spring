package com.example.demo.model.product;

import java.util.List;

import com.example.demo.model.response.PageResponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductResponse extends PageResponse {
	@ApiModelProperty(required = true, value = "")
	private List<Product> items;
}
