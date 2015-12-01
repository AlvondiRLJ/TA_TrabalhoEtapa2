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
import modelo.PessoaJuridica;

/**
 *
 * @author Alvondi
 */
@Stateless
public class PessoaJuridicaDAO implements Serializable {
    
    @PersistenceContext(unitName = "GenContractsPU")
    private EntityManager em;
    private List<PessoaJuridica> listarTodos;

    public PessoaJuridicaDAO() {
    }
    
    public void persist(PessoaJuridica objeto) throws Exception {
        em.persist(objeto);        
    }
    
    public void merge(PessoaJuridica objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(PessoaJuridica objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public PessoaJuridica getObjectById(Integer id) throws Exception {
        PessoaJuridica obj = em.find(PessoaJuridica.class, id);
        // Executar o size das listas para inicializa-las
        //////obj.getTelefones().size();
        return obj;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<PessoaJuridica> getListarTodos() {
        return em.createQuery("from PessoaJuridica order by nome").getResultList();
    }

    public void setListarTodos(List<PessoaJuridica> listarTodos) {
        this.listarTodos = listarTodos;
    }
    
    
    
}
