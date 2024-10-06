package com.br.titanium.useCases;

import com.br.titanium.entitys.MateriaPrima;
import com.br.titanium.entitys.OrdemDeCorte;
import com.br.titanium.entitys.OrdemServico;
import com.br.titanium.repositorys.MateriaPrimaRepository;
import com.br.titanium.useCases.materiaPrima.domains.MateriaPrimaResponseDom;
import com.br.titanium.useCases.ordemCorte.domains.OrdemCorteResponseDom;
import com.br.titanium.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            mpResponseDom.setNome(resultado.getNome());
            mpResponseDom.setComprimento(resultado.getComprimento());
            mpResponseDom.setQtde(resultado.getQtde());
            mpResponseDom.setLargura(resultado.getLargura());
            mpResponseDom.setCodReferencia(resultado.getCodReferencia());

            return mpResponseDom;
    }



    public MateriaPrimaResponseDom carregarOSById(Long id) {

        Optional<MateriaPrima> result = materiaPrimaRepository.findById(id);

        if (result.isPresent()) {
            MateriaPrima mpResesponse = result.get();

            MateriaPrimaResponseDom mpResponseDom = new MateriaPrimaResponseDom();
            mpResponseDom.setId(mpResesponse.getId());
            mpResponseDom.setNome(mpResesponse.getNome());
            mpResponseDom.setComprimento(mpResesponse.getComprimento());
            mpResponseDom.setQtde(mpResesponse.getQtde());
            mpResponseDom.setLargura(mpResesponse.getLargura());
            mpResponseDom.setCodReferencia(mpResesponse.getCodReferencia());

            return mpResponseDom;
        }


        return null;
    }


    public MateriaPrimaResponseDom atualizarMateriaPrima( Long id, MateriaPrimaResponseDom materiaprima) throws CrudException {
//        List<String> mensagens = this.validarMateriaPrima(materiaprima);
//        if (!mensagens.isEmpty()){
//            throw new CrudException(mensagens);
//        }

        Optional<MateriaPrima> result = materiaPrimaRepository.findById(id);

        if (result.isPresent()) {
            MateriaPrima materiaPrimaEntidade = result.get();

            materiaPrimaEntidade.setNome(materiaprima.getNome());
            materiaPrimaEntidade.setComprimento(materiaprima.getComprimento());
            materiaPrimaEntidade.setQtde(materiaprima.getQtde());
            materiaPrimaEntidade.setLargura(materiaprima.getLargura());
            materiaPrimaEntidade.setCodReferencia(materiaprima.getCodReferencia());
            MateriaPrima materiaPrimaAtualizada = materiaPrimaRepository.save(materiaPrimaEntidade);

            MateriaPrimaResponseDom materiaPrimaResponse = new  MateriaPrimaResponseDom();
            materiaPrimaResponse.setNome(materiaPrimaAtualizada.getNome());
            materiaPrimaResponse.setComprimento(materiaPrimaAtualizada.getComprimento());
            materiaPrimaResponse.setQtde(materiaPrimaAtualizada.getQtde());
            materiaPrimaResponse.setLargura(materiaPrimaAtualizada.getLargura());
            materiaPrimaResponse.setCodReferencia(materiaPrimaAtualizada.getCodReferencia());


            return materiaPrimaResponse;
        }
        throw new CrudException("Matéria prima não encontrada com o ID: " + id);
    }




    public MateriaPrimaResponseDom atualizarQtdeTotalMaterial( Long id, MateriaPrimaResponseDom materiaPrimaQtdes) throws CrudException {
//        List<String> mensagens = this.validarMateriaPrima(materiaprima);
//        if (!mensagens.isEmpty()){
//            throw new CrudException(mensagens);
//        }

        Optional<MateriaPrima> result = materiaPrimaRepository.findById(id);

        if (result.isPresent()) {
            MateriaPrima materiaPrimaEntidade = result.get();

            materiaPrimaEntidade.setComprimento(materiaPrimaQtdes.getComprimento());
            materiaPrimaEntidade.setQtde(materiaPrimaQtdes.getQtde());
            MateriaPrima materiaPrimaAtualizada = materiaPrimaRepository.save(materiaPrimaEntidade);

            MateriaPrimaResponseDom materiaPrimaResponse = new  MateriaPrimaResponseDom();
            materiaPrimaResponse.setId(materiaPrimaAtualizada.getId());
            materiaPrimaResponse.setComprimento(materiaPrimaAtualizada.getComprimento());
            materiaPrimaResponse.setQtde(materiaPrimaAtualizada.getQtde());


            return materiaPrimaResponse;
        }
        throw new CrudException("Matéria prima não encontrada com o ID: " + id);
    }








    public List<String> validarMateriaPrima (MateriaPrimaResponseDom materiaPrima) {
        List<String> mensagens = new ArrayList<>();
        if (materiaPrima.getNome() == null || materiaPrima.getNome().equals("")){
            mensagens.add("Matéria prima não informada.");
        }
        return mensagens;
    }


}
