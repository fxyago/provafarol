package com.yagofx.farolshoppingdemo.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yagofx.farolshoppingdemo.entity.Item;
import com.yagofx.farolshoppingdemo.entity.enums.Status;
import com.yagofx.farolshoppingdemo.repository.ItemRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ItemController {

	@Autowired
	private ItemRepository repository;

	@GetMapping("/itens")
	public ResponseEntity<List<Item>> getAllItens() {
		try {
			List<Item> itens = new ArrayList<>();
			this.repository.findAll().forEach(itens::add);
			
			if (itens.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(itens, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/itens/{id}")
	public ResponseEntity<Item> findById(@PathVariable(value="id") Integer id) {
		Item item = this.repository.findById(id).orNull();
		if (item == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	@GetMapping("/itens/codigo/{codigo}")
	public ResponseEntity<Item> findByCodigo(@PathVariable(value="codigo") String codigo) {
		Item item = this.repository.findByCodigo(codigo);
		if (item == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	@PutMapping("/itens/{id}")
	public ResponseEntity<String> updateItem(@PathVariable("id") int id, @RequestBody Item item) {
		Item itemFound = this.repository.findById(id).orNull();
		if (itemFound != null) {
			if (item.getDescricao() != null) {
				itemFound.setDescricao(item.getDescricao());				
			}
			if (item.getLocalizacao() != null) {
				itemFound.setLocalizacao(item.getLocalizacao());				
			}
			if (item.getDisponibilidade() != null) {
				itemFound.setDisponibilidade(item.getDisponibilidade());				
			}
			if (item.getStatus() != null) {
				itemFound.setStatus(item.getStatus());				
			}
			try {
				this.repository.save(itemFound);				
			} catch (ConstraintViolationException cve) {
				List<String> erros = new ArrayList<>();
				cve.getConstraintViolations().forEach(t -> erros.add(t.getMessage()));
				return new ResponseEntity<>(erros.toString(), HttpStatus.NOT_ACCEPTABLE);
			}
			return new ResponseEntity<>("Item foi alterado com sucesso.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Item não foi encontrado com o id '" + id + "'", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/itens")
	public ResponseEntity<String> createItem(@RequestBody Item item) {
		try {
			this.repository.save(new Item(item.getCodigo(), item.getDescricao(), item.getLocalizacao()));
			return new ResponseEntity<>("Item criado com sucesso.", HttpStatus.CREATED);
		} catch (Exception e) {
			String message = "Já existe um item cadastrado com esse código";
			return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@DeleteMapping("/itens/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable("id") int id) {
		Item itemFound = this.repository.findById(id).orNull();
		if (itemFound != null) {
			itemFound.setStatus(Status.INATIVO);
			this.repository.save(itemFound);
			return new ResponseEntity<>("Item desativado com sucesso.", HttpStatus.OK);
		}
		return new ResponseEntity<>("Item não encontrado.", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/itens/descricao={descricao}")
	public ResponseEntity<List<Item>> getItensByDescricao(@PathVariable("descricao") String descricao) {
		List<Item> itensFound = this.repository.findItensByDescricao(descricao);
		if (itensFound.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(itensFound, HttpStatus.OK);
	}
	
}
