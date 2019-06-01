/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alugaveiculoweb.beans;

import alugaveiculoweb.entidades.Motorista;
import alugaveiculoweb.entidades.Veiculo;
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
    
    public boolean existeMotorista(@NotNull Motorista motorista){
    TypedQuery<Motorista> query
                = em.createNamedQuery(Motorista.MotoristaPorReputacao, Motorista.class);    
        query.setParameter(1, motorista.getReputacao());
        return !query.getResultList().isEmpty();
    }
    
    public void persistirMotorista(Motorista motorista) {

        em.persist(motorista);
    }
    
    @TransactionAttribute(SUPPORTS)
    public Motorista criarMotorista() {

        return new Motorista();
    }
    
    public Motorista atualizaMotorista(Motorista motorista) {

        motorista = em.merge(motorista);
        em.flush();
        return motorista;
    }
    
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
