package escolaAPI.handler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import escolaAPI.dto.AlunoFormularioDTO;
import escolaAPI.entity.Aluno;
import escolaAPI.repository.AlunoRepository;

public class AlunoHandler {
	@Autowired
	private AlunoRepository alunoRepository;

	public Iterable<Aluno> getTodos() {
		return this.alunoRepository.findAll();
	}

	public Aluno getPorId(Integer id) {
		Optional<Aluno> aluno = this.alunoRepository.findById(id);
		if (aluno.isPresent()) {
			return aluno.get();
		} else {
			return null;
		}
	}

	public String cadastrar(AlunoFormularioDTO aluno) {
		Aluno entidade = new Aluno();
		entidade.setNome(aluno.getNome());
		entidade.setRa(aluno.getRa());
		this.alunoRepository.save(entidade);

		return "Aluno cadastrado com sucesso!";
	}

	public void excluir(Integer id) {
		this.alunoRepository.deleteById(id);
	}

	public String alterar(Integer id, AlunoFormularioDTO aluno) {
		Optional<Aluno> alunoBanco = this.alunoRepository.findById(id);
		Aluno entidade = alunoBanco.get();
		entidade.setNome(aluno.getNome());
		entidade.setRa(aluno.getRa());
		this.alunoRepository.save(entidade);
		return "Aluno alterado com Sucesso!";
	}
}
