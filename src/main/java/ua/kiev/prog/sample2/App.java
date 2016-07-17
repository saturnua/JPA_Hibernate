package ua.kiev.prog.sample2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class App {
    public static void main( String[] args ) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
        EntityManager em = emf.createEntityManager();
        try {
            Course course = new Course("Java EE Pro ");
            Client client;
            String courseName;

            // #1
            System.out.println("------------------ #1 ------------------");
            for (int i = 0; i < 3; i++) {
                course.addGroup(new Group("Group" + (i+1)));
                for (int j = 0; j < 5; j++) {
                    client = new Client("Name" + j, j);
                    course.getGroups().get(i).addClient(client);
                }
            }

            em.getTransaction().begin();
            try {
               em.persist(course);  // save course with groups and clients
            } catch (Exception ex) {
                em.getTransaction().rollback();
                return;
            }


           // #2
            System.out.println("------------------ #2 ------------------");

            Query query  = em.createNamedQuery("Groups.findAll", Group.class);
            List<Group> groupsList = query.getResultList();

            Query query1 = em.createNamedQuery("Course.findAll",Course.class);
            List<Course> courseList = query1.getResultList();

            for( Course course1 : courseList) {
                System.out.println(course1.toString() + "  Names of group :" );
                for (Group group : groupsList) {
                    System.out.println("    " + group.toString() + "numbers of students - " + group.getClients().size()+ "    ");
                }
            }



        } finally {
            em.close();
            emf.close();
        }
    }
}
