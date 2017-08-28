package br.com.kerley.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kerley.pokemon.model.Treinador;
import br.com.kerley.pokemon.repository.Treinadores;
import br.com.kerley.pokemon.repository.filter.TreinadorFilter;
import br.com.kerley.pokemon.service.exception.NaoEncontradoException;

@Service
public class CadastroTreinadorService {

	@Autowired
	private Treinadores treinadores;

	public Treinador salvar(Treinador treinador) {

		return treinadores.save(treinador);
	}

	public void excluir(Long codigo) {
		treinadores.delete(codigo);
	}

	public void atualizar(Treinador treinador) {
		verificarExistencia(treinador);
		treinadores.save(treinador);
	}

	private void verificarExistencia(Treinador treinador) {
		buscar(treinador.getId());
	}

	public Treinador buscar(Long id) {
		Treinador treinador = treinadores.findOne(id);
		if (treinador == null) {
			throw new NaoEncontradoException("O Treinador nao pôde ser encontrado.");
		}
		return treinador;
	}

	public List<Treinador> filtrar(TreinadorFilter filtro) {
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();

		return treinadores.findByNomeContaining(nome);

	}
}
