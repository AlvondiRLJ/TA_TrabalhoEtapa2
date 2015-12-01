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
import modelo.Cidade;

/**
 *
 * @author Alvondi
 */
@Stateless
public class CidadeDAO implements Serializable{
    
    @PersistenceContext(unitName = "GenContractsPU")
    private EntityManager em;
    private List<Cidade> listarTodos;

    public CidadeDAO() {
    }
    
    public void persist(Cidade objeto) throws Exception {
        em.persist(objeto);        
    }
    
    public void merge(Cidade objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(Cidade objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Cidade getObjectById(Integer id) throws Exception {
        return em.find(Cidade.class, id);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Cidade> getListarTodos() {
        return em.createQuery("from Cidade order by nome").getResultList();
    }

    public void setListarTodos(List<Cidade> listarTodos) {
        this.listarTodos = listarTodos;
    }
    
}
