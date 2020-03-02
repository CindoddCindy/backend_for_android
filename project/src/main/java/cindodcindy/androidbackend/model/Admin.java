package cindodcindy.androidbackend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GenerationType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class Admin{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long admin_id;

    @Column(name = "admin_post", nullable = false)
    private String admin_post;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date updated_at;

    @Column(name="deleted_at", nullable = true)
    private Date deleted_at;

    public void setId(Long admin_id){
        this.admin_id=admin_id;
    }

    public Long getId(){
        return admin_id;
    }

    public void setAdminPost(String admin_post){
        this.admin_post=admin_post;
    }

    public String getAdminPost(){
        return admin_post;
    }

    public Date getCreated(){
        return created_at=created_at;
    }

    public void setCreated(Date created_at){
        this.created_at=created_at;
    }

    public Date getUpdated(){
        return updated_at;
    }

    public void setUpdated(Date updated_at){
        this.updated_at=updated_at;
    }

    public void setDeleted(Date deleted_at){
        this.deleted_at=deleted_at;
    }

    public Date getDeleted(){
        return deleted_at;
    }
}
