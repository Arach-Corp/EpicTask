package br.com.fiap.epictask.services.exceptions;

public class DeleteResourceException extends RuntimeException {
    public DeleteResourceException(String message) {
        super(message);
    }
}
