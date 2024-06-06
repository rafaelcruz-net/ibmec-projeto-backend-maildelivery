package br.com.malldelivery.lojista.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    @OneToMany
    @JoinColumn(name = "id_perfil", referencedColumnName = "id")
    private List<Perfil> roles = new ArrayList<>();
}
