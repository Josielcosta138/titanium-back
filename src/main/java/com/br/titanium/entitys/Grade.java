package com.br.titanium.entitys;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String identificacaoGrade;

    @OneToMany(mappedBy = "tamanhoGrade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GradeMateriaPrima> gradeMateriaPrimaList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacaoGrade() {
        return identificacaoGrade;
    }

    public void setIdentificacaoGrade(String identificacaoGrade) {
        this.identificacaoGrade = identificacaoGrade;
    }


    public List<GradeMateriaPrima> getGradeMateriaPrimaList() {
        return gradeMateriaPrimaList;
    }

    public void setGradeMateriaPrimaList(List<GradeMateriaPrima> gradeMateriaPrimaList) {
        this.gradeMateriaPrimaList = gradeMateriaPrimaList;
    }
}
