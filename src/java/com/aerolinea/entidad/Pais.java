package com.aerolinea.entidad;
// Generated 03-18-2017 02:18:05 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Pais generated by hbm2java
 */
@Entity
@Table(name="pais"
    ,catalog="aerolinea"
)
public class Pais  implements java.io.Serializable {


     private Integer idpais;
     private String pais;
     private Set<Aeropuerto> aeropuertos = new HashSet<Aeropuerto>(0);
     private Set<Usuario> usuarios = new HashSet<Usuario>(0);

    public Pais() {
    }

	
    public Pais(String pais) {
        this.pais = pais;
    }
    public Pais(String pais, Set<Aeropuerto> aeropuertos, Set<Usuario> usuarios) {
       this.pais = pais;
       this.aeropuertos = aeropuertos;
       this.usuarios = usuarios;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idpais", unique=true, nullable=false)
    public Integer getIdpais() {
        return this.idpais;
    }
    
    public void setIdpais(Integer idpais) {
        this.idpais = idpais;
    }

    
    @Column(name="pais", nullable=false, length=50)
    public String getPais() {
        return this.pais;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pais")
    public Set<Aeropuerto> getAeropuertos() {
        return this.aeropuertos;
    }
    
    public void setAeropuertos(Set<Aeropuerto> aeropuertos) {
        this.aeropuertos = aeropuertos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pais")
    public Set<Usuario> getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }




}


