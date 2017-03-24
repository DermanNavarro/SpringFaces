package com.aerolinea.dao;

import com.aerolinea.entidad.Usuario;
import java.util.List;
import java.util.logging.Level;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("UsuarioDaoImpl")
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, String> implements UsuarioDao {

    @Override
    public List<Usuario> findAll() throws Exception {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT u FROM Usuario u JOIN FETCH u.pais JOIN FETCH u.rol");
            List<Usuario> entities = query.list();
            session.getTransaction().commit();
            return entities;
        } catch (Exception ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING, "Falló al hacer un rollback", exc);
            }
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }

    public List<Usuario> consultarUsuarios(String nombres) throws Exception {
        Session session = sessionFactory.openSession();
        
        try {
            if (nombres == null)nombres = "";
            session.beginTransaction();
            Query query = session.createQuery("SELECT u FROM Usuario u JOIN FETCH u.pais JOIN FETCH u.rol "
                    + "WHERE u.nombres like" + " :nombres");
            query.setParameter("nombres", "%" + nombres + "%");
            List<Usuario> entities = query.list();
            session.getTransaction().commit();
            return entities;
        } catch (Exception ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING, "Falló al hacer un rollback", exc);
            }
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }
    
     public Usuario validarUsuario(Usuario u) {
        Usuario usuario = null;
//        Session s = HibernateUtil.getSessionFactory().openSession(); //Administrar conexiones manual mente en hybernate
        Session s = sessionFactory.openSession();

        try {
            s.beginTransaction();
            Query q = s.createQuery("SELECT u FROM Usuario u WHERE idusuario=:usuario AND clave=:clave");

            q.setParameter("usuario", u.getIdusuario());
            q.setParameter("clave", u.getClave());

            usuario = (Usuario) q.uniqueResult();
            s.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return usuario;
    }
}
