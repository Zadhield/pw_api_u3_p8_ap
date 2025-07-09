package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.repository.HijoRepo;
import uce.edu.web.api.repository.modelo.Hijo;

@ApplicationScoped
public class HijoServiceImpl implements HijoService{

    private HijoRepo hijoRepo;

    @Override
    public List<Hijo> buscarPorEstudianteId(Integer id) {
        return this.hijoRepo.buscarPorEstudianteId(id);
    }

}
