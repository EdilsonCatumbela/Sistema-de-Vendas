package ao.com.catumbela.sistemavendas.domain;

import java.math.BigDecimal;

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
public class ItemVenda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(nullable = false)
	private Short quantidade;
	
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal valorParcial;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Venda venda;

}
