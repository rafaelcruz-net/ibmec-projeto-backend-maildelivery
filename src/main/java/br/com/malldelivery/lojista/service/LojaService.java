package br.com.malldelivery.lojista.service;

import br.com.malldelivery.lojista.exception.LojaException;
import br.com.malldelivery.lojista.model.DadoBancario;
import br.com.malldelivery.lojista.model.Endereco;
import br.com.malldelivery.lojista.model.Loja;
import br.com.malldelivery.lojista.repository.DadoBancarioRepository;
import br.com.malldelivery.lojista.repository.EnderecoRepository;
import br.com.malldelivery.lojista.repository.LojaRepository;
import br.com.malldelivery.lojista.request.LojistaAtivacaoRequest;
import br.com.malldelivery.lojista.request.LojistaRequest;
import br.com.malldelivery.lojista.response.LojistaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;
    @Autowired
    private DadoBancarioRepository dadoBancarioRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public LojistaResponse criarLoja(LojistaRequest request) throws Exception {
        Loja loja = Loja.fromRequest(request);

        //Verifica se o cpnj já está cadastrado
        if (this.lojaRepository.findByCnpj(loja.getCnpj()).isEmpty() == false) {
            throw new LojaException("cpnj", "CNPJ do lojista já cadastrado.");
        }

        //Salva o endereço no banco de dados
        Endereco endereco = loja.getEnderecos().getFirst();
        this.enderecoRepository.save(endereco);

        //Salva o dado bancario no banco de dados
        DadoBancario dadoBancario = loja.getDadosBancarios().getFirst();
        this.dadoBancarioRepository.save(dadoBancario);

        //Adicionando a data de cadastro
        loja.setDtCadastro(LocalDateTime.now());

        //Deixa a loja desabilita
        loja.setEnabled(Boolean.FALSE);

        //Grava a loja e atualiza as referencias de dado bancario e endereço
        this.lojaRepository.save(loja);

        LojistaResponse response = Loja.toResponse(loja);

        return response;
    }

    public LojistaResponse obterLojistaPorId(int id) {
        Optional<Loja> optLoja = this.lojaRepository.findById(id);

        if (optLoja.isEmpty())
            return null;

        return Loja.toResponse(optLoja.get());
    }

    public LojistaResponse obterLojistaPorCnpj(String cnpj) {
        Optional<Loja> optLoja = this.lojaRepository.findByCnpj(cnpj);

        if (optLoja.isEmpty())
            return null;

        return Loja.toResponse(optLoja.get());
    }

    public LojistaResponse ativarLojista(int id, LojistaAtivacaoRequest request) throws LojaException {
        Optional<Loja> optLoja = this.lojaRepository.findById(id);

        if (optLoja.isEmpty()) {
            throw new LojaException("id", "identificador da loja não encontrado");
        }

        Loja loja = optLoja.get();

        //Seta os valores para habilitar a loja
        loja.setEnabled(request.getEnabled());
        loja.setDtAtivacao(LocalDateTime.now());

        //Grava o responsável pela a validação dos dados
        loja.setUserNameAtivacao(request.getUserNameAtivacao());

        this.lojaRepository.save(loja);

        return Loja.toResponse(loja);

    }

    public LojistaResponse atualizarDadosLojista(int id, LojistaRequest request) throws LojaException {
        Optional<Loja> optLoja = this.lojaRepository.findById(id);

        if (optLoja.isEmpty()) {
            throw new LojaException("id", "identificador da loja não encontrado");
        }

        Loja loja = Loja.fromRequest(optLoja.get(), request);

        //Atualiza o endereço no banco de dados
        Endereco endereco = loja.getEnderecos().getFirst();
        this.enderecoRepository.save(endereco);

        //Atualiza o dado bancario no banco de dados
        DadoBancario dadoBancario = loja.getDadosBancarios().getFirst();
        this.dadoBancarioRepository.save(dadoBancario);

        //Atualiza o dado da loja
        this.lojaRepository.save(loja);

        return Loja.toResponse(loja);
    }
}
