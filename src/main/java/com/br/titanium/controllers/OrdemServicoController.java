package com.br.titanium.controllers;

import com.br.titanium.useCases.OrdemServico.domains.OrdemServicoResponseDom;
import com.br.titanium.useCases.OrdemServicoService;
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


}
