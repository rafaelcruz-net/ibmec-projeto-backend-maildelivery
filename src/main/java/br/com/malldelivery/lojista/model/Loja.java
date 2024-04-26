package br.com.malldelivery.lojista.model;

import br.com.malldelivery.lojista.request.LojistaRequest;
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
            throw new Exception("Tipo de conta invalido");
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
}
