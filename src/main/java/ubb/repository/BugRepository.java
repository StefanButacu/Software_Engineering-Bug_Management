package ubb.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ubb.repository.entity.BugEntity;
import ubb.repository.entity.EmployeeEntity;
import ubb.utils.ApplicationException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class BugRepository {
    private final SessionFactory factory;

    public BugRepository(SessionFactory factory) {
        this.factory = factory;
    }

    public void save(BugEntity bug){
        getSession().save(bug);
    }

    public void delete(Long id){
        BugEntity entity = findById(id).orElseThrow( () -> new ApplicationException("Non existing bug with id" + id));
        getSession().delete(entity);
    }

    public void update(BugEntity entity){
        getSession().update(entity);
    }

    public Optional<BugEntity> findById(Long id){
        Query query = getSession().createQuery("SELECT b FROM BugEntity b WHERE id_bug=:id", BugEntity.class);
        query.setParameter("id", id);
        return (Optional<BugEntity>) query.getResultList().stream().findFirst();
    }

    public List<BugEntity> getAll(){
        return getSession().createQuery("SELECT bug FROM BugEntity bug", BugEntity.class).getResultList();
    }
    private Session getSession(){
        Session session = factory.getCurrentSession();
        if(session == null){
            session = factory.openSession();
        }
        return session;

    }
}

