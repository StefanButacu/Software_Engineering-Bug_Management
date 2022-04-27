package ubb.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ubb.repository.entity.EmployeeEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {
    @Autowired
    private SessionFactory factory;


    public void save(EmployeeEntity entity){
        getSession().save(entity);
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
