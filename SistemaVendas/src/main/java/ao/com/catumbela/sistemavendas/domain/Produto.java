package ao.com.catumbela.sistemavendas.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(length = 80, nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private Short quantidade;
	
	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal preco;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Fornecedor fornecedor;
	
	@Transient
	private String caminho;
	
	@Override
	public String toString() {
		return String.format("%s[codigo=%d]", getClass().getSimpleName(), codigo);
	}
}
