package com.br.titanium.useCases.ordemCorte.domains;

import com.br.titanium.entitys.MateriaPrima;
import com.br.titanium.entitys.OrdemCorteTamanho;
import com.br.titanium.entitys.OrdemServico;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class OrdemCorteResponseDom {

    private Long id;

    private MateriaPrima materiaPrima;

    private OrdemServico ordemServico;


    private List<OrdemCorteTamanho> ordemCorteTamanhos;

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
