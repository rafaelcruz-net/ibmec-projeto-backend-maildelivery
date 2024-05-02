package br.com.malldelivery.lojista.errorHandler;


import br.com.malldelivery.lojista.exception.LojaException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse validationHandler(MethodArgumentNotValidException e) {
        ValidationErrorResponse response = new ValidationErrorResponse();

        for (FieldError item : e.getFieldErrors()) {
            Validation validation = new Validation();
            validation.setField(item.getField());
            validation.setMessage(item.getDefaultMessage());
            response.getValidationsErrors().add(validation);
        }

        return response;
    }

    @ExceptionHandler(LojaException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ValidationErrorResponse validationBusinessError(LojaException e) {
        ValidationErrorResponse response = new ValidationErrorResponse();
        Validation validation = new Validation();
        validation.setMessage(e.getMessage());
        validation.setField(e.getField());
        response.getValidationsErrors().add(validation);
        return response;
    }


}
