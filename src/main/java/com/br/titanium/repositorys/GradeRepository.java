package com.br.titanium.repositorys;

import com.br.titanium.entitys.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long> {
}
