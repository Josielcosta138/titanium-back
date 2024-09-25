package com.br.titanium.useCases.ordemCorteTam.domains;

public class OrdemCorteTamResponseDom {

    private Long id;

    private Long ordemDeCorteId;

    private Long tamanhoGradeId;

    private Integer quantidade;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdemDeCorteId() {
        return ordemDeCorteId;
    }

    public void setOrdemDeCorteId(Long ordemDeCorteId) {
        this.ordemDeCorteId = ordemDeCorteId;
    }

    public Long getTamanhoGradeId() {
        return tamanhoGradeId;
    }

    public void setTamanhoGradeId(Long tamanhoGradeId) {
        this.tamanhoGradeId = tamanhoGradeId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
