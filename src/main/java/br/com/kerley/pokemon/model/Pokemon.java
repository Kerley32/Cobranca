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
public class Pokemon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "O nome é obrigatório")
	@Size(max = 60, message = "O nome não pode conter mais de 60 caracteres")
	@JsonProperty("name")
	private String nome;
	private String url;

	// @OneToMany(mappedBy = "pokemon")
	// @JsonIgnore
	// @JsonProperty("movies")
	// private List<Habilidade> habilidades;

	//
	// @JsonProperty("base_experience")
	// private int experienciaBasica;
	//
	// @JsonProperty("is_defalut")
	// private boolean padrao;
	//
	// @JsonProperty("order")
	// private int ordem;
	//
	// @JsonProperty("weight")
	// private int peso;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
