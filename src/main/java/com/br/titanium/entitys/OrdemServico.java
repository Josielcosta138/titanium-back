package com.br.titanium.entitys;


import jakarta.persistence.*;

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
    private Integer qtdeMaterialFalhas;
    @Column(nullable = false)
    private Integer qtdeMaterialRestante;
    @Column(nullable = false)
    private Double valorPorPeca;
    @Column(nullable = false)
    private Double ValorTotal;


    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    //ordem de serviço pode estar associada a várias matérias-primas.
    //essa sugestão de relacionamento bi-direcional foi uma sugestão pelo roberto
    //aplicada para grades tmb
    @ManyToMany
    @JoinTable(
            name = "ordem_servico_materia_prima",
            joinColumns = @JoinColumn(name = "ordem_servico_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_prima_id")
    )
    private List<MateriaPrima> materiasPrimas;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;


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

    public Integer getQtdeMaterialFalhas() {
        return qtdeMaterialFalhas;
    }

    public void setQtdeMaterialFalhas(Integer qtdeMaterialFalhas) {
        this.qtdeMaterialFalhas = qtdeMaterialFalhas;
    }

    public Integer getQtdeMaterialRestante() {
        return qtdeMaterialRestante;
    }

    public void setQtdeMaterialRestante(Integer qtdeMaterialRestante) {
        this.qtdeMaterialRestante = qtdeMaterialRestante;
    }

    public Double getValorPorPeca() {
        return valorPorPeca;
    }

    public void setValorPorPeca(Double valorPorPeca) {
        this.valorPorPeca = valorPorPeca;
    }

    public Double getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        ValorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<MateriaPrima> getMateriasPrimas() {
        return materiasPrimas;
    }

    public void setMateriasPrimas(List<MateriaPrima> materiasPrimas) {
        this.materiasPrimas = materiasPrimas;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
