/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansTest;

import static BeansTest.Teste.container;
import alugaveiculoweb.beans.MotoristaBean;
import alugaveiculoweb.entidades.Motorista;
import alugaveiculoweb.entidades.Reputacao;
import alugaveiculoweb.entidades.Veiculo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.naming.NamingException;
import javax.validation.constraints.NotNull;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author aluno
 */
public class MotoristaBeanTest extends Teste {

    private MotoristaBean motoristabean;

    @Before
    public void setUp() throws NamingException {
        motoristabean = (MotoristaBean) container.getContext().lookup("java:global/classes/MotoristaBean!alugaveiculoweb.beans.MotoristaBean");
    }

    @After
    public void tearDown() {
        motoristabean = null;
    }

    @Test
    public void existeMotorista() {
        Motorista motorista = motoristabean.criarMotorista();
        motorista.setReputacao(Reputacao.Boa);
        assertTrue(motoristabean.existeMotorista(motorista));

    }

    @Test
    public void persistirMotorista() {
        Motorista motorista = motoristabean.criarMotorista();
        String telefone1 = "32258965";
        String telefone2 = "998542369";
        Collection<String> telefones = new ArrayList();
        telefones.add(telefone1);
        telefones.add(telefone2);
        motorista.setNome("Mario");
        motorista.setSobrenome("Zotelli");
        motorista.setSenha("hyau6,Kp");
        motorista.setTelefones(telefones);
        motorista.setCpf("526.824.900-26");
        motorista.setEmail("mariozotelli@gmail.com");
        String habilitacaoMoto = "Moto";
        String habilitacaoCarro = "Carro";
        List<String> habilitacoes = new ArrayList();
        habilitacoes.add(habilitacaoMoto);
        habilitacoes.add(habilitacaoCarro);
        motorista.setHabilitacoes(habilitacoes);
        motorista.setReputacao(Reputacao.Excelente);

        motoristabean.persistirMotorista(motorista);
        assertNotNull(motorista.getId());
    }
    
    @Test
    public void atualizarMotorista(){
        Motorista motorista = motoristabean.consultarMotoristaPorId(new Long(7));
        assertEquals(Reputacao.Boa,motorista.getReputacao());
        
        motorista.setReputacao(Reputacao.Razoavel); 
        motoristabean.atualizaMotorista(motorista);
        motorista = motoristabean.consultarMotoristaPorId(new Long(7));
        assertEquals(Reputacao.Razoavel, motorista.getReputacao());
        
    }

    @Test
    public void MotoristaPorId() {
        assertNotNull(motoristabean.consultarMotoristaPorId(new Long(7)));
    }

}
