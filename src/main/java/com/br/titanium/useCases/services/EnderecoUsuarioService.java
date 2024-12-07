package com.br.titanium.useCases.services;

import com.br.titanium.entitys.*;
import com.br.titanium.repositorys.*;
import com.br.titanium.useCases.domains.EnderecoUsuarioRequestDom;
import com.br.titanium.useCases.domains.EnderecoUsuarioResponseDom;
import com.br.titanium.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EnderecoUsuarioService {

    @Autowired
    private EnderecoUsuarioRepository enderecoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CidadeUsuarioRepository cidadeRepository;

    public List<EnderecoUsuarioResponseDom> carregarEndereco(){

        List<EnderecoUsuario> resultadoDeEnderecosBanco = enderecoRepository.findAll();

            List<EnderecoUsuarioResponseDom> listaDeEnderecos = new ArrayList<>();

        for (EnderecoUsuario dadoResultado: resultadoDeEnderecosBanco) {
            EnderecoUsuarioResponseDom aux = new EnderecoUsuarioResponseDom();
            aux.setId(dadoResultado.getId());
            aux.setRua(dadoResultado.getRua());
            aux.setBairro(dadoResultado.getBairro());
            aux.setCidadeId(dadoResultado.getCidade().getId());
            aux.setUsuarioId(dadoResultado.getUsuario().getId());
            aux.setUsuario(dadoResultado.getUsuario());
            aux.setCidades(dadoResultado.getCidade());

            listaDeEnderecos.add(aux);
        }

        return listaDeEnderecos;
    }


    public EnderecoUsuarioResponseDom carregarEnderecosById(Long id){
        Optional<EnderecoUsuario> resultado = enderecoRepository.findById(id);

        if (resultado.isPresent()){
            EnderecoUsuario enderecos = resultado.get();

            EnderecoUsuarioResponseDom responseDOM = new EnderecoUsuarioResponseDom();
            responseDOM.setId(enderecos.getId());
            responseDOM.setRua(enderecos.getRua());
            responseDOM.setBairro(enderecos.getBairro());
            responseDOM.setCidadeId(enderecos.getCidade().getId());
            responseDOM.setUsuarioId(enderecos.getUsuario().getId());
            responseDOM.setUsuario(enderecos.getUsuario());
            responseDOM.setCidades(enderecos.getCidade());
            return responseDOM;
        }
        return null;
    }

    public List<EnderecoUsuarioResponseDom> carregarEnderecosByIdCliente(Long id){
        List<EnderecoUsuario> resultadoDeEnderecosPostamn = enderecoRepository.findByUsuarioId(id);
        List<EnderecoUsuarioResponseDom> listaDeEnderecos = new ArrayList<>();

        for (EnderecoUsuario dadoResultado: resultadoDeEnderecosPostamn) {

            EnderecoUsuarioResponseDom aux = new EnderecoUsuarioResponseDom();
            aux.setId(dadoResultado.getId());
            aux.setRua(dadoResultado.getRua());
            aux.setBairro(dadoResultado.getBairro());
            aux.setCidadeId(dadoResultado.getCidade().getId());
            listaDeEnderecos.add(aux);
        }
        return listaDeEnderecos;
    }


    public EnderecoUsuarioResponseDom criarEndereco(EnderecoUsuarioRequestDom endereco) throws CrudException {

        List<String>mensagens = this.validarEndereco(endereco);
            if (!mensagens.isEmpty()){
                throw new CrudException(mensagens);
            }

        Optional<Usuario> resultadoIdCliente = usuarioRepository.findById(endereco.getIdUsuario());
        Optional<CidadeUsuario> resultadoIdCidade = cidadeRepository.findById(endereco.getIdCidade());


        EnderecoUsuario enderecosEntidades = new EnderecoUsuario();
                enderecosEntidades.setBairro(endereco.getBairro());
                enderecosEntidades.setRua(endereco.getRua());
                enderecosEntidades.setCidade(resultadoIdCidade.get());
                enderecosEntidades.setUsuario(resultadoIdCliente.get());

            EnderecoUsuario resultado = enderecoRepository.save(enderecosEntidades);

            EnderecoUsuarioResponseDom responseDOM = new EnderecoUsuarioResponseDom();
                responseDOM.setId(resultado.getId());
                responseDOM.setBairro(resultado.getBairro());
                responseDOM.setRua(resultado.getRua());
                responseDOM.setUsuarioId(resultado.getUsuario().getId());
                responseDOM.setCidadeId(resultado.getCidade().getId());

            return responseDOM;
    }



    public EnderecoUsuarioResponseDom atualizarEndereco(Long id, EnderecoUsuarioRequestDom endereco) throws CrudException{

        List<String>mensagens = this.validarEndereco(endereco);
        if (!mensagens.isEmpty()){
            throw new CrudException(mensagens);
        }

        Optional<Usuario> resultadoIdCliente = usuarioRepository.findById(endereco.getIdUsuario());
        Optional<CidadeUsuario> resultadoIdCidade = cidadeRepository.findById(endereco.getIdCidade());


            if (resultadoIdCliente.isPresent() && resultadoIdCidade.isPresent()) {

                Optional<EnderecoUsuario> resultado = enderecoRepository.findById(id).map(record -> {
                    record.setRua(endereco.getRua());
                    record.setBairro(endereco.getBairro());
                    record.setUsuario(resultadoIdCliente.get());
                    record.setCidade(resultadoIdCidade.get());

                    return enderecoRepository.save(record);
                });

                if (resultado.isPresent()){
                    EnderecoUsuario enderecosEntidades = resultado.get();

                    EnderecoUsuarioResponseDom responseDOM = new EnderecoUsuarioResponseDom();
                    responseDOM.setId(enderecosEntidades.getId());
                    responseDOM.setRua(enderecosEntidades.getRua());
                    responseDOM.setBairro(enderecosEntidades.getBairro());
                    responseDOM.setCidadeId(enderecosEntidades.getCidade().getId());
                    responseDOM.setUsuarioId(enderecosEntidades.getUsuario().getId());

                    return responseDOM;
                }
            } else {
                throw new RuntimeException("Cliente não encontrado pelo ID fornecido: " + endereco.getIdUsuario());
            }
            return null;


    }

    public void excluirEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

    private List<String> validarEndereco(EnderecoUsuarioRequestDom endereco) {
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
