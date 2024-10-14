package com.br.titanium.controllers;

import com.br.titanium.useCases.OrdemServico.domains.OrdemServicoResponseDom;
import com.br.titanium.useCases.OrdemServico.domains.OrdemServicoResquestDom;
import com.br.titanium.useCases.OrdemServicoService;
import com.br.titanium.utils.CrudException;
import com.br.titanium.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3001")
@Controller
@RequestMapping("/titanium/ordemServico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;


    @CrossOrigin(origins = "http://localhost:3001")
    @GetMapping("/carregar")
    public ResponseEntity<List<OrdemServicoResponseDom>> carregarEnderecos(){
        try {
            List<OrdemServicoResponseDom> response = ordemServicoService.carregarOS();
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
    @GetMapping("/carregarFaturamentoTotal")
    public ResponseEntity<BigDecimal>carregarFaturamentoTotal(){
        try {
            BigDecimal response = ordemServicoService.carregarFaturamentoTotal();
            int status = 200;
            if (response.compareTo(BigDecimal.ZERO) < 0){
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
    @GetMapping("/carregar/{id}")
    public ResponseEntity<OrdemServicoResponseDom> carregarEnderecosById(@PathVariable Long id){

        try {
            OrdemServicoResponseDom response = ordemServicoService.carregarOSById(id);

            if (response != null){
                return ResponseEntity.ok(response);
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



    @CrossOrigin(origins = "http://localhost:3001")
    @GetMapping("/carregarNome/{nome}")
    public ResponseEntity<List<OrdemServicoResponseDom>> carregarEnderecosByName(@PathVariable String nome){

        try {
            List<OrdemServicoResponseDom> response = ordemServicoService.carregarOSByName(nome);

            if (response != null){
                return ResponseEntity.ok(response);
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



    @CrossOrigin(origins = "http://localhost:3001")
    @PostMapping(value = "/criar", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<?> criarOrdemServico(@RequestBody OrdemServicoResquestDom ordemServico){
        try {
            OrdemServicoResponseDom responseDOM = ordemServicoService.criarOrdemServico(ordemServico);
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



    @CrossOrigin(origins = "http://localhost:3001")
    @PutMapping("/atualizarOS/{id}")
    public ResponseEntity<OrdemServicoResponseDom> atualizarOS(@PathVariable Long id, @RequestBody OrdemServicoResponseDom os){
        try {
            OrdemServicoResponseDom responseDOM = ordemServicoService.atualizarOrdemServico(id, os);

            if (responseDOM == null){
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(responseDOM);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }



    @CrossOrigin(origins = "http://localhost:3001")
    @PutMapping("/atualizarStatusOs/{id}")
    public ResponseEntity<OrdemServicoResponseDom> atualizarStatusOs(@PathVariable Long id, @RequestBody OrdemServicoResponseDom statusOs){
        try {
            OrdemServicoResponseDom responseDOM = ordemServicoService.atualizarStatusOs(id, statusOs);

            if (responseDOM == null){
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(responseDOM);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }



    @CrossOrigin(origins = "http://localhost:3001")
    @PutMapping("/atualizarQtdesFalhasSobras/{id}")
    public ResponseEntity<OrdemServicoResponseDom> atualizarQtdesFalhasSobras(@PathVariable Long id, @RequestBody OrdemServicoResponseDom qtdes){
        try {
            OrdemServicoResponseDom responseDOM = ordemServicoService.atualizarQtdesFalhaSobras(id, qtdes);

            if (responseDOM == null){
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(responseDOM);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }





}
