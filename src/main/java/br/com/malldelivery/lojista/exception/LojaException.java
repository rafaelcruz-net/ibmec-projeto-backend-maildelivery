package br.com.malldelivery.lojista.exception;

public class LojaException extends Exception {
    private String field;
    public LojaException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return this.field;
    }
}
