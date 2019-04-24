package escolaAPI.handler;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import escolaAPI.dto.EmprestimoFormularioDTO;
import escolaAPI.dto.EmprestimoResultDTO;
import escolaAPI.entity.Aluno;
import escolaAPI.entity.Emprestimo;
import escolaAPI.entity.Livro;
import escolaAPI.entity.Status;
import escolaAPI.repository.AlunoRepository;
import escolaAPI.repository.EmprestimoRepository;
import escolaAPI.repository.LivroRepository;

public class EmprestimoHandler {
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private AlunoRepository alunoRepository;

	public EmprestimoResultDTO getPorId( Integer id) {
		Optional<Emprestimo> emprestimoPorId = this.emprestimoRepository.findById(id);
		Emprestimo emprestimo = emprestimoPorId.get();
		Livro livroEmprestimo =  emprestimo.getLivro();
		Aluno alunoEmprestimo =  emprestimo.getAluno();
		
		EmprestimoResultDTO emprestimoConsultado = new EmprestimoResultDTO();
		emprestimoConsultado.setCodigoEmprestimo(emprestimo.getId());
		emprestimoConsultado.setNomeLivro(livroEmprestimo.getTitulo());
		emprestimoConsultado.setNomeAluno(alunoEmprestimo.getNome());
		if(emprestimoPorId.isPresent()) {
			return emprestimoConsultado;
		}else {
			return null;
		}
	}
	
	public Iterable<Emprestimo> getTodos(){
		return this.emprestimoRepository.findAll();
	}
	
	public String cadastrar(EmprestimoFormularioDTO emprestimoDto) {
		
		Emprestimo entidade =  new Emprestimo();
		
		Optional<Livro> livro =  this.livroRepository.findById(emprestimoDto.getIdLivro());
		Optional<Aluno> aluno =  this.alunoRepository.findById(emprestimoDto.getIdAluno());
		
		Livro livroSelecionado = livro.get();
		entidade.setLivro(livroSelecionado);
		
		Aluno alunoSelecionado = aluno.get();
		entidade.setAluno(alunoSelecionado);

		entidade.setDataAluguel(new Date());
		entidade.setDataEntrega(emprestimoDto.getDataEntrega());	
		
		if(livroSelecionado.getStatus() == Status.Disponível){
			livroSelecionado.setStatus(Status.Alugado);	
			this.emprestimoRepository.save(entidade);
			this.livroRepository.save(livroSelecionado);
			return "Empréstimo cadastrado com sucesso cadastrado com sucesso!";
		}else {
			return "Não é possível efetuar o cadastro, pois este livro já está alugado!";
		}
	}
	
	public String devolver(Integer id) {
		Optional<Emprestimo> emprestimo =
				this.emprestimoRepository.findById(id);
		if(emprestimo != null) {
			Emprestimo entidade = new Emprestimo();
			entidade = emprestimo.get();
		    Livro livro =  entidade.getLivro();
		    if(livro != null){
				livro.setStatus(Status.Disponível);
				this.livroRepository.save(livro);
			}
		  this.emprestimoRepository.deleteById(id);
          return "Devolução Realizada com Sucesso!";

		}else {
			 ResponseEntity.notFound().build();
			 return "O empréstimo foi encerrado!";
		}
	}
	
}
