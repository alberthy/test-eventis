package com.example.demo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.client.customer.CustomerClient;
import com.example.demo.client.product.ProductDetailClient;
import com.example.demo.dtos.CustomerPurshasesDto;
import com.example.demo.dtos.ProductDetailDto;
import com.example.demo.exceptions.ApplicationException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.response.CustomerProductDetail;
import com.example.demo.response.ProductDetail;
import com.example.demo.response.RecommendedProduct;

/*
 *  @author: Albert Oliveira 10.09.2024
 */

@Service
public class CustomerPurshasesService {

	private CustomerClient customerClient;
	private ProductDetailClient productDetailClient;

	public CustomerPurshasesService(CustomerClient customerClient, ProductDetailClient productDetailClient) {
		this.customerClient = customerClient;
		this.productDetailClient = productDetailClient;
	}
	
	/*
	 *  Find all
	 */
	public List<CustomerProductDetail> findAll() throws ApplicationException {

		List<ProductDetailDto> productsDetail = productDetailClient.findAll();
		List<CustomerPurshasesDto> customersPurshases = customerClient.findAll();

		List<CustomerProductDetail> customerProductDetailResponse = new ArrayList<CustomerProductDetail>();

		customersPurshases.forEach((customer) -> {
			CustomerProductDetail customerProductDetailCurrent = new CustomerProductDetail();

			List<ProductDetail> productsDetailCurrent = new ArrayList<ProductDetail>();

			customer.getCompras().forEach((product) -> {
				ProductDetailDto productDetailCurrent = null;

				productDetailCurrent = productsDetail.stream()
						.filter((detail) -> String.valueOf(detail.getCodigo()).equals(product.getCodigo()))
						.collect(Collectors.toList()).get(0);
				if (productDetailCurrent != null) {
					productsDetailCurrent.add(productDetailCurrent.copyWith(product.getQuantidade()));
				}
			});

			customerProductDetailCurrent.setProductDetail(productsDetailCurrent);
			customerProductDetailCurrent.setCpf(customer.getNome());
			customerProductDetailCurrent.setNome(customer.getCpf());

			customerProductDetailResponse.add(customerProductDetailCurrent);

		});

		return customerProductDetailResponse;

	}

	/*
	 *  Filter by year
	 */
	public CustomerProductDetail findBiggestPurchase(Integer year) throws ApplicationException {
		
		validadeRequiredParam(year);

		// Fetch customers and products
		List<CustomerProductDetail> customerProductList = this.findAll();
		List<CustomerProductDetail> customerProductFilter = new ArrayList<CustomerProductDetail>();

		// Filter by year
		customerProductFilter = this.filterByYear(customerProductList, year);

		return customerProductFilter.get(0);

	}

	public List<CustomerProductDetail> filterByYear(List<CustomerProductDetail> customerProductList,
			Integer year) {
		
		List<CustomerProductDetail> customerProductFilter = new ArrayList<CustomerProductDetail>();

		Comparator<ProductDetail> byBiggestPurshaseComparatorProduct = (ProductDetail p1, ProductDetail p2) -> p2
				.getPrice().compareTo(p1.getPrice());

		customerProductList.forEach((item) -> {
			List<ProductDetail> productDetailFilterList = item.getProductDetail().stream().filter((e) -> e.getAnoCompra().equals(year))
					.collect(Collectors.toList());

			if (!productDetailFilterList.isEmpty()) {
				productDetailFilterList.sort(byBiggestPurshaseComparatorProduct);
				item.getProductDetail().clear();
				item.getProductDetail().add(productDetailFilterList.get(0));
				customerProductFilter.add(item);
			} 
		});

		return customerProductFilter;
	}

	/*
	 *  Top 3 Customers
	 */
	public List<CustomerProductDetail> getLoyalCustomers() throws ApplicationException {

		List<CustomerProductDetail> customerProductList = this.findAll();
		List<CustomerProductDetail> customerProductFilter = new ArrayList<CustomerProductDetail>();
		
		// max products
		Comparator<CustomerProductDetail> bySizedComparatorCustomer = (CustomerProductDetail p1,
				CustomerProductDetail p2) -> String.valueOf(p2.getProductDetail().size())
						.compareTo(String.valueOf(p1.getProductDetail().size()));

		// Order by amount
		customerProductFilter = this.orderByAmount(customerProductList);

		// Order master list
		customerProductFilter.sort(bySizedComparatorCustomer);

		return customerProductFilter.subList(0, 3);
	}
	
	public List<CustomerProductDetail> orderByAmount(List<CustomerProductDetail> customerProductList) {
		
		List<CustomerProductDetail> customerProductFilter = new ArrayList<CustomerProductDetail>();

		Comparator<ProductDetail> byAmountComparatorProduct = (ProductDetail p1, ProductDetail p2) -> p2
				.getAmount().compareTo(p1.getAmount());

		customerProductList.forEach((item) -> {
			item.getProductDetail().sort(byAmountComparatorProduct);
			customerProductFilter.add(item);
		});

		return customerProductFilter;
	}

	/*
	 *  Recommended product
	 */
	public RecommendedProduct getRecommendedProduct() throws ApplicationException {
		
		List<CustomerProductDetail> customerProductList = this.findAll();
		List<ProductDetail> productDetails = new ArrayList<ProductDetail>();
		
		LinkedHashSet<String> types = new LinkedHashSet<String>();
		Map<String, Integer> sizedTableTypes = new HashMap<String, Integer>();
		
		// load types and all products
		customerProductList.forEach((item) -> {
			productDetails.addAll(item.getProductDetail());
			types.addAll(item.getProductDetail().stream().map((detail) -> detail.getTipoVinho()).collect(Collectors.toList()));
		});
		
		// create table with key and size
		types.forEach( (type) -> {
			int countType = 0;
			for (ProductDetail item : productDetails) {
				if (item.getTipoVinho().equals(type)) {
					countType++;
					sizedTableTypes.put(type.concat("-" + item.getSafra()), countType);
				}
			}
		});
		
		// Extract max sale
		Integer max = 0;
		RecommendedProduct recommendedProduct = null;
		
		for (Map.Entry<String, Integer> map : sizedTableTypes.entrySet()) {
			if (map.getValue() > max) {
				max = map.getValue();
				recommendedProduct = new RecommendedProduct(map.getKey().split("-")[0], map.getKey().split("-")[1]);
			}
		}
		
		return recommendedProduct;
	}
	
	/*
	 * Validations
	 */
	private void validadeRequiredParam(Integer year) throws BadRequestException {
		if (Objects.isNull(year)) {
			throw new BadRequestException("O parâmetro ANO é obrigatório.");
		}
		
		if (year.compareTo(2017) == -1 || (year.compareTo(2022) == 1)){
			throw new BadRequestException("O parâmetro ANO deve está no internvalo entre 2017 à 2022.");
		}
		
	}
	
	
}
