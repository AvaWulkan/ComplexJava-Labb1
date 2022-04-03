package se.iths.service;

import se.iths.entity.Student;
import se.iths.util.NonNumericalEntryException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void addStudent(Student student){
        if(student.getFirstName().isEmpty() || student.getLastName().isEmpty() || student.getEmail().isEmpty()){
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("All field except phone number is mandatory.")
                    .type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        if(!checkIfPhoneNumberIsNumeric(student.getPhoneNumber())){
            throw new NonNumericalEntryException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("Phone number can only contain numbers and whitespace.")
                    .type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        if(checkIfEmailIsDuplicate(student.getEmail(), student.getId())){
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("Email " + student.getEmail() + " already exists.")
                    .type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        entityManager.persist(student);
    }

    public void updateStudent(Student student){
        if(student.getFirstName().isEmpty() || student.getLastName().isEmpty() || student.getEmail().isEmpty()){
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("All field except phonenumber is mandatory.")
                    .type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        if(checkIfEmailIsDuplicate(student.getEmail(), student.getId())){
            throw  new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("Email " + student.getEmail() + " already exists.")
                    .type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        entityManager.merge(student);
    }

    public Student findStudentById(Long id){
        return entityManager.find(Student.class, id);
    }

    public List<Student> findStudentByLastName(String lastName){
        String query = "SELECT s from Student s where s.lastName = :lastname";
        return entityManager.createQuery(query, Student.class).setParameter("lastname", lastName).getResultList();
    }

    public List<Student> getAllStudents(){
        return entityManager.createQuery("SELECT s from Student s", Student.class).getResultList();
    }

    public void deleteStudent(Long id){
        Student foundStudent = entityManager.find(Student.class, id);
        entityManager.remove(foundStudent);
    }

    public Student updatePhoneNumber(Long id, String phoneNumber){
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setPhoneNumber(phoneNumber);
        return foundStudent;
    }

    public boolean checkIfEmailIsDuplicate(String email, Long id){
        List<Student> students = getAllStudents();
        for (Student s: students) {
            if(s.getEmail().equals(email)){
                if(!s.getId().equals(id)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkIfPhoneNumberIsNumeric(String phoneNumber) {
        return phoneNumber.matches("([0-9]+\\s*)+");
    }
}
