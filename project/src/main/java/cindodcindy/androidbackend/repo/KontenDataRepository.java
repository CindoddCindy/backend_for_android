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

import cindodcindy.androidbackend.model.KontenData;
import cindodcindy.androidbackend.intfc.KontenDataInterface;

@Singleton
public class KontenDataRepository implements KontenDataInterface{

    @PersistenceContext
    private EntityManager entityManager;

    public KontenDataRepository(EntityManager entityManager){
        this.entityManager=entityManager;

    }

    @Transactional(readOnly = true)
    @Override
    public List<KontenDataRepository> findAll(int page, int limit){
        TypedQuery<KontenData> query = entityManager
        .creteQuery("form KontenData", KontenData.class)
        .setFirstResult(page > 1 ? page * limit - limit : 0)
        .setMaxResults(limit);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Long save(@NotNull KontenData kontenData){
        try{
            entityManager.persist(kontenData);
            return kontenData.getId();
        }catch(Exception e){
            return null;
        }
    }

@Transactional(readOnly = true)
@Override
public Long size(){
    return entityManager.creteQuery("select count(*) from KontenData", Long.class).getSingleResult();

}

@Transactional(readOnly = true)
@Override
public KontenData findById(@NotNull Long id){
    return entityManager.find(KontenData.class,id);
}

@Transactional
@Override
public boolean update(@NotNull Long id, String name){
    try{

        KontenData kontenData = entityManager.find(KontenData.class,id);
        if(keterangan !=null) kontenData.setKeterangan(keterangan);
        if(gambar != null) kontenData.setGambar(gambar);
        kontenData.setUpdated(new Date());
        return true;
    }catch (Exception e){
        return false;
    }
}

@Transactional
@Override
public boolean destroy(@NotNull Long id){
    try{
        KontenData kontenData = entityManager.find(KontenData.class,id);
        entityManager.remove(kontenData);
        return true;
    }catch(Exception e){
        return false;
    }
}


}