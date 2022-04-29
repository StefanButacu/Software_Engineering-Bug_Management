package ubb.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ubb.repository.entity.EmployeeEntity;
import ubb.utils.ApplicationException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class EmployeeRepository {
    @Autowired
    private SessionFactory factory;


    public void save(EmployeeEntity entity){
        getSession().save(entity);
    }

    public void delete(Long id){
        EmployeeEntity entity = findById(id).orElseThrow( () -> new ApplicationException("Non existing employee with id" + id));
        getSession().delete(entity);


    }


    public void update(EmployeeEntity entity){
        getSession().update(entity);
    }

    public Optional<EmployeeEntity> findById(Long id){
        Query query = getSession().createQuery("SELECT e FROM EmployeeEntity e WHERE id_employee=:id", EmployeeEntity.class);
        query.setParameter("id", id);
        return (Optional<EmployeeEntity>) query.getResultList().stream().findFirst();

    }

    public Optional<EmployeeEntity> findByUsername(String username){

        Query query = getSession().createQuery("SELECT e FROM EmployeeEntity e WHERE username=:username", EmployeeEntity.class);
        query.setParameter("username", username);
        return (Optional<EmployeeEntity>) query.getResultList().stream().findFirst();

    }

    public List<EmployeeEntity> getAll(){
        return getSession().createQuery("SELECT e FROM EmployeeEntity e", EmployeeEntity.class).getResultList();
    }


    private Session getSession(){
        Session session = factory.getCurrentSession();
        if(session == null){
            session = factory.openSession();
        }
        return session;

    }

}
