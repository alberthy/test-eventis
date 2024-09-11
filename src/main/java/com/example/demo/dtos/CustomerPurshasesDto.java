package com.example.demo.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerPurshasesDto {

	private String nome;
	private String cpf;
	private List<ProductReferenceDto> compras;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public List<ProductReferenceDto> getCompras() {
		return compras;
	}
	
	public void setCompras(List<ProductReferenceDto> compras) {
		this.compras = compras;
	}
	
}
