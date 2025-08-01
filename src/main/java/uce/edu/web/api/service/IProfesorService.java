package uce.edu.web.api.service;

import java.util.List;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.to.ProfesorTo;

public interface IProfesorService {
    public Profesor buscarPorId( Integer id);
    
    public List<Profesor> buscarTodos();

    public void actualizarPorId(Profesor profesor);

    public void actualizarParcialPorId(Profesor profesor);

    public void borrarPorId(Integer id);

    public void guardar(Profesor profesor);

}
