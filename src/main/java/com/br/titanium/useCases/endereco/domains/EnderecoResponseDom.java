package com.br.titanium.useCases.endereco.domains;

import com.br.titanium.entitys.Cidade;
import com.br.titanium.entitys.Cliente;
import com.br.titanium.entitys.Endereco;

public class EnderecoResponseDom {

    private Long id;
    private String rua;
    private String bairro;
    private Long clienteId;
    private Long cidadeId;
    private Cliente client;
    private Cidade cidades;

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }

    public Cidade getCidades() {
        return cidades;
    }

    public void setCidades(Cidade cidades) {
        this.cidades = cidades;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }
}
