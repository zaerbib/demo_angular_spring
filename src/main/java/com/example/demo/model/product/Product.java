package com.example.demo.model.product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
	
	@Id
	private Integer id;
	private String  productCode;
	private String  productName;
	private String  description;
	private Long    standardCost;
	private Long    listPrice;
	private Integer targetLevel;
	private Integer reorderLevel;
	private Integer minimumReorderQuantity;
	private String  quantityPerUnit;
	private Integer discontinued;
	
	@ApiModelProperty(allowableValues = "Camera, Laptop, Tablet, Phone") 
	private String category;
	
	public Product(){}
}
