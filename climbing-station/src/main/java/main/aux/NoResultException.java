package main.aux;

import java.util.List;

public class NoResultException extends RuntimeException {
    private final List<String> errors;

    public NoResultException(List<String> errors) {
        super("could not fetch results");
        this.errors = errors;
    }
}