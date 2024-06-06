package br.com.malldelivery.lojista.controller;

import br.com.malldelivery.lojista.exception.LojaException;
import br.com.malldelivery.lojista.model.Usuario;
import br.com.malldelivery.lojista.request.UsuarioRequest;
import br.com.malldelivery.lojista.response.LojistaResponse;
import br.com.malldelivery.lojista.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody @Valid UsuarioRequest request) throws LojaException {
        Usuario usuarioNovo = this.usuarioService.criar(request.getUsername(), request.getPassword(), request.getIdPerfil());
        return new ResponseEntity<>(usuarioNovo, HttpStatus.CREATED);
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
