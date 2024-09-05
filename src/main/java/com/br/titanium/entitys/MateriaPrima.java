package com.br.titanium.entitys;

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
    private Integer comprimento;
    @Column(nullable = false)
    private Integer qtde;
    @Column(nullable = false)
    private String largura;
    @Column(nullable = true)
    private String codReferencia;



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

    public Integer getComprimento() {
        return comprimento;
    }

    public void setComprimento(Integer comprimento) {
        this.comprimento = comprimento;
    }

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }

    public String getLargura() {
        return largura;
    }

    public void setLargura(String largura) {
        this.largura = largura;
    }

    public String getCodReferencia() {
        return codReferencia;
    }

    public void setCodReferencia(String codReferencia) {
        this.codReferencia = codReferencia;
    }


    public List<OrdemDeCorte> getGradeMateriaPrimaList() {
        return gradeMateriaPrimaList;
    }

    public void setGradeMateriaPrimaList(List<OrdemDeCorte> gradeMateriaPrimaList) {
        this.gradeMateriaPrimaList = gradeMateriaPrimaList;
    }
}
