package br.uff.ic.agenda.model;

import java.io.Serializable;

public class Contato implements Serializable {
    private String nome;
    private String telefone;
    private String detalhes;
    private String enderecoResidencial;
    private String enderecoComercial;

    public Contato() {
        nome = "Novo Contato";
        telefone = "";
        detalhes = "";
        enderecoComercial = "";
        enderecoResidencial = "";

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getEnderecoResidencial() {
        return this.enderecoResidencial;
    }

    public void setEnderecoResidencial(String detalhes) {
        this.enderecoResidencial = enderecoResidencial;
    }

    public String getEnderecoComerial() {
        return this.enderecoComercial;
    }

    public void setEnderecoComercial(String enderecoComercial) {
        this.enderecoComercial = enderecoComercial;
    }

    @Override
    public String toString() {
        return nome;
    }    
}