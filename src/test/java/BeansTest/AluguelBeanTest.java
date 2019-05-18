/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansTest;

import static BeansTest.Teste.container;
import alugaveiculoweb.beans.AluguelBean;
import alugaveiculoweb.beans.VeiculoBean;
import alugaveiculoweb.entidades.Aluguel;
import alugaveiculoweb.entidades.Veiculo;
import java.util.List;
import javax.naming.NamingException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author davi
 */
public class AluguelBeanTest extends Teste {
    
    private AluguelBean aluguelbean;
    
     @Before
    public void setUp() throws NamingException {
        aluguelbean = (AluguelBean) container.getContext().lookup("java:global/classes/AluguelBean!alugaveiculoweb.beans.AluguelBean");
    }
    
    @After
    public void tearDown() {
        aluguelbean = null;
    }
    
    @Test
    public void aluguelPorPessoa(){
      List<Aluguel> alugueis = aluguelbean.aluguelPorPessoa();
        assertEquals(alugueis.size(), 3);  
        
    }
    
}
