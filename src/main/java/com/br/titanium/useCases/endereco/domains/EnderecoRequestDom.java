package com.br.titanium.useCases.endereco.domains;

import com.br.titanium.entitys.Cidade;
import com.br.titanium.entitys.Cliente;

public class EnderecoRequestDom {

    private String rua;
    private String bairro;
    private Long clienteId;
    private Long cidadeId;

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
