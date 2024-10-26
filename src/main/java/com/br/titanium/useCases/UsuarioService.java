package com.br.titanium.useCases;

import com.br.titanium.entitys.Cliente;
import com.br.titanium.entitys.Usuario;
import com.br.titanium.jwt.TokenService;
import com.br.titanium.repositorys.UsuarioRepository;
import com.br.titanium.useCases.Usuario.domains.UsuarioLoginRequestDom;
import com.br.titanium.useCases.Usuario.domains.UsuarioLoginResponseDom;
import com.br.titanium.useCases.Usuario.domains.UsuarioResponseDom;
import com.br.titanium.useCases.cliente.domains.ClienteResponseDom;
import com.br.titanium.utils.CrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuariosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public List<UsuarioResponseDom> caregarUsuarios(){

        List<Usuario> listaDeUsuarios = usuariosRepository.findAll();
        List<UsuarioResponseDom> usuarios = new ArrayList<>();

        for (Usuario resultadoUsuarios : listaDeUsuarios) {

            UsuarioResponseDom aux = new UsuarioResponseDom();
            aux.setId(resultadoUsuarios.getId());
            aux.setCnpj(resultadoUsuarios.getCnpj());
            aux.setRazaoSocial(resultadoUsuarios.getRazaoSocial());
            aux.setNomeFantasia(resultadoUsuarios.getNomeFantasia());
            aux.setLogin(resultadoUsuarios.getLogin());
            aux.setSenha(resultadoUsuarios.getSenha());
            aux.setEmail(resultadoUsuarios.getEmail());
            aux.setTelefone(resultadoUsuarios.getTelefone());
            usuarios.add(aux);
        }
        return usuarios;
    }


    public int carregarTotalDeUsuarios() {
        List<Usuario> usuarios = usuariosRepository.findAll();
        int qtdeTotal = usuarios.size();
        return qtdeTotal;
    }



    public UsuarioLoginResponseDom autenticarCliente(UsuarioLoginRequestDom usuarioLoginRequestDom) throws CrudException {
        Optional<Usuario> usuarioOptional = usuariosRepository.findByLogin(usuarioLoginRequestDom.getLogin());
        if (!usuarioOptional.isPresent()) {
            throw new CrudException("Usuário não encontrado");
        }

        Usuario usuario = usuarioOptional.get();

        if (passwordEncoder.matches(usuarioLoginRequestDom.getSenha(), usuario.getSenha())) {
            String token = tokenService.gerarToken(usuario);

            return new UsuarioLoginResponseDom(
                    usuario.getLogin(),
                    token,
                    usuario.getSenha()
            );
        }
        throw new CrudException("Senha inválida!");
    }





    public UsuarioLoginResponseDom recuperarToken(UsuarioLoginRequestDom usuarioLoginRequestDom) throws CrudException {
        Optional<Usuario> usuarioOptional = usuariosRepository.findByLogin(usuarioLoginRequestDom.getLogin());
        if (!usuarioOptional.isPresent()) {
            throw new CrudException("Usuário não encontrado");
        }

        Usuario usuario = usuarioOptional.get();

        if (passwordEncoder.matches(usuarioLoginRequestDom.getSenha(), usuario.getSenha())) {
            String token = tokenService.gerarToken(usuario);

            return new UsuarioLoginResponseDom(
                    usuario.getLogin(),
                    token,
                    usuario.getSenha()
            );
        }
        throw new CrudException("Senha inválida!");
    }





    public UsuarioResponseDom carregarUsuarioById(Long id){
        Optional<Usuario> resultado = usuariosRepository.findById(id);

        if (resultado.isPresent()){
            Usuario usuarios = resultado.get();

            UsuarioResponseDom responseDOM = new UsuarioResponseDom();
            responseDOM.setId(usuarios.getId());
            responseDOM.setCnpj(usuarios.getCnpj());
            responseDOM.setEmail(usuarios.getEmail());
            responseDOM.setRazaoSocial(usuarios.getRazaoSocial());
            responseDOM.setNomeFantasia(usuarios.getNomeFantasia());
            responseDOM.setLogin(usuarios.getLogin());
            responseDOM.setSenha(usuarios.getSenha());
            responseDOM.setTelefone(usuarios.getTelefone());
            return responseDOM;
        }
        return null;
    }




    private static final String API_URL = "https://www.receitaws.com.br/v1/cnpj/";
    public UsuarioResponseDom carregarUsuarioApi(String cnpj) {
        try {
            // Realiza a chamada à API externa
            RestTemplate restTemplate = new RestTemplate();
            String url = API_URL + cnpj;
            ResponseEntity<UsuarioResponseDom> response = restTemplate.getForEntity(url, UsuarioResponseDom.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                UsuarioResponseDom apiResponse = response.getBody();

                    UsuarioResponseDom responseDOM = new UsuarioResponseDom();
                    responseDOM.setCnpj(apiResponse.getCnpj());
                    responseDOM.setRazaoSocial(apiResponse.getRazaoSocial());
                    responseDOM.setNomeFantasia(apiResponse.getNomeFantasia());
                    responseDOM.setEmail(apiResponse.getEmail());
                    responseDOM.setTelefone(apiResponse.getTelefone());
                    responseDOM.setLogradouro(apiResponse.getLogradouro());
                    responseDOM.setBairro(apiResponse.getBairro());
                    responseDOM.setMunicipio(apiResponse.getMunicipio());
                    responseDOM.setUf(apiResponse.getUf());
                    responseDOM.setCep(apiResponse.getCep());

                return responseDOM;
            }

        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return null;
    }



    public UsuarioResponseDom criarUsuario(UsuarioResponseDom cliente) throws CrudException {
        List<String> mensagens = this.validarUsuario(cliente);
            if(!mensagens.isEmpty()) {
                throw new CrudException(mensagens);
            }
        Optional<Usuario> usuario = usuariosRepository.findByLogin(cliente.getLogin());
            if (usuario.isPresent()) {
                throw  new CrudException("Usário já esta cadastrado!");
            }


        Usuario usuariosEntidades = new Usuario();
            usuariosEntidades.setRazaoSocial(cliente.getRazaoSocial());
            usuariosEntidades.setNomeFantasia(cliente.getNomeFantasia());
            usuariosEntidades.setEmail(cliente.getEmail());
            usuariosEntidades.setTelefone(cliente.getTelefone());
            usuariosEntidades.setCnpj(cliente.getCnpj());
            String senhaCodificada = passwordEncoder.encode(cliente.getSenha());
            usuariosEntidades.setSenha(senhaCodificada);
            usuariosEntidades.setLogin(cliente.getLogin());
        Usuario resultado = usuariosRepository.save(usuariosEntidades);

        UsuarioResponseDom responseDom = new UsuarioResponseDom();
            responseDom.setId(resultado.getId());
            responseDom.setRazaoSocial(resultado.getRazaoSocial());
            responseDom.setNomeFantasia(resultado.getNomeFantasia());
            responseDom.setEmail(resultado.getEmail());
            responseDom.setTelefone(resultado.getTelefone());
            responseDom.setCnpj(resultado.getCnpj());
            responseDom.setSenha(resultado.getSenha());
            responseDom.setLogin(resultado.getLogin());

        return responseDom;
    }



    public UsuarioResponseDom atualizarUsuarios(Long id, UsuarioResponseDom usuario)throws CrudException{

        List<String>mensagens = this.validarUsuario(usuario);
        if (!mensagens.isEmpty()){
            throw new CrudException(mensagens);
        }


        Optional<Usuario> resultado = usuariosRepository.findById(id).map(record -> {
            record.setRazaoSocial(usuario.getNomeFantasia());
            record.setNomeFantasia(usuario.getNomeFantasia());
            record.setEmail(usuario.getEmail());
            record.setLogin(usuario.getLogin());
            record.setSenha(usuario.getSenha());
            record.setTelefone(usuario.getTelefone());
            record.setCnpj(usuario.getCnpj());

            return usuariosRepository.save(record);
        });

        if (resultado.isPresent()){
            Usuario usuarioEntidades = resultado.get();

            UsuarioResponseDom responseDOM = new UsuarioResponseDom();
            responseDOM.setId(usuarioEntidades.getId());
            responseDOM.setRazaoSocial(usuarioEntidades.getNomeFantasia());
            responseDOM.setNomeFantasia(usuarioEntidades.getNomeFantasia());
            responseDOM.setEmail(usuarioEntidades.getEmail());
            responseDOM.setTelefone(usuarioEntidades.getTelefone());
            responseDOM.setCnpj(usuarioEntidades.getCnpj());
            responseDOM.setSenha(usuarioEntidades.getSenha());
            responseDOM.setLogin(usuarioEntidades.getLogin());

            return responseDOM;
        }
        return null;
    }


    public void excluirUsuario(Long id){
        usuariosRepository.deleteById(id);
    }




    public List<String> validarUsuario(UsuarioResponseDom usuarios){
        List<String> mensagens = new ArrayList<>();

        if (usuarios.getCnpj() == null || usuarios.getCnpj().equals("")) {
            mensagens.add("cnpj do cliente não informado");
        }

        if (usuarios.getNomeFantasia() == null || usuarios.getNomeFantasia().equals("")) {
            mensagens.add("nome do cliente não informado");
        }
        return mensagens;
    }


}
