package com.br.titanium.useCases;

import com.br.titanium.entitys.Cliente;
import com.br.titanium.repositorys.ClienteRepository;
import com.br.titanium.useCases.cliente.domains.ClienteResponseDom;
import com.br.titanium.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;


    public List<ClienteResponseDom> caregarCliente(){

        List<Cliente> listaDeClientes = clienteRepository.findAll();
        List<ClienteResponseDom> clientes = new ArrayList<>();

        for (Cliente resultadoClientes : listaDeClientes) {

            ClienteResponseDom aux = new ClienteResponseDom();
            aux.setId(resultadoClientes.getId());
            aux.setCnpj(resultadoClientes.getCnpj());
            aux.setNomeFantasia(resultadoClientes.getNomeFantasia());
            aux.setRazaoSocial( resultadoClientes.getRazaoSocial());
            aux.setEmail(resultadoClientes.getEmail());
            aux.setTelefone(resultadoClientes.getTelefone());

            clientes.add(aux);
        }

        return clientes;
    }



    public ClienteResponseDom carregarClienteById(Long id){
        Optional<Cliente> resultado = clienteRepository.findById(id);

        if (resultado.isPresent()){
            Cliente clientes = resultado.get();

            ClienteResponseDom responseDOM = new ClienteResponseDom();
            responseDOM.setId(clientes.getId());
            responseDOM.setCnpj(clientes.getCnpj());
            responseDOM.setEmail(clientes.getEmail());
            responseDOM.setNomeFantasia(clientes.getNomeFantasia());
            responseDOM.setRazaoSocial(clientes.getRazaoSocial());
            responseDOM.setTelefone(clientes.getTelefone());

            return responseDOM;
        }
        return null;
    }



    public ClienteResponseDom criarClientes(ClienteResponseDom cliente) throws CrudException {
        List<String> mensagens = this.validarCliente(cliente);
            if(!mensagens.isEmpty()) {
                throw new CrudException(mensagens);
            }

        Cliente clienteEntidades = new Cliente();
            clienteEntidades.setRazaoSocial(cliente.getRazaoSocial());
            clienteEntidades.setNomeFantasia(cliente.getNomeFantasia());
            clienteEntidades.setEmail(cliente.getEmail());
            clienteEntidades.setTelefone(cliente.getTelefone());
            clienteEntidades.setCnpj(cliente.getCnpj());
            Cliente resultado = clienteRepository.save(clienteEntidades);

        ClienteResponseDom responseDom = new ClienteResponseDom();
            responseDom.setId(resultado.getId());
            responseDom.setRazaoSocial(resultado.getRazaoSocial());
            responseDom.setNomeFantasia(resultado.getNomeFantasia());
            responseDom.setEmail(resultado.getEmail());
            responseDom.setTelefone(resultado.getTelefone());
            responseDom.setCnpj(resultado.getCnpj());

        return responseDom;
    }



    public ClienteResponseDom atualizarCliente(Long id, ClienteResponseDom cliente){
        Optional<Cliente> resultado = clienteRepository.findById(id).map(record -> {
            record.setRazaoSocial(cliente.getRazaoSocial());
            record.setNomeFantasia(cliente.getNomeFantasia());
            record.setEmail(cliente.getEmail());
            record.setTelefone(cliente.getTelefone());
            record.setCnpj(cliente.getCnpj());

            return clienteRepository.save(record);
        });

        if (resultado.isPresent()){
            Cliente clientesEntidades = resultado.get();

            ClienteResponseDom responseDOM = new ClienteResponseDom();
            responseDOM.setId(clientesEntidades.getId());
            responseDOM.setRazaoSocial(clientesEntidades.getRazaoSocial());
            responseDOM.setNomeFantasia(clientesEntidades.getNomeFantasia());
            responseDOM.setEmail(clientesEntidades.getEmail());
            responseDOM.setTelefone(clientesEntidades.getTelefone());
            responseDOM.setCnpj(clientesEntidades.getCnpj());

            return responseDOM;
        }
        return null;
    }


    public void excluirCliente(Long id){
        clienteRepository.deleteById(id);
    }




    public List<String> validarCliente(ClienteResponseDom cliente){
        List<String> mensagens = new ArrayList<>();

        if (cliente.getCnpj() == null || cliente.getCnpj().equals("")) {
            mensagens.add("cnpj do cliente não informado");
        }

        if (cliente.getRazaoSocial() == null || cliente.getRazaoSocial().equals("")) {
            mensagens.add("nome do cliente não informado");
        }
        return mensagens;
    }
}
