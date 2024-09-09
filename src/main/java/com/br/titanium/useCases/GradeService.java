package com.br.titanium.useCases;

import com.br.titanium.entitys.Grade;
import com.br.titanium.repositorys.GradeRepository;
import com.br.titanium.useCases.grade.domains.GradeResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<GradeResponseDom> carregarGrade(){
        List<Grade> gradeBanco = gradeRepository.findAll();

        List<GradeResponseDom> listaGrade = new ArrayList<>();

        for (Grade resultado: gradeBanco){
            GradeResponseDom aux = new GradeResponseDom();

            aux.setId(resultado.getId());
            aux.setIdentificacaoGrade(resultado.getIdentificacaograde());

            listaGrade.add(aux);
        }

        return listaGrade;
    }


}
