package com.br.titanium.controllers;

import com.br.titanium.useCases.services.ClienteService;
import com.br.titanium.useCases.domains.ClienteResponseDom;
import com.br.titanium.utils.CrudException;
import com.br.titanium.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/titanium/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/carregar")
    public ResponseEntity<List<ClienteResponseDom>> carregarClientes() {
        try {
            List<ClienteResponseDom> response = clienteService.caregarCliente();

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


    @GetMapping("/carregar/{id}")
    public  ResponseEntity<ClienteResponseDom> carregarClientesById(@PathVariable Long id){
        try {
            ClienteResponseDom responseDOM = clienteService.carregarClienteById(id);
            if (responseDOM != null){
                return ResponseEntity.ok(responseDOM);
            }
            return ResponseEntity.status(204).body(null);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }



    @GetMapping("/carregarDadosApis/{cnpj}")
    public ResponseEntity<ClienteResponseDom> carregarClientesViaApi(@PathVariable String cnpj) {
        try {
            ClienteResponseDom responseDOM = clienteService.carregarClienteApi(cnpj);
            if (responseDOM != null) {
                return ResponseEntity.ok(responseDOM);
            }
            return ResponseEntity.status(204).body(null);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }



    @PostMapping(value = "/criarClientes", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<?> criarCliente(@RequestBody ClienteResponseDom cliente){
        try {
            ClienteResponseDom responseDom = clienteService.criarClientes(cliente);
            return ResponseEntity.status(201).body(responseDom);
        }
        catch (CrudException es){
            es.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMap(es.getMessages()));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMap("Erro não mapeado"+ e.getMessage()));
        }
    }





    @PutMapping("/atualizarCliente/{id}")
    public ResponseEntity<ClienteResponseDom> atualizarCliente(@PathVariable Long id, @RequestBody ClienteResponseDom cliente){
        try {
            ClienteResponseDom responseDOM = clienteService.atualizarCliente(id, cliente);

            if (responseDOM == null){
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(responseDOM);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }



    @GetMapping("/carregarTotalDeClientes")
    public ResponseEntity<Integer>carregarTotalDeClientes(){
        try {
            int response = clienteService.carregarTotalDeClientes();
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




    @DeleteMapping("/excluirCliente/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id){
        try {
            clienteService.excluirCliente(id);
            return ResponseEntity.ok(null);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }



}
