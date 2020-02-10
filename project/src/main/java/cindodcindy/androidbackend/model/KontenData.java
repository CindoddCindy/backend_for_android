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
@Table(name = "konten_data")
public class KontenData{
     
     @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;

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

     public Long getId(){
         return id;
     }

     public void setId(Long id){
         this.id=id;
     }

     public String getKeterngan(){
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

     public void setUpdated(Date updated_at){
         this.updated_at=updated_at;
     }

     public Date getDeleted(){
         return deleted_at;
     }

     public void setDeleted(Date deleted_at){
         this.deleted_at=deleted_at;
     }





     
}