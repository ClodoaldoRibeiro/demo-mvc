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
@Table(name = "MARCAS")
public class Marca extends AbstractEntity<Long> {

	@NotBlank(message = "Nome é obrigatório")
	@Size(min = 3, max = 60, message = "O nome da marca deve ter entre {min} e {max} caracteres.")
	@Column(name = "nome", nullable = false, length = 60, unique = true)
	private String nome;

	@OneToMany(mappedBy = "marca")
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
