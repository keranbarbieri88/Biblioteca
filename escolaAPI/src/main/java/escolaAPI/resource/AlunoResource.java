package escolaAPI.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import escolaAPI.dto.AlunoFormularioDTO;
import escolaAPI.entity.Aluno;
import escolaAPI.handler.AlunoHandler;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

	@Autowired
	private AlunoHandler alunoHandler;

	@GetMapping
	public Iterable<Aluno> getTodos() {
		return this.alunoHandler.getTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Aluno> getPorId(@PathVariable("id") Integer id) {
		Aluno aluno = alunoHandler.getPorId(id);
		if (aluno != null) {
			return ResponseEntity.ok(aluno);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public String cadastrar(@RequestBody AlunoFormularioDTO aluno) {
		return alunoHandler.cadastrar(aluno);
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable("id") Integer id) {
		this.alunoHandler.excluir(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String alterar(@PathVariable("id") Integer id, @RequestBody AlunoFormularioDTO aluno) {
		return alunoHandler.alterar(id, aluno);
	}
}
