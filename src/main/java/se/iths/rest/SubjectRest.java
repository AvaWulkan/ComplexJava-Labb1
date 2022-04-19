package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Path("")
    @POST
    public Response createSubject(Subject subject){
        subjectService.addSubject(subject);
        return Response.ok(subject).build();
    }

    @Path("{id}")
    @GET
    public Response findSubjectById(@PathParam("id") Long id){
        Subject foundSubject = subjectService.findSubjectById(id);
        if (foundSubject == null){
            throw  new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Subject with ID " + id + "\": \"NOT FOUND\"}")
                    .type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundSubject).build();
    }

    @Path("")
    @GET
    public Response getAllSubjects(){
        List<Subject> foundSubjects = subjectService.getAllSubjects();
        if (foundSubjects.isEmpty()){
            throw  new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Subjects\": \"EMPTY\"}")
                    .type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundSubjects).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id){
        Subject foundSubject = subjectService.findSubjectById(id);
        if (foundSubject == null){
            throw  new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Subject with ID " + id + "\": \"NOT FOUND\"}")
                    .type(MediaType.APPLICATION_JSON).build());
        }
        subjectService.deleteSubject(id);
        return Response.ok("{\"Subject with ID " + id + "\": \"DELETED\"}").type(MediaType.APPLICATION_JSON).build();
    }
}
