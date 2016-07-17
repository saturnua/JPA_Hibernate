package ua.kiev.prog.sample2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="Groups")
@NamedQuery(name="Groups.findAll", query = "SELECT c FROM Group c")
public class Group {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Client> clients = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id")
    private Course course;

    public Group() {}

    public Group(String name) {
        this.name = name;
    }

    public void addClient(Client client) {
        client.setGroup(this);
        clients.add(client);
    }

    public List<Client> getClients() {
        return Collections.unmodifiableList(clients);
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

    public Course getCourse() {return course;}

    public void setCourse(Course course) {this.course = course;}

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
