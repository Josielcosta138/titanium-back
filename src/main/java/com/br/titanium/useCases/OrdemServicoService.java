package com.br.titanium.useCases;


import com.br.titanium.entitys.*;
import com.br.titanium.repositorys.ClienteRepository;
import com.br.titanium.repositorys.EnderecoRepository;
import com.br.titanium.repositorys.OrdemDeCorteRepository;
import com.br.titanium.repositorys.OrdemServicoRepository;
import com.br.titanium.useCases.OrdemServico.domains.OrdemServicoResponseDom;
import com.br.titanium.useCases.OrdemServico.domains.OrdemServicoResquestDom;
import com.br.titanium.useCases.endereco.domains.EnderecoRequestDom;
import com.br.titanium.useCases.endereco.domains.EnderecoResponseDom;
import com.br.titanium.useCases.ordemCorte.domains.OrdemCorteRequestoDom;
import com.br.titanium.useCases.ordemCorte.domains.OrdemCorteResponseDom;
import com.br.titanium.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    @Autowired
    private OrdemDeCorteRepository ordemDeCorteRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    ClienteRepository clienteRepository;


    public List<OrdemServicoResponseDom> carregarOS() {

        List<OrdemServico> result = ordemServicoRepository.findAll();
        List<OrdemServicoResponseDom> listaDeOrdemServico = new ArrayList<>();

            for (OrdemServico ordemServico : result){
                OrdemServicoResponseDom auxResponse = new OrdemServicoResponseDom();

                auxResponse.setId(ordemServico.getId());
                auxResponse.setValorTotal(ordemServico.getValorTotal());
                auxResponse.setDataEntrada(ordemServico.getDataEntrada());
                auxResponse.setDataEntrega(ordemServico.getDataEntrega());
                auxResponse.setQtdeMaterialFalhas(ordemServico.getQtdeMaterialFalhas());
                auxResponse.setQtdeMaterialRestante(ordemServico.getQtdeMaterialRestante());
                auxResponse.setQtdePecas(ordemServico.getQtdePecas());
                auxResponse.setQtdeRolos(ordemServico.getQtdeRolos());
                auxResponse.setValorPorPeca(ordemServico.getValorPorPeca());
                auxResponse.setCliente(ordemServico.getCliente());
                auxResponse.setCodReferenciaOs(ordemServico.getCodReferenciaOs());
                auxResponse.setModelo(ordemServico.getModelo());
                auxResponse.setNumeorNotaFiscal(ordemServico.getNumeorNotaFiscal());
                auxResponse.setCampoObservacao(ordemServico.getCampoObservacao());
                auxResponse.setStatus(ordemServico.getStatus());



                List<OrdemDeCorte> ordensDeCorte = ordemDeCorteRepository.findByOrdemServicoId(ordemServico.getId());
                List<OrdemCorteResponseDom> ListaMateriaPrima = new ArrayList<>();

                for (OrdemDeCorte ordemDeCorte : ordensDeCorte) {
                    OrdemCorteResponseDom ordemDeCorteResponseDom = new OrdemCorteResponseDom();
                    ordemDeCorteResponseDom.setId(ordemDeCorte.getId());
                    ordemDeCorteResponseDom.setMateriaPrima(ordemDeCorte.getMateriaPrima());

                    ListaMateriaPrima.add(ordemDeCorteResponseDom);
                }
                auxResponse.setOrdensDeCorte(ListaMateriaPrima);



                List<Endereco> resultadoDeEnderecosPostamn = enderecoRepository.findByClienteId(ordemServico.getCliente().getId());
                List<EnderecoResponseDom> listaDeEnderecos = new ArrayList<>();

                for (Endereco dadoResultado: resultadoDeEnderecosPostamn) {
                    EnderecoResponseDom aux = new EnderecoResponseDom();
                    aux.setId(dadoResultado.getId());
                    aux.setRua(dadoResultado.getRua());
                    aux.setBairro(dadoResultado.getBairro());
                    aux.setCidadeId(dadoResultado.getCidade().getId());

                    List<Endereco> resultadoCidadesPorEndereco = enderecoRepository.findByCidadeId(dadoResultado.getCidade().getId());
                    aux.setCidadeResponseDomList(resultadoCidadesPorEndereco);

                    listaDeEnderecos.add(aux);
                }
                auxResponse.setEnderecosCliemte(listaDeEnderecos);

                listaDeOrdemServico.add(auxResponse);
            }
        return listaDeOrdemServico;
    }



    public OrdemServicoResponseDom carregarOSById(Long id) {
        Optional<OrdemServico> result = ordemServicoRepository.findById(id);

        if (result.isPresent()) {
            OrdemServico ordensServicoResponse = result.get();

            OrdemServicoResponseDom ordemServicoResponseDom = new OrdemServicoResponseDom();

            ordemServicoResponseDom.setId(ordensServicoResponse.getId());
            ordemServicoResponseDom.setValorTotal(ordensServicoResponse.getValorTotal());
            ordemServicoResponseDom.setDataEntrada(ordensServicoResponse.getDataEntrada());
            ordemServicoResponseDom.setDataEntrega(ordensServicoResponse.getDataEntrega());
            ordemServicoResponseDom.setQtdeMaterialFalhas(ordensServicoResponse.getQtdeMaterialFalhas());
            ordemServicoResponseDom.setQtdeMaterialRestante(ordensServicoResponse.getQtdeMaterialRestante());
            ordemServicoResponseDom.setQtdePecas(ordensServicoResponse.getQtdePecas());
            ordemServicoResponseDom.setQtdeRolos(ordensServicoResponse.getQtdeRolos());
            ordemServicoResponseDom.setValorPorPeca(ordensServicoResponse.getValorPorPeca());
            ordemServicoResponseDom.setCliente(ordensServicoResponse.getCliente());
            ordemServicoResponseDom.setCodReferenciaOs(ordensServicoResponse.getCodReferenciaOs());
            ordemServicoResponseDom.setModelo(ordensServicoResponse.getModelo());
            ordemServicoResponseDom.setNumeorNotaFiscal(ordensServicoResponse.getNumeorNotaFiscal());
            ordemServicoResponseDom.setCampoObservacao(ordensServicoResponse.getCampoObservacao());
            ordemServicoResponseDom.setStatus(ordensServicoResponse.getStatus());

            return ordemServicoResponseDom;
        }

        return null;
    }

    public OrdemServicoResponseDom criarOrdemServico(OrdemServicoResquestDom ordemServico) throws CrudException {

        /* List<String>mensagens = this.validarOrdemServico(ordemServico);
        if (!mensagens.isEmpty()){
            throw new CrudException(mensagens);
        } */

        Optional<Cliente> clienteId = clienteRepository.findById(ordemServico.getClienteId());

        OrdemServico ordemServicoEntidade = new OrdemServico();
        ordemServicoEntidade.setCampoObservacao(ordemServico.getCampoObservacao());
        ordemServicoEntidade.setQtdeRolos(ordemServico.getQtdeRolos());
        ordemServicoEntidade.setDataEntrada(ordemServico.getDataEntrada());
        ordemServicoEntidade.setDataEntrega(ordemServico.getDataEntrega());
        ordemServicoEntidade.setQtdePecas(ordemServico.getQtdePecas());
        ordemServicoEntidade.setQtdeMaterialFalhas(ordemServico.getQtdeMaterialFalhas());
        ordemServicoEntidade.setQtdeMaterialRestante(ordemServico.getQtdeMaterialRestante());
        ordemServicoEntidade.setValorPorPeca(ordemServico.getValorPorPeca());
        ordemServicoEntidade.setValorTotal(ordemServico.getValorTotal());
        ordemServicoEntidade.setCodReferenciaOs(ordemServico.getCodReferenciaOs());
        ordemServicoEntidade.setModelo(ordemServico.getModelo());
        ordemServicoEntidade.setNumeorNotaFiscal(ordemServico.getNumeorNotaFiscal());
        ordemServicoEntidade.setCampoObservacao(ordemServico.getCampoObservacao());
        ordemServicoEntidade.setStatus(ordemServico.getStatus());
        ordemServicoEntidade.setCliente(clienteId.get());

        OrdemServico respoonseEntidade = ordemServicoRepository.save(ordemServicoEntidade);

        OrdemServicoResponseDom ordemServicoResponseDom = new OrdemServicoResponseDom();
        ordemServicoResponseDom.setId(ordemServicoEntidade.getId());
        ordemServicoResponseDom.setQtdeRolos(ordemServicoEntidade.getQtdeRolos());
        ordemServicoResponseDom.setDataEntrada(ordemServicoEntidade.getDataEntrada());
        ordemServicoResponseDom.setDataEntrega(ordemServicoEntidade.getDataEntrega());
        ordemServicoResponseDom.setQtdePecas(ordemServicoEntidade.getQtdePecas());
        ordemServicoResponseDom.setQtdeMaterialFalhas(ordemServicoEntidade.getQtdeMaterialFalhas());
        ordemServicoResponseDom.setQtdeMaterialRestante(ordemServicoEntidade.getQtdeMaterialRestante());
        ordemServicoResponseDom.setValorPorPeca(ordemServicoEntidade.getValorPorPeca());
        ordemServicoResponseDom.setValorTotal(ordemServicoEntidade.getValorTotal());
        ordemServicoResponseDom.setCodReferenciaOs(ordemServicoEntidade.getCodReferenciaOs());
        ordemServicoResponseDom.setModelo(ordemServicoEntidade.getModelo());
        ordemServicoResponseDom.setNumeorNotaFiscal(ordemServicoEntidade.getNumeorNotaFiscal());
        ordemServicoResponseDom.setCampoObservacao(ordemServicoEntidade.getCampoObservacao());
        ordemServicoResponseDom.setStatus(ordemServicoEntidade.getStatus());
        ordemServicoResponseDom.setClienteId(ordemServicoEntidade.getCliente().getId());

        return ordemServicoResponseDom;
    }







    private List<String> validarOrdemServico(OrdemServicoResquestDom ordemServico) {
        List<String> mensagens = new ArrayList<>();

        if (ordemServico.getQtdeRolos() == null || ordemServico.getQtdeRolos() <= 0) {
            mensagens.add("Quantidade de rolos deve ser informada e maior que zero.");
        }

        if (ordemServico.getDataEntrada() == null) {
            mensagens.add("Data de entrada não informada.");
        }

        if (ordemServico.getDataEntrega() == null) {
            mensagens.add("Data de entrega não informada.");
        }

        if (ordemServico.getQtdePecas() == null || ordemServico.getQtdePecas() <= 0) {
            mensagens.add("Quantidade de peças deve ser informada e maior que zero.");
        }

        if (ordemServico.getQtdeMaterialFalhas() == null || ordemServico.getQtdeMaterialFalhas() < 0) {
            mensagens.add("Quantidade de material com falhas deve ser informada e não pode ser negativa.");
        }

        if (ordemServico.getQtdeMaterialRestante() == null || ordemServico.getQtdeMaterialRestante() < 0) {
            mensagens.add("Quantidade de material restante deve ser informada e não pode ser negativa.");
        }

        if (ordemServico.getValorPorPeca() == null || ordemServico.getValorPorPeca().compareTo(BigDecimal.ZERO) <= 0) {
            mensagens.add("Valor por peça deve ser informado e maior que zero.");
        }

        if (ordemServico.getValorTotal() == null || ordemServico.getValorTotal().compareTo(BigDecimal.ZERO) <= 0) {
            mensagens.add("Valor total deve ser informado e maior que zero.");
        }

        if (ordemServico.getCodReferenciaOs() == null || ordemServico.getCodReferenciaOs().trim().isEmpty()) {
            mensagens.add("Código de referência da OS deve ser informado.");
        }

        if (ordemServico.getModelo() == null || ordemServico.getModelo().trim().isEmpty()) {
            mensagens.add("Modelo deve ser informado.");
        }

        if (ordemServico.getNumeorNotaFiscal() != null && ordemServico.getNumeorNotaFiscal() <= 0) {
            mensagens.add("Número da nota fiscal, se informado, deve ser maior que zero.");
        }

        if (ordemServico.getStatus() == null) {
            mensagens.add("Status da OS deve ser informado.");
        }

        if (ordemServico.getClienteId() == null || ordemServico.getClienteId() <= 0) {
            mensagens.add("ID do cliente deve ser informado e maior que zero.");
        }

        return mensagens;
    }


}
