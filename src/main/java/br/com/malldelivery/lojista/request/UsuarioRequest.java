package br.com.malldelivery.lojista.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioRequest {
    @NotEmpty(message = "O campo username não pode ser vazio")
    private String username;

    @NotEmpty(message = "O campo password não pode ser vazio")
    private String password;

    @NotNull(message = "O campo perfil não pode ser vazio")
    private Integer idPerfil;
}
