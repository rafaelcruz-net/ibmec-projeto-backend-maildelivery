package br.com.malldelivery.lojista.controller;

import br.com.malldelivery.lojista.exception.LojaException;
import br.com.malldelivery.lojista.model.Usuario;
import br.com.malldelivery.lojista.request.UsuarioRequest;
import br.com.malldelivery.lojista.response.LojistaResponse;
import br.com.malldelivery.lojista.response.TokenResponse;
import br.com.malldelivery.lojista.seguranca.authentication.JwtTokenService;
import br.com.malldelivery.lojista.seguranca.userdetails.UserDetailsImpl;
import br.com.malldelivery.lojista.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody @Valid UsuarioRequest request) throws LojaException {
        Usuario usuarioNovo = this.usuarioService.criar(request.getUsername(), request.getPassword(), request.getIdPerfil());
        return new ResponseEntity<>(usuarioNovo, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid UsuarioRequest request) throws LojaException {
        Usuario usuario = this.usuarioService.obterUsuarioPorUsernameAndPassword(request.getUsername(), request.getPassword());

        //Autentica dentro do Spring
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        //Cria o token JWT
        String jwtToken = jwtTokenService.generateToken(userDetails);

        //Seta o token para a resposta
        TokenResponse response = new TokenResponse();
        response.setToken(jwtToken);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obterTodos() {
        return new ResponseEntity<>(this.usuarioService.obterTodos(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> obterPorId(@PathVariable("id") int id) {
        Usuario response = this.usuarioService.obterUsuarioPorId(id);
        if (response == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
