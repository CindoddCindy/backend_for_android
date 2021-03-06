package cindodcindy.androidbackend.controller;

import cindodcindy.androidbackend.model.Admin;
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
import cindodcindy.androidbackend.repo.AdminRepository;


import java.util.HashMap;
import java.util.List;


@Validated
@Controller("/Admin")
public class AdminController{

    private AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository){
        this.adminRepository=adminRepository;
    }

     @Get(processes = MediaType.APPLICATION_JSON)
    public String index(@Nullable @QueryValue final int page, @QueryValue final int limit){
        final HashMap<String, Object> data = new HashMap<>();
        try {
            final List<Admin> admin= adminRepository.findAll(page, limit);
            data.put("page", Math.ceil(adminRepository.size()/limit));
            data.put("status", "ok");
            data.put("message", "Data Admin");
            data.put("data", adminRepository);
            return (new Gson().toJson(data));  
        } catch (Exception e) {
            data.put("status", "error");
            data.put("message", e.getMessage());
            return (new Gson().toJson(data));
        }
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String save(@Body final Admin admin){
        final HashMap<String, Object> data = new HashMap<>();
        Long result = adminRepository.save(admin);
        if (result != null) {
            data.put("status", "ok");
            data.put("id", result);
        } else {
            data.put("status", "fail");
        }
        return (new Gson()).toJson(data);
    }

    @Get("{/admin_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String show(@PathVariable @Nullable final Long admin_id){
        return (new Gson().toJson(adminRepository.findById(admin_id)));
    }

    @Put("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String update(@PathVariable @Nullable final Long admin_id, @Body final Admin admin){
        final HashMap<String, Object> data = new HashMap<>();
        if (adminRepository.update(admin_id, admin.getAdminPost())) {
            data.put("status", "ok");
        }else{
            data.put("status", "fail");
        }
        return (new Gson().toJson(data));
    }

    @Delete("{/admin_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String destroy(@PathVariable @Nullable final Long admin_id){
        final HashMap<String, Object> data = new HashMap<>();
        if (adminRepository.destroy(admin_id)) {
            data.put("status", "ok");
        }else{
            data.put("status", "fail");
        }
        return (new Gson().toJson(data));
    }



}