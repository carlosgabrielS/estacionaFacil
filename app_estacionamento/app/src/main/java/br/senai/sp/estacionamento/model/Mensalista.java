package br.senai.sp.estacionamento.model;

import java.io.Serializable;

public class Mensalista implements Serializable {
    private Long codMensalista;
    private String nome;
    private String cpf;
    private String email;
    private String valorMensal;

    public Long getCodMensalista() {
        return codMensalista;
    }

    public void setCodMensalista(Long codMensalista) {
        this.codMensalista = codMensalista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(String valorMensal) {
        this.valorMensal = valorMensal;
    }

    @Override
    public String toString() {
        return "Mensalista{" +
                "codMensalista=" + codMensalista +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", valorMensal='" + valorMensal + '\'' +
                '}';
    }
}
