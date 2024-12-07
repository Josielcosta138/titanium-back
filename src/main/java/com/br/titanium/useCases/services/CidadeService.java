package com.br.titanium.useCases.services;

import com.br.titanium.entitys.Cidade;
import com.br.titanium.repositorys.CidadeRepository;
import com.br.titanium.useCases.domains.CidadeRequestDom;
import com.br.titanium.useCases.domains.CidadeResponseDom;
import com.br.titanium.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public CidadeResponseDom atualizarCidade(Long id, CidadeRequestDom cidade) throws CrudException{
        List<String>mensagens = this.validarCidadeAtt(cidade);
        if (!mensagens.isEmpty()){
            throw new CrudException(mensagens);
        }

            Optional<Cidade> resultado = cidadeRepository.findById(id).map(record -> {
                record.setName(cidade.getName());
                record.setUf(cidade.getUf());

                return cidadeRepository.save(record);
            });

            if (resultado.isPresent()){
                Cidade cidadeEntidades = resultado.get();

                CidadeResponseDom responseDOM = new CidadeResponseDom();
                responseDOM.setId(cidadeEntidades.getId());
                responseDOM.setName(cidadeEntidades.getName());
                responseDOM.setUf(cidadeEntidades.getUf());

                return responseDOM;
            }


        return null;
    }



    public List<String> validarCidadeAtt(CidadeRequestDom cidade){
        List<String> mensagens = new ArrayList<>();

        if (cidade.getName() == null || cidade.getName().equals("")) {
            mensagens.add("nome do cidade não informado");
        }
        if (cidade.getUf() == null || cidade.getUf().equals("")) {
            mensagens.add("nome do estado não informado");
        }
        return mensagens;
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
