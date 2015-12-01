/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ContratoDAO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Aditivo;
import modelo.Contrato;
import modelo.Objeto;
import util.Util;

/**
 *
 * @author Alvondi
 */
@ManagedBean(name = "controleContrato")
@ViewScoped
public class ControleContrato implements Serializable  {
    
    @EJB
    private ContratoDAO dao;
    private Contrato objeto;
    private Aditivo aditivo;
    private Boolean novoAditivo;
    Objeto obj;

    public ControleContrato() {
    }
    
    public String listar() {
        return "/privado/contrato/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Contrato();
    }

    public void salvar() {
        try {
            if (objeto.getCodigo()== null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + e.getMessage());
        }
    }

    public void editar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "+e.getMessage());
        }
    }
    
    public void excluir(Integer id){
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto:"+Util.getMensagemErro(e));
        }
    }
    
    public void novoAditivo(){
        aditivo = new Aditivo();
        novoAditivo = true;
    }
    
    public void alterarAditivo(int index){
        aditivo = objeto.getAditivos().get(index);
        novoAditivo = false;
    }
    
    public void salvarAditivo(){
        if (novoAditivo){
            objeto.adicionarAditivo(aditivo);
        }
        Util.mensagemInformacao("Operação realizada com sucesso");
    }
    
    public void removerAditivo(int index){
        objeto.removerAditivo(index);
        Util.mensagemInformacao("Operação realizada com sucesso");
    }
    
    public ContratoDAO getDao() {
        return dao;
    }

    public void setDao(ContratoDAO dao) {
        this.dao = dao;
    }

    public Contrato getObjeto() {
        return objeto;
    }

    public void setObjeto(Contrato objeto) {
        this.objeto = objeto;
    }

    public Aditivo getAditivo() {
        return aditivo;
    }

    public void setAditivo(Aditivo aditivo) {
        this.aditivo = aditivo;
    }

    public Boolean getNovoAditivo() {
        return novoAditivo;
    }

    public void setNovoAditivo(Boolean novoAditivo) {
        this.novoAditivo = novoAditivo;
    }
    
    
    
}
