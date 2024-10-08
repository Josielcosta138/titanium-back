package com.br.titanium.repositorys;

import com.br.titanium.entitys.OrdemCorteTamanho;
import com.br.titanium.entitys.OrdemDeCorte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemCorteTamRepository extends JpaRepository<OrdemCorteTamanho, Long> {

}