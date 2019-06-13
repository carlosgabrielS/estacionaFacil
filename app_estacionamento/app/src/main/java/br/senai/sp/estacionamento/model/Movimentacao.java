package br.senai.sp.estacionamento.model;

import java.io.Serializable;

public class Movimentacao implements Serializable{

    private int codMovimentacao;
    private String placa;
    private String modelo;
    private String horaEntrada;
    private String horaSaida;
    private String tempo;
    private String tipo;
    private String valorPago;

    public int getCodMovimentacao() {
        return codMovimentacao;
    }

    public void setCodMovimentacao(int codMovimentacao) {
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

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getValorPago() {
        return valorPago;
    }

    public void setValorPago(String valorPago) {
        this.valorPago = valorPago;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return  "Placa= " + placa + "  Modelo= " + modelo;
    }
}
