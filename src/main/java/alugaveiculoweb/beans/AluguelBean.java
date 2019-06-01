/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alugaveiculoweb.beans;

import alugaveiculoweb.entidades.Aluguel;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
@Stateless(name = "AluguelBean")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class AluguelBean {
    
    @PersistenceContext(name = "AlugaVeiculoWeb", type = TRANSACTION)
    protected EntityManager em;
    
     public Aluguel consultarAluguelPorId(@NotNull Long id) {

        return em.find(Aluguel.class, id);
    }
     
    @TransactionAttribute(SUPPORTS)
    public Aluguel criarAluguel() {

        return new Aluguel();
    }
    
    public void persistirAluguel(Aluguel aluguel) {

        em.persist(aluguel);
    }

    public Aluguel atualizaAluguel(Aluguel aluguel) {

        aluguel = em.merge(aluguel);
        em.flush();
        return aluguel;
    } 
    
    public boolean existeAluguel(@NotNull Aluguel aluguel) {
        TypedQuery<Aluguel> query
                = em.createNamedQuery(Aluguel.AluguelPorDataInicio, Aluguel.class);
        query.setParameter(1, aluguel.getDatainicio());
        return !query.getResultList().isEmpty();
    }
    @TransactionAttribute(SUPPORTS)
    public List<Aluguel> aluguelPorPreco(String preco){
        TypedQuery<Aluguel> query = em.createNamedQuery(Aluguel.AluguelPorPreco, Aluguel.class);
        query.setParameter(1, preco);
        
        return query.getResultList();
    }
    
    @TransactionAttribute(SUPPORTS)
    public List<Aluguel> aluguelPorDataInicial(Date datainicio){
        
        TypedQuery<Aluguel> query = em.createNamedQuery(Aluguel.AluguelPorDataInicio, Aluguel.class);
        query.setParameter(1, datainicio);
        
        return query.getResultList();
        
    }
    @TransactionAttribute(SUPPORTS)
    public List<Aluguel> aluguelPorDataFinal(Date datafinal){
        
        TypedQuery<Aluguel> query = em.createNamedQuery(Aluguel.AluguelPorDataFinal, Aluguel.class);
        query.setParameter(1, datafinal);
        
        return query.getResultList();
        
    }
    @TransactionAttribute(SUPPORTS)
    public List<Aluguel> aluguelPorPessoa(){
        
        TypedQuery<Aluguel> query = em.createNamedQuery(Aluguel.AluguelPorPessoa, Aluguel.class);
        
        return query.getResultList();
        
    }
    
     
    
}
