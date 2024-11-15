package com.br.titanium.controllers;


import com.br.titanium.entitys.EnderecoUsuario;
import com.br.titanium.useCases.EnderecoUsuario.domains.EnderecoUsuarioRequestDom;
import com.br.titanium.useCases.EnderecoUsuario.domains.EnderecoUsuarioResponseDom;
import com.br.titanium.useCases.EnderecoUsuarioService;
import com.br.titanium.useCases.endereco.domains.EnderecoRequestDom;
import com.br.titanium.useCases.endereco.domains.EnderecoResponseDom;
import com.br.titanium.utils.CrudException;
import com.br.titanium.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/titanium/enderecoUsuario")
public class EnderecoUsuarioController {

    @Autowired
    private EnderecoUsuarioService enderecoUsuarioService;


    @GetMapping("/carregar")
    public ResponseEntity<List<EnderecoUsuarioResponseDom>> carregarEnderecos(){
        try {
            List<EnderecoUsuarioResponseDom> response = enderecoUsuarioService.carregarEndereco();
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



    @GetMapping("/carregar/{id}")
    public ResponseEntity<EnderecoUsuarioResponseDom> carregarEnderecosById(@PathVariable Long id){

        try {
            EnderecoUsuarioResponseDom responseDOM = enderecoUsuarioService.carregarEnderecosById(id);

            if (responseDOM != null){
                return ResponseEntity.ok(responseDOM);
            }
            return ResponseEntity
                    .status(204)
                    .body(null);
        }
        catch (Exception e){
            e.printStackTrace();

            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }



    @GetMapping("/carregaEnderecosIdCliente/{id}")
    public ResponseEntity<List<EnderecoUsuarioResponseDom>> carregarEnderecosByIdCliente(@PathVariable Long id){

        try {
            List<EnderecoUsuarioResponseDom> responseDOM = enderecoUsuarioService.carregarEnderecosByIdCliente(id);
            int status = 200;

            if (responseDOM.isEmpty()){
                status = 204;
            }
            return ResponseEntity.status(status).body(responseDOM);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }




    @PostMapping(value = "/criar", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<?> criarEndereco(@RequestBody EnderecoUsuarioRequestDom endereco){
        try {
            EnderecoUsuarioResponseDom responseDOM = enderecoUsuarioService.criarEndereco(endereco);
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




    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EnderecoUsuarioResponseDom> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoUsuarioRequestDom endereco) {
        try {
            EnderecoUsuarioResponseDom responseDOM = enderecoUsuarioService.atualizarEndereco(id, endereco);
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


    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirEndereco(@PathVariable Long id){
        try {
            enderecoUsuarioService.excluirEndereco(id);
            return ResponseEntity.ok(null);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }



}
