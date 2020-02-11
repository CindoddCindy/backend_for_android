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


@Entity
@Table(name ="user_data")
public class UserData {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable =false)
    private String email;

    @Column(name = "password", nullable=false)
    private String password;

    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name="deleted_at", nullable=false)
    private Date deleted_at;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String emai){
        this.email=email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public Date getCreated(){
        return created_at;
    }

    public void setCreated(Date created_at){
        this.created_at=created_at;
    }

    public Date getUpdated(){
        return updated_at;
    }

    public void setUpdated(Date updated_at){
        this.updated_at = updated_at;
    }

    public Date getDeleted(){
        return deleted_at;
    }

    public void setDeleted(Date deleted_at){
        this.deleted_at=deleted_at;
    }


}