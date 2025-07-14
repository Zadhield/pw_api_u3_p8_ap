package uce.edu.web.api.service.to;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.ProfesorController;

public class ProfesorTo {
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer telf;
    private Integer cedula;
    public Map<String, String> _links = new HashMap<>();

  
     public void buildURI (UriInfo uriInfo){
         URI todasMaterias = uriInfo.getBaseUriBuilder().path(ProfesorController.class)
        .path(ProfesorController.class,"obtenerMateriasPorId").build(id);
        _links.put("materias",todasMaterias.toString());
     }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Map<String, String> get_links() {
        return _links;
    }

    public void set_links(Map<String, String> _links) {
        this._links = _links;
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

    public Integer getTelf() {
        return telf;
    }

    public void setTelf(Integer telf) {
        this.telf = telf;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

}
