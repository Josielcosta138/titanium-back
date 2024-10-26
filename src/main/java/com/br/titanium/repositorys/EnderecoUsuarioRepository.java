package com.br.titanium.repositorys;

import com.br.titanium.entitys.Endereco;
import com.br.titanium.entitys.EnderecoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoUsuarioRepository extends JpaRepository<EnderecoUsuario, Long> {
    List<EnderecoUsuario> findByUsuarioId(Long usuarioId);
    List<EnderecoUsuario> findByCidadeId(Long cidadeId);

}
