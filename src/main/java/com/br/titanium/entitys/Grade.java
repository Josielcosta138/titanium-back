package com.br.titanium.entitys;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String identificacaograde;

    @OneToMany(mappedBy = "tamanhoGrade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GradeMateriaPrima> gradeMateriaPrimaList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacaograde() {
        return identificacaograde;
    }

    public void setIdentificacaograde(String identificacaograde) {
        this.identificacaograde = identificacaograde;
    }


    public List<GradeMateriaPrima> getGradeMateriaPrimaList() {
        return gradeMateriaPrimaList;
    }

    public void setGradeMateriaPrimaList(List<GradeMateriaPrima> gradeMateriaPrimaList) {
        this.gradeMateriaPrimaList = gradeMateriaPrimaList;
    }
}
