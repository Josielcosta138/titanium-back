package com.br.titanium.entitys;


import com.br.titanium.utils.StatusOS;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer qtdeRolos;
    @Column(nullable = false)
    private LocalDate dataEntrada;
    @Column(nullable = false)
    private LocalDate dataEntrega;
    @Column(nullable = false)
    private Integer qtdePecas;
    @Column(nullable = false)
    private Double qtdeMaterialFalhas;
    @Column(nullable = false)
    private Double qtdeMaterialRestante;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valorPorPeca;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valorTotal;
    @Column(nullable = false)
    private String codReferenciaOs;
    @Column(nullable = false)
    private String modelo;
    @Column(nullable = true)
    private Integer numeorNotaFiscal;
    @Column(nullable = true)
    private String campoObservacao;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOS status;




    // --- GETTERS AND SETTERS
    public StatusOS getStatus() {
        return status;
    }

    public void setStatus(StatusOS status) {
        this.status = status;
    }

    public String getCampoObservacao() {
        return campoObservacao;
    }

    public void setCampoObservacao(String campoObservacao) {
        this.campoObservacao = campoObservacao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCodReferenciaOs() {
        return codReferenciaOs;
    }

    public void setCodReferenciaOs(String codReferenciaOs) {
        this.codReferenciaOs = codReferenciaOs;
    }

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;


    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdemDeCorte> gradeMateriaPrimaList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQtdeRolos() {
        return qtdeRolos;
    }

    public void setQtdeRolos(Integer qtdeRolos) {
        this.qtdeRolos = qtdeRolos;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Integer getQtdePecas() {
        return qtdePecas;
    }

    public void setQtdePecas(Integer qtdePecas) {
        this.qtdePecas = qtdePecas;
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

    public Integer getNumeorNotaFiscal() {
        return numeorNotaFiscal;
    }

    public void setNumeorNotaFiscal(Integer numeorNotaFiscal) {
        this.numeorNotaFiscal = numeorNotaFiscal;
    }

    public BigDecimal getValorPorPeca() {
        return valorPorPeca;
    }

    public void setValorPorPeca(BigDecimal valorPorPeca) {
        this.valorPorPeca = valorPorPeca;
    }


    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<OrdemDeCorte> getGradeMateriaPrimaList() {
        return gradeMateriaPrimaList;
    }

    public void setGradeMateriaPrimaList(List<OrdemDeCorte> gradeMateriaPrimaList) {
        this.gradeMateriaPrimaList = gradeMateriaPrimaList;
    }
}
