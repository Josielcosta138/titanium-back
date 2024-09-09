package com.br.titanium.useCases.grade.domains;

public class GradeResponseDom {

    private long id;
    private String identificacaoGrade;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdentificacaoGrade() {
        return identificacaoGrade;
    }

    public void setIdentificacaoGrade(String identificacaoGrade) {
        this.identificacaoGrade = identificacaoGrade;
    }
}
