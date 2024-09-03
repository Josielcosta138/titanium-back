package com.br.titanium.useCases;

import com.br.titanium.entitys.Cidade;
import com.br.titanium.entitys.Cliente;
import com.br.titanium.entitys.Endereco;
import com.br.titanium.repositorys.CidadeRepository;
import com.br.titanium.repositorys.ClienteRepository;
import com.br.titanium.repositorys.EnderecoRepository;
import com.br.titanium.useCases.endereco.domains.EnderecoRequestDom;
import com.br.titanium.useCases.endereco.domains.EnderecoResponseDom;
import com.br.titanium.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CidadeRepository cidadeRepository;

    public List<EnderecoResponseDom> carregarEndereco(){

        List<Endereco> resultadoDeEnderecosBanco = enderecoRepository.findAll();

            List<EnderecoResponseDom> listaDeEnderecos = new ArrayList<>();

        for (Endereco dadoResultado: resultadoDeEnderecosBanco) {
            EnderecoResponseDom aux = new EnderecoResponseDom();
            aux.setId(dadoResultado.getId());
            aux.setRua(dadoResultado.getRua());
            aux.setBairro(dadoResultado.getBairro());
            aux.setCidadeId(dadoResultado.getCidade().getId());
            aux.setClienteId(dadoResultado.getCliente().getId());
            aux.setClient(dadoResultado.getCliente());
            aux.setCidades(dadoResultado.getCidade());

            listaDeEnderecos.add(aux);
        }

        return listaDeEnderecos;
    }


    public EnderecoResponseDom carregarEnderecosById(Long id){
        Optional<Endereco> resultado = enderecoRepository.findById(id);

        if (resultado.isPresent()){
            Endereco enderecos = resultado.get();

            EnderecoResponseDom responseDOM = new EnderecoResponseDom();
            responseDOM.setId(enderecos.getId());
            responseDOM.setRua(enderecos.getRua());
            responseDOM.setBairro(enderecos.getBairro());
            responseDOM.setCidadeId(enderecos.getCidade().getId());
            responseDOM.setClienteId(enderecos.getCliente().getId());
            responseDOM.setClient(enderecos.getCliente());
            responseDOM.setCidades(enderecos.getCidade());
            return responseDOM;
        }
        return null;
    }

    public List<EnderecoResponseDom> carregarEnderecosByIdCliente(Long id){
        List<Endereco> resultadoDeEnderecosPostamn = enderecoRepository.findByClienteId(id);
        List<EnderecoResponseDom> listaDeEnderecos = new ArrayList<>();

        for (Endereco dadoResultado: resultadoDeEnderecosPostamn) {

            EnderecoResponseDom aux = new EnderecoResponseDom();
            aux.setId(dadoResultado.getId());
            aux.setRua(dadoResultado.getRua());
            aux.setBairro(dadoResultado.getBairro());
            aux.setCidadeId(dadoResultado.getCidade().getId());

            listaDeEnderecos.add(aux);
        }
        return listaDeEnderecos;
    }


    public EnderecoResponseDom criarEndereco(EnderecoRequestDom endereco) throws CrudException {

        List<String>mensagens = this.validarEndereco(endereco);
            if (!mensagens.isEmpty()){
                throw new CrudException(mensagens);
            }

        Optional<Cliente> resultadoIdCliente = clienteRepository.findById(endereco.getIdCliente());
        Optional<Cidade> resultadoIdCidade = cidadeRepository.findById(endereco.getIdCidade());


        Endereco enderecosEntidades = new Endereco();
                enderecosEntidades.setBairro(endereco.getBairro());
                enderecosEntidades.setRua(endereco.getRua());
                enderecosEntidades.setCidade(resultadoIdCidade.get());
                enderecosEntidades.setCliente(resultadoIdCliente.get());

            Endereco resultado = enderecoRepository.save(enderecosEntidades);

            EnderecoResponseDom responseDOM = new EnderecoResponseDom();
                responseDOM.setId(resultado.getId());
                responseDOM.setBairro(resultado.getBairro());
                responseDOM.setRua(resultado.getRua());
                responseDOM.setClienteId(resultado.getCliente().getId());
                responseDOM.setCidadeId(resultado.getCidade().getId());

            return responseDOM;
    }



    public EnderecoResponseDom atualizarEndereco(Long id, EnderecoRequestDom endereco) throws CrudException{

        List<String>mensagens = this.validarEndereco(endereco);
        if (!mensagens.isEmpty()){
            throw new CrudException(mensagens);
        }

        Optional<Cliente> resultadoIdCliente = clienteRepository.findById(endereco.getIdCliente());
        Optional<Cidade> resultadoIdCidade = cidadeRepository.findById(endereco.getIdCidade());


            if (resultadoIdCliente.isPresent() && resultadoIdCidade.isPresent()) {

                Optional<Endereco> resultado = enderecoRepository.findById(id).map(record -> {
                    record.setRua(endereco.getRua());
                    record.setBairro(endereco.getBairro());
                    record.setCliente(resultadoIdCliente.get());
                    record.setCidade(resultadoIdCidade.get());

                    return enderecoRepository.save(record);
                });

                if (resultado.isPresent()){
                    Endereco enderecosEntidades = resultado.get();

                    EnderecoResponseDom responseDOM = new EnderecoResponseDom();
                    responseDOM.setId(enderecosEntidades.getId());
                    responseDOM.setRua(enderecosEntidades.getRua());
                    responseDOM.setBairro(enderecosEntidades.getBairro());
                    responseDOM.setCidadeId(enderecosEntidades.getCidade().getId());
                    responseDOM.setClienteId(enderecosEntidades.getCliente().getId());

                    return responseDOM;
                }
            } else {
                throw new RuntimeException("Cliente não encontrado pelo ID fornecido: " + endereco.getIdCliente());
            }
            return null;


    }

    public void excluirEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }







    private List<String> validarEndereco(EnderecoRequestDom endereco) {
        List<String> mensagens = new ArrayList<>();

        if (endereco.getBairro() == null || endereco.getBairro().equals("")) {
            mensagens.add("bairro não informado");
        }
        if (endereco.getRua() == null || endereco.getRua().equals("")) {
            mensagens.add("rua não informado");
        }
        return mensagens;

    }

}
