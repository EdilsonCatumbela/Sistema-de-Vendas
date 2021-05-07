package ao.com.catumbela.sistemavendas.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Venda{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;
	
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal precoTotal;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;

}
