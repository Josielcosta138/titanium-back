package com.br.titanium.useCases;


import com.br.titanium.entitys.Cidade;
import com.br.titanium.entitys.Endereco;
import com.br.titanium.entitys.OrdemDeCorte;
import com.br.titanium.entitys.OrdemServico;
import com.br.titanium.repositorys.EnderecoRepository;
import com.br.titanium.repositorys.OrdemDeCorteRepository;
import com.br.titanium.repositorys.OrdemServicoRepository;
import com.br.titanium.useCases.OrdemServico.domains.OrdemServicoResponseDom;
import com.br.titanium.useCases.endereco.domains.EnderecoResponseDom;
import com.br.titanium.useCases.ordemCorte.domains.OrdemCorteResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
