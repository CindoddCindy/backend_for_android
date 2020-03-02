package cindodcindy.androidbackend.repo;

import java.util.Date;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import cindodcindy.androidbackend.model.Admin;
import cindodcindy.androidbackend.intfc.AdminInterface;



@Singleton
public class AdminRepository implements AdminInterface{
    @PersistenceContext
    private EntityManager entityManager;

    public AdminRepository(@CurrentSession EntityManager entityManager){
        this.entityManager=entityManager;
    }

@Transactional(readOnly=true)
@Override
public List<Admin> findAll(int page, int limit){
    TypedQuery<Admin> query = entityManager
    .createQuery("from Admin", Admin.class)
    .setFirstResult(page>1 ? page * limit - limit : 0)
    .setMaxResults(limit);
    return query.getResultList();
}

@Transactional
@Override
public Long save(@NotNull Admin admin){
    try{
        entityManager.persist(admin);
        return admin.getId();
    }catch(Exception e){
        return null;
    }
}

@Transactional(readOnly = true)
@Override
public Long size(){
    return entityManager.createQuery("select count(*) from Admin", Long.class)
    .getSingleResult();
}

@Transactional(readOnly = true)
@Override
public Admin findById(@NotNull Long admin_id){
    return entityManager.find(Admin.class,admin_id);
}

@Transactional
@Override
public boolean update(@NotNull Long admin_id, String admin_post){
    try{
        Admin admin = entityManager.find(Admin.class, admin_id);
        if(admin_post !=null) admin.setAdminPost(admin_post);
        admin.setUpdated(new Date());
        return true;
    }catch (Exception e){
        return false;
    }


}

@Transactional
@Override
public boolean destroy(@NotNull Long admin_id){
    try{
        Admin admin = entityManager.find(Admin.class,admin_id);
        entityManager.remove(admin);
        return true;
    }catch (Exception e){
        return false;
    }
}


}