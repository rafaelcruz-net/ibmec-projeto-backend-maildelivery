package br.com.malldelivery.lojista.model;

import lombok.Data;

@Data
public class Endereco {
    private int id;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
