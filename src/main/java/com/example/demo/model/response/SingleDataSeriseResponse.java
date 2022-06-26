package com.example.demo.model.response;

import java.util.List;

import com.example.demo.model.data.SingleSerise;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SingleDataSeriseResponse extends OperationResponse {
	@SuppressWarnings("unused")
	private List<SingleSerise> items;
}
