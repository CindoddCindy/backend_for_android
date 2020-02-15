package cindodcindy.androidbackend.controller;

import cindodcindy.androidbackend.model.UserData;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.reactivex.annotations.Nullable;
import io.micronaut.http.annotation.QueryValue;

import com.google.gson.Gson;
import cindodcindy.androidbackend.repo.UserDataRepo;
import cindodcindy.androidbackend.intfc.UserDataInf;

import java.util.HashMap;
import java.util.List;

@Validated
@Controller("/user_data")
public class UserDataController{

    private UserDataInf userDataInf;

    public UserDataController(UserDataInf userDataInf){
        this.userDataInf=userDataInf;
    }

    @Get("/")

    public String index(@Nullable @QueryValue final int page, @QueryValue final int limit){
        final HashMap<String, Object>data = new HashMap<>();
        try{
            List<UserData> userData = userDataInf.findAll( page, limit);
            data.put("status", "ok");
            data.put("message","user data ");
            data.put("data","userdata");
            return new Gson().toJson(data);
        }catch(Exception e){
            data.put("status","error");
            data.put("message", e.getMessage());
            return new Gson().toJson(data);
        }

    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String save(@Body final UserData userData){
        final HashMap<String, Object> data = new HashMap<>();
        String result = userDataInf.save(userData);
        if(result !=null){
            data.put("status","ok");
            data.put("id",result);
        }else {
            data.put("status","fail");
        }
        return (new Gson()).toJson(data);

    }
    /*

     @Post(consumes=MediaType.APPLICATION_JSON)//APPLICATION_FORM_URLENCODED
    public String save(@Body UserData t) {
        HashMap<String, Object> data = new HashMap<>();
        if (userDataInf.save(t)) {
            data.put("status", "ok");
        } else {
            data.put("status", "fail");
        }
        return (new Gson()).toJson(data);
    }
    */

    @Get("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String show (@PathVariable @Nullable final Long id){
        return(new Gson().toJson(userDataInf.findById(id)));

    }

    @Put("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String upadate(@PathVariable @Nullable final Long id, @Body final UserData userData){
        final HashMap<String, Object> data = new HashMap<>();
        if(userDataInf.update(id, userData.getName(), userData.getEmail(), userData.getPassword())){
            data.put("status","ok");

        }else{
            data.put("status","fail");
        }
        return(new Gson().toJson(data));
    }

    @Delete("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String destroy(@PathVariable @Nullable final Long id){
        final HashMap<String, Object>data = new HashMap<>();
        if(userDataInf.destroy(id)){
            data.put("status","ok");

        }else{
            data.put("status","fail");
        }
        return (new Gson().toJson(data));
    }
}
