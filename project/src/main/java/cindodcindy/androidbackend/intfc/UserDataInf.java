package cindodcindy.androidbackend.intfc;

import cindodcindy.androidbackend.model.UserData;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserDataInf{
    List<UserData> findAll(int page, int limit);
    String save(@NotNull UserData userData);
    Long size();
    UserData findById(@NotNull Long id);
    boolean update (@NotNull Long id, String name, String email, String password);
    boolean destroy (@NotNull Long id);
}