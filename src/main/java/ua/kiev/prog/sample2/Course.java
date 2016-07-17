package ua.kiev.prog.sample2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="Course")
@NamedQuery(name="Course.findAll", query = "SELECT c FROM Course c")
public class Course {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Group> groups = new ArrayList<>();

    public Course(){};

    public Course(String name) {
        this.name = name;
    }

    public void addGroup(Group group) {
        group.setCourse(this);
        groups.add(group);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Group> getGroups(){return Collections.unmodifiableList(groups);}


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
