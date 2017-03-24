package com.aerolinea.control;

import com.aerolinea.dao.PaisDaoImpl;
import com.aerolinea.dao.RolDaoImpl;
import com.aerolinea.dao.UsuarioDaoImpl;
import com.aerolinea.entidad.*;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped
public class ControlUsuario implements Serializable {

    private Usuario usuario;
    private Rol rol;
    private Pais pais;
    private List<Usuario> usuarios;
    private List<Rol> roles;
    private List<Pais> paises;
    private String busqueda;
    private String msg;

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
            System.out.println(e.getMessage());
        }
    }

    public String guardar() throws Exception {
        try {
            usuario.setPais(pais);
            usuario.setRol(rol);
            usuarioDaoImpl.saveOrUpdate(usuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "/UsuarioLista.xhtml?faces-redirect=true";
    }

    public String seleccionaEdit(Usuario u) throws Exception {
        usuario = u;
        System.out.println(u.getIdusuario());
        return "/UsuarioForm.xhtml?faces-redirect=true";
    }

    public void eliminarUsuario(Usuario a) throws Exception {
        usuario = a;
        usuarioDaoImpl.delete(usuario.getIdusuario());
        this.getUsuarios();
    }

    public String login() throws Exception {
        System.out.println("Nombre: " + usuario.getIdusuario() + "Clave: " + usuario.getClave());
        try {
            Usuario usuariologueado = usuarioDaoImpl.validarUsuario(usuario);
            if (usuariologueado != null) {
                HttpSession s = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                s.setAttribute("idusuario", usuario.getIdusuario());

                return "/UsuarioLista.xhtml?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO, "Informacion", "Error en logeo"));
                return "/index.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Informacion", "Error en logeo"));
            return "/index.xhtml?faces-redirect=true";
        }
    }

    public String usuarioLogeado() throws Exception {
        HttpSession s = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String idusuario = (String) s.getAttribute("idusuario");

        if (idusuario != null) {
            Usuario u = usuarioDaoImpl.get(idusuario);
            String nombre = u.getNombres() + " " + u.getApellidos();

            return nombre;
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("/SpringFaces/faces/index.xhtml");
            return "/index.xhtml?faces-redirect=true";
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

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
            //usuarios = usuarioDaoImpl.findAll();
            usuarios = usuarioDaoImpl.consultarUsuarios(busqueda);
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

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
