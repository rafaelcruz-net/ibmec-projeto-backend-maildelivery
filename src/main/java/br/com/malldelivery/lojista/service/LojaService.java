package br.com.malldelivery.lojista.service;

import br.com.malldelivery.lojista.model.Loja;
import br.com.malldelivery.lojista.repository.LojaRepository;
import br.com.malldelivery.lojista.request.LojistaRequest;
import br.com.malldelivery.lojista.response.LojistaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LojaService {

    @Autowired
    private LojaRepository repository;

    public LojistaResponse criarLoja(LojistaRequest request) throws Exception {
        Loja loja = Loja.fromRequest(request);

        this.repository.save(loja);

        LojistaResponse response = Loja.toResponse(loja);

        return response;
    }
}
