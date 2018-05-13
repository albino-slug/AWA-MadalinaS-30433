package main.aux;

import java.util.ArrayList;
import java.util.List;

public class Notification<T> {

    private T result;
    private List<String> errors;

    public Notification() {
        this.errors = new ArrayList<>();
    }

    public void addError(String message) {
        errors.add(message);
    }

    public boolean hasErrors() {
        return errors.size() > 0;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public T getResult() throws NoResultException {
        if (hasErrors()) {
            throw new NoResultException(errors);
        }
        return result;
    }

    public String getFormattedErrors() {
        String result = "";
        for (String error : errors){
            result += error + "\n";
        }
        return result;
    }
}
