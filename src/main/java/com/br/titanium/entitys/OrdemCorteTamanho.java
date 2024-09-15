package com.br.titanium.entitys;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class OrdemCorteTamanho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ordem_corte_id", nullable = false)
    private OrdemDeCorte ordemDeCorte;

    @ManyToOne
    @JoinColumn(name = "tamanho_grade_id", nullable = false)
    private Grade tamanhoGrade;

    @Column(nullable = false)
    private Integer quantidade;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdemDeCorte getOrdemDeCorte() {
        return ordemDeCorte;
    }

    public void setOrdemDeCorte(OrdemDeCorte ordemDeCorte) {
        this.ordemDeCorte = ordemDeCorte;
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
