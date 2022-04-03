package se.iths.util;

import se.iths.entity.Student;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData() {
        Student student1 = new Student("Ava", "Wulkan", "ava.wulkan@iths.se", "0701 23 45 67");
        Student student2 = new Student("Knut", "Knutsson", "knut.knutsson@iths.se", "046 67 68 34");
        Student student3 = new Student("Henrik", "Wulkan", "henrik.wulkan@iths.se", "");
        Student student4 = new Student("Asta", "Karlsson", "asta.karlsson@iths.se", "08 53 53 53");
        Student student5 = new Student("Zorro", "Classified", "zorro@iths.se", "");


        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
        entityManager.persist(student4);
        entityManager.persist(student5);
    }
}
