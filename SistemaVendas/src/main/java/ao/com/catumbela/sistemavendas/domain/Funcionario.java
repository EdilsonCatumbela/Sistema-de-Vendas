package ao.com.catumbela.sistemavendas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(length = 20, nullable = false)
	private String cateiraTrabalho;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataAdmissao;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;
	
	
}
