package com.yagofx.farolshoppingdemo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.yagofx.farolshoppingdemo.entity.Item;

public interface ItemRepository extends PagingAndSortingRepository<Item, String> {

	@Query("SELECT i FROM Item i WHERE i.descricao LIKE %:filtro% ORDER BY codigo, numeroDeSerie, status")
	public List<Item> findByFiltro (@Param("filtro") String filtro);

	
	
}
