package com.br.titanium.repositorys;

import com.br.titanium.entitys.Cidade;
import com.br.titanium.entitys.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByClienteId(Long clienteId);
    List<Endereco> findByCidadeId(Long cidadeId);

}
