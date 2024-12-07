package com.br.titanium.useCases.domains;

import com.br.titanium.entitys.*;

import java.util.List;

public class EnderecoUsuarioResponseDom {

    private Long id;
    private String rua;
    private String bairro;
    private Long usuarioId;
    private Long cidadeId;
    private Usuario usuario;
    private CidadeUsuario cidades;

    private List<Endereco> cidadeResponseDomList;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CidadeUsuario getCidades() {
        return cidades;
    }

    public void setCidades(CidadeUsuario cidades) {
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




    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }

    public List<Endereco> getCidadeResponseDomList() {
        return cidadeResponseDomList;
    }

    public void setCidadeResponseDomList(List<Endereco> cidadeResponseDomList) {
        this.cidadeResponseDomList = cidadeResponseDomList;
    }
}
