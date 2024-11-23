package br.com.lindolfomoizinho.qrcode.exceptions;


public class InvalidContentException extends RuntimeException {
    public InvalidContentException(String message) {
        super(message);
    }
}