package br.com.malldelivery.lojista.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "dado_bancario")
public class DadoBancario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "codigo_banco")
    private String codigoBanco;

    @Column(name = "agencia_conta")
    private String agencia;

    @Column(name = "numero_conta")
    private String conta;

    @Column(name = "tipo_conta")
    private TipoConta tipoConta;
}
