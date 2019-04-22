package escolaAPI.resource;

import java.util.Date;
import java.util.Optional;

import escolaAPI.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import escolaAPI.dto.EmprestimoFormularioDTO;
import escolaAPI.entity.Aluno;
import escolaAPI.entity.Emprestimo;
import escolaAPI.entity.Livro;
import escolaAPI.entity.Status;
import escolaAPI.repository.AlunoRepository;
import escolaAPI.repository.EmprestimoRepository;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoResouce {

	@Autowired
	private EmprestimoRepository emprestimoRepository;
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private AlunoRepository alunoRepository;


	@GetMapping
	public Iterable<Emprestimo> getTodos(){
		
		return this.emprestimoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Emprestimo> getPorId(@PathVariable("id") Integer id) {
		Optional<Emprestimo> emprestimo = 
		 this.emprestimoRepository.findById(id);
		if(emprestimo.isPresent()) {
			return ResponseEntity.ok(emprestimo.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public String cadastrar(@RequestBody  EmprestimoFormularioDTO emprestimoDto) {
		
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

	@DeleteMapping("/{id}")
	public String devolver(@PathVariable("id") Integer id) {
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
