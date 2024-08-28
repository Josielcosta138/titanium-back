package com.br.titanium.entitys;

import jakarta.persistence.*;

@Entity
public class GradeMateriaPrima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "materia_prima_id", nullable = false)
    private MateriaPrima materiaPrima;

    @ManyToOne
    @JoinColumn(name = "tamanho_grade_id", nullable = false)
    private Grade tamanhoGrade;

    @ManyToOne
    @JoinColumn(name = "ordem_servico_id", nullable = false)
    private OrdemServico ordemServico;

    @Column(nullable = false)
    private Integer quantidade;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public Grade getTamanhoGrade() {
        return tamanhoGrade;
    }

    public void setTamanhoGrade(Grade tamanhoGrade) {
        this.tamanhoGrade = tamanhoGrade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
