package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.modelo.Materia;

public interface MateriaRepo {
    public List <Materia> buscarPorProfesorId(Integer id);
}
