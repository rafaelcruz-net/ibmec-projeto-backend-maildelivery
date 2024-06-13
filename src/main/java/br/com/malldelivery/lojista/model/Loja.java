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

    @Column
    private Boolean enabled = false;

    @Column
    private LocalDateTime dtAtivacao;

    @Column
    private String userNameAtivacao;


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
        loja.setCnpj(request.getCnpj());

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

    public static Loja fromRequest(Loja loja, LojistaRequest request) throws LojaException {

        loja.setNome(request.getNome());
        loja.setTelefone(request.getTelefone());
        loja.setBanner(request.getBanner());
        loja.setUrlLoja(request.getUrlLoja());
        loja.setNumMaxProduto(request.getNumMaxProduto());
        loja.setCnpj(request.getCnpj());

        DadoBancario dadoBancario = loja.dadosBancarios.get(0);

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

        Endereco endereco = loja.getEnderecos().get(0);

        endereco.setCep(request.getCep());
        endereco.setBairro(request.getBairro());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());
        endereco.setComplemento(request.getComplemento());
        endereco.setLogradouro(request.getLogradouro());

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
        response.setDtCadastro(loja.getDtCadastro());
        response.setEnabled(loja.getEnabled());
        response.setDtAtivacao(loja.getDtAtivacao());
        response.setUserNameAtivacao(loja.getUserNameAtivacao());

        if (loja.getDadosBancarios().size() > 0) {
            response.setConta(loja.getDadosBancarios().get(0).getConta());
            response.setAgencia(loja.getDadosBancarios().get(0).getConta());
            response.setCodigoBanco(loja.getDadosBancarios().get(0).getConta());
            response.setTipoConta(loja.getDadosBancarios().get(0).getTipoConta().toString());
        }

        if (loja.getEnderecos().size() > 0) {
            response.setCep(loja.getEnderecos().get(0).getCep());
            response.setBairro(loja.getEnderecos().get(0).getBairro());
            response.setCidade(loja.getEnderecos().get(0).getCidade());
            response.setEstado(loja.getEnderecos().get(0).getEstado());
            response.setComplemento(loja.getEnderecos().get(0).getComplemento());
            response.setLogradouro(loja.getEnderecos().get(0).getLogradouro());
        }

        return response;

    }
}
