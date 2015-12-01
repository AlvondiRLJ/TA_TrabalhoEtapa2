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
import modelo.Contrato;

/**
 *
 * @author Alvondi
 */
@Stateless
public class ContratoDAO implements Serializable {
    
    @PersistenceContext(unitName = "GenContractsPU")
    private EntityManager em;
    private List<Contrato> listarTodos;

    public ContratoDAO() {
    }
    public void persist(Contrato objeto) throws Exception {
        em.persist(objeto);        
    }
    
    public void merge(Contrato objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(Contrato objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Contrato getObjectById(Integer id) throws Exception {
        Contrato obj = em.find(Contrato.class, id);
        // Executar o size das listas para inicializa-las
        obj.getAditivos().size();
        return obj;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Contrato> getListarTodos() {
        return em.createQuery("from Contrato order by numero").getResultList();
    }

    public void setListarTodos(List<Contrato> listarTodos) {
        this.listarTodos = listarTodos;
    }
    
}
