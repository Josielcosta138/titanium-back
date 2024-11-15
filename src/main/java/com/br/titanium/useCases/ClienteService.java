package com.br.titanium.useCases;

import com.br.titanium.entitys.Cliente;
import com.br.titanium.entitys.OrdemDeCorte;
import com.br.titanium.entitys.OrdemServico;
import com.br.titanium.repositorys.ClienteRepository;
import com.br.titanium.useCases.OrdemServico.domains.OrdemServicoResponseDom;
import com.br.titanium.useCases.cliente.domains.ClienteResponseDom;
import com.br.titanium.useCases.endereco.domains.EnderecoResponseDom;
import com.br.titanium.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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
            aux.setFantasia(resultadoClientes.getNomeFantasia());
            aux.setNome( resultadoClientes.getRazaoSocial());
            aux.setEmail(resultadoClientes.getEmail());
            aux.setTelefone(resultadoClientes.getTelefone());

            clientes.add(aux);
        }
        return clientes;
    }





    public int carregarTotalDeClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        int qtdeTotal = clientes.size();
        return qtdeTotal;
    }




    public ClienteResponseDom carregarClienteById(Long id){
        Optional<Cliente> resultado = clienteRepository.findById(id);

        if (resultado.isPresent()){
            Cliente clientes = resultado.get();

            ClienteResponseDom responseDOM = new ClienteResponseDom();
            responseDOM.setId(clientes.getId());
            responseDOM.setCnpj(clientes.getCnpj());
            responseDOM.setEmail(clientes.getEmail());
            responseDOM.setFantasia(clientes.getNomeFantasia());
            responseDOM.setNome(clientes.getRazaoSocial());
            responseDOM.setTelefone(clientes.getTelefone());

            return responseDOM;
        }
        return null;
    }


    private static final String API_URL = "https://www.receitaws.com.br/v1/cnpj/";
    public ClienteResponseDom carregarClienteApi(String cnpj) {
        try {
            // Realiza a chamada à API externa
            RestTemplate restTemplate = new RestTemplate();
            String url = API_URL + cnpj;
            ResponseEntity<ClienteResponseDom> response = restTemplate.getForEntity(url, ClienteResponseDom.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                ClienteResponseDom apiResponse = response.getBody();

                    ClienteResponseDom responseDOM = new ClienteResponseDom();
                    responseDOM.setCnpj(apiResponse.getCnpj());
                    responseDOM.setNome(apiResponse.getNome());
                    responseDOM.setFantasia(apiResponse.getFantasia());
                    responseDOM.setEmail(apiResponse.getEmail());
                    responseDOM.setTelefone(apiResponse.getTelefone());
                    responseDOM.setLogradouro(apiResponse.getLogradouro());
                    responseDOM.setBairro(apiResponse.getBairro());
                    responseDOM.setMunicipio(apiResponse.getMunicipio());
                    responseDOM.setUf(apiResponse.getUf());
                    responseDOM.setCep(apiResponse.getCep());


                return responseDOM;
            }

        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return null;
    }



    public ClienteResponseDom criarClientes(ClienteResponseDom cliente) throws CrudException {
        List<String> mensagens = this.validarCliente(cliente);
            if(!mensagens.isEmpty()) {
                throw new CrudException(mensagens);
            }

        Cliente clienteEntidades = new Cliente();
            clienteEntidades.setRazaoSocial(cliente.getNome());
            clienteEntidades.setNomeFantasia(cliente.getFantasia());
            clienteEntidades.setEmail(cliente.getEmail());
            clienteEntidades.setTelefone(cliente.getTelefone());
            clienteEntidades.setCnpj(cliente.getCnpj());
            Cliente resultado = clienteRepository.save(clienteEntidades);

        ClienteResponseDom responseDom = new ClienteResponseDom();
            responseDom.setId(resultado.getId());
            responseDom.setNome(resultado.getRazaoSocial());
            responseDom.setFantasia(resultado.getNomeFantasia());
            responseDom.setEmail(resultado.getEmail());
            responseDom.setTelefone(resultado.getTelefone());
            responseDom.setCnpj(resultado.getCnpj());

        return responseDom;
    }



    public ClienteResponseDom atualizarCliente(Long id, ClienteResponseDom cliente)throws CrudException{

        List<String>mensagens = this.validarCliente(cliente);
        if (!mensagens.isEmpty()){
            throw new CrudException(mensagens);
        }


        Optional<Cliente> resultado = clienteRepository.findById(id).map(record -> {
            record.setRazaoSocial(cliente.getNome());
            record.setNomeFantasia(cliente.getFantasia());
            record.setEmail(cliente.getEmail());
            record.setTelefone(cliente.getTelefone());
            record.setCnpj(cliente.getCnpj());

            return clienteRepository.save(record);
        });

        if (resultado.isPresent()){
            Cliente clientesEntidades = resultado.get();

            ClienteResponseDom responseDOM = new ClienteResponseDom();
            responseDOM.setId(clientesEntidades.getId());
            responseDOM.setNome(clientesEntidades.getRazaoSocial());
            responseDOM.setFantasia(clientesEntidades.getNomeFantasia());
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

        if (cliente.getFantasia() == null || cliente.getFantasia().equals("")) {
            mensagens.add("nome do cliente não informado");
        }
        return mensagens;
    }


}
