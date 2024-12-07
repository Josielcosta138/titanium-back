package com.br.titanium.useCases.services;

import com.br.titanium.entitys.MateriaPrima;
import com.br.titanium.entitys.OrdemDeCorte;
import com.br.titanium.entitys.OrdemServico;
import com.br.titanium.repositorys.MateriaPrimaRepository;
import com.br.titanium.repositorys.OrdemDeCorteRepository;
import com.br.titanium.repositorys.OrdemServicoRepository;
import com.br.titanium.useCases.domains.OrdemCorteRequestoDom;
import com.br.titanium.useCases.domains.OrdemCorteResponseDom;
import com.br.titanium.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemCorteService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    @Autowired
    private OrdemDeCorteRepository ordemDeCorteRepository;
    @Autowired
    private MateriaPrimaRepository materiaPrimaRepository;

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



    public int carregarTotalDeOrdens() {
        List<OrdemDeCorte> ordensDeCorte = ordemDeCorteRepository.findAll();
        int qtdeTotal = ordensDeCorte.size();
        return qtdeTotal;
    }




    public OrdemCorteResponseDom criarOrdemCorte(OrdemCorteRequestoDom ordemDeCorte) throws CrudException {
        /*List<String>mensagens = this.validarEndereco(ordemDeCorte);
        if (!mensagens.isEmpty()){
            throw new CrudException(mensagens);
        }*/

        Optional<OrdemServico> resultadoIdCliente = ordemServicoRepository.findById(ordemDeCorte.getServicoId());
        Optional<MateriaPrima> resultadoMateriaPrima = materiaPrimaRepository.findById(ordemDeCorte.getMateriaPrimaId());


        OrdemDeCorte ordemCorteEntidades = new OrdemDeCorte();
        ordemCorteEntidades.setOrdemServico(resultadoIdCliente.get());
        ordemCorteEntidades.setMateriaPrima(resultadoMateriaPrima.get());
        OrdemDeCorte resultadoOrdem = ordemDeCorteRepository.save(ordemCorteEntidades);


        OrdemCorteResponseDom responseDom = new OrdemCorteResponseDom();
        responseDom.setServicoId(resultadoOrdem.getOrdemServico().getId());
        responseDom.setMateriaPrimaCorteId(resultadoOrdem.getMateriaPrima().getId());
        responseDom.setId(resultadoOrdem.getId());

        return responseDom;
    }


    private List<String> validarEndereco(OrdemCorteRequestoDom ordemCorte) {
        List<String> mensagens = new ArrayList<>();

        if (ordemCorte.getOrdemServico().getId() == null) {
            mensagens.add("Id OS não informado");
        }
        if (ordemCorte.getMateriaPrima().getId() == null) {
            mensagens.add("Id Matéria não informado");
        }
        return mensagens;

    }



}
