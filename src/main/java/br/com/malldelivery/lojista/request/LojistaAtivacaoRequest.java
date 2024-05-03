package br.com.malldelivery.lojista.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LojistaAtivacaoRequest {
    @NotNull(message = "Habilitação não fornecida")
    private Boolean enabled;

    @NotBlank(message = "Campo nome de usuário responsável pela ativação não fornecido")
    private String userNameAtivacao;
}
