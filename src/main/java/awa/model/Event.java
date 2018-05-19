package awa.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "events")
public class Event {

    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String description;

    @ManyToMany(mappedBy = "events")
    private List<User> users = new ArrayList<User>();

    private static final Integer maxUsers = 20;


    public Event(){}

    public Event(@NotNull Date date, @NotNull String name, @NotNull String description) {
        this.date = date;
        this.name = name;
        this.description = description;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean userAttend(Optional<User> user){
        if (users.size() < maxUsers && user.isPresent()){
            users.add(user.get());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void dropUser(Optional<User> user){
        if (user.isPresent()) {
            users.remove(user.get());
        }
    }

    @Override
    public boolean equals(Object v) {
        if (v == null){
            return false;
        }
        if (!(v instanceof Event)) {
            return false;
        }
        return ((Event)v).getId().intValue() == this.getId().intValue();
    }


    @Override
    public String toString() {
        return "Event{" +
                "date=" + date +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
