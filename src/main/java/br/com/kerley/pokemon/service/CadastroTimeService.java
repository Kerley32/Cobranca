package br.com.kerley.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kerley.pokemon.model.Time;
import br.com.kerley.pokemon.repository.Times;
import br.com.kerley.pokemon.repository.filter.TimeFilter;
import br.com.kerley.pokemon.service.exception.NaoEncontradoException;

@Service
public class CadastroTimeService {

	@Autowired
	private Times times;

	public Time salvar(Time time) {
		return times.save(time);
	}

	public void excluir(Long codigo) {
		times.delete(codigo);
	}

	public void atualizar(Time time) {
		verificarExistencia(time);
		times.save(time);
	}

	public List<Time> filtrar(TimeFilter filtro) {
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();

		return times.findByNomeContaining(nome);

	}

	private void verificarExistencia(Time time) {
		buscar(time.getId());
	}

	public Time buscar(Long id) {
		Time time = times.findOne(id);
		if (time == null) {
			throw new NaoEncontradoException("O time nao pôde ser encontrado.");
		}
		return time;
	}
}
