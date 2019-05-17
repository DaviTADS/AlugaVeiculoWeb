/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansTest;

import alugaveiculoweb.beans.VeiculoBean;
import alugaveiculoweb.entidades.Veiculo;
import javax.naming.NamingException;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author davi
 */
public class VeiculoBeanTest extends Teste {
    
    private VeiculoBean veiculobean;
    
    @Before
    public void setUp() throws NamingException {
        veiculobean = (VeiculoBean) container.getContext().lookup("java:global/classes/VeiculoBean!alugaveiculoweb.beans.VeiculoBean");
    }

    @After
    public void tearDown() {
        veiculobean = null;
    }
    
    @Test
    public void existeVeiculo() {
        Veiculo veiculo = veiculobean.criarVeiculo();
        veiculo.setTipo("Hatch");
        assertTrue(veiculobean.existeVeiculo(veiculo));
    }
    
}
