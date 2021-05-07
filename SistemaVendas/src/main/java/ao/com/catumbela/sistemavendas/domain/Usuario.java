package ao.com.catumbela.sistemavendas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Usuario{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column (length = 32, nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private Character tipo;
	
	@Column(nullable = false)
	private Boolean activo;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;
	
	@Transient
	public String getTipoFormatado() {
		String tipoFormatado = null;
		
		if(tipo =='A') {
			tipoFormatado = "Administrador";
		}else if(tipo == 'B') {
			tipoFormatado =  "Balconista";
		}else if(tipo == 'G') {
			tipoFormatado = "Gerente";
		}
		
		return tipoFormatado;
	}
	
	@Transient
	public String getActivoFormatado() {
		String activoFormatado = null;
		if(activo) {
			activoFormatado = "Sim";
		}else {
			activoFormatado = "Não";
		}
		
		return activoFormatado;
	}

}
