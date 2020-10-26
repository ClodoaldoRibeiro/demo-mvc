package com.mbalem.cursos.boot.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "COMBUSTIVEIS")
public class Combustivel extends AbstractEntity<Long> {

	@NotBlank(message = "Nome do conbustivel não informado!")
	@Size(max = 60, message = "O nome do conbustivel deve conter no máximo 60 caracteres.")
	@Column(name = "nome", nullable = false, length = 60)
	private String nome;

	@OneToMany(mappedBy = "combustivel")
	@Column
	private List<Veiculo> veiculo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Veiculo> getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(List<Veiculo> veiculo) {
		this.veiculo = veiculo;
	}

}
