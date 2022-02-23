package com.yagofx.farolshoppingdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.google.common.base.Optional;
import com.yagofx.farolshoppingdemo.entity.Item;
import com.yagofx.farolshoppingdemo.entity.enums.Disponibilidade;
import com.yagofx.farolshoppingdemo.entity.enums.Status;

public interface ItemRepository extends CrudRepository<Item, Long> {

	@Query(value = "SELECT * FROM itens i WHERE i.descricao ILIKE %:descricao%", nativeQuery = true)
	List<Item> findItensByDescricao(@Param("descricao") String descricao);
	List<Item> findAllByStatus(Status status);
	List<Item> findAllByDisponibilidade(Disponibilidade disponibilidade);
	Optional<Item> findById(int id);
	Item findByCodigo(String codigo);
	
	
}
