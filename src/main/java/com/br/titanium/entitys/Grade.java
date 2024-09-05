package com.br.titanium.entitys;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String idenficacaograde;

    @OneToMany(mappedBy = "tamanhoGrade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdemCorteTamanho> ordemCorteTamanhos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdenficacaograde() {
        return idenficacaograde;
    }

    public void setIdenficacaograde(String idenficacaograde) {
        this.idenficacaograde = idenficacaograde;
    }


    public List<OrdemCorteTamanho> getOrdemCorteTamanhos() {
        return ordemCorteTamanhos;
    }

    public void setOrdemCorteTamanhos(List<OrdemCorteTamanho> ordemCorteTamanhos) {
        this.ordemCorteTamanhos = ordemCorteTamanhos;
    }
}
