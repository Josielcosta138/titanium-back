package com.br.titanium.controllers;

import com.br.titanium.useCases.OrdemCorteTamService;
import com.br.titanium.useCases.ordemCorteTam.domains.OrdemCorteTamRequesteDom;
import com.br.titanium.useCases.ordemCorteTam.domains.OrdemCorteTamResponseDom;
import com.br.titanium.utils.CrudException;
import com.br.titanium.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001")
@Controller
@RequestMapping("/titanium/ordemCorteTamanho")
public class OrdemCorteTamController {

    @Autowired
    private OrdemCorteTamService ordemCorteTamService;


    @CrossOrigin(origins = "http://localhost:3001")
    @PostMapping(value = "/criar", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<?> criarOrdemcorteTam(@RequestBody OrdemCorteTamRequesteDom ordemDeCortetAM){
        try {
            List<OrdemCorteTamResponseDom> responseDOM = ordemCorteTamService.criarOrdemCorteTam(ordemDeCortetAM);
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
