package com.br.titanium.controllers;

import com.br.titanium.useCases.OrdemCorteService;
import com.br.titanium.useCases.ordemCorte.domains.OrdemCorteResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001")
@Controller
@RequestMapping("/titanium/ordemCorte")
public class OrdemCorteController {

    @Autowired
    private OrdemCorteService ordemCorteService;


    @CrossOrigin(origins = "http://localhost:3001")
    @GetMapping("/carregarMateriaPrimaByOrdemCorte/{id}")
    public ResponseEntity<List<OrdemCorteResponseDom>> carregarEnderecos(@PathVariable Long id){
        try {
            List<OrdemCorteResponseDom> response = ordemCorteService.carregarMateriaPrimaByOS(id);
            int status = 200;
            if (response.isEmpty()){
                status = 204;
            }
            return ResponseEntity.status(status).body(response);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

}
