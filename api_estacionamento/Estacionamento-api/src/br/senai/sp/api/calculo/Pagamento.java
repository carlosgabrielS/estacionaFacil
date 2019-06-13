package br.senai.sp.api.calculo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;

import br.senai.sp.api.conversao.DataBanco;
import br.senai.sp.api.model.Movimentacao;
import br.senai.sp.api.model.Preco;

public class Pagamento {
	
	public Movimentacao movimentacao;
	
	public Preco preco;

	public Pagamento(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}
	
	public Movimentacao fecharMovimento(Preco preco) {
		this.preco = preco;
		movimentacao.setHoraSaida(DataBanco.getFormatoBanco("yyyy-MM-dd HH:mm:00"));
		movimentacao.setTempo(getHoras(movimentacao.getHoraEntrada(), preco.getTolerancia()));
		movimentacao.setValorPago(getValorPagar(movimentacao.getTempo(), preco.getValorPrimeiraHora(), preco.getValorDemaisHoras()));
		
		return movimentacao;
	}
	
	public int getHoras(String horaEntrada, Integer tolerancia) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
		double horasPagar = 0;
		try {
			//Convertendo String 
			Date entrada = df.parse(horaEntrada);
			Date saida = new Date();
			
			//Converte a data em milisegundos
			Long milisegundos = saida.getTime() - entrada.getTime();
			
			//Converte milisegundos em minutos
			double minutos = milisegundos / 1000 / 60;
			
			//Converte minutos em horas
			double horas = (int) minutos / 60;
			
			double minutosPassados = minutos - horas * 60;
		
			
			if((minutos >= horas * 60)) {
				if((minutos - horas * 60) > tolerancia) {
					horasPagar = horas + 1;
				}else {
					horasPagar = horas;
				}
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return (int) horasPagar;
	}
	
	public Double getValorPagar(Integer horas, Double valorPrimeiraHora, Double valorDemaisHoras) {
		
		Double valorPago = 0.00;
		
		int i = 0;
		 
		for(i=0; i < horas; i++) {
			if( i == 0) {
				valorPago = valorPago + valorPrimeiraHora;
			}else {
				valorPago = valorPago + valorDemaisHoras;
			}
		}
		
		return valorPago;
	}
	
}
