/**
 * 
 */
package br.com.kerley.pokemon.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author kerley
 *
 */
@Entity
public class Time {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "O nome é obrigatório")
	@Size(max = 60, message = "O nome não pode conter mais de 60 caracteres")
	private String nome;

	@JsonInclude(Include.NON_NULL)
	@ManyToOne
	@JoinColumn(name = "treinador_id")
	private Treinador treinador;

	@JsonInclude(Include.NON_EMPTY)
	@OneToMany(mappedBy = "time")
	private List<PokemonTime> pokemons;

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

	public Treinador getTreinador() {
		return treinador;
	}

	public void setTreinador(Treinador treinador) {
		this.treinador = treinador;
	}

	public List<PokemonTime> getPokemons() {
		return pokemons;
	}

	public void setPokemons(List<PokemonTime> pokemons) {
		this.pokemons = pokemons;
	}

}
