package escolaAPI.handler;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import escolaAPI.dto.LivroFormularioDTO;
import escolaAPI.entity.Livro;
import escolaAPI.entity.Status;
import escolaAPI.repository.LivroRepository;

public class LivroHandler {
	
	@Autowired
	private LivroRepository livroRepository;
	
	public Iterable<Livro> getTodos(){
		return this.livroRepository.findAll();
	}
	
	public Livro getPorId( Integer id) {
		Optional<Livro> livro = 
		 this.livroRepository.findById(id);
		if(livro.isPresent()) {
			return livro.get();
		}else {
			return null;
		}
	}
	

	public String cadastrar(LivroFormularioDTO livro) {
		Livro entidade  =  new Livro();
		entidade.setTitulo(livro.getTitulo());
		entidade.setAutor(livro.getAutor());
		entidade.setStatus(Status.Disponível);
		this.livroRepository.save(entidade);
		
		return "Livro cadastrado com sucesso!";
	}
	public void excluir(Integer id) {
		this.livroRepository.deleteById(id);
	}

	public String alterar(Integer id,  LivroFormularioDTO livro){
		Optional<Livro> livroBanco =
				this.livroRepository.findById(id);
		Livro entidade = livroBanco.get();
		entidade.setAutor(livro.getAutor());
		entidade.setTitulo(livro.getTitulo());
		this.livroRepository.save(entidade);
		return "Livro alterado com Sucesso!";
	}
	
	public List<Livro> getTodosLivrosAtivos(){
		return this.livroRepository.BuscaLivrosAtivos();
	}
}
