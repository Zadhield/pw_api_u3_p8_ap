package uce.edu.web.api.repository;

import java.util.List;

import io.quarkus.hibernate.orm.runtime.dev.HibernateOrmDevInfo.Entity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Materia;
@ApplicationScoped
@Transactional
public class MateriaRepoImpl implements MateriaRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Materia> buscarPorProfesorId(Integer id) {
        TypedQuery<Materia> myQuery = this.entityManager
                .createQuery("SELECT m FROM Materia m WHERE m.profesor.id =: id", Materia.class);
        myQuery.setParameter("id", id);
        return myQuery.getResultList();
    }

}
