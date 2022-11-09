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

import br.com.cotiinformatica.dtos.EmpresasGetDto;
import br.com.cotiinformatica.dtos.EmpresasPostDto;
import br.com.cotiinformatica.dtos.EmpresasPutDto;
import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.services.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Empresas")
@RestController
public class EmpresasController {

	@Autowired //injeção de dependência (inicialização automática)
	EmpresaService empresaService;
	
	@CrossOrigin("http://localhost:4200")
	@ApiOperation("Serviço para cadastro de empresas.")
	@PostMapping("/api/empresas")
	public ResponseEntity<String> post(@RequestBody EmpresasPostDto dto) {

		try {
			
			Empresa empresa = new Empresa();			
			empresa.setNomeFantasia(dto.getNomeFantasia());
			empresa.setRazaoSocial(dto.getRazaoSocial());
			empresa.setCnpj(dto.getCnpj());
			
			empresaService.cadastrar(empresa);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Empresa " + empresa.getNomeFantasia() + " cadastrado com sucesso.");
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
	@ApiOperation("Serviço para atualização de empresas.")
	@PutMapping("/api/empresas")
	public ResponseEntity<String> put(@RequestBody EmpresasPutDto dto) {

		try {
						
			Empresa empresa = new Empresa();
			
			empresa.setIdEmpresa(dto.getIdEmpresa());
			empresa.setNomeFantasia(dto.getNomeFantasia());
			empresa.setRazaoSocial(dto.getRazaoSocial());
			empresa.setCnpj(dto.getCnpj());
			
			empresaService.atualizar(empresa);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body("Empresa " + empresa.getNomeFantasia() + " atualizado com sucesso.");
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
	@ApiOperation("Serviço para exclusão de empresas.")
	@DeleteMapping("/api/empresas/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {

		try {
			
			empresaService.excluir(id);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body("Empresa excluido com sucesso.");
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
	@ApiOperation("Serviço para consultar todas as empresas cadastradas.")
	@GetMapping("/api/empresas")
	public ResponseEntity<List<EmpresasGetDto>> getAll() {

		try {
			
			List<EmpresasGetDto> lista = new ArrayList<EmpresasGetDto>();
			
			for(Empresa empresa : empresaService.consultar()) {
				
				EmpresasGetDto dto = new EmpresasGetDto();
				
				dto.setIdEmpresa(empresa.getIdEmpresa());
				dto.setNomeFantasia(empresa.getNomeFantasia());
				dto.setRazaoSocial(empresa.getRazaoSocial());
				dto.setCnpj(empresa.getCnpj());
				
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














