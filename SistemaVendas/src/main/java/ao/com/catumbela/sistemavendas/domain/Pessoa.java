package ao.com.catumbela.sistemavendas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Pessoa{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 14, nullable = false)
	private String bi;

	@Column(length = 50, nullable = false)
	private String bairro;
	
	@Column(length = 50)
	private String complemento;

	@Column(length = 17, nullable = false)
	private String telefone;
	
	@Column(length = 100, nullable = false)
	private String email;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Municipio municipio;

	

}
