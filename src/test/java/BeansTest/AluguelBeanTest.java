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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.naming.NamingException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
    public void persistirAluguel(){
        Aluguel aluguel = aluguelbean.criarAluguel();
        aluguel.setPreco("R$2000");
        Calendar ci = new GregorianCalendar();
        ci.set(2019, Calendar.MAY, 01);
        aluguel.setDatainicio(ci.getTime());
        Calendar cf = new GregorianCalendar();
        cf.set(2019, Calendar.JUNE, 26);
        aluguel.setDatafinal(cf.getTime());
        
        aluguelbean.persistirAluguel(aluguel);
        assertNotNull(aluguel.getId());
        
        
    }
    
    @Test
    public void existeAluguel(){
        Aluguel aluguel = aluguelbean.criarAluguel();
        Calendar ci = new GregorianCalendar();
        ci.set(2019, Calendar.MAY, 01);
        aluguel.setDatainicio(ci.getTime());
        assertTrue(aluguelbean.existeAluguel(aluguel));
        
    }
    
    @Test
    public void atualizaAluguel(){
        String preco = "R$2600";
        Aluguel aluguel = aluguelbean.consultarAluguelPorId(new Long(2));
        assertEquals(preco,aluguel.getPreco());
        
        String newpreco = "R$3600";
        aluguel.setPreco(newpreco);
        aluguelbean.atualizaAluguel(aluguel);
        aluguel = aluguelbean.consultarAluguelPorId(new Long(2));
        assertEquals(newpreco,aluguel.getPreco());
        
        
    }
    
    
    @Test
    public void aluguelPorPessoa(){
      List<Aluguel> alugueis = aluguelbean.aluguelPorPessoa();
        assertEquals(alugueis.size(), 3);  
        
    }
    
}
