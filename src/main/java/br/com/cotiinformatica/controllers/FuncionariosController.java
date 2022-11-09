package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.FuncionariosGetDto;
import br.com.cotiinformatica.dtos.FuncionariosPostDto;
import br.com.cotiinformatica.dtos.FuncionariosPutDto;
import br.com.cotiinformatica.entities.Funcionario;
import br.com.cotiinformatica.services.FuncionarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Funcionários")
@RestController
public class FuncionariosController {

	@Autowired
	FuncionarioService funcionarioService;
		
	@CrossOrigin("http://localhost:4200")
	@ApiOperation("Serviço para cadastro de funcionários.")
	@PostMapping("/api/funcionarios")
	public ResponseEntity<String> post(@RequestBody FuncionariosPostDto dto) {

		try {
			
			Funcionario funcionario = new Funcionario();
			funcionario.setNome(dto.getNome());
			funcionario.setCpf(dto.getCpf());
			funcionario.setMatricula(dto.getMatricula());
			funcionario.setDataAdmissao(dto.getDataAdmissao());
			
			funcionarioService.cadastrar(funcionario, dto.getIdEmpresa());
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Funcionário " + funcionario.getNome() + ", cadastrado com sucesso.");
		}
		catch(IllegalArgumentException e) {			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
		catch(Exception e) {			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}

	@CrossOrigin("http://localhost:4200")
	@ApiOperation("Serviço para atualização de funcionários.")
	@PutMapping("/api/funcionarios")
	public ResponseEntity<String> put(@RequestBody FuncionariosPutDto dto) {

		try {
			
			Funcionario funcionario = new Funcionario();
			
			funcionario.setIdFuncionario(dto.getIdFuncionario());
			funcionario.setNome(dto.getNome());
			funcionario.setCpf(dto.getCpf());
			funcionario.setMatricula(dto.getMatricula());
			funcionario.setDataAdmissao(dto.getDataAdmissao());
			
			funcionarioService.atualizar(funcionario, dto.getIdEmpresa());
			
			return ResponseEntity.status(HttpStatus.OK)
					.body("Funcionário " + funcionario.getNome() + ", atualizado com sucesso.");
		}
		catch(IllegalArgumentException e) {			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
		catch(Exception e) {			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}		
	}

	@CrossOrigin("http://localhost:4200")
	@ApiOperation("Serviço para exclusão de funcionários.")
	@DeleteMapping("/api/funcionarios/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {

		try {
			
			funcionarioService.excluir(id);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body("Funcionário excluído com sucesso.");
		}
		catch(IllegalArgumentException e) {			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
		catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}			
	}

	@CrossOrigin("http://localhost:4200")
	@ApiOperation("Serviço para consultar todos os funcionários cadastrados.")
	@GetMapping("/api/funcionarios")
	public ResponseEntity<List<FuncionariosGetDto>> getAll() {

		try {
			
			List<FuncionariosGetDto> lista = new ArrayList<FuncionariosGetDto>();
			
			for(Funcionario funcionario : funcionarioService.consultar()) {
				
				FuncionariosGetDto dto = new FuncionariosGetDto();
				
				dto.setIdFuncionario(funcionario.getIdFuncionario());
				dto.setNome(funcionario.getNome());
				dto.setCpf(funcionario.getCpf());
				dto.setMatricula(funcionario.getMatricula());
				dto.setDataAdmissao(funcionario.getDataAdmissao());
				
				lista.add(dto);
			}
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(lista);
		}
		catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}
}






