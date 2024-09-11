package com.example.demo.dtos;

import java.math.BigDecimal;

import com.example.demo.response.ProductDetail;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailDto {
	
	private Integer codigo;
	
	@JsonProperty("tipo_vinho")
	private String tipoVinho;
	
	private BigDecimal preco;
	
	private String safra;
	
	@JsonProperty("ano_compra")
	private Integer anoCompra;
	
//	public Integer getCodigo() {
//		return codigo;
//	}
//	
//	public void setCodigo(Integer codigo) {
//		this.codigo = codigo;
//	}
//	
//	public String getTipoVinho() {
//		return tipoVinho;
//	}
//	
//	public void setTipoVinho(String tipoVinho) {
//		this.tipoVinho = tipoVinho;
//	}
//	
//	public BigDecimal getPreco() {
//		return preco;
//	}
//	
//	public void setPreco(BigDecimal preco) {
//		this.preco = preco;
//	}
//	
//	public String getSafra() {
//		return safra;
//	}
//	
//	public void setSafra(String safra) {
//		this.safra = safra;
//	}
//	
//	public Integer getAnoCompra() {
//		return anoCompra;
//	}
//	
//	public void setAnoCompra(Integer anoCompra) {
//		this.anoCompra = anoCompra;
//	}
	
	public ProductDetail copyWith(Integer amount) {
		return new ProductDetail(this.codigo, this.tipoVinho, this.preco, this.safra, this.anoCompra, amount);
	}
	
	
}

