package br.com.kerley.pokemon.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "pokemon_time")
public class PokemonTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "time_id", nullable = false)
	private Time time;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pokemon_id", nullable = false)
	private Pokemon pokemon;

	@JsonInclude(Include.NON_EMPTY)
	@OneToMany
	private List<HabilidadePokemonTime> habilidades;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	public List<HabilidadePokemonTime> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<HabilidadePokemonTime> habilidades) {
		this.habilidades = habilidades;
	}

}
