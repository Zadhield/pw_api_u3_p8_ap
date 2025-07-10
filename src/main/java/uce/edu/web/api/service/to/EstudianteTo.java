package uce.edu.web.api.service.to;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.EstudianteController;

public class EstudianteTo {

    private Integer id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String genero;
    private Map<String, String> _links = new HashMap<>();


    public Integer getId() {
        return id;
    }

    public Map<String, String> get_links() {
        return _links;
    }

    public void buildURI( UriInfo uriInfo){
        URI todosHijos = uriInfo.getBaseUriBuilder().path(EstudianteController.class)
                .path(EstudianteController.class, "obtenerHijosPorId").build(id);
                _links.put("hijos", todosHijos.toString());
    }

    public void set_links(Map<String, String> _links) {
        this._links = _links;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
