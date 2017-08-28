package br.com.kerley.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kerley.pokemon.model.Habilidade;
import br.com.kerley.pokemon.repository.Habilidades;
import br.com.kerley.pokemon.repository.filter.HabilidadeFilter;
import br.com.kerley.pokemon.service.exception.NaoEncontradoException;

@Service
public class CadastroHabilidadeService {

	@Autowired
	private Habilidades habilidades;

	public Habilidade salvar(Habilidade habilidade) {
		return habilidades.save(habilidade);

	}

	public void excluir(Long codigo) {
		habilidades.delete(codigo);
	}

	public boolean porNome(String nome) {
		Habilidade habilidade = habilidades.findByNome(nome);
		if (habilidade == null) {
			return false;
		}
		return true;
	}

	public void atualizar(Habilidade habilidade) {
		verificarExistencia(habilidade);
		habilidades.save(habilidade);
	}

	public List<Habilidade> filtrar(HabilidadeFilter filtro) {
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();

		return habilidades.findByNomeContaining(nome);

	}

	private void verificarExistencia(Habilidade habilidade) {
		buscar(habilidade.getId());
	}

	public Habilidade buscar(Long id) {
		Habilidade habilidade = habilidades.findOne(id);
		if (habilidade == null) {
			throw new NaoEncontradoException("Habilidade nao pôde ser encontrada.");
		}
		return habilidade;
	}
}
