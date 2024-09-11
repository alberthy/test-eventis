package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.CustomerProductDetail;
import com.example.demo.domain.RecommendedProduct;
import com.example.demo.exceptions.ApplicationException;
import com.example.demo.service.CustomerPurshasesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerPurshasesController {

	final CustomerPurshasesService customerPurshasesService;
	
	@GetMapping("/compras")
	public ResponseEntity<List<CustomerProductDetail>> getPurshasesOrder() {
		return ResponseEntity.ok(customerPurshasesService.findAll());
	}
	
	@GetMapping("/maior-compra/{ano}")
	public ResponseEntity<CustomerProductDetail> getPBiggerPurshase(@PathVariable Integer ano) throws ApplicationException {
		return ResponseEntity.ok(customerPurshasesService.findBiggestPurchase(ano));
	}
	
	@GetMapping("/clientes-fieis")
	public ResponseEntity<List<CustomerProductDetail>> getLoyalCustomers() {
		return ResponseEntity.ok(customerPurshasesService.getLoyalCustomers());
	}
	
	@GetMapping("/recomendacao/cliente/tipo")
	public ResponseEntity<RecommendedProduct> getRecommendedProduct() {
		return ResponseEntity.ok(customerPurshasesService.getRecommendedProduct());
	}
	
}
