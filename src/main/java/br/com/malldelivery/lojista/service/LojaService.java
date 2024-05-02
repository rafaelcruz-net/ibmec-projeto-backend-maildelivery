package br.com.malldelivery.lojista.service;

import br.com.malldelivery.lojista.model.DadoBancario;
import br.com.malldelivery.lojista.model.Endereco;
import br.com.malldelivery.lojista.model.Loja;
import br.com.malldelivery.lojista.repository.DadoBancarioRepository;
import br.com.malldelivery.lojista.repository.EnderecoRepository;
import br.com.malldelivery.lojista.repository.LojaRepository;
import br.com.malldelivery.lojista.request.LojistaRequest;
import br.com.malldelivery.lojista.response.LojistaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LojaService {

    @Autowired
    private LojaRepository repository;
    @Autowired
    private DadoBancarioRepository dadoBancarioRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public LojistaResponse criarLoja(LojistaRequest request) throws Exception {
        Loja loja = Loja.fromRequest(request);


        //Salva o endereço no banco de dados
        Endereco endereco = loja.getEnderecos().getFirst();
        this.enderecoRepository.save(endereco);


        //Salva o dado bancario no banco de dados
        DadoBancario dadoBancario = loja.getDadosBancarios().getFirst();
        this.dadoBancarioRepository.save(dadoBancario);

        //Grava a loja e atualiza as referencias de dado bancario e endereço
        this.repository.save(loja);

        LojistaResponse response = Loja.toResponse(loja);

        return response;
    }
}
