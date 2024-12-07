package com.br.titanium.useCases.domains;

import com.br.titanium.entitys.MateriaPrima;
import com.br.titanium.entitys.OrdemCorteTamanho;
import com.br.titanium.entitys.OrdemServico;

import java.util.List;

public class OrdemCorteRequestoDom {


    private MateriaPrima materiaPrima;

    private OrdemServico ordemServico;


    private List<OrdemCorteTamanho> ordemCorteTamanhos;

    private Long servicoId;
    private Long materiaPrimaId;

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

    public Long getServicoId() {
        return servicoId;
    }

    public Long getMateriaPrimaId() {
        return  materiaPrimaId;
    }
}
