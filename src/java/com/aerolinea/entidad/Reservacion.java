package com.aerolinea.entidad;
// Generated 03-18-2017 02:18:05 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Reservacion generated by hbm2java
 */
@Entity
@Table(name="reservacion"
    ,catalog="aerolinea"
)
public class Reservacion  implements java.io.Serializable {


     private Integer idreservacion;
     private Usuario usuario;
     private Vuelo vuelo;
     private int nboletos;
     private BigDecimal precio;

    public Reservacion() {
    }

	
    public Reservacion(Usuario usuario, Vuelo vuelo, int nboletos) {
        this.usuario = usuario;
        this.vuelo = vuelo;
        this.nboletos = nboletos;
    }
    public Reservacion(Usuario usuario, Vuelo vuelo, int nboletos, BigDecimal precio) {
       this.usuario = usuario;
       this.vuelo = vuelo;
       this.nboletos = nboletos;
       this.precio = precio;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idreservacion", unique=true, nullable=false)
    public Integer getIdreservacion() {
        return this.idreservacion;
    }
    
    public void setIdreservacion(Integer idreservacion) {
        this.idreservacion = idreservacion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idusuario", nullable=false)
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idvuelo", nullable=false)
    public Vuelo getVuelo() {
        return this.vuelo;
    }
    
    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    
    @Column(name="nboletos", nullable=false)
    public int getNboletos() {
        return this.nboletos;
    }
    
    public void setNboletos(int nboletos) {
        this.nboletos = nboletos;
    }

    
    @Column(name="precio", precision=10)
    public BigDecimal getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }




}


