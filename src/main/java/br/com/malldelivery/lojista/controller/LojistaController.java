package br.com.malldelivery.lojista.controller;


import br.com.malldelivery.lojista.request.LojistaRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lojista")
public class LojistaController {

    @PostMapping
    public ResponseEntity criarLoja(@RequestBody @Valid LojistaRequest request) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
