package br.com.cotiinformatica.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.entities.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {

	@Query("select f from Funcionario f where f.cpf = :param")
	Funcionario findByCpf(@Param("param") String cpf) throws Exception;

	@Query("select f from Funcionario f where f.matricula = :param")
	Funcionario findByMatricula(@Param("param") String matricula) throws Exception;
}
