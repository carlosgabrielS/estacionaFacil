package br.senai.sp.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_movimentacao")
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codMovimentacao;
	
	@NotNull
	private String placa;
	
	private String modelo;
	
	@NotNull
	private String horaEntrada;
 	
	private String horaSaida;
 	private Integer tempo;
 	
 	private Double valorPago;
 	
 	private String tipo;
 	
 	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Long getCodMovimentacao() {
		return codMovimentacao;
	}
	public void setCodMovimentacao(Long codMovimentacao) {
		this.codMovimentacao = codMovimentacao;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public String getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}
	public Integer getTempo() {
		return tempo;
	}
	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}
	public Double getValorPago() {
		return valorPago;
	}
	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}
	@Override
	public String toString() {
		return "Movimentacao [codMovimentacao=" + codMovimentacao + ", placa=" + placa + ", modelo=" + modelo
				+ ", horaEntrada=" + horaEntrada + ", horaSaida=" + horaSaida + ", tempo=" + tempo + ", valorPago="
				+ valorPago + ", tipo=" + tipo + "]";
	}


}
