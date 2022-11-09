package br.com.cotiinformatica.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.entities.Empresa;

@Repository
public interface EmpresaRepository extends CrudRepository<Empresa, Integer> {

	@Query("select e from Empresa e where e.razaoSocial = :param")
	Empresa findByRazaoSocial(@Param("param") String razaoSocial) throws Exception;

	@Query("select e from Empresa e where e.cnpj = :param")
	Empresa findByCnpj(@Param("param") String cnpj) throws Exception;

}
