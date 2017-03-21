package com.aerolinea.control;

import com.aerolinea.dao.PaisDaoImpl;
import com.aerolinea.dao.RolDaoImpl;
import com.aerolinea.dao.UsuarioDaoImpl;
import com.aerolinea.entidad.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean
@RequestScoped
public class ControlUsuario {

    private Usuario usuario;
    private Rol rol;
    private Pais pais;
    private List<Usuario> usuarios;
    private List<Rol> roles;
    private List<Pais> paises;

    @ManagedProperty("#{UsuarioDaoImpl}")
    private UsuarioDaoImpl usuarioDaoImpl;
    @ManagedProperty("#{PaisDaoImpl}")
    private PaisDaoImpl paisDaoImpl;
    @ManagedProperty("#{RolDaoImpl}")
    private RolDaoImpl rolDaoImpl;

    @PostConstruct
    public void init() {
        try {
            usuario = usuarioDaoImpl.create();
            pais = paisDaoImpl.create();
            rol = rolDaoImpl.create();
        } catch (Exception e) {
        }
    }

    public String guardar() throws Exception {
        usuario.setPais(pais);
        usuario.setRol(rol);
        usuarioDaoImpl.saveOrUpdate(usuario);
        return "/index.xhtml?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Usuario> getUsuarios() {
        try {
            usuarios = usuarioDaoImpl.findAll();
        } catch (Exception ex) {
            Logger.getLogger(ControlUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Rol> getRoles() throws Exception {
        roles = rolDaoImpl.findAll();
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public List<Pais> getPaises() throws Exception {
        paises = paisDaoImpl.findAll();
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public UsuarioDaoImpl getUsuarioDaoImpl() {
        return usuarioDaoImpl;
    }

    public void setUsuarioDaoImpl(UsuarioDaoImpl usuarioDaoImpl) {
        this.usuarioDaoImpl = usuarioDaoImpl;
    }

    public PaisDaoImpl getPaisDaoImpl() {
        return paisDaoImpl;
    }

    public void setPaisDaoImpl(PaisDaoImpl paisDaoImpl) {
        this.paisDaoImpl = paisDaoImpl;
    }

    public RolDaoImpl getRolDaoImpl() {
        return rolDaoImpl;
    }

    public void setRolDaoImpl(RolDaoImpl rolDaoImpl) {
        this.rolDaoImpl = rolDaoImpl;
    }

}
