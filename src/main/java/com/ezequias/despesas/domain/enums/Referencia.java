package com.ezequias.despesas.domain.enums;

public enum  Referencia {
	JANEIRO(1, "Janeiro"),
	FEVEREIRO(2, "Fevereiro"),
	MARCO(3, "Março"),
	ABRIL(4,"Abril"),
	MAIO(5, "Maio"),
	JUNHO(6, "Junho"),
	JULHO(7, "Julho"),
	AGOSTO(8, "Agosto"),
	SETEMBRO(9, "Setembro"),
	OUTUBRO(10, "Outubro"),
	NOVEMBRO(11, "Novembro"),
	DEZEMBRO(12, "Dezembro");
	
	private int cod;
	private String descricao;
	
	private Referencia(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static Referencia toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		for(Referencia x : Referencia.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}		
		}
		throw new IllegalArgumentException("Id inválido " + cod);
	}
}
