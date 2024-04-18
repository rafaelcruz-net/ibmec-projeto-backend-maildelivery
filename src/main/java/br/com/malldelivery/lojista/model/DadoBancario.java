package br.com.malldelivery.lojista.model;

import lombok.Data;

@Data
public class DadoBancario {
    private int id;
    private String codigoBanco;
    private String agencia;
    private String conta;
    private TipoConta tipoConta;
}
