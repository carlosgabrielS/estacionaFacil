package br.senai.sp.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "tbl_contrato")
public class Contrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codContrato;

	private Long quantidadeVagas;

	private Double valor;

	private String diaPagamento;

	@ManyToOne
	@JoinColumn(name = "codMensalista")
	private Mensalista mensalista;

	public long getCodContrato() {
		return codContrato;
	}

	public void setCodContrato(long codContrato) {
		this.codContrato = codContrato;
	}

	public Long getQuantidadeVagas() {
		return quantidadeVagas;
	}

	public void setQuantidadeVagas(Long quantidadeVagas) {
		this.quantidadeVagas = quantidadeVagas;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDiaPagamento() {
		return diaPagamento;
	}

	public void setDiaPagamento(String diaPagamento) {
		this.diaPagamento = diaPagamento;
	}

	public Mensalista getMensalista() {
		return mensalista;
	}

	public void setMensalista(Mensalista mensalista) {
		this.mensalista = mensalista;
	}

	@Override
	public String toString() {
		return "Contrato [codContrato=" + codContrato + ", quantidadeVagas=" + quantidadeVagas + ", valor=" + valor
				+ ", diaPagamento=" + diaPagamento + ", mensalista=" + mensalista + "]";
	}
	
	

}
