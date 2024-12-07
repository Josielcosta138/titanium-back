package com.br.titanium.useCases.domains;

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

    private Long servicoId;
    private List materiaPrimaId;
    private Long materiaPrimaCorteId;


    public Long getMateriaPrimaCorteId() {
        return materiaPrimaCorteId;
    }

    public void setMateriaPrimaCorteId(Long materiaPrimaCorteId) {
        this.materiaPrimaCorteId = materiaPrimaCorteId;
    }

    public Long getServicoId() {
        return servicoId;
    }

    public void setServicoId(Long servicoId) {
        this.servicoId = servicoId;
    }

    public List getMateriaPrimaId() {
        return materiaPrimaId;
    }

    public void setMateriaPrimaId(List materiaPrimaId) {
        this.materiaPrimaId = materiaPrimaId;
    }

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
