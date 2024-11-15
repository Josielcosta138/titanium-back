package com.br.titanium.controllers;


import com.br.titanium.useCases.CidadeService;
import com.br.titanium.useCases.CidadeUsuarioService;
import com.br.titanium.useCases.cidade.domains.CidadeRequestDom;
import com.br.titanium.useCases.cidade.domains.CidadeResponseDom;
import com.br.titanium.useCases.cidadeUsuario.domains.CidadeUsuarioRequestDom;
import com.br.titanium.useCases.cidadeUsuario.domains.CidadeUsuarioResponseDom;
import com.br.titanium.utils.CrudException;
import com.br.titanium.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/titanium/cidadeUsuario")
public class CidadeUsuarioController {

    @Autowired
    private CidadeUsuarioService cidadeUsuarioService;

    @GetMapping("/carregar")
    public ResponseEntity<List<CidadeUsuarioResponseDom>> carregarEnderecos(){
        try {
            List<CidadeUsuarioResponseDom> response = cidadeUsuarioService.carregarCidades();
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
    public ResponseEntity<?> criarCidade(@RequestBody CidadeUsuarioResponseDom cidade){
        try {
            CidadeUsuarioResponseDom responseDom = cidadeUsuarioService.criarCidade(cidade);
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
    public ResponseEntity<CidadeUsuarioResponseDom> atualizarCidade(@PathVariable Long id, @RequestBody CidadeUsuarioRequestDom cidade){
        try {
            CidadeUsuarioResponseDom responseDOM = cidadeUsuarioService.atualizarCidade(id, cidade);
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
