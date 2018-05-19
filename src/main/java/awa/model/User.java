package awa.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "users")
public class User{

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @NotNull
    @Min(value = 16, message = "Must be over 16 years of age in order to have an account")
    private Integer age;

    @Column
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+$", message = "Invalid Username")
    private String username;

    @Column
    @NotNull
    @Length(min = 4, message = "Password has to be at least 4 characters long")
    @Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$", message = "Password must have at least one digit, one lowercase and one uppercase letter, one special character and mustn't contain any whitespaces")
    private String password;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "User_Event",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "event_id") }
    )
    List<Event>  events  = new ArrayList<Event>();

    @Column
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Incorrect e-mail format")
    private String email;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "User_Course",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    List<Course> courses = new ArrayList<Course>();

    public User(){}

    public User(@NotNull @Pattern(regexp = "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+$", message = "Invalid Username") String username, @NotNull @Min(value = 16, message = "Must be over 16 years of age in order to have an account") int age, @NotNull @Length(min = 4, message = "Password has to be at least 4 characters long") @Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$", message = "Password must have at least one digit, one lowercase and one uppercase letter, one special character and mustn't contain any whitespaces") String password, @NotNull Role role, @NotNull @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Incorrect e-mail format") String email) {
        this.age = age;
        this.role = role;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }



    public void addCourseToUser(Optional<Course> course){
        if (course.isPresent() && !courses.contains(course.get())){
            courses.add(course.get());
        }
    }

    public void addEventToUser(Optional<Event> event){
        if (event.isPresent() && !events.contains(event.get())){
            events.add(event.get());
        }
    }

    public void dropCourse(Optional<Course> course){
        if (course.isPresent() && courses.contains(course.get())){
            courses.remove(course.get());
        }
    }

    public void dropEvent(Optional<Event> event){
        if (event.isPresent() && events.contains(event.get())){
            events.remove(event.get());
        }
    }

    @Override
    public boolean equals(Object v) {
        if (v == null){
            return false;
        }
        if (!(v instanceof User)) {
            return false;
        }
        return ((User)v).getId().intValue() == this.getId().intValue();
    }

    @Override
    public String toString() {
        return "User{" +
                "role=" + role +
                ", id=" + id +
                ", age=" + age +
                ", email=" + email +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
