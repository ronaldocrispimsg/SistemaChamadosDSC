package DataAccess;

import DomainModel.Usuario;
import Util.PersistenceUtil;
import java.util.List;
import javax.persistence.*;

public class UsuarioDAO {
    
    public void salvar(Usuario u) {
        EntityManager em = PersistenceUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            if (u.getId() == null) em.persist(u);
            else em.merge(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    // Método essencial para a tela de login
    public Usuario validarLogin(String login, String senhaHash) {
        EntityManager em = PersistenceUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.login = :l AND u.senha = :s", Usuario.class)
                     .setParameter("l", login)
                     .setParameter("s", senhaHash)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Usuario> buscarTodos() {
        EntityManager em = PersistenceUtil.getEntityManager();
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }
    
    public void remover(Long id) {
        EntityManager em = PersistenceUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario u = em.find(Usuario.class, id);
            if (u != null) em.remove(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Usuario buscarPorId(Long id) {
        EntityManager em = PersistenceUtil.getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }
}