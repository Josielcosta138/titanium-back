package com.br.titanium.entitys;

import jakarta.persistence.*;

import java.util.Map;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "grade_tamanhos", joinColumns = @JoinColumn(name = "grade_id"))
    @MapKeyJoinColumn(name = "tamanho_id")
    @Column(name = "quantidade")
    private Map<Tamanho, Integer> tamanhosQuantidades; // Mapeia o Tamanho à Quantidade


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Tamanho, Integer> getTamanhosQuantidades() {
        return tamanhosQuantidades;
    }

    public void setTamanhosQuantidades(Map<Tamanho, Integer> tamanhosQuantidades) {
        this.tamanhosQuantidades = tamanhosQuantidades;
    }
}
