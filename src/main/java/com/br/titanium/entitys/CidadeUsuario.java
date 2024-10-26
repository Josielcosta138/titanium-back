package com.br.titanium.entitys;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class CidadeUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, length = 2)
    private String uf;

    @OneToMany(mappedBy = "cidade")
    private List<EnderecoUsuario> enderecos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @JsonIgnore
    public List<EnderecoUsuario> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoUsuario> enderecos) {
        this.enderecos = enderecos;
    }
}
