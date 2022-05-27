package ubb.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ubb.repository.entity.AssignmentEntity;
import ubb.utils.ApplicationException;

import java.util.List;

@Repository
public class AssignmentRepository {
    private final SessionFactory factory;

    public AssignmentRepository(SessionFactory factory) {
        this.factory = factory;
    }

    public void save(AssignmentEntity assignmentEntity){
        getSession().save(assignmentEntity);
    }


    public List<AssignmentEntity> getAll(){
        return getSession().createQuery("SELECT entity FROM AssignmentEntity as entity", AssignmentEntity.class).getResultList();
    }

    private Session getSession(){
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
//        Session session = factory.getCurrentSession();
//        if(session == null){
//            session = factory.openSession();
//        }
        return session;

    }

    public AssignmentEntity findAssignmentByIdBug(Long idBug) {
        List<AssignmentEntity>  list = getSession().createQuery("SELECT a FROM AssignmentEntity a WHERE a.bug.id = :id").setParameter("id", idBug).getResultList();
        if( list.size() <= 0)
            throw new ApplicationException("Invalid query on assignemnts");
        return list.get(0);


    }
}
