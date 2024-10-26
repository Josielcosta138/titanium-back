package com.br.titanium.repositorys;

import com.br.titanium.entitys.Cidade;
import com.br.titanium.entitys.CidadeUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeUsuarioRepository extends JpaRepository<CidadeUsuario, Long> {

}

