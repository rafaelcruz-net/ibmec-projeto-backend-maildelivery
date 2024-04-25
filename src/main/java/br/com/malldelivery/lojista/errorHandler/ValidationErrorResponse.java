package br.com.malldelivery.lojista.errorHandler;

import lombok.Data;
import org.hibernate.collection.spi.PersistentList;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorResponse {
    private String message = "Existem erros na sua requisição, por favor verifique";
    private List<Validation> validationsErrors = new ArrayList<>();
}
