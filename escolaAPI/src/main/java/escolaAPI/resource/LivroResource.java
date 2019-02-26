package escolaAPI.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import escolaAPI.dto.LivroFormularioDTO;
import escolaAPI.entity.Livro;
import escolaAPI.entity.Status;
import escolaAPI.repository.LivroRepository;

@RestController
@RequestMapping("/livros")
public class LivroResource {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@GetMapping
	public Iterable<Livro> getTodos(){
		
		return this.livroRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> getPorId(@PathVariable("id") Integer id) {
		Optional<Livro> livro = 
		 this.livroRepository.findById(id);
		if(livro.isPresent()) {
			return ResponseEntity.ok(livro.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public String cadastrar(@RequestBody  LivroFormularioDTO livro) {
		Livro entidade  =  new Livro();
		entidade.setTitulo(livro.getTitulo());
		entidade.setAutor(livro.getAutor());
		entidade.setStatus(Status.Disponivel);
		this.livroRepository.save(entidade);
		
		return "Livro cadastrado com sucesso!";
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable("id") Integer id) {
		this.livroRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String alterar(@PathVariable("id") Integer id,@RequestBody  LivroFormularioDTO livro){
		Optional<Livro> alunoBanco =
				this.livroRepository.findById(id);
		Livro entidade = alunoBanco.get();
		entidade.setAutor(livro.getAutor());
		entidade.setTitulo(livro.getTitulo());
		this.livroRepository.save(entidade);
		return "Livro alterado com Sucesso!";
	}
}
