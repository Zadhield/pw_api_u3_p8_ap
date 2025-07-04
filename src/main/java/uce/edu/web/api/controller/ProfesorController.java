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
import uce.edu.web.api.service.to.ProfesorTo;

@Path("/profesores")
public class ProfesorController {

    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ProfesorTo profe = this.profesorService.buscarPorId(id, uriInfo);
        return Response.status(Response.Status.OK).entity(profe).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarTodos() {
        return Response.status(Response.Status.OK).entity(this.profesorService.buscarTodos()).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardar(@RequestBody Profesor profesor) {
        this.profesorService.guardar(profesor);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarPorId(@RequestBody Profesor profesor, @PathParam("id") Integer id) {
        profesor.setId(id);
        this.profesorService.actualizarPorId(profesor);
        return Response.status(Response.Status.OK).build();
    }

    /*
     * @PATCH
     * 
     * @Path("/{id}")
     * 
     * @Consumes(MediaType.APPLICATION_JSON)
     * public Response actualizarParcial(@RequestBody Profesor
     * profesor, @PathParam("id") Integer id) {
     * profesor.setId(id);
     * Profesor p = this.profesorService.buscarPorId(id);
     * if (profesor.getNombre() != null) {
     * p.setNombre(profesor.getNombre());
     * }
     * if (profesor.getApellido() != null) {
     * p.setApellido(profesor.getApellido());
     * }
     * if (profesor.getTelf() != null) {
     * p.setTelf(profesor.getTelf());
     * }
     * if (profesor.getCedula() != null) {
     * p.setCedula(profesor.getCedula());
     * }
     * this.profesorService.actualizarParcialPorId(p);
     * return Response.status(Response.Status.OK).build();
     * }
     */

    @DELETE
    @Path("/{id}")
    public Response borrarporId(@PathParam("id") Integer id) {
        this.profesorService.borrarPorId(id);
        return Response.status(Response.Status.OK).build();

    }

    @GET
    @Path("/{id}/materias")
    public List<Materia> obtenerMateriasPorId(@PathParam("id") Integer id) {

        Materia m1 = new Materia();
        m1.setNombre("Programacion 1");
        Materia m2 = new Materia();
        m2.setNombre("Inteligencia Artificial");
        List<Materia> materias = new ArrayList<>();
        materias.add(m1);
        materias.add(m2);
        return materias;

    }

}
