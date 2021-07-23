package com.ezequias.despesas.dto;

import java.util.Date;

import com.ezequias.despesas.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

public class UsuarioDTO {
	private Integer id;		
	private String nome;
	private String email;
	private String senha;	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date cadastro;
	
	public UsuarioDTO() {}
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.cadastro = usuario.getCadastro();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
