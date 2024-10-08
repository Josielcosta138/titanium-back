package com.br.titanium.controllers;

import com.br.titanium.useCases.OrdemCorteService;
import com.br.titanium.useCases.endereco.domains.EnderecoRequestDom;
import com.br.titanium.useCases.endereco.domains.EnderecoResponseDom;
import com.br.titanium.useCases.ordemCorte.domains.OrdemCorteRequestoDom;
import com.br.titanium.useCases.ordemCorte.domains.OrdemCorteResponseDom;
import com.br.titanium.utils.CrudException;
import com.br.titanium.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    @CrossOrigin(origins = "http://localhost:3001")
    @GetMapping("/carregarTotalDeOrdemCorte")
    public ResponseEntity<Integer>carregarTotalDeOrdens(){
        try {
            int response = ordemCorteService.carregarTotalDeOrdens();
            int status = 200;
            if (response < 0){
                status = 204;
            }
            return ResponseEntity.status(status).body(response);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }



    @CrossOrigin(origins = "http://localhost:3001")
    @PostMapping(value = "/criar", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<?> criarOrdemcorte(@RequestBody OrdemCorteRequestoDom ordemDeCorte){
        try {
            OrdemCorteResponseDom responseDOM = ordemCorteService.criarOrdemCorte(ordemDeCorte);
            return ResponseEntity.status(201).body(responseDOM);
        }
        catch (CrudException es){
            es.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMap(es.getMessages()));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMap("Erro não mapeado"+ e.getMessage()));
        }
    }



}
