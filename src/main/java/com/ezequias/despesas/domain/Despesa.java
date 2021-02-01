package com.ezequias.despesas.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class Despesa implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;		
	private String descricao;
	private Double valor;	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date cadastro;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "DESPESA_DESPESAITEM",
		joinColumns = @JoinColumn(name = "despesa_id"),
		inverseJoinColumns = @JoinColumn(name = "despesaitem_id")
	)
	private List<DespesaItem> itens = new ArrayList<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="referencias")
	private Set<Integer> referencias = new HashSet<>();
	
	public Despesa() {
		this.cadastro = new Date();
	}
	
	public Despesa(Integer id, String descricao, Double valor) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.cadastro = new Date();
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

	public Date getCadastro() {
		return cadastro;
	}

	public List<DespesaItem> getItens() {
		return itens;
	}

	public void setItens(List<DespesaItem> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Despesa other = (Despesa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
