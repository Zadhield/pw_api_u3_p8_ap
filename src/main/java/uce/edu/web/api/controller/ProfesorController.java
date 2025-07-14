package uce.edu.web.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Materia;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;
import uce.edu.web.api.service.MateriaService;
import uce.edu.web.api.service.mapper.ProfesorMapper;
import uce.edu.web.api.service.to.ProfesorTo;

@Path("/profesores")
public class ProfesorController {

    @Inject
    private IProfesorService profesorService;
    @Inject
    private MateriaService materiaService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ProfesorTo profe = ProfesorMapper.toTo(this.profesorService.buscarPorId(id));
        profe.buildURI(uriInfo);
        return Response.status(Response.Status.OK).entity(profe).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarTodos(@Context UriInfo uriInfo) {
        List<Profesor> profesores = this.profesorService.buscarTodos();
        List<ProfesorTo> listaTo = new ArrayList<>();
        for (Profesor p : profesores) {
            ProfesorTo profe = ProfesorMapper.toTo(p);
            profe.buildURI(uriInfo);
            listaTo.add(profe);
        }
        return Response.status(Response.Status.OK).entity(listaTo).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardar(@RequestBody ProfesorTo profesorTo) {
        Profesor profesor = ProfesorMapper.toEntity(profesorTo);
        this.profesorService.guardar(profesor);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarPorId(@RequestBody ProfesorTo profesorTo, @PathParam("id") Integer id) {
        Profesor profesor = ProfesorMapper.toEntity(profesorTo);
        profesor.setId(id);
        this.profesorService.actualizarPorId(profesor);
        return Response.ok().build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarParcialPorId(@RequestBody ProfesorTo profesorTo, @PathParam("id") Integer id) {
        Profesor p = this.profesorService.buscarPorId(id);
        if (profesorTo.getNombre() != null) {
            p.setNombre(profesorTo.getNombre());
        }
        if (profesorTo.getApellido() != null) {
            p.setApellido(profesorTo.getApellido());
        }
        if (profesorTo.getTelf() != null) {
            p.setTelf(profesorTo.getTelf());
        }
        if (profesorTo.getCedula() != null) {
            p.setCedula(profesorTo.getCedula());
        }
        this.profesorService.actualizarParcialPorId(p);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    public Response borrarporId(@PathParam("id") Integer id) {
        this.profesorService.borrarPorId(id);
        return Response.status(Response.Status.OK).build();

    }

    @GET
    @Path("/{id}/materias")
    public List<Materia> obtenerMateriasPorId(@PathParam("id") Integer id) {

        return this.materiaService.buscarPorProfesorId(id);

    }

}