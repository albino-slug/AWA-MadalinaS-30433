package main.model;

import org.springframework.lang.NonNull;

import javax.annotation.Nonnegative;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "items")
public class Item {
    public static final int DESCRIPTION_LENGTH = 500;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer id;
    @Column
    @NonNull
    private String title;
    @Column
    @NonNull
    private String creator;
    @Column
    @NonNull
    @Enumerated(EnumType.STRING)
    private ItemType type;
    @Column
    private String description;

    public Item(@NonNull String title, @NonNull String creator, @NonNull ItemType type, String description) {
        this.title = title;
        this.creator = creator;
        this.description = description;
        this.type = type;
    }

    @Override

    public String toString(){
        return "id:" + this.getId() + " " +
                "title:" + this.getTitle() + " " +
                "creator:" + this.getCreator() + " " +
                "type:" + this.getType();
    }

    public Item(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
