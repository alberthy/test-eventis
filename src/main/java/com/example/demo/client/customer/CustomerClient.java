package com.example.demo.client.customer;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dtos.CustomerPurshasesDto;

import feign.Headers;

@FeignClient(name = "costumer-client", url = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com")
public interface CustomerClient {

	 @GetMapping("/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json")
	 @Headers(value = "Content-Type: application/json")
	 List<CustomerPurshasesDto> findAll();
	 
}