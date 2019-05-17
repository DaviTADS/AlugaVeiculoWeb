/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alugaveiculoweb.beans;

import alugaveiculoweb.entidades.Motorista;
import alugaveiculoweb.entidades.Veiculo;
import java.util.ArrayList;
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

@Stateless(name = "VeiculoBean")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class VeiculoBean {

    @PersistenceContext(name = "AlugaVeiculoWeb", type = TRANSACTION)
    protected EntityManager em;

    @TransactionAttribute(SUPPORTS)
    public boolean existeVeiculo(@NotNull Veiculo veiculo) {
        TypedQuery<Veiculo> query
                = em.createNamedQuery(Veiculo.VeiculoPorTipo, Veiculo.class);
        query.setParameter(1, veiculo.getTipo());
        return !query.getResultList().isEmpty();
    }

    public void persistirVeiculo(Veiculo veiculo) {

        em.persist(veiculo);
    }

    public Veiculo criarVeiculo() {

        return new Veiculo();
    }

    public void atualizaVeiculo(Veiculo veiculo) {

        em.merge(veiculo);
        em.flush();
    }

    public Veiculo consultarPorId(@NotNull Long id) {

        return em.find(Veiculo.class, id);
    }

    public List<Veiculo> consultaVeiculosComMotorista() {
        TypedQuery<Veiculo> query = em.createNamedQuery(Veiculo.VeiculoPorMotorista, Veiculo.class);

        return query.getResultList();
    }

    public List<Veiculo> consultaVeiculosPorTipo(String tipo) {
        TypedQuery<Veiculo> query = em.createNamedQuery(Veiculo.VeiculoPorTipo, Veiculo.class);
        query.setParameter(1, tipo);

        return query.getResultList();
    }

}
