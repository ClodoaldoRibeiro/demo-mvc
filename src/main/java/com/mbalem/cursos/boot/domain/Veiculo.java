package com.mbalem.cursos.boot.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name = "VEICULOS")
public class Veiculo extends AbstractEntity<Long> {

	@NotBlank
	@Size(max = 60, min = 3)
	@Column(nullable = false)
	private String modelo;

	@NotBlank
	@Size(max = 15)
	@Column(nullable = false, unique = true)
	private String renavam;

	@NotBlank
	@Size(max = 8, min = 8)
	@Column(nullable = false, unique = true)
	private String placa;

	@NotBlank
	@Size(max = 20)
	@Column(nullable = false, unique = true)
	private String chassi;

	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "anoFrabricacao", nullable = false, columnDefinition = "DATE")
	private LocalDate anoFrabricacao;

	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "anaModelo", nullable = false, columnDefinition = "DATE")
	private LocalDate anaModelo;

	@NotNull(message = "UF é obrigatória")
	@Column(nullable = false, length = 2)
	@Enumerated(EnumType.STRING)
	private UF uf;

	@NotNull(message = "Selecione um combustível")
	@ManyToOne
	@JoinColumn(name = "id_combusteveil_fk")
	private Combustivel combustivel;

	@NotNull(message = "Selecione um marca")
	@ManyToOne
	@JoinColumn(name = "id_marca_fk")
	private Marca marca;

	@NotNull(message = "Selecione um tipo de Veiculo")
	@ManyToOne
	@JoinColumn(name = "id_tiposveiculo_fk")
	private TiposVeiculos tiposVeiculos;

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public LocalDate getAnoFrabricacao() {
		return anoFrabricacao;
	}

	public void setAnoFrabricacao(LocalDate anoFrabricacao) {
		this.anoFrabricacao = anoFrabricacao;
	}

	public LocalDate getAnaModelo() {
		return anaModelo;
	}

	public void setAnaModelo(LocalDate anaModelo) {
		this.anaModelo = anaModelo;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public Combustivel getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(Combustivel combustivel) {
		this.combustivel = combustivel;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public TiposVeiculos getTiposVeiculos() {
		return tiposVeiculos;
	}

	public void setTiposVeiculos(TiposVeiculos tiposVeiculos) {
		this.tiposVeiculos = tiposVeiculos;
	}

}
