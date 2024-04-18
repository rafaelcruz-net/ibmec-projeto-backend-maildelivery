package br.com.malldelivery.lojista.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Loja {
    private int id;
    private String nome;
    private String telefone;
    private String cnpj;
    private LocalDateTime dtCadastro;
    private String banner;
    private String urlLoja;
    private int numMaxProduto;
    private List<DadoBancario> dadosBancarios;
    private List<Endereco> enderecos;
}
