package com.example.demo.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.ApplicationException;
import com.example.demo.response.CustomerProductDetail;
import com.example.demo.response.RecommendedProduct;
import com.example.demo.service.CustomerPurshasesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerPurshasesController {
	
	private final Logger logger = Logger.getLogger("NotificationController");

	final CustomerPurshasesService customerPurshasesService;
	
	@GetMapping(value = "/compras", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerProductDetail>> getPurshasesOrder() {
		List<CustomerProductDetail> customerProductRelationsList = null;
		try {
			customerProductRelationsList = customerPurshasesService.findAll();
		} catch (ApplicationException e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customerProductRelationsList);
		}
		return ResponseEntity.ok(customerProductRelationsList);
	}
	
	@GetMapping(value = "/maior-compra/{ano}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerProductDetail> getPBiggerPurshase(@PathVariable Integer ano) {
		CustomerProductDetail customerProductBiggestPurshase = null;
		try {
			customerProductBiggestPurshase = customerPurshasesService.findBiggestPurchase(ano);
		} catch (ApplicationException e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customerProductBiggestPurshase);
		}
		return ResponseEntity.ok(customerProductBiggestPurshase);
	}
	
	@GetMapping(value = "/clientes-fieis", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerProductDetail>> getLoyalCustomers() {
		List<CustomerProductDetail> loayalCustomers = null;
		try {
			loayalCustomers = customerPurshasesService.getLoyalCustomers();
		} catch (ApplicationException e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(loayalCustomers);
		}
		return ResponseEntity.ok(loayalCustomers);
	}
	
	@GetMapping(value = "/recomendacao/cliente/tipo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecommendedProduct> getRecommendedProduct() {
		RecommendedProduct recommendedProduct = null;
		try {
			recommendedProduct = customerPurshasesService.getRecommendedProduct();
		} catch (ApplicationException e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(recommendedProduct);
		}
		return ResponseEntity.ok(recommendedProduct);
	}
	
}
