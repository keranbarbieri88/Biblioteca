package escolaAPI.resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import escolaAPI.dto.EmprestimoFormularioDTO;
import escolaAPI.dto.EmprestimoResultDTO;
import escolaAPI.entity.Emprestimo;
import escolaAPI.handler.EmprestimoHandler;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoResouce {

	@Autowired
	private EmprestimoHandler emprestimoHandler;
	
	@GetMapping
	public Iterable<Emprestimo> getTodos(){
		return this.emprestimoHandler.getTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmprestimoResultDTO> getPorId(@PathVariable("id") Integer id) {
		EmprestimoResultDTO emprestimoConsultado = emprestimoHandler.getPorId(id);
		if(emprestimoConsultado != null) {
			return ResponseEntity.ok(emprestimoConsultado);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public String cadastrar(@RequestBody  EmprestimoFormularioDTO emprestimoDto) {
		return emprestimoHandler.cadastrar(emprestimoDto);
	}

	@DeleteMapping("/{id}")
	public String devolver(@PathVariable("id") Integer id) {
		return emprestimoHandler.devolver(id);
	}
}
