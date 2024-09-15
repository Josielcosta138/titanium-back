package com.br.titanium.useCases;

import com.br.titanium.entitys.OrdemDeCorte;
import com.br.titanium.repositorys.OrdemDeCorteRepository;
import com.br.titanium.repositorys.OrdemServicoRepository;
import com.br.titanium.useCases.ordemCorte.domains.OrdemCorteResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdemCorteService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    @Autowired
    private OrdemDeCorteRepository ordemDeCorteRepository;

    public List<OrdemCorteResponseDom> carregarMateriaPrimaByOS(Long id) {

        List<OrdemDeCorte> ordensDeCorte = ordemDeCorteRepository.findByOrdemServicoId(id);
        List<OrdemCorteResponseDom> ListaMateriaPrima = new ArrayList<>();

        for (OrdemDeCorte ordemDeCorte : ordensDeCorte) {
            OrdemCorteResponseDom ordemDeCorteResponseDom = new OrdemCorteResponseDom();
            ordemDeCorteResponseDom.setId(ordemDeCorte.getId());
            ordemDeCorteResponseDom.setMateriaPrima(ordemDeCorte.getMateriaPrima());

            ListaMateriaPrima.add(ordemDeCorteResponseDom);
        }
        return ListaMateriaPrima;

    }




}
