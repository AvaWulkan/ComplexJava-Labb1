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
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public void addTeacher(Teacher teacher){
        if(teacher.getName().isEmpty()){
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("{\"name\": \"REQUIRED\"}")
                    .type(MediaType.APPLICATION_JSON).build());
        }
        entityManager.persist(teacher);
    }

    public Teacher findTeacherById(Long id){
        return entityManager.find(Teacher.class, id);
    }

    public List<Teacher> findTeacherByName(String name){
        String query = "SELECT s from Teacher s where s.name = :name";
        return entityManager.createQuery(query, Teacher.class).setParameter("name", name).getResultList();
    }

    public List<Teacher> getAllTeachers(){
        return entityManager.createQuery("SELECT s from Teacher s", Teacher.class).getResultList();
    }

    public void deleteTeacher(Long id){
        Teacher foundTeacher = entityManager.find(Teacher.class, id);

        for (Subject subject: foundTeacher.getSubjects()){

            for(Student student: subject.getStudents()){
                student.removeSubject(subject);
            }
        }
        entityManager.remove(foundTeacher);
    }
}
