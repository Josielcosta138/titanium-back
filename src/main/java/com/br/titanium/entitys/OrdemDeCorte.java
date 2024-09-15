package com.br.titanium.entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class OrdemDeCorte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "materia_prima_id", nullable = false)
    private MateriaPrima materiaPrima;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ordem_servico_id", nullable = false)
    private OrdemServico ordemServico;

    @OneToMany(mappedBy = "ordemDeCorte", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdemCorteTamanho> ordemCorteTamanhos;

    // Getters e Setters
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

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }


    public List<OrdemCorteTamanho> getOrdemCorteTamanhos() {
        return ordemCorteTamanhos;
    }

    public void setOrdemCorteTamanhos(List<OrdemCorteTamanho> ordemCorteTamanhos) {
        this.ordemCorteTamanhos = ordemCorteTamanhos;
    }
}
