package cindodcindy.androidbackend.controller;

import cindodcindy.androidbackend.model.KontenData;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.reactivex.annotations.Nullable;

import com.google.gson.Gson;


import java.util.Date;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import cindodcindy.androidbackend.repo.KontenDataRepository;


import java.util.HashMap;
import java.util.List;


@Validated
@Controller("/KontenData")
public class KontenDataController{

    private KontenDataRepository kontenDataRepository;

    public KontenDataController(KontenDataRepository kontenDataRepository){
        this.kontenDataRepository=kontenDataRepository;
    }

     @Get(processes = MediaType.APPLICATION_JSON)
    public String index(@Nullable @QueryValue final int page, @QueryValue final int limit){
        final HashMap<String, Object> data = new HashMap<>();
        try {
            final List<KontenData> kontenData= kontenDataRepository.findAll(page, limit);
            data.put("page", Math.ceil(kontenDataRepository.size()/limit));
            data.put("status", "ok");
            data.put("message", "Data Level");
            data.put("data", kontenData);
            return (new Gson().toJson(data));  
        } catch (Exception e) {
            data.put("status", "error");
            data.put("message", e.getMessage());
            return (new Gson().toJson(data));
        }
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String save(@Body final KontenData kontenData){
        final HashMap<String, Object> data = new HashMap<>();
        Long result = kontenDataRepository.save(kontenData);
        if (result != null) {
            data.put("status", "ok");
            data.put("id", result);
        } else {
            data.put("status", "fail");
        }
        return (new Gson()).toJson(data);
    }

    @Get("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String show(@PathVariable @Nullable final Long id){
        return (new Gson().toJson(kontenDataRepository.findById(id)));
    }

    @Put("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String update(@PathVariable @Nullable final Long id, @Body final KontenData kontenData){
        final HashMap<String, Object> data = new HashMap<>();
        if (kontenDataRepository.update(id, kontenData.getKeterangan(), kontenData.getGambar())) {
            data.put("status", "ok");
        }else{
            data.put("status", "fail");
        }
        return (new Gson().toJson(data));
    }

    @Delete("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String destroy(@PathVariable @Nullable final Long id){
        final HashMap<String, Object> data = new HashMap<>();
        if (kontenDataRepository.destroy(id)) {
            data.put("status", "ok");
        }else{
            data.put("status", "fail");
        }
        return (new Gson().toJson(data));
    }



}