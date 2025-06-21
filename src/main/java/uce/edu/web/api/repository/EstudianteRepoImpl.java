package uce.edu.web.api.repository;

import io.quarkus.hibernate.orm.runtime.dev.HibernateOrmDevInfo.Entity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Estudiante;
@Transactional
@ApplicationScoped
public class EstudianteRepoImpl implements IEstudianteRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Estudiante seleccionarPorId(Integer id) {
       return this.entityManager.find(Estudiante.class,id);
        
    }

}
