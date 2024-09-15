package com.br.titanium.controllers;

import com.br.titanium.useCases.MateriaPrimaService;
import com.br.titanium.useCases.materiaPrima.domains.MateriaPrimaResponseDom;
import com.br.titanium.useCases.ordemCorte.domains.OrdemCorteResponseDom;
import com.br.titanium.utils.CrudException;
import com.br.titanium.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping ("/titanium/materiaprima")
public class MateriaPrimaController {

    @Autowired
    private MateriaPrimaService materiaPrimaService;

    @GetMapping("/carregar")
    public ResponseEntity<List<MateriaPrimaResponseDom>> carregarMateriaPrima(){
        try {
            List<MateriaPrimaResponseDom> response = materiaPrimaService.carregarMateriaPrima();
            int status = 200;
            if (response.isEmpty()){
                status = 204;
            }

            return ResponseEntity.status(status).body(response);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body(null);
        }
    }



    @PostMapping("/criarMateriaPrima")
    public ResponseEntity<?> criarMateriaPrima(@RequestBody MateriaPrimaResponseDom materiaPrima){
        try {
            MateriaPrimaResponseDom responseDom = materiaPrimaService.criarMateriaPrima(materiaPrima);
            return ResponseEntity.status(201).body(responseDom);

        } catch (CrudException es){
            es.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMap(es.getMessage()));

        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMap("Erro não mapeado" + e.getMessage()));
        }


    }
}
