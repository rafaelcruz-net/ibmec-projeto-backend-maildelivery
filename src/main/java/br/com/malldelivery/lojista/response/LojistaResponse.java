package br.com.malldelivery.lojista.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LojistaResponse {
    private int id;
    private String nome;
    private String telefone;
    private String banner;
    private String urlLoja;
    private int numMaxProduto;
    private String codigoBanco;
    private String agencia;
    private String conta;
    private String tipoConta;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private LocalDateTime dtCadastro;
    private Boolean enabled;
    private LocalDateTime dtAtivacao;
    private String userNameAtivacao;
}
