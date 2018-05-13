package main.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column
    @NonNull
    @Pattern(regexp = "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+$", message = "Invalid name")
    private String name;
    @Column
    @Min(value = 14, message = "too young to be an employee")
    @NonNull
    private int age;
    @Column
    @NonNull
    @Length(min = 3, message = "Password is too short")
    private String password;
    @Column
    @NonNull
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(){}

    public User(@NonNull @Pattern(regexp = "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+$", message = "Invalid name") String name, @NonNull @Min(value = 14, message = "too young to be an employee") int age, @NonNull @Length(min = 3, message = "Password is too short") String password, @NonNull Role role) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.role = role;
    }

    @Override

    public String toString() {
        return "id:" + this.getId() + " " +
                "name:" + this.getName() + " " +
                "age:" + this.getAge() + " " +
                "role:" + this.getRole();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
