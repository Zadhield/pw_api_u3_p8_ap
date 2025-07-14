package uce.edu.web.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.HijoService;
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.mapper.EstudianteMapper;
import uce.edu.web.api.service.to.EstudianteTo;

@Path("/estudiantes")
public class EstudianteController {

    @Inject
    private IEstudianteService estudianteService;
    @Inject
    private HijoService hijoService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {

        EstudianteTo estu = EstudianteMapper.toTo(this.estudianteService.buscarPorId(id));
        estu.buildURI(uriInfo);
        return Response.status(227).entity(estu).build();
    }

    // ?genero=Masculino&provincia=pichincha
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar estudiantes", description = "Este endpoint permite registrar un nuevo estudiante")
    public Response consultarTodos(@QueryParam("genero") String genero, @Context UriInfo uriInfo) {
        List<Estudiante> estudiantes = this.estudianteService.buscarTodos(genero);
        List<EstudianteTo> listaTo = new ArrayList<>();
        for (Estudiante e : estudiantes) {
            EstudianteTo to = EstudianteMapper.toTo(e);
            to.buildURI(uriInfo);
            listaTo.add(to);
        }
        return Response.status(Response.Status.OK).entity(listaTo).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar estudiante", description = "Este endpoint permite guardar un nuevo estudiante")
    public Response guardar(@RequestBody EstudianteTo estudianteto) {
        Estudiante estudiante = EstudianteMapper.toEntity(estudianteto);
        this.estudianteService.guardar(estudiante);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarPorId(@RequestBody EstudianteTo estudianteTo, @PathParam("id") Integer id) {
        Estudiante estudiante = EstudianteMapper.toEntity(estudianteTo);
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
        return Response.ok().build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarParcialPorId(@RequestBody EstudianteTo estudianteTo, @PathParam("id") Integer id) {
        Estudiante e = this.estudianteService.buscarPorId(id);
        if (estudianteTo.getApellido() != null) {
            e.setApellido(estudianteTo.getApellido());
        }
        if (estudianteTo.getNombre() != null) {
            e.setNombre(estudianteTo.getNombre());
        }
        if (estudianteTo.getFechaNacimiento() != null) {
            e.setFechaNacimiento(estudianteTo.getFechaNacimiento());
        }
        if (estudianteTo.getGenero() != null) {
            e.setGenero(estudianteTo.getGenero());
        }
        this.estudianteService.actualizarParcialPorId(e);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    public Response borrarporId(@PathParam("id") Integer id) {
        this.estudianteService.borrarPorId(id);
        return Response.status(Response.Status.OK).build();
    }

    // http://......../estudiantes/1/hijos GET
    @GET
    @Path("/{id}/hijos")
    public List<Hijo> obtenerHijosPorId(@PathParam("id") Integer id) {

        return this.hijoService.buscarPorEstudianteId(id);

    }

}
