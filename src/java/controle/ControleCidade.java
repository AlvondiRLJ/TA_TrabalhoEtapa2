/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.CidadeDAO;
import dao.EstadoDAO;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Cidade;
import util.Util;

/**
 *
 * @author Alvondi
 */
@ManagedBean(name = "controleCidade")
@SessionScoped
public class ControleCidade {
    
    @EJB
    private CidadeDAO dao;
    private Cidade objeto;
    @EJB
    private EstadoDAO daoEstado;

    public ControleCidade() {
    }
    
    public String listar() {
        return "/privado/cidade/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Cidade();
        return "formulario";
    }

    public String salvar() {
        try {
            if (objeto.getCodigo()== null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
            return "listar";
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + e.getMessage());
            return "formulario";
        }
    }

    public String cancelar() {
        objeto = null;
        return "listar";
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

    public CidadeDAO getDao() {
        return dao;
    }

    public void setDao(CidadeDAO dao) {
        this.dao = dao;
    }

    public Cidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Cidade objeto) {
        this.objeto = objeto;
    }

    public EstadoDAO getDaoEstado() {
        return daoEstado;
    }

    public void setDaoEstado(EstadoDAO daoEstado) {
        this.daoEstado = daoEstado;
    }
    
}
