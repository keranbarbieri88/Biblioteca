package escolaAPI.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import escolaAPI.dto.LivroFormularioDTO;
import escolaAPI.entity.Livro;
import escolaAPI.handler.LivroHandler;


@RestController
@RequestMapping("/livros")
public class LivroResource {
	
	@Autowired
	private LivroHandler livroHandler;
	
	@GetMapping
	public Iterable<Livro> getTodos(){
		return this.livroHandler.getTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> getPorId(@PathVariable("id") Integer id) {
		Livro livro = this.livroHandler.getPorId(id);
		if(livro != null) {
			return ResponseEntity.ok(livro);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public String cadastrar(@RequestBody  LivroFormularioDTO livro) {
		return livroHandler.cadastrar(livro);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable("id") Integer id) {
		livroHandler.excluir(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String alterar(@PathVariable("id") Integer id,@RequestBody  LivroFormularioDTO livro){
		return livroHandler.alterar(id, livro);
	}
	
	@GetMapping("/livros-disponiveis")
	@ResponseStatus(HttpStatus.OK)
	public List<Livro> getTodosLivrosAtivos(){
		return livroHandler.getTodosLivrosAtivos();
	}
}
