package com.example.demo.client.product;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dtos.ProductDetailDto;

import feign.Headers;

@FeignClient(name = "product-detail-client", url = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com")
public interface ProductDetailClient {
	
	 @GetMapping("/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json")
	 @Headers(value = "Content-Type: application/json")
	 List<ProductDetailDto> findAll();

}
