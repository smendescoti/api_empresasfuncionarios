package br.com.cotiinformatica.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_FUNCIONARIO")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDFUNCIONARIO")
	private Integer idFuncionario;

	@Column(name = "NOME", length = 150, nullable = false)
	private String nome;

	@Column(name = "CPF", length = 14, nullable = false, unique = true)
	private String cpf;

	@Column(name = "MATRICULA", length = 10, nullable = false, unique = true)
	private String matricula;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATAADMISSAO", nullable = false)
	private Date dataAdmissao;

	@ManyToOne // Muitos funcionários para 1 empresa
	@JoinColumn(name = "IDEMPRESA", nullable = false) // chave estrangeira
	private Empresa empresa;
}
