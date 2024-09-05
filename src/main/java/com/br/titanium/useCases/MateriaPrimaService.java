package com.br.titanium.useCases;

import com.br.titanium.entitys.MateriaPrima;
import com.br.titanium.repositorys.MateriaPrimaRepository;
import com.br.titanium.useCases.materiaPrima.domains.MateriaPrimaResponseDom;
import com.br.titanium.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MateriaPrimaService {
    @Autowired
    private MateriaPrimaRepository materiaPrimaRepository;

    public List<MateriaPrimaResponseDom> carregarMateriaPrima(){
        List<MateriaPrima> materiaPrimaBanco = materiaPrimaRepository.findAll();

        List<MateriaPrimaResponseDom> listaMateriaPrima = new ArrayList<>();

        for (MateriaPrima dadoResultado: materiaPrimaBanco) {
            MateriaPrimaResponseDom aux = new MateriaPrimaResponseDom();
            aux.setId(dadoResultado.getId());
            aux.setNome(dadoResultado.getNome());
            aux.setComprimento(dadoResultado.getComprimento());
            aux.setQtde(dadoResultado.getQtde());
            aux.setLargura(dadoResultado.getLargura());
            aux.setCodReferencia(dadoResultado.getCodReferencia());

            listaMateriaPrima.add(aux);
        }

        return listaMateriaPrima;
    }

    public MateriaPrimaResponseDom criarMateriaPrima(MateriaPrimaResponseDom materiaPrima) throws CrudException{
        List<String> mensagens = this.validarMateriaPrima(materiaPrima);
            if (!mensagens.isEmpty()){
                throw new CrudException(mensagens);
            }

        MateriaPrima mpEntidade = new MateriaPrima();
            mpEntidade.setNome(materiaPrima.getNome());
            mpEntidade.setComprimento(materiaPrima.getComprimento());
            mpEntidade.setLargura(materiaPrima.getLargura());
            mpEntidade.setQtde(materiaPrima.getQtde());
            mpEntidade.setCodReferencia(materiaPrima.getCodReferencia());

            MateriaPrima resultado = materiaPrimaRepository.save(mpEntidade);

            MateriaPrimaResponseDom mpResponseDom = new MateriaPrimaResponseDom();
            mpResponseDom.setId(resultado.getId());
            mpResponseDom.setComprimento(resultado.getComprimento());
            mpResponseDom.setQtde(resultado.getQtde());
            mpResponseDom.setLargura(resultado.getLargura());
            mpResponseDom.setCodReferencia(resultado.getCodReferencia());

            return mpResponseDom;
    }

    public List<String> validarMateriaPrima (MateriaPrimaResponseDom materiaPrima){
        List<String> mensagens = new ArrayList<>();

        if (materiaPrima.getNome() == null || materiaPrima.getNome().equals("")){
            mensagens.add("Matéria prima não informada.");
        }
        return mensagens;
    }
}
