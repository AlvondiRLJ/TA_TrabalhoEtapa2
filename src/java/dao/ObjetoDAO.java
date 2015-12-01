/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Objeto;

/**
 *
 * @author Alvondi
 */
@Stateless
public class ObjetoDAO implements Serializable {
    
    @PersistenceContext(unitName = "GenContractsPU")
    private EntityManager em;
    private List<Objeto> listarTodos;

    public ObjetoDAO() {
    }
    
    public void persist(Objeto obj) throws Exception {
        em.persist(obj);        
    }
    
    public void merge(Objeto obj) throws Exception {
        em.merge(obj);
    }
    
    public void remove(Objeto obj) throws Exception{
        obj = em.merge(obj);
        em.remove(obj);
    }
    
    public Objeto getObjectById(Integer id) throws Exception {
        return em.find(Objeto.class, id);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Objeto> getListarTodos() {
        return em.createQuery("from Objeto order by descricao").getResultList();
    }

    public void setListarTodos(List<Objeto> listarTodos) {
        this.listarTodos = listarTodos;
    }
    
}
