package com.br.titanium.useCases.materiaPrima.domains;

import jakarta.persistence.Column;

public class MateriaPrimaRequestDom {

    private String nome;
    private Double comprimento;
    private Double qtde;
    private Double largura;
    private String codReferencia;
    private Double qtdeMaterialFalhas;
    private Double qtdeMaterialRestante;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getComprimento() {
        return comprimento;
    }

    public void setComprimento(Double comprimento) {
        this.comprimento = comprimento;
    }

    public Double getQtde() {
        return qtde;
    }

    public void setQtde(Double qtde) {
        this.qtde = qtde;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public String getCodReferencia() {
        return codReferencia;
    }

    public void setCodReferencia(String codReferencia) {
        this.codReferencia = codReferencia;
    }


    public Double getQtdeMaterialFalhas() {
        return qtdeMaterialFalhas;
    }

    public void setQtdeMaterialFalhas(Double qtdeMaterialFalhas) {
        this.qtdeMaterialFalhas = qtdeMaterialFalhas;
    }

    public Double getQtdeMaterialRestante() {
        return qtdeMaterialRestante;
    }

    public void setQtdeMaterialRestante(Double qtdeMaterialRestante) {
        this.qtdeMaterialRestante = qtdeMaterialRestante;
    }
}

