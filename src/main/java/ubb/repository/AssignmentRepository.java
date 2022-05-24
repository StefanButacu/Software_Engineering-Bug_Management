package ubb.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ubb.repository.entity.AssignmentEntity;

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
        return null;
        // todo
    }
}
