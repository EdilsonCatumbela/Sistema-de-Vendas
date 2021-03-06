package ao.com.catumbela.sistemavendas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Provincia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(length = 2, nullable = false)
	private String sigle;
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	
	
}
