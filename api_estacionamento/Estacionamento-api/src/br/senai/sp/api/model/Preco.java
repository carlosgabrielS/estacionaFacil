package br.senai.sp.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tbl_preco")
public class Preco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codPreco;
	
	@NotNull
	private Double valorPrimeiraHora;
	
	@NotNull
	private Double valorDemaisHoras;
	
	private String dataFim;
	
	private Integer tempoTolerancia;

	public Long getCodPreco() {
		return codPreco;
	}

	public void setCodPreco(Long codPreco) {
		this.codPreco = codPreco;
	}

	public Double getValorPrimeiraHora() {
		return valorPrimeiraHora;
	}

	public void setValorPrimeiraHora(Double valorPrimeiraHora) {
		this.valorPrimeiraHora = valorPrimeiraHora;
	}

	public Double getValorDemaisHoras() {
		return valorDemaisHoras;
	}

	public void setValorDemaisHoras(Double valorDemaisHoras) {
		this.valorDemaisHoras = valorDemaisHoras;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getTolerancia() {
		return tempoTolerancia;
	}

	public void setTolerancia(Integer tolerancia) {
		this.tempoTolerancia = tolerancia;
	}

	@Override
	public String toString() {
		return "Preco [codPreco=" + codPreco + ", valorPrimeiraHora=" + valorPrimeiraHora + ", valorDemaisHoras="
				+ valorDemaisHoras + ", dataFim=" + dataFim + ", tempoTolerancia=" + tempoTolerancia + "]";
	}
	
	
	
}
