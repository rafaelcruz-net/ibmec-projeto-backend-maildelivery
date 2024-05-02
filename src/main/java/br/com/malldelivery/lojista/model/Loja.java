package br.com.malldelivery.lojista.model;

import br.com.malldelivery.lojista.exception.LojaException;
import br.com.malldelivery.lojista.request.LojistaRequest;
import br.com.malldelivery.lojista.response.LojistaResponse;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "loja")
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nome;

    @Column
    private String telefone;

    @Column
    private String cnpj;

    @Column
    private LocalDateTime dtCadastro;

    @Column
    private String banner;

    @Column
    private String urlLoja;

    @Column
    private int numMaxProduto;

    @OneToMany
    @JoinColumn(name = "id_loja", referencedColumnName = "id")
    private List<DadoBancario> dadosBancarios = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "id_loja", referencedColumnName = "id")
    private List<Endereco> enderecos = new ArrayList<>();

    public static Loja fromRequest(LojistaRequest request) throws Exception {
        Loja loja = new Loja();

        loja.setNome(request.getNome());
        loja.setTelefone(request.getTelefone());
        loja.setBanner(request.getBanner());
        loja.setUrlLoja(request.getUrlLoja());
        loja.setNumMaxProduto(request.getNumMaxProduto());

        DadoBancario dadoBancario = new DadoBancario();

        if (request.getTipoConta().equals("CC")) {
            dadoBancario.setTipoConta(TipoConta.CONTA_CORRENTE);
        } else if (request.getTipoConta().equals("CP")) {
            dadoBancario.setTipoConta(TipoConta.CONTA_POUPANCA);
        } else if (request.getTipoConta().equals("CI")) {
            dadoBancario.setTipoConta(TipoConta.CONTA_INVESTIMENTO);
        } else {
            throw new LojaException("tipoConta", "Tipo de conta invalido, valores validos: CC, CI, CP");
        }

        dadoBancario.setConta(request.getConta());
        dadoBancario.setAgencia(request.getAgencia());
        dadoBancario.setCodigoBanco(request.getCodigoBanco());
        loja.getDadosBancarios().add(dadoBancario);

        Endereco endereco = new Endereco();
        endereco.setCep(request.getCep());
        endereco.setBairro(request.getBairro());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());
        endereco.setComplemento(request.getComplemento());
        endereco.setLogradouro(request.getLogradouro());

        loja.getEnderecos().add(endereco);

        return loja;
    }

    public static LojistaResponse toResponse(Loja loja) {
        LojistaResponse response = new LojistaResponse();

        response.setNome(loja.getNome());
        response.setTelefone(loja.getTelefone());
        response.setBanner(loja.getBanner());
        response.setUrlLoja(loja.getUrlLoja());
        response.setNumMaxProduto(loja.getNumMaxProduto());
        response.setId(loja.getId());

        response.setConta(loja.getDadosBancarios().getFirst().getConta());
        response.setAgencia(loja.getDadosBancarios().getFirst().getConta());
        response.setCodigoBanco(loja.getDadosBancarios().getFirst().getConta());
        response.setTipoConta(loja.getDadosBancarios().getFirst().getTipoConta().toString());

        response.setCep(loja.getEnderecos().getFirst().getCep());
        response.setBairro(loja.getEnderecos().getFirst().getBairro());
        response.setCidade(loja.getEnderecos().getFirst().getCidade());
        response.setEstado(loja.getEnderecos().getFirst().getEstado());
        response.setComplemento(loja.getEnderecos().getFirst().getComplemento());
        response.setLogradouro(loja.getEnderecos().getFirst().getLogradouro());

        return response;

    }
}
