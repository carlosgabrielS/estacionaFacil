package br.senai.sp.estacionamento.model;

import java.io.Serializable;

public class Preco implements Serializable{

    private Double primeiraHora;
    private Double demaisHoras;
    private Integer tolerancia;

    public Double getPrimeiraHora() {
        return primeiraHora;
    }

    public void setPrimeiraHora(Double primeiraHora) {
        this.primeiraHora = primeiraHora;
    }

    public Double getDemaisHoras() {
        return demaisHoras;
    }

    public void setDemaisHoras(Double demaisHoras) {
        this.demaisHoras = demaisHoras;
    }

    public Integer getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(Integer tolerancia) {
        this.tolerancia = tolerancia;
    }

    @Override
    public String toString() {
        return "Preco{" +
                "primeiraHora=" + primeiraHora +
                ", demaisHoras=" + demaisHoras +
                ", tolerancia=" + tolerancia +
                '}';
    }
}
