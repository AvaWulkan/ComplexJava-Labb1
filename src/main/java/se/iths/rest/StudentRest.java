package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Path("")
    @POST
    public Response createStudent(Student student){
        studentService.addStudent(student);
        return Response.ok(student).build();
    }

    @Path("")
    @PUT
    public Response updateStudent(Student student){
        studentService.updateStudent(student);
        return Response.ok(student).build();
    }

    @Path("{id}")
    @GET
    public Response findStudentById(@PathParam("id") Long id){
        Student foundStudent = studentService.findStudentById(id);
        if (foundStudent == null){
            throw  new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Student with ID " + id + "\": \"NOT FOUND\"}")
                    .type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundStudent).build();
    }

    @Path("lastname:{lastname}")
    @GET
    public Response findStudentByLastName(@PathParam("lastname") String lastName){
        List<Student>  foundStudents = studentService.findStudentByLastName(lastName);
        if (foundStudents.isEmpty()){
            throw  new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Students with lastname " + lastName + "\": \"NOT FOUND\"}")
                    .type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundStudents).build();
    }

    @Path("")
    @GET
    public Response getAllStudents(){
        List<Student> foundStudents = studentService.getAllStudents();
        if (foundStudents.isEmpty()){
            throw  new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Students\": \"EMPTY\"}")
                    .type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundStudents).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id){
        Student foundStudent = studentService.findStudentById(id);
        if (foundStudent == null){
            throw  new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Student with ID " + id + "\": \"NOT FOUND\"}")
                    .type(MediaType.APPLICATION_JSON).build());
        }
        studentService.deleteStudent(id);
        return Response.ok("{\"Student with ID " + id + "\": \"DELETED\"}").type(MediaType.APPLICATION_JSON).build();
    }

    @Path("{id}")
    @PATCH
    public Response updatePhoneNumber(@PathParam("id") Long id, Student student){
        if (studentService.findStudentById(id) == null){
            throw  new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Student with ID " + id + "\": \"NOT FOUND\"}")
                    .type(MediaType.APPLICATION_JSON).build());
        }
        Student updatedStudent = studentService.updatePhoneNumber(id, student.getPhoneNumber());
        return Response.ok(updatedStudent).build();
    }
}
