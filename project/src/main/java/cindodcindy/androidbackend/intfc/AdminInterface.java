package cindodcindy.androidbackend.intfc;

import cindodcindy.androidbackend.model.Admin;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface AdminInterface {
    List<Admin> findAll(int page, int limit);
    Long save(@NotNull Admin admin);
    Long size();
    Admin findById(@NotNull Long admin_id);
    boolean update (@NotNull Long admin_id, String admin_post);
    boolean destroy (@NotNull Long admin_id);

}