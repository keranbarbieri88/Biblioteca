package escolaAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import escolaAPI.entity.Livro;

public interface LivroRepository  extends CrudRepository<Livro, Integer>{
	@Query(value = "Select l from Livro l Where l.status = escolaAPI.entity.Status.Disponível ")
	List<Livro> BuscaLivrosAtivos();
}
