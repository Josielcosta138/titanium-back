package com.br.titanium.controllers;

import com.br.titanium.useCases.services.MateriaPrimaService;
import com.br.titanium.useCases.domains.MateriaPrimaResponseDom;
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





    @GetMapping("/carregarEconomiaDeMaterial")
    public ResponseEntity<Double>carregarEconomiaDeMateriais(){
        try {
            Double response = materiaPrimaService.carregarEconomiaDeMaterial();
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




    @GetMapping("/carregar/{id}")
    public ResponseEntity<MateriaPrimaResponseDom> carregarMateriaPrimaById(@PathVariable Long id){

        try {
            MateriaPrimaResponseDom response = materiaPrimaService.carregarOSById(id);

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



    @PutMapping("/atualizarMateriaPrima/{id}")
    public ResponseEntity<MateriaPrimaResponseDom> atualizarMateriaPrima(@PathVariable Long id, @RequestBody MateriaPrimaResponseDom materiaprima) {
        try {
            MateriaPrimaResponseDom responseDOM = materiaPrimaService.atualizarMateriaPrima(id, materiaprima);

            if (responseDOM == null) {
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(responseDOM);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }




    @PutMapping("/atualizarFalhasRestantes/{id}")
    public ResponseEntity<MateriaPrimaResponseDom> atualizarQtdeFalhasRestantes(@PathVariable Long id, @RequestBody MateriaPrimaResponseDom materiaprima) {
        try {
            MateriaPrimaResponseDom responseDOM = materiaPrimaService.atualizarQtdeFalhasRestantes(id, materiaprima);

            if (responseDOM == null) {
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(responseDOM);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }




    @PutMapping("/atualizarQtdeTotalMateriaPrima/{id}")
    public ResponseEntity<MateriaPrimaResponseDom> atualizarQtdeTotalMaterial(@PathVariable Long id, @RequestBody MateriaPrimaResponseDom qtdeMateriaprima){
        try {
            MateriaPrimaResponseDom responseDOM = materiaPrimaService.atualizarQtdeTotalMaterial(id, qtdeMateriaprima);

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
