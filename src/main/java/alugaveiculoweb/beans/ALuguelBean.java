/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alugaveiculoweb.beans;

import alugaveiculoweb.entidades.Aluguel;
import alugaveiculoweb.entidades.Veiculo;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.TRANSACTION;
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
public class ALuguelBean {
    
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
    
    
    
}
