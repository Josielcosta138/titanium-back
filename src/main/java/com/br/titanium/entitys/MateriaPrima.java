package com.br.titanium.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class MateriaPrima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Double comprimento;
    @Column(nullable = false)
    private Double qtde;
    @Column(nullable = false)
    private Double largura;
    @Column(nullable = true)
    private String codReferencia;

    @Column(nullable = false)
    private Double qtdeMaterialFalhas;
    @Column(nullable = false)
    private Double qtdeMaterialRestante;

    @JsonIgnore
    @OneToMany(mappedBy = "materiaPrima", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdemDeCorte> gradeMateriaPrimaList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getQtde() {
        return qtde;
    }

    public void setQtde(Double qtde) {
        this.qtde = qtde;
    }

    public String getCodReferencia() {
        return codReferencia;
    }

    public void setCodReferencia(String codReferencia) {
        this.codReferencia = codReferencia;
    }


    @JsonIgnore
    public List<OrdemDeCorte> getGradeMateriaPrimaList() {
        return gradeMateriaPrimaList;
    }

    public void setGradeMateriaPrimaList(List<OrdemDeCorte> gradeMateriaPrimaList) {
        this.gradeMateriaPrimaList = gradeMateriaPrimaList;
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
