package com.br.titanium.useCases.domains.domains;

import java.util.List;

public class OrdemCorteTamRequesteDom {


    private Long ordemDeCorteId;

    private List<TamanhoQuantidadeRequest> tamanhos;

    public void setOrdemDeCorteId(Long ordemDeCorteId) {
        this.ordemDeCorteId = ordemDeCorteId;
    }

    public Long getOrdemDeCorteId() {
        return ordemDeCorteId;
    }

    public List<TamanhoQuantidadeRequest> getTamanhos() {
        return tamanhos;
    }

    public void setTamanhos(List<TamanhoQuantidadeRequest> tamanhos) {
        this.tamanhos = tamanhos;
    }


    public static  class TamanhoQuantidadeRequest {
        private Long tamanhoGradeId;
        private Integer quantidade;

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






}
