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
import escolaAPI.repository.EmprestimoRepository;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoResouce {

	@Autowired
	private EmprestimoRepository emprestimoRepository;
	@Autowired
	private LivroRepository livroRepository;

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
		Aluno aluno = new Aluno();
		Livro livro =  new Livro();
		aluno.setId(emprestimoDto.getIdAluno());
		entidade.setAluno(aluno);
		livro.setId(emprestimoDto.getIdLivro());
		entidade.setLivro(livro);
		entidade.setDataAluguel(new Date());
		entidade.setDataEntrega(new Date());
		if(livro.getStatus()== Status.Disponivel) {
			livro.setStatus(Status.Alugado);
			this.emprestimoRepository.save(entidade);
			this.livroRepository.save(livro);
		}
		if(livro.getStatus() == Status.Alugado){
			return "Não é possível efetuar o cadastro, pois este livro já está alugado!";
		}
		return "Empréstimo cadastrado com sucesso cadastrado com sucesso!";
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
				livro.setStatus(Status.Disponivel);
				this.livroRepository.save(livro);
			}
		  this.emprestimoRepository.deleteById(id);
          return "Devolução Realizada com Sucesso!";

		}else {
			 ResponseEntity.notFound().build();
			 return "O empréstimo foi encerrado!";
		}
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String alterar(@PathVariable("id") Integer id,@RequestBody  EmprestimoFormularioDTO emprestimoDTO){
		Optional<Emprestimo> emprestimo =
				this.emprestimoRepository.findById(id);
		Emprestimo entidade = emprestimo.get();
		Aluno aluno = new Aluno();
		Livro livro =  new Livro();
		livro.setId(emprestimoDTO.getIdLivro());
		aluno.setId(emprestimoDTO.getIdAluno());
		entidade.setAluno(aluno);
		entidade.setLivro(livro);
		entidade.setDataAluguel(new Date());
		entidade.setDataAluguel(new Date());
		this.emprestimoRepository.save(entidade);
		return "Empréstimo alterado com Sucesso!";
	}
}
