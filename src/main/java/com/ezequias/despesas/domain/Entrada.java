package com.ezequias.despesas.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Entrada implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;		
	private String descricao;
	private Double valor;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="referencias")
	private Set<Integer> referencias = new HashSet<>();
	
	public Entrada() {}

	public Entrada(Integer id, String descricao, Double valor) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
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

	public Set<Integer> getReferencias() {
		return referencias;
	}

	public void setReferencias(Set<Integer> referencias) {
		this.referencias = referencias;
	}
	
	
}
