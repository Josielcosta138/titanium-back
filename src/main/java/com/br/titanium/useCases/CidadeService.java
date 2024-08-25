package com.br.titanium.useCases;

import com.br.titanium.entitys.Cidade;
import com.br.titanium.entitys.Endereco;
import com.br.titanium.repositorys.CidadeRepository;
import com.br.titanium.useCases.cidade.domains.CidadeResponseDom;
import com.br.titanium.useCases.cliente.domains.ClienteResponseDom;
import com.br.titanium.useCases.endereco.domains.EnderecoResponseDom;
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
            List<String> mensagens = this.validarCliente(cidade);
                if(!mensagens.isEmpty()) {
                    throw new CrudException(mensagens);
                }

            Cidade cidadeEntidade = new Cidade();
                cidadeEntidade.setName(cidade.getName());
                cidadeEntidade.setUf(cidade.getUf());
                Cidade reasultado = cidadeRepository.save(cidadeEntidade);

                CidadeResponseDom responseDom = new CidadeResponseDom();
                responseDom.setId(reasultado.getId());
                responseDom.setName(reasultado.getName());
                responseDom.setUf(reasultado.getUf());

        return responseDom;
    }





    public List<String> validarCliente(CidadeResponseDom cidade){
        List<String> mensagens = new ArrayList<>();

        if (cidade.getName() == null || cidade.getName().equals("")) {
            mensagens.add("nome do cliente não informado");
        }
        return mensagens;
    }

}
