package com.br.titanium.useCases.domains;

import com.br.titanium.entitys.Cliente;
import com.br.titanium.entitys.OrdemDeCorte;
import com.br.titanium.useCases.domains.EnderecoResponseDom;
import com.br.titanium.useCases.domains.OrdemCorteResponseDom;
import com.br.titanium.utils.StatusOS;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrdemServicoResponseDom {

    private Long id;
    private Integer qtdeRolos;
    private LocalDate dataEntrada;
    private LocalDate dataEntrega;
    private Integer qtdePecas;
    private Double qtdeMaterialFalhas;
    private Double qtdeMaterialRestante;
    private BigDecimal valorPorPeca;
    private BigDecimal valorTotal;
    private String codReferenciaOs;
    private String modelo;
    private Integer numeorNotaFiscal;
    private String campoObservacao;
    private StatusOS status;

    private Cliente cliente;
    private List<OrdemDeCorte> gradeMateriaPrimaList;
    private List<OrdemCorteResponseDom> ordensDeCorte;
    private List<EnderecoResponseDom> enderecosCliemte;
    private Long clienteId;


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

    public String getCodReferenciaOs() {
        return codReferenciaOs;
    }

    public void setCodReferenciaOs(String codReferenciaOs) {
        this.codReferenciaOs = codReferenciaOs;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getNumeorNotaFiscal() {
        return numeorNotaFiscal;
    }

    public void setNumeorNotaFiscal(Integer numeorNotaFiscal) {
        this.numeorNotaFiscal = numeorNotaFiscal;
    }

    public String getCampoObservacao() {
        return campoObservacao;
    }

    public void setCampoObservacao(String campoObservacao) {
        this.campoObservacao = campoObservacao;
    }

    public StatusOS getStatus() {
        return status;
    }

    public void setStatus(StatusOS status) {
        this.status = status;
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

    public List<OrdemCorteResponseDom> getOrdensDeCorte() {
        return ordensDeCorte;
    }


    public void setOrdensDeCorte(List<OrdemCorteResponseDom> ordensDeCorte) {
        this.ordensDeCorte = ordensDeCorte;
    }

    public List<EnderecoResponseDom> getEnderecosCliemte() {
        return enderecosCliemte;
    }

    public void setEnderecosCliemte(List<EnderecoResponseDom> enderecosCliemte) {
        this.enderecosCliemte = enderecosCliemte;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}
