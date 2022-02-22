package com.yagofx.farolshoppingdemo.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.Length;

import com.yagofx.farolshoppingdemo.entity.enums.Disponibilidade;
import com.yagofx.farolshoppingdemo.entity.enums.Status;
import com.yagofx.farolshoppingdemo.entity.types.PostgreSQLEnumType;

@Entity
@TypeDef(name = "enum_postgresql", typeClass = PostgreSQLEnumType.class)
@DynamicInsert
@Table(name = "itens")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@NotEmpty
	@Length(max = 7, message = "O código deve conter no máximo {max} caracteres")
	@Column(nullable=false)
	private String codigo;

	@GeneratedValue
	@Column(name = "numero_de_serie", nullable = false)
	private String numeroDeSerie;

	@Enumerated(EnumType.STRING)
	@Type(type = "enum_postgresql")
	@Column(nullable = false)
	private Status status;
	
	@Enumerated(EnumType.STRING)
	@Type(type = "enum_postgresql")
	@Column(nullable = false)
	private Disponibilidade disponibilidade;
	
	@NotEmpty
	@Length(max = 100, message = "A descriçao deve conter no maximo {max} caracteres")
	@Column(nullable = false)
	private String descricao;
	
	@NotEmpty
	@Length(min = 3, max = 250, message = "A localização deve conter entre {min} e {max} caracteres")
	@Column(nullable = false)
	private String localizacao;

	@Column(name = "data_de_movimentacao", nullable = false)
	private String dataDeMovimentacao;

	@Column(name = "data_de_cadastro", nullable = false)
	private String dataDeCadastro;
	
	protected Item() {}
	
	public Item(String codigo, String descricao, String localizacao) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.localizacao = localizacao;
	}
	
	
	public int getId() {
		return id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}
	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public String getDataDeMovimentacao() {
		return dataDeMovimentacao;
	}
	public void setDataDeMovimentacao(String dataDeMovimentacao) {
		this.dataDeMovimentacao = dataDeMovimentacao;
	}
	public String getDataDeCadastro() {
		return dataDeCadastro;
	}
	public void setDataDeCadastro(String dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Disponibilidade getDisponibilidade() {
		return disponibilidade;
	}
	public void setDisponibilidade(Disponibilidade disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
	@Override
	public String toString() {
		return "Item [codigo=" + codigo + ", numeroDeSerie=" + numeroDeSerie + ", status=" + status
				+ ", disponibilidade=" + disponibilidade + ", descricao=" + descricao + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return id == other.id;
	}

}
