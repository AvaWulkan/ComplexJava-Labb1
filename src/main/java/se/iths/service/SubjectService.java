package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public void addSubject(Subject subject){
        if(subject.getName().isEmpty()){
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("{\"name\": \"REQUIRED\"}")
                    .type(MediaType.APPLICATION_JSON).build());
        }
        entityManager.persist(subject);
    }

    public Subject findSubjectById(Long id){
        return entityManager.find(Subject.class, id);
    }

    public List<Subject> findSubjectByName(String name){
        String query = "SELECT s from Subject s where s.name = :name";
        return entityManager.createQuery(query, Subject.class).setParameter("name", name).getResultList();
    }

    public List<Subject> getAllSubjects(){
        return entityManager.createQuery("SELECT s from Subject s", Subject.class).getResultList();
    }

    public void deleteSubject(Long id){
        Subject foundSubject = entityManager.find(Subject.class, id);
        for(Student student: foundSubject.getStudents()){
            student.removeSubject(foundSubject);
        }
        foundSubject.getTeacher().removeSubject(foundSubject);
        entityManager.remove(foundSubject);
    }
}
