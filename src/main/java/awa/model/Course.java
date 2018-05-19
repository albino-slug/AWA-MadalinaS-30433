package awa.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column
    @NotNull
    private String description;

    @ManyToMany(mappedBy = "courses")
    private List<User> users = new ArrayList<User>();

    private static final Integer maxUsers = 15;

    public Course(){}

    public Course(@NotNull String name, @NotNull Date endDate, @NotNull Date startDate, @NotNull String description) {
        this.name = name;
        this.endDate = endDate;
        this.startDate = startDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean enrollUser(Optional<User> user){
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
        if (!(v instanceof Course)) {
            return false;
        }
        return ((Course)v).getId().intValue() == this.getId().intValue();
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
