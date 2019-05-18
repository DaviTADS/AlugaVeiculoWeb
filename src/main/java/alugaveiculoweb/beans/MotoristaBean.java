/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alugaveiculoweb.beans;

import alugaveiculoweb.entidades.Motorista;
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
@Stateless(name = "MotoristaBean")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class MotoristaBean {
    
    @PersistenceContext(name = "AlugaVeiculoWeb", type = TRANSACTION)
    protected EntityManager em;
    
    public Motorista consultarMotoristaPorId(@NotNull Long id) {

        return em.find(Motorista.class, id);
    }
     
//     
//    @TransactionAttribute(SUPPORTS)
//    public List<Motorista> consultaMotoristasComVeiculo() {
//        TypedQuery<Motorista> query = em.createNamedQuery(Motorista.MotoristaComVeiculo, Motorista.class);
////          query.setParameter(1, tipo);
//
//        return query.getResultList();
//    }
    
}
