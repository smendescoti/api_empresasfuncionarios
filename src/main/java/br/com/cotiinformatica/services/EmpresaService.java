package br.com.cotiinformatica.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.repositories.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	EmpresaRepository empresaRepository;

	public void cadastrar(Empresa empresa) throws Exception {

		// Regra 1: A Razão Social deve ser unica para cada empresa
		if (empresaRepository.findByRazaoSocial(empresa.getRazaoSocial()) != null) {
			throw new IllegalArgumentException("A Razão Social informada já está cadastrado, tente outra.");
		}

		// Regra 2: O CNPJ deve ser unico para cada empresa
		if (empresaRepository.findByCnpj(empresa.getCnpj()) != null) {
			throw new IllegalArgumentException("O CNPJ informado já está cadastrado, tente outro.");
		}

		// salvar no banco de dados
		empresaRepository.save(empresa);
	}

	public void atualizar(Empresa empresa) throws Exception {

		// Regra 1: A empresa informada deve existir no banco de dados
		Optional<Empresa> optional = empresaRepository.findById(empresa.getIdEmpresa());
		if(optional.isEmpty()) {
			throw new IllegalArgumentException("A Empresa não foi encontrada, verifique o ID informado.");
		}

		// Regra 2: A Razão Social deve ser unica para cada empresa
		Empresa empresaByRazaoSocial = empresaRepository.findByRazaoSocial(empresa.getRazaoSocial());
		if(empresaByRazaoSocial != null && empresaByRazaoSocial.getIdEmpresa() != empresa.getIdEmpresa()) {
			throw new IllegalArgumentException("A Razão Social informada já está cadastrada para outra empresa.");
		}

		// Regra 3: O CNPJ deve ser unico para cada empresa
		Empresa empresaByCnpj = empresaRepository.findByCnpj(empresa.getCnpj());
		if(empresaByCnpj != null && empresaByCnpj.getIdEmpresa() != empresa.getIdEmpresa()) {
			throw new IllegalArgumentException("O CNPJ informada já está cadastrado para outra empresa.");
		}
		
		//atualizar no banco de dados
		empresaRepository.save(empresa);
	}

	public void excluir(Integer idEmpresa) throws Exception {

		// Regra 1: A empresa informada deve existir no banco de dados
		Optional<Empresa> optional = empresaRepository.findById(idEmpresa);
		if(optional.isEmpty()) {
			throw new IllegalArgumentException("A Empresa não foi encontrada, verifique o ID informado.");
		}

		Empresa empresa = optional.get();
		
		//excluindo no banco de dados
		empresaRepository.delete(empresa);
	}

	public List<Empresa> consultar() throws Exception {

		//consultar e retornar todas as empresas do banco de dados
		return (List<Empresa>) empresaRepository.findAll();
	}
}
