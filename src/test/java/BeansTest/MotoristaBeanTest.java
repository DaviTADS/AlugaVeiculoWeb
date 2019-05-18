/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansTest;

import static BeansTest.Teste.container;
import alugaveiculoweb.beans.MotoristaBean;
import alugaveiculoweb.entidades.Motorista;
import alugaveiculoweb.entidades.Veiculo;
import javax.naming.NamingException;
import javax.validation.constraints.NotNull;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author aluno
 */
public class MotoristaBeanTest extends Teste {
    private MotoristaBean motoristaBean;
    
    @Before
    public void setUp() throws NamingException {
        motoristaBean = (MotoristaBean) container.getContext().lookup("java:global/classes/MotoristaBean!alugaveiculoweb.beans.MotoristaBean");
    }
    
    @After
    public void tearDown() {
        motoristaBean = null;
    }
    
    @Test
    public void MotoristaPorId() {
        assertNotNull(motoristaBean.consultarMotoristaPorId(new Long(7)));
    }
    
}
