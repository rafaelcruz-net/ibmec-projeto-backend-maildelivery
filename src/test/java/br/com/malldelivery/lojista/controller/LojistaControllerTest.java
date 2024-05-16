package br.com.malldelivery.lojista.controller;

import br.com.malldelivery.lojista.model.DadoBancario;
import br.com.malldelivery.lojista.model.Endereco;
import br.com.malldelivery.lojista.model.Loja;
import br.com.malldelivery.lojista.model.TipoConta;
import br.com.malldelivery.lojista.request.LojistaRequest;
import br.com.malldelivery.lojista.service.LojaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@AutoConfigureMockMvc
@WebMvcTest(controllers = LojistaController.class)
public class LojistaControllerTest {
    @MockBean
    private LojaService lojaService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Loja loja;

    @BeforeEach
    public void setup() {
        loja = new Loja();
        loja.setCnpj("82427325000110");
        loja.setBanner("http://xpto.com");
        loja.setNome("Lojista do Teste");
        loja.setId(1);

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua do teste");
        endereco.setComplemento("Apto do Teste");
        endereco.setBairro("Bairro do Teste");
        endereco.setCidade("Cidade do Teste");
        endereco.setCep("99999-999");
        loja.getEnderecos().add(endereco);

        DadoBancario dadoBancario = new DadoBancario();
        dadoBancario.setTipoConta(TipoConta.CONTA_CORRENTE);
        dadoBancario.setAgencia("9999");
        dadoBancario.setConta("99990-0");
        dadoBancario.setCodigoBanco("341");

        loja.getDadosBancarios().add(dadoBancario);
    }


    @Test
    public void deveConsultarLojistaPorIdComSucesso() throws Exception {
        int id = 1;
        given(this.lojaService.obterLojistaPorId(id)).willReturn(Loja.toResponse(this.loja));

        mvc.perform(MockMvcRequestBuilders.get("/lojista/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.nome", is(this.loja.getNome())));
    }

    @Test
    public void deveConsultarLojistaPorIdRetornandoNotFound() throws Exception {
        int id = 1;
        given(this.lojaService.obterLojistaPorId(id)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders.get("/lojista/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}

