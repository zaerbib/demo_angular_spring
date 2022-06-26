package com.example.demo.api.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.data.SingleSerise;
import com.example.demo.model.response.OperationResponse.ResponseStatusEnum;
import com.example.demo.model.response.SingleDataSeriseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"Product"})
public class ProductStatsController {

	@Autowired 
	private JdbcTemplate jdbcTemplate;

	@ApiOperation(value = "Product Stats", response = SingleDataSeriseResponse.class)
	@RequestMapping(value = "/product-stats-by-quantity", method = RequestMethod.GET)
	public SingleDataSeriseResponse getProductStatsByQuantity() {

		String sql = "select product_name as name, sum(quantity) as value from order_details group by product_name ";
		SingleSerise singleSerise;
		SingleDataSeriseResponse resp = new SingleDataSeriseResponse();
		ArrayList<SingleSerise> dataItemList = new ArrayList<SingleSerise>();


		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : list) {
			singleSerise = new SingleSerise((String)row.get("name"),(BigDecimal)row.get("value") );
			dataItemList.add(singleSerise);
		}
		resp.setItems(dataItemList);
		resp.setOperationStatus(ResponseStatusEnum.SUCCESS);
		resp.setOperationMessage("Orders by Quantity Ordered");
		//resp.setItems(singleSerise);
		return resp;
	}
}
