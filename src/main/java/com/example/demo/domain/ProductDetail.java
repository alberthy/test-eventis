package com.example.demo.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDetail {

	private Integer codigo;
	
	private String tipoVinho;
	
	private BigDecimal price;
	
	private String safra;
	
	private Integer anoCompra;
	
	private Integer amount;
	
}
