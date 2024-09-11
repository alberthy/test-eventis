package com.example.demo.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerProductDetail {

	private String nome;
	private String cpf;
	private List<ProductDetail> productDetail;
	
	public BigDecimal getTotalPurchases() {
		
		List<BigDecimal> prices = productDetail.stream().map( (item) -> item.getPrice()).collect(Collectors.toList());
		
		Optional<BigDecimal> result = prices.stream().reduce((x, y) -> x.add(y));
		
		return result.get();
	}
	
	
}
