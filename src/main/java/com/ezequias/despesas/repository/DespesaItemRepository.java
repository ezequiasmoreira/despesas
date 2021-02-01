package com.ezequias.despesas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezequias.despesas.domain.DespesaItem;
import com.ezequias.despesas.domain.Entrada;

@Repository
public interface DespesaItemRepository extends JpaRepository<DespesaItem, Integer>{
	
}

