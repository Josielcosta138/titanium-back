package com.br.titanium.controllers;

import com.br.titanium.entitys.Usuario;
import com.br.titanium.useCases.ClienteService;
import com.br.titanium.useCases.Usuario.domains.UsuarioLoginRequestDom;
import com.br.titanium.useCases.Usuario.domains.UsuarioLoginResponseDom;
import com.br.titanium.useCases.Usuario.domains.UsuarioRequestDom;
import com.br.titanium.useCases.Usuario.domains.UsuarioResponseDom;
import com.br.titanium.useCases.UsuarioService;
import com.br.titanium.useCases.cliente.domains.ClienteResponseDom;
import com.br.titanium.utils.CrudException;
import com.br.titanium.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@Controller
@RequestMapping("/titanium/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/carregar")
    public ResponseEntity<List<UsuarioResponseDom>> carregarUsuarios() {
        try {
            List<UsuarioResponseDom> response = usuarioService.caregarUsuarios();

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
    public  ResponseEntity<UsuarioResponseDom> carregarUsuarioById(@PathVariable Long id){
        try {
            UsuarioResponseDom responseDOM = usuarioService.carregarUsuarioById(id);
            if (responseDOM != null){
                return ResponseEntity.ok(responseDOM);
            }
            return ResponseEntity.status(204).body(null);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticarUsuario(@RequestBody UsuarioLoginRequestDom usuario) {
        try {
            UsuarioLoginResponseDom response = usuarioService.autenticarCliente(usuario);
            return ResponseEntity.ok(response);
        } catch (CrudException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseUtil.responseMap(e.getMessages()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMap("Erro não mapeado: " + e.getMessage()));
        }
    }






    @PostMapping("/recuperarAutenticacao")
    public ResponseEntity<?> recuperarAutenticacao(@RequestBody UsuarioLoginRequestDom usuario) {
        try {
            UsuarioLoginResponseDom response = usuarioService.recuperarToken(usuario);
            return ResponseEntity.ok(response);
        } catch (CrudException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseUtil.responseMap(e.getMessages()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMap("Erro não mapeado: " + e.getMessage()));
        }
    }




    @GetMapping("/carregarDadosApis/{cnpj}")
    public ResponseEntity<UsuarioResponseDom> carregarUsuariosViaApi(@PathVariable String cnpj) {
        try {
            UsuarioResponseDom responseDOM = usuarioService.carregarUsuarioApi(cnpj);
            if (responseDOM != null) {
                return ResponseEntity.ok(responseDOM);
            }
            return ResponseEntity.status(204).body(null);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }



    @PostMapping(value = "/criarUsuarios", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<?> criarUsuarios(@RequestBody UsuarioResponseDom usuarios){
        try {
            UsuarioResponseDom responseDom = usuarioService.criarUsuario(usuarios);
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




    @PutMapping("/atualizarUsuarios/{id}")
    public ResponseEntity<UsuarioResponseDom> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioResponseDom usuarios){
        try {
            UsuarioResponseDom responseDOM = usuarioService.atualizarUsuarios(id, usuarios);

            if (responseDOM == null){
                return ResponseEntity.badRequest().body(responseDOM);
            }
            return ResponseEntity.ok(responseDOM);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }






    @PutMapping("/recuperarSenha/{id}")
    public ResponseEntity<UsuarioResponseDom> recuperarSenha(@PathVariable Long id, @RequestBody UsuarioResponseDom usuarios){
        try {
            UsuarioResponseDom responseDOM = usuarioService.recuperarSenha(id, usuarios);

            if (responseDOM == null){
                return ResponseEntity.badRequest().body(responseDOM);
            }
            return ResponseEntity.ok(responseDOM);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }





    @GetMapping("/carregarTotalDeUsuarios")
    public ResponseEntity<Integer>carregarTotalDeUsuarios(){
        try {
            int response = usuarioService.carregarTotalDeUsuarios();
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
            usuarioService.excluirUsuario(id);
            return ResponseEntity.ok(null);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }



}
