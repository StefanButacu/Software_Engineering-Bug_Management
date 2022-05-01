package ubb.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ubb.repository.entity.EmployeeEntity;
import ubb.repository.entity.RoleEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class RoleRepository {
    @Autowired
    private SessionFactory factory;
    public List<RoleEntity> getAll(){
        return getSession().createQuery("SELECT role FROM RoleEntity role", RoleEntity.class).getResultList();
    }

    private Session getSession(){
        Session session = factory.getCurrentSession();
        if(session == null){
            session = factory.openSession();
        }
        return session;

    }

    public Optional<RoleEntity> findRoleEntityByName(String roleName) {
        Query query = getSession().createQuery("SELECT r FROM RoleEntity r WHERE role=:role", RoleEntity.class);
        query.setParameter("role", roleName);
        return (Optional<RoleEntity>) query.getResultList().stream().findFirst();

    }
}
