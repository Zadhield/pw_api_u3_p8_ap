package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.MateriaRepo;
import uce.edu.web.api.repository.modelo.Materia;

@ApplicationScoped
public class MateriaServiceImpl implements MateriaService {

    
    @Inject
    private MateriaRepo materiaRepo;

    @Override
    public List<Materia> buscarPorProfesorId(Integer id) {
        return this.materiaRepo.buscarPorProfesorId(id);
    }
}
