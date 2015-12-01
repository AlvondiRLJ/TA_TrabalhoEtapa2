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
import modelo.Estado;

/**
 *
 * @author Alvondi
 */
@Stateless
public class EstadoDAO implements Serializable{
    
    @PersistenceContext(unitName = "GenContractsPU")
    private EntityManager em;
    private List<Estado> listarTodos;

    public EstadoDAO() {
    }
    
    public void persist(Estado objeto) throws Exception {
        em.persist(objeto);        
    }
    
    public void merge(Estado objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(Estado objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Estado getObjectById(Integer id) throws Exception {
        return em.find(Estado.class, id);
    }    

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Estado> getListarTodos() {
        return em.createQuery("from Estado order by nome").getResultList();
    }

    public void setListarTodos(List<Estado> listarTodos) {
        this.listarTodos = listarTodos;
    }
    
}
