package DataAccess;

import DomainModel.Setor;
import Util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;

public class SetorDAO {
    
    public void salvar(Setor s) {
        EntityManager em = PersistenceUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(s); // Salva o objeto no banco
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Setor> buscarTodos() {
        EntityManager em = PersistenceUtil.getEntityManager();
        return em.createQuery("SELECT s FROM Setor s", Setor.class).getResultList();
    }
    
    public void remover(Long id) {
        EntityManager em = PersistenceUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Setor s = em.find(Setor.class, id); // Busca antes de remover
            if (s != null) {
                em.remove(s);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Setor buscarPorId(Long id) {
        EntityManager em = PersistenceUtil.getEntityManager();
        try {
            return em.find(Setor.class, id);
        } finally {
            em.close();
        }
    }
}