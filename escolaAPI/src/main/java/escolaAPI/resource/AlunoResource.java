package escolaAPI.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import escolaAPI.dto.AlunoFormularioDTO;
import escolaAPI.entity.Aluno;
import escolaAPI.repository.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping
	public Iterable<Aluno> getTodos(){
		
		return this.alunoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> getPorId(@PathVariable("id") Integer id) {
		Optional<Aluno> aluno = 
		 this.alunoRepository.findById(id);
		if(aluno.isPresent()) {
			return ResponseEntity.ok(aluno.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public String cadastrar(@RequestBody  AlunoFormularioDTO aluno) {
		Aluno entidade =  new Aluno();
		entidade.setNome(aluno.getNome());
		entidade.setRa(aluno.getRa());
		this.alunoRepository.save(entidade);
		
		return "Aluno cadastrado com sucesso!";
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable("id") Integer id) {
		this.alunoRepository.deleteById(id);
	}


	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String alterar(@PathVariable("id") Integer id,@RequestBody  AlunoFormularioDTO aluno){
		Optional<Aluno> alunoBanco =
				this.alunoRepository.findById(id);
		Aluno entidade = alunoBanco.get();
		entidade.setNome(aluno.getNome());
		entidade.setRa(aluno.getRa());
		this.alunoRepository.save(entidade);
		return "Aluno alterado com Sucesso!";
	}
}
