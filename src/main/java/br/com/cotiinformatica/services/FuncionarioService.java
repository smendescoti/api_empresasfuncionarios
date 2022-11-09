package br.com.cotiinformatica.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.entities.Funcionario;
import br.com.cotiinformatica.repositories.EmpresaRepository;
import br.com.cotiinformatica.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Autowired
	EmpresaRepository empresaRepository;

	public void cadastrar(Funcionario funcionario, Integer idEmpresa) throws Exception {

		// Regra 1: Não permitir cadastro duplicado de CPF
		if (funcionarioRepository.findByCpf(funcionario.getCpf()) != null) {
			throw new IllegalArgumentException("O CPF informado já está cadastrado, tente outro.");
		}

		// Regra 2: Não permitir cadastro duplicado de Matrícula
		if (funcionarioRepository.findByMatricula(funcionario.getMatricula()) != null) {
			throw new IllegalArgumentException("A Matrícula informada já está cadastrada, tente outra.");
		}

		// Regra 3: A empresa do funcionário deve existir no banco de dados
		Optional<Empresa> optional = empresaRepository.findById(idEmpresa);
		if(optional.isEmpty()) {
			throw new IllegalArgumentException("A Empresa informada não existe, verifique o ID da empresa.");
		}
		
		Empresa empresa = optional.get();
		funcionario.setEmpresa(empresa);
		
		//salvando no banco de dados
		funcionarioRepository.save(funcionario);
	}
	
	public void atualizar(Funcionario funcionario, Integer idEmpresa) throws Exception {

		// Regra 1: Verificar se o funcionário existe no banco de dados
		Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(funcionario.getIdFuncionario());
		if(funcionarioOptional.isEmpty()) {
			throw new IllegalArgumentException("O Funcionário não foi encontrado, verifique o ID informado.");
		}
		
		// Regra 2: Não permitir cadastro duplicado de CPF
		Funcionario funcionarioByCpf = funcionarioRepository.findByCpf(funcionario.getCpf());
		if (funcionarioByCpf != null && funcionarioByCpf.getIdFuncionario() != funcionario.getIdFuncionario()) {
			throw new IllegalArgumentException("O CPF informado já está cadastrado para outro funcionário.");
		}

		// Regra 3: Não permitir cadastro duplicado de Matrícula
		Funcionario funcionarioByMatricula = funcionarioRepository.findByMatricula(funcionario.getMatricula());
		if (funcionarioByMatricula != null && funcionarioByMatricula.getIdFuncionario() != funcionario.getIdFuncionario()) {
			throw new IllegalArgumentException("A Matrícula informada já está cadastrada para outro funcionário.");
		}

		// Regra 4: A empresa do funcionário deve existir no banco de dados
		Optional<Empresa> empresaOptional = empresaRepository.findById(idEmpresa);
		if(empresaOptional.isEmpty()) {
			throw new IllegalArgumentException("A Empresa informada não existe, verifique o ID da empresa.");
		}
		
		Empresa empresa = empresaOptional.get();
		funcionario.setEmpresa(empresa);
		
		//atualizando no banco de dados
		funcionarioRepository.save(funcionario);
	}
	
	public void excluir(Integer idFuncionario) throws Exception {

		// Regra 1: Verificar se o funcionário existe no banco de dados
		Optional<Funcionario> optional = funcionarioRepository.findById(idFuncionario);
		if(optional.isEmpty()) {
			throw new IllegalArgumentException("O Funcionário não foi encontrado, verifique o ID informado.");
		}
				
		Funcionario funcionario = optional.get();
		
		//excluindo no banco de dados
		funcionarioRepository.delete(funcionario);
	}
	
	public List<Funcionario> consultar() throws Exception {

		//consultar e retornar todas os funcionários do banco de dados
		return (List<Funcionario>) funcionarioRepository.findAll();
	}
}
