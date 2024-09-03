package com.br.titanium.useCases;

import com.br.titanium.entitys.Cidade;
import com.br.titanium.repositorys.CidadeRepository;
import com.br.titanium.useCases.cidade.domains.CidadeResponseDom;
import com.br.titanium.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    public List<CidadeResponseDom> carregarCidades(){
        List<Cidade> resultadoCidadeBanco = cidadeRepository.findAll();

            List<CidadeResponseDom> listaDeCidades = new ArrayList<>();

        for (Cidade dadoResultado: resultadoCidadeBanco) {
            CidadeResponseDom aux = new CidadeResponseDom();
            aux.setId(dadoResultado.getId());
            aux.setName(dadoResultado.getName());
            aux.setUf(dadoResultado.getUf());

            listaDeCidades.add(aux);
        }

        return listaDeCidades;
    }


    public CidadeResponseDom criarCidade(CidadeResponseDom cidade) throws CrudException {
            List<String> mensagens = this.validarCidade(cidade);
                if(!mensagens.isEmpty()) {
                    throw new CrudException(mensagens);
                }

        //---Validar cidade já existente
        List<Cidade> resultadoCidadesBanco = cidadeRepository.findAll();

        //for (Cidade dadoResultado : resultadoCidadesBanco) {
        // if (dadoResultado.getName().equalsIgnoreCase(cidade.getName()) &&
        //    dadoResultado.getUf().equals(cidade.getUf())) {
        //  mensagens.add("Já existe cidade e estado cadastrada para este endereço!");
        // throw new CrudException(mensagens);
        //}
        //}


        Cidade cidadeEntidade = new Cidade();
        CidadeResponseDom responseDom = new CidadeResponseDom();

            for (Cidade dadoResultado: resultadoCidadesBanco) {
                CidadeResponseDom aux = new CidadeResponseDom();
                aux.setName(dadoResultado.getName());
                aux.setUf(dadoResultado.getUf());


                        if (dadoResultado.getName().equalsIgnoreCase(cidade.getName())){
                            cidadeEntidade.setName(dadoResultado.getName());
                        }else {
                            cidadeEntidade.setName(cidade.getName());
                        }

                        if (dadoResultado.getUf().equals(cidade.getUf())) {
                            cidadeEntidade.setUf(dadoResultado.getUf());
                        }else {
                            cidadeEntidade.setUf(cidade.getUf());
                        }

                        Cidade reasultado = cidadeRepository.save(cidadeEntidade);
                        responseDom.setId(reasultado.getId());
                        responseDom.setName(reasultado.getName());
                        responseDom.setUf(reasultado.getUf());
                        return responseDom;
            }

        return responseDom;
    }





    public List<String> validarCidade(CidadeResponseDom cidade){
        List<String> mensagens = new ArrayList<>();

        if (cidade.getName() == null || cidade.getName().equals("")) {
            mensagens.add("nome do cidade não informado");
        }
        if (cidade.getUf() == null || cidade.getUf().equals("")) {
            mensagens.add("nome do estado não informado");
        }
        return mensagens;
    }



}
