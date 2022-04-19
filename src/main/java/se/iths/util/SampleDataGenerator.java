package se.iths.util;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

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

        Teacher teacher1 = new Teacher("Magistern");
        Teacher teacher2 = new Teacher("Clownen");
        Teacher teacher3 = new Teacher("Hunden");

        Subject subject1 = new Subject("Matte");
        Subject subject2 = new Subject("Historia");
        Subject subject3 = new Subject("Keramik");
        Subject subject4 = new Subject("Karate");

        teacher1.addSubject(subject1);
        teacher2.addSubject(subject2);
        teacher1.addSubject(subject3);
        teacher3.addSubject(subject4);

        student1.addSubject(subject1);
        student1.addSubject(subject2);
        student1.addSubject(subject3);
        student2.addSubject(subject2);
        student3.addSubject(subject1);
        student3.addSubject(subject3);
        student3.addSubject(subject2);
        student3.addSubject(subject4);
        student4.addSubject(subject3);
        student5.addSubject(subject1);

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
        entityManager.persist(student4);
        entityManager.persist(student5);
        entityManager.persist(teacher1);
        entityManager.persist(teacher2);
        entityManager.persist(teacher3);

    }
}
