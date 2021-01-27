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
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Movimento implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;		
	private String descricao;
	private Integer mes;
	private Integer ano;	
	private Double total;	
	private Date cadastro;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "MOVIMENTO_ENTRADA",
		joinColumns = @JoinColumn(name = "entrada_id"),
		inverseJoinColumns = @JoinColumn(name = "movimento_id")
	)
	private List<Despesa> entradas = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "MOVIMENTO_SAIDA",
		joinColumns = @JoinColumn(name = "saida_id"),
		inverseJoinColumns = @JoinColumn(name = "movimento_id")
	)
	private List<Despesa> saidas = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "MOVIMENTO_DESPESA",
		joinColumns = @JoinColumn(name = "movimento_id"),
		inverseJoinColumns = @JoinColumn(name = "despesa_id")
	)
	private List<Despesa> despesas = new ArrayList<>();	
	
	public Movimento() {}	

	public Movimento(Integer id, String descricao, Integer mes, Integer ano, Date cadastro) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.mes = mes;
		this.ano = ano;
		this.cadastro = cadastro;
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

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}	

	public List<Despesa> getEntradas() {
		return entradas;
	}

	public void setEntradas(List<Despesa> entradas) {
		this.entradas = entradas;
	}

	public List<Despesa> getSaidas() {
		return saidas;
	}

	public void setSaidas(List<Despesa> saidas) {
		this.saidas = saidas;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
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
		Movimento other = (Movimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
