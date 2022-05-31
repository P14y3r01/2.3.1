package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImpl() {
        this.entityManager = entityManager;
    }

    @Override
    public void add (User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(long id){
        entityManager.createQuery("delete from User where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public User getUser(long id){
        return entityManager.find(User.class, id);
    }

    @Override
    public void change( User user){
        entityManager.merge(user);
    }

    @Override
    public List<User> all(){
        return entityManager.createQuery("from User", User.class).getResultList();
    }

}
