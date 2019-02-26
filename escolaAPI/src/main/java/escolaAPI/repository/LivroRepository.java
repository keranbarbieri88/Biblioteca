package escolaAPI.repository;

import org.springframework.data.repository.CrudRepository;

import escolaAPI.entity.Livro;

public interface LivroRepository  extends CrudRepository<Livro, Integer>{

}
