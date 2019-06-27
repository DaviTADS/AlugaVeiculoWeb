/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alugaveiculoweb.beans;

import alugaveiculoweb.entidades.PessoaFisica;
import alugaveiculoweb.entidades.PessoaJuridica;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.TRANSACTION;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;

/**
 *
 * @author davi
 */
@Stateless(name = "PessoajBean")
@LocalBean  
@ValidateOnExecution(type = ExecutableType.ALL)
public class PessoaJuridicaBean {
 
    
    @PersistenceContext(name = "AlugaVeiculoWeb", type = TRANSACTION)
    protected EntityManager em;
    
    public boolean existePessoaJ(@NotNull PessoaJuridica pessoaj){
    TypedQuery<PessoaJuridica> query
                = em.createNamedQuery(PessoaJuridica.PessoaJporCnpj, PessoaJuridica.class);    
        query.setParameter(1, pessoaj.getCnpj());
        return !query.getResultList().isEmpty();
    }
    
    public void persistirPessoaJ(PessoaJuridica pessoaj) {

        em.persist(pessoaj);
    }
    
    @TransactionAttribute(SUPPORTS)
    public PessoaJuridica criarPessoaj() {

        return new PessoaJuridica();
    }
    
    public PessoaJuridica atualizaPessoaj(PessoaJuridica pessoaj) {

        pessoaj = em.merge(pessoaj);
        em.flush();
        return pessoaj;
    }
    
    public PessoaJuridica consultarPessoaJPorId(@NotNull Long id) {

        return em.find(PessoaJuridica.class, id);
    }
    
    
    
}
