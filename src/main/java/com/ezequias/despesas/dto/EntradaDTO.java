package com.ezequias.despesas.dto;

import java.util.Date;

import com.ezequias.despesas.domain.Entrada;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EntradaDTO {
	private Integer id;		
	private String descricao;
	private Double valor;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date cadastro;
	
	public EntradaDTO() {}
	
	public EntradaDTO(Entrada entrada) {
		this.id = entrada.getId();
		this.descricao = entrada.getDescricao();
		this.valor = entrada.getValor();
		this.cadastro = entrada.getCadastro();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}
	
}
