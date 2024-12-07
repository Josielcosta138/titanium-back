package com.br.titanium.useCases.services;

import com.br.titanium.entitys.CidadeUsuario;
import com.br.titanium.repositorys.CidadeUsuarioRepository;
import com.br.titanium.useCases.domains.CidadeUsuarioRequestDom;
import com.br.titanium.useCases.domains.CidadeUsuarioResponseDom;
import com.br.titanium.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CidadeUsuarioService {
    @Autowired
    private CidadeUsuarioRepository cidadeRepository;

    public List<CidadeUsuarioResponseDom> carregarCidades(){
        List<CidadeUsuario> resultadoCidadeBanco = cidadeRepository.findAll();

            List<CidadeUsuarioResponseDom> listaDeCidades = new ArrayList<>();

        for (CidadeUsuario dadoResultado: resultadoCidadeBanco) {
            CidadeUsuarioResponseDom aux = new CidadeUsuarioResponseDom();
            aux.setId(dadoResultado.getId());
            aux.setName(dadoResultado.getName());
            aux.setUf(dadoResultado.getUf());

            listaDeCidades.add(aux);
        }

        return listaDeCidades;
    }


    public CidadeUsuarioResponseDom criarCidade(CidadeUsuarioResponseDom cidade) throws CrudException {
            List<String> mensagens = this.validarCidade(cidade);
                if(!mensagens.isEmpty()) {
                    throw new CrudException(mensagens);
                }

        //---Validar cidade já existente
        List<CidadeUsuario> resultadoCidadesBanco = cidadeRepository.findAll();

        CidadeUsuario cidadeEntidade = new CidadeUsuario();
        CidadeUsuarioResponseDom responseDom = new CidadeUsuarioResponseDom();

            for (CidadeUsuario dadoResultado: resultadoCidadesBanco) {
                CidadeUsuarioResponseDom aux = new CidadeUsuarioResponseDom();
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

                        CidadeUsuario reasultado = cidadeRepository.save(cidadeEntidade);
                        responseDom.setId(reasultado.getId());
                        responseDom.setName(reasultado.getName());
                        responseDom.setUf(reasultado.getUf());
                        return responseDom;
            }

        return responseDom;
    }


    public CidadeUsuarioResponseDom atualizarCidade(Long id, CidadeUsuarioRequestDom cidade) throws CrudException{
        List<String>mensagens = this.validarCidadeAtt(cidade);
        if (!mensagens.isEmpty()){
            throw new CrudException(mensagens);
        }

            Optional<CidadeUsuario> resultado = cidadeRepository.findById(id).map(record -> {
                record.setName(cidade.getName());
                record.setUf(cidade.getUf());

                return cidadeRepository.save(record);
            });

            if (resultado.isPresent()){
                CidadeUsuario cidadeEntidades = resultado.get();

                CidadeUsuarioResponseDom responseDOM = new CidadeUsuarioResponseDom();
                responseDOM.setId(cidadeEntidades.getId());
                responseDOM.setName(cidadeEntidades.getName());
                responseDOM.setUf(cidadeEntidades.getUf());

                return responseDOM;
            }


        return null;
    }



    public List<String> validarCidadeAtt(CidadeUsuarioRequestDom cidade){
        List<String> mensagens = new ArrayList<>();

        if (cidade.getName() == null || cidade.getName().equals("")) {
            mensagens.add("nome do cidade não informado");
        }
        if (cidade.getUf() == null || cidade.getUf().equals("")) {
            mensagens.add("nome do estado não informado");
        }
        return mensagens;
    }


    public List<String> validarCidade(CidadeUsuarioResponseDom cidade){
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
