package com.br.titanium.useCases;


import com.br.titanium.entitys.*;
import com.br.titanium.repositorys.GradeRepository;
import com.br.titanium.repositorys.OrdemCorteTamRepository;
import com.br.titanium.repositorys.OrdemDeCorteRepository;
import com.br.titanium.useCases.ordemCorte.domains.OrdemCorteRequestoDom;
import com.br.titanium.useCases.ordemCorte.domains.OrdemCorteResponseDom;
import com.br.titanium.useCases.ordemCorteTam.domains.OrdemCorteTamRequesteDom;
import com.br.titanium.useCases.ordemCorteTam.domains.OrdemCorteTamResponseDom;
import com.br.titanium.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemCorteTamService {

    @Autowired
    private OrdemDeCorteRepository ordemDeCorteRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private OrdemCorteTamRepository ordemCorteTamRepository;

    public List<OrdemCorteTamResponseDom> criarOrdemCorteTam(OrdemCorteTamRequesteDom ordemDeCorteTam) throws CrudException {

        Optional<OrdemDeCorte> resultadoIdOrdemCorte = ordemDeCorteRepository.findById(ordemDeCorteTam.getOrdemDeCorteId());

        if (!resultadoIdOrdemCorte.isPresent()) {
            throw new CrudException("Ordem de corte não encontrada");
        }

        List<OrdemCorteTamanho> ordemCorteTamanhos = new ArrayList<>();

        for (OrdemCorteTamRequesteDom.TamanhoQuantidadeRequest tamanho : ordemDeCorteTam.getTamanhos()) {

            Optional<Grade> resultadoIdGrade = gradeRepository.findById(tamanho.getTamanhoGradeId());

            if (!resultadoIdGrade.isPresent()) {
                throw new CrudException("Grade não encontrada");
            }

            OrdemCorteTamanho ordemCorteTamanho = new OrdemCorteTamanho();
            ordemCorteTamanho.setOrdemDeCorte(resultadoIdOrdemCorte.get());
            ordemCorteTamanho.setTamanhoGrade(resultadoIdGrade.get());
            ordemCorteTamanho.setQuantidade(tamanho.getQuantidade());
            ordemCorteTamanhos.add(ordemCorteTamRepository.save(ordemCorteTamanho));
        }

        // Construir a lista de resposta
        List<OrdemCorteTamResponseDom> responseDomList = new ArrayList<>();

        for (OrdemCorteTamanho ordemCorteTamanho : ordemCorteTamanhos) {
            OrdemCorteTamResponseDom ordemCorteTamResponse = new OrdemCorteTamResponseDom();
            ordemCorteTamResponse.setTamanhoGradeId(ordemCorteTamanho.getTamanhoGrade().getId());
            ordemCorteTamResponse.setQuantidade(ordemCorteTamanho.getQuantidade());
            ordemCorteTamResponse.setId(ordemCorteTamanho.getId());
            responseDomList.add(ordemCorteTamResponse);
        }

        return responseDomList;
    }


}




