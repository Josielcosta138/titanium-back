package com.br.titanium.useCases;
import com.br.titanium.entitys.MateriaPrima;
import com.br.titanium.entitys.OrdemServico;
import com.br.titanium.repositorys.MateriaPrimaRepository;
import com.br.titanium.useCases.materiaPrima.domains.MateriaPrimaResponseDom;
import com.br.titanium.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

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
            aux.setQtdeMaterialFalhas(dadoResultado.getQtdeMaterialFalhas());
            aux.setQtdeMaterialRestante(dadoResultado.getQtdeMaterialRestante());

            listaMateriaPrima.add(aux);
        }

        return listaMateriaPrima;
    }




    public Double carregarEconomiaDeMaterial() {
        List<MateriaPrima> valoresTotais = materiaPrimaRepository.findAll();
        Double qtdeTotal = 0.0;

        for (MateriaPrima valore : valoresTotais) {
            qtdeTotal += valore.getQtdeMaterialRestante();
        }

    return qtdeTotal;
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
            mpEntidade.setQtdeMaterialFalhas(materiaPrima.getQtdeMaterialFalhas());
            mpEntidade.setQtdeMaterialRestante(materiaPrima.getQtdeMaterialRestante());

            MateriaPrima resultado = materiaPrimaRepository.save(mpEntidade);

            MateriaPrimaResponseDom mpResponseDom = new MateriaPrimaResponseDom();
            mpResponseDom.setId(resultado.getId());
            mpResponseDom.setNome(resultado.getNome());
            mpResponseDom.setComprimento(resultado.getComprimento());
            mpResponseDom.setQtde(resultado.getQtde());
            mpResponseDom.setLargura(resultado.getLargura());
            mpResponseDom.setCodReferencia(resultado.getCodReferencia());
            mpResponseDom.setQtdeMaterialRestante(resultado.getQtdeMaterialRestante());
            mpResponseDom.setQtdeMaterialFalhas(resultado.getQtdeMaterialFalhas());

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
            mpResponseDom.setQtdeMaterialFalhas(mpResesponse.getQtdeMaterialFalhas());
            mpResesponse.setQtdeMaterialRestante(mpResesponse.getQtdeMaterialRestante());

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
            materiaPrimaEntidade.setQtdeMaterialRestante(materiaprima.getQtdeMaterialRestante());
            materiaPrimaEntidade.setQtdeMaterialFalhas(materiaprima.getQtdeMaterialFalhas());
            MateriaPrima materiaPrimaAtualizada = materiaPrimaRepository.save(materiaPrimaEntidade);

            MateriaPrimaResponseDom materiaPrimaResponse = new  MateriaPrimaResponseDom();
            materiaPrimaResponse.setNome(materiaPrimaAtualizada.getNome());
            materiaPrimaResponse.setComprimento(materiaPrimaAtualizada.getComprimento());
            materiaPrimaResponse.setQtde(materiaPrimaAtualizada.getQtde());
            materiaPrimaResponse.setLargura(materiaPrimaAtualizada.getLargura());
            materiaPrimaResponse.setCodReferencia(materiaPrimaAtualizada.getCodReferencia());
            materiaPrimaResponse.setQtdeMaterialFalhas(materiaPrimaAtualizada.getQtdeMaterialFalhas());
            materiaPrimaResponse.setQtdeMaterialRestante(materiaPrimaAtualizada.getQtdeMaterialRestante());

            return materiaPrimaResponse;
        }
        throw new CrudException("Matéria prima não encontrada com o ID: " + id);
    }



    public MateriaPrimaResponseDom atualizarQtdeFalhasRestantes(Long id, MateriaPrimaResponseDom materiaprima) throws CrudException {

        Optional<MateriaPrima> result = materiaPrimaRepository.findById(id);
        if (result.isPresent()) {
            MateriaPrima materiaPrimaEntidade = result.get();
            
            materiaPrimaEntidade.setQtdeMaterialRestante(materiaprima.getQtdeMaterialRestante());
            materiaPrimaEntidade.setQtdeMaterialFalhas(materiaprima.getQtdeMaterialFalhas());
            MateriaPrima materiaPrimaAtualizada = materiaPrimaRepository.save(materiaPrimaEntidade);

            MateriaPrimaResponseDom materiaPrimaResponse = new  MateriaPrimaResponseDom();
            materiaPrimaResponse.setQtdeMaterialFalhas(materiaPrimaAtualizada.getQtdeMaterialFalhas());
            materiaPrimaResponse.setQtdeMaterialRestante(materiaPrimaAtualizada.getQtdeMaterialRestante());

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
