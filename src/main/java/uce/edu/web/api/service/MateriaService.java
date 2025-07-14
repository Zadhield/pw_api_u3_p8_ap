package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.modelo.Materia;

public interface MateriaService {
    public List<Materia> buscarPorProfesorId(Integer id);
       

}
