package br.com.malldelivery.lojista.request;


import br.com.malldelivery.lojista.model.TipoConta;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LojistaRequest {
    @NotBlank(message = "Campo nome não pode ser vazio")
    private String nome;

    @NotBlank(message = "Campo telefone não pode ser vazio")
    private String telefone;

    @NotBlank(message = "Campo banner não pode ser vazio")
    private String banner;

    private String urlLoja;
    private int numMaxProduto = 10;

    @NotBlank(message = "Campo código do banco não pode ser vazio")
    private String codigoBanco;

    @NotBlank(message = "Campo agência do banco não pode ser vazio")
    private String agencia;

    @NotBlank(message = "Campo conta do banco não pode ser vazio")
    private String conta;

    @NotBlank(message = "Campo tipo de conta não pode ser vazio")
    private String tipoConta;

    @NotBlank(message = "Campo logradouro não pode ser vazio")
    private String logradouro;

    @NotBlank(message = "Campo complemento não pode ser vazio")
    private String complemento;

    @NotBlank(message = "Campo bairro não pode ser vazio")
    private String bairro;

    @NotBlank(message = "Campo cidade não pode ser vazio")
    private String cidade;

    @NotBlank(message = "Campo estado não pode ser vazio")
    private String estado;

    @NotBlank(message = "Campo cep não pode ser vazio")
    private String cep;
}
