package br.senai.sp.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_tipo_telefone")
public class TipoTelefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codTipo;
	
	private String tipo;

	public Long getCodTipo() {
		return codTipo;
	}

	public void setCodTipo(Long codTipo) {
		this.codTipo = codTipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoTelefone [codTipo=" + codTipo + ", tipo=" + tipo + "]";
	}
	
	
}
