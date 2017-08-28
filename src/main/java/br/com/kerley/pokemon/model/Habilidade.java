package br.com.kerley.pokemon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author kerley
 *
 */
@Entity
public class Habilidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "O nome é obrigatório")
	@Size(max = 60, message = "O nome não pode conter mais de 60 caracteres")
	@JsonProperty("name")
	private String nome;
	@JsonProperty("accuracy")
	private int precisao;

	@JsonProperty("effect_chance")
	private int chanceDeEfeito;

	@JsonProperty("pp")
	private int pontosDePoder;

	@JsonProperty("priority")
	private int prioridade;

	@JsonProperty("power")
	private int poder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPrecisao() {
		return precisao;
	}

	public void setPrecisao(int precisao) {
		this.precisao = precisao;
	}

	public int getChanceDeEfeito() {
		return chanceDeEfeito;
	}

	public void setChanceDeEfeito(int chanceDeEfeito) {
		this.chanceDeEfeito = chanceDeEfeito;
	}

	public int getPontosDePoder() {
		return pontosDePoder;
	}

	public void setPontosDePoder(int pontosDePoder) {
		this.pontosDePoder = pontosDePoder;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public int getPoder() {
		return poder;
	}

	public void setPoder(int poder) {
		this.poder = poder;
	}

}
