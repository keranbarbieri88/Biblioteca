package escolaAPI.repository;

import org.springframework.data.repository.CrudRepository;

import escolaAPI.entity.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Integer> {

}
