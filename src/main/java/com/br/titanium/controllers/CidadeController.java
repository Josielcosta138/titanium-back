package com.br.titanium.controllers;


import com.br.titanium.useCases.services.CidadeService;
import com.br.titanium.useCases.domains.CidadeRequestDom;
import com.br.titanium.useCases.domains.CidadeResponseDom;
import com.br.titanium.utils.CrudException;
import com.br.titanium.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/titanium/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping("/carregar")
    public ResponseEntity<List<CidadeResponseDom>> carregarEnderecos(){
        try {
            List<CidadeResponseDom> response = cidadeService.carregarCidades();
            int status = 200;
            if (response.isEmpty()){
                status = 204;
            }
            return ResponseEntity
                    .status(status)
                    .body(response);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }



    @PostMapping("/criarCidade")
    public ResponseEntity<?> criarCidade(@RequestBody CidadeResponseDom cidade){
        try {
            CidadeResponseDom responseDom = cidadeService.criarCidade(cidade);
            return ResponseEntity.status(201).body(responseDom);
        }
        catch (CrudException es){
            es.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMap(es.getMessage()));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMap("Erro não mapeado"+ e.getMessage()));
        }
    }



    @PutMapping("/atualizarCidade/{id}")
    public ResponseEntity<CidadeResponseDom> atualizarCidade(@PathVariable Long id, @RequestBody CidadeRequestDom cidade){
        try {
            CidadeResponseDom responseDOM = cidadeService.atualizarCidade(id, cidade);
            if (responseDOM == null){
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(responseDOM);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }


}
