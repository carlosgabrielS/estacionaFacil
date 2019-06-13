package br.senai.sp.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_mensalista_telefone")
public class MensalistaTelefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codMensalistaTelefone;
	
	@ManyToOne
	@JoinColumn(name="cod_mensalista")
	private Mensalista mensalista;
	
	@ManyToOne
	@JoinColumn(name = "cod_telefone")
	private Telefone telefone;

	public Long getCodMensalistaTelefone() {
		return codMensalistaTelefone;
	}

	public void setCodMensalistaTelefone(Long codMensalistaTelefone) {
		this.codMensalistaTelefone = codMensalistaTelefone;
	}

	public Mensalista getMensalista() {
		return mensalista;
	}

	public void setMensalista(Mensalista mensalista) {
		this.mensalista = mensalista;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "MensalistaTelefone [codMensalistaTelefone=" + codMensalistaTelefone + ", mensalista=" + mensalista
				+ ", telefone=" + telefone + "]";
	}
	
	
}
