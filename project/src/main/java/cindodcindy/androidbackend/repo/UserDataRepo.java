package cindodcindy.androidbackend.repo;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

import cindodcindy.androidbackend.model.UserData;
import cindodcindy.androidbackend.intfc.UserDataInf;

@Singleton
public class UserDataRepo implements UserDataInf{

    @PersistenceContext
    private EntityManager entityManager;

    public UserDataRepo(@CurrentSession EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserData> findAll(int page, int limit){
        TypedQuery<UserData> query = entityManager
        .createQuery("from UserData", UserData.class)
        .setFirstResult(page>1? page * limit - limit : 0)
        .setMaxResults(limit);
        return query.getResultList();
    }

    @Transactional
    @Override
    public String save(@NotNull UserData userData){
        try{
            entityManager.persist(userData);
            return userData.getName();
        }catch (Exception e){
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Long size(){
        return entityManager.createQuery("select count (*) from UserData", Long.class).getSingleResult();


    }


    @Transactional(readOnly = true)
    @Override
    public UserData findById(@NotNull Long id) {
        return entityManager.find(UserData.class, id);
    }


    @Transactional
    @Override
    public boolean update(@NotNull Long id, String name, String email, String password){
        try{
            UserData userData = entityManager.find(UserData.class,id);
            if(name !=null) userData.setName(name);
            if(email !=null) userData.setEmail(email);
            if(password!=null) userData.setPassword(password);
            userData.setUpdated(new Date());
            return true;
        }catch (Exception e){
            return false;
        }

    }


    @Transactional
    @Override
    public boolean destroy(@NotNull Long id){
        try{
            UserData userData = entityManager.find(UserData.class,id);
            entityManager.remove(userData);
            return true;
        }catch(Exception e){
            return false;
        }

        
    }

    
}