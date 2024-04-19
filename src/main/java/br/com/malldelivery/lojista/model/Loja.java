package br.com.malldelivery.lojista.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
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
    private List<DadoBancario> dadosBancarios;

    @OneToMany
    @JoinColumn(name = "id_loja", referencedColumnName = "id")
    private List<Endereco> enderecos;
}
