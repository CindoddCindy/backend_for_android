package cindodcindy.androidbackend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "konten_data")
public class KontenData{
     
     @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;


    @ManyToOne(optional=false)
    @JoinColumn(name = "admin_id", referencedColumnName = "admin_id", insertable=false, updatable=false)
    private Admin admin;
    private Long admin_id;

    @ManyToOne(optional=false)
    @JoinColumn(name = "userdata_id", referencedColumnName = "userdata_id", insertable=false, updatable=false)
    private UserData userData;
    private Long userdata_id;





     @Column(name="keterangan", nullable = false)
     private String keterangan;

     @Column(name="gambar", nullable=false)
     private String gambar;

     @CreationTimestamp
     @Temporal(TemporalType.TIMESTAMP)
     @Column(name="created_at")
     private Date created_at;

     @CreationTimestamp
     @Temporal(TemporalType.TIMESTAMP)
     @Column(name ="updated_at")
     private Date updated_at;

     @Column(name = "deleted_at", nullable=true)
     private Date deleted_at;

     public UserData getUserData(){
         return userData;
     }

     public void setUserData(UserData userData){
         this.userData=userData;
     }

     public Long getUserDataId(){
         return userdata_id;
     }

     public void setUserDataId(Long userdata_id){
         this.userdata_id=userdata_id;
     }

     public Admin getAdmin(){
         return admin;
     }

     public void setAdmin(Admin admin){
         this.admin=admin;
     }

     public Long getAdminId(){
         return admin_id;
     }

     public void setAdminId(Long admin_id){
         this.admin_id=admin_id;
     }

     public Long getId(){
         return id;
     }

     public void setId(Long id){
         this.id=id;
     }

     public String getKeterangan(){
         return keterangan;
     }

     public void setKeterangan(String keterangan){
         this.keterangan=keterangan;
     }

     public String getGambar(){
         return gambar;
     }

     public void setGambar(String gambar){
         this.gambar=gambar;
     }

     public Date getCreated(){
         return updated_at;
     }

     public void setUpdated(Date updated_at){
         this.updated_at=updated_at;
     }

     public Date getUpdated(){
         return updated_at;
     }

    
     public Date getDeleted(){
         return deleted_at;
     }

     public void setDeleted(Date deleted_at){
         this.deleted_at=deleted_at;
     }





     
}
