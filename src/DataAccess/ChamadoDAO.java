package DataAccess;

import DomainModel.Chamado;
import Util.PersistenceUtil;
import java.util.List;
import javax.persistence.*;

public class ChamadoDAO {
    
    public void salvar(Chamado c) {
        EntityManager em = PersistenceUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            if (c.getId() == null) em.persist(c);
            else em.merge(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    // Busca chamados por status (Ex: apenas os "ABERTOS")
    public List<Chamado> buscarPorStatus(String status) {
        EntityManager em = PersistenceUtil.getEntityManager();
        return em.createQuery("SELECT c FROM Chamado c WHERE c.status = :s", Chamado.class)
                 .setParameter("s", status)
                 .getResultList();
    }

    public List<Chamado> buscarTodos() {
        EntityManager em = PersistenceUtil.getEntityManager();
        return em.createQuery("SELECT c FROM Chamado c ORDER BY c.dataAbertura DESC", Chamado.class)
                 .getResultList();
    }
    public Chamado buscarPorId(Long id) {
        EntityManager em = PersistenceUtil.getEntityManager();
        try {
            return em.find(Chamado.class, id);
        } finally {
            em.close();
        }
    }

    // MÉTODO EXTRA PARA O REQUISITO DE BUSCA: Busca por texto na descrição
    public List<Chamado> buscarPorDescricao(String texto) {
        EntityManager em = PersistenceUtil.getEntityManager();
        try {
            // Usa o LIKE do SQL para buscar partes do texto
            return em.createQuery("SELECT c FROM Chamado c WHERE UPPER(c.descricao) LIKE :t", Chamado.class)
                     .setParameter("t", "%" + texto.toUpperCase() + "%")
                     .getResultList();
        } finally {
            em.close();
        }
    }
}