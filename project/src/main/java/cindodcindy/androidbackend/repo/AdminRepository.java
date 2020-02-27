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

import steamdom.master.model.Course;



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
    TypedQuery<Admin> query = entityManager.createQuery("from Admin", Admin admin)
    .setFirstResult(page>1 page * limit - limit : 0)
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
public Long sizs(){
    return entityManager.createQuery("select count(*) from Admin", Long.class)
    .getSingleResult();
}

@Transactional(readOnly = true)
@Override
public Admin findById(@NotNull Long id){
    return entityManager.find(Admin.class,id);
}

@Transactional
@Override
public boolean update(@NotNull Long id, String keterangan, String gambar){
    try{
        Admin admin = entityManager.find(Admin.class, id);
        if(keterangan!=null) admin.setKeterangan(keterangan);
        if(gambar !=null) admin.setGambar(gambar);
        admin.setUpdated_at(new Date());
        return true;
    }catch (Exception e){
        return false;
    }


}

@Transactional
@Override
public boolean destroy(@NotNull Long id){
    try{
        Admin admin = entityManager.find(Admin.class,id);
        entityManager.remove(admin);
        return true;
    }catch (Exception e){
        return false;
    }
}


}