package com.br.titanium.controllers;

import com.br.titanium.useCases.services.GradeService;
import com.br.titanium.useCases.domains.GradeResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping ("/titanium/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("carregar")
    public ResponseEntity<List<GradeResponseDom>> carregarGrade(){
        try{
            List<GradeResponseDom> response = gradeService.carregarGrade();
            int status = 200;

            if (response.isEmpty()){
                status = 204;
            }

            return ResponseEntity.status(status).body(response);

        } catch (Exception e){
            e.printStackTrace();

            return ResponseEntity.badRequest().body(null);
        }
    }
}
