package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher){
        teacherService.addTeacher(teacher);
        return Response.ok(teacher).build();
    }

    @Path("{id}")
    @GET
    public Response findTeacherById(@PathParam("id") Long id){
        Teacher foundTeacher = teacherService.findTeacherById(id);
        if (foundTeacher == null){
            throw  new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Teacher with ID " + id + "\": \"NOT FOUND\"}")
                    .type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundTeacher).build();
    }

    @Path("")
    @GET
    public Response getAllTeachers(){
        List<Teacher> foundTeachers = teacherService.getAllTeachers();
        if (foundTeachers.isEmpty()){
            throw  new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Teachers\": \"EMPTY\"}")
                    .type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundTeachers).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id){
        Teacher foundTeacher = teacherService.findTeacherById(id);
        if (foundTeacher == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Teacher with ID " + id + "\": \"NOT FOUND\"}")
                    .type(MediaType.APPLICATION_JSON).build());
        }
        teacherService.deleteTeacher(id);
        return Response.ok("{\"Teacher with ID " + id + "\": \"DELETED\"}").type(MediaType.APPLICATION_JSON).build();

    }
}
