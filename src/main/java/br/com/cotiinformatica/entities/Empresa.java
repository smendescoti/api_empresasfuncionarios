package br.com.cotiinformatica.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_EMPRESA")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDEMPRESA")
	private Integer idEmpresa;

	@Column(name = "NOMEFANTASIA", length = 150, nullable = false)
	private String nomeFantasia;

	@Column(name = "RAZAOSOCIAL", length = 100, nullable = false, unique = true)
	private String razaoSocial;

	@Column(name = "CNPJ", length = 20, nullable = false, unique = true)
	private String cnpj;

	// mappedBy -> nome do atributo da classe Funcionário onde foi mapeado
	// a chave estrangeira, ou seja a @JoinColumn
	@OneToMany(mappedBy = "empresa")
	private List<Funcionario> funcionarios;
}
