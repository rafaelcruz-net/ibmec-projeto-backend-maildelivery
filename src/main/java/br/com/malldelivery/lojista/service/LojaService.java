package br.com.malldelivery.lojista.service;

import br.com.malldelivery.lojista.model.Loja;
import br.com.malldelivery.lojista.repository.LojaRepository;
import br.com.malldelivery.lojista.request.LojistaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LojaService {

    @Autowired
    private LojaRepository repository;

    public Loja criarLoja(LojistaRequest request) throws Exception {
        Loja loja = Loja.fromRequest(request);
        return loja;
    }
}
