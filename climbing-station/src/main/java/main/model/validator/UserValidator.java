package main.model.validator;

import main.model.Role;
import main.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import javax.persistence.Column;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements org.springframework.validation.Validator  {
    public static final int MIN_PASSWORD_LENGTH = 3;
    private static final String USERNAME_REGEX = "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+$";

    @Override
    public boolean supports(Class<?> someClass) {
        return someClass.isAssignableFrom(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User)target;
        nameValidator(user.getName(), errors);
        passwordValidator(user.getPassword(), errors);
    }

    private void nameValidator(String name, Errors errors){
        if (!Pattern.compile(USERNAME_REGEX).matcher(name).matches()) {
            errors.rejectValue("username", "username contains illegal characters");
        }
    }

    private void passwordValidator(String password, Errors errors){
        if (password.length() < MIN_PASSWORD_LENGTH) {
            errors.rejectValue("password", "password too short");
        }
        if (!containsSpecialCharacter(password)) {
            errors.rejectValue("password", "password must contain at least a special character");
        }
        if (!containsDigit(password)) {
            errors.rejectValue("password", "password must contain at least one digit");
        }
    }

    private boolean containsSpecialCharacter(String s) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        return m.find();
    }

    private boolean containsDigit(String s) {
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    return true;
                }
            }
        }
        return false;
    }



}