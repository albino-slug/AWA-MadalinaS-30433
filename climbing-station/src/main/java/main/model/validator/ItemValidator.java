package main.model.validator;

import main.model.Item;
import main.model.ItemType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@Component
public class ItemValidator implements org.springframework.validation.Validator {
    public static final int MAX_DESCRIPTION_LENGTH = 500;

    @Override
    public boolean supports(Class<?> someClass) {
        return someClass.isAssignableFrom(Item.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;
        titleValidator(item.getTitle(), errors);
        creatorValidator(item.getCreator(), errors);
        typeValidator(item.getType(), errors);
        descriptionValidator(item.getDescription(), errors);
    }

    private void titleValidator(String title, Errors errors){
        if (title == null){
            errors.rejectValue("title", "This field cannot be null");
        }
        else if (title.isEmpty()){
            errors.rejectValue("title", "This field cannot be empty");
        }
    }

    private void creatorValidator(String creator, Errors errors){
        if (creator == null){
            errors.rejectValue("creator", "creator field cannot be null");
        }
        else if (creator.isEmpty()){
            errors.rejectValue("creator", "creator field cannot be empty");
        }
    }

    private void typeValidator(ItemType type, Errors errors){
        //no restrictions
    }

    private void descriptionValidator(String description, Errors errors){
        if(description.length() < MAX_DESCRIPTION_LENGTH){
            errors.rejectValue("description", "description cannot be longer than " + MAX_DESCRIPTION_LENGTH);
        }
    }
}
