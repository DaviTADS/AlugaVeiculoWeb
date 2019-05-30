/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansTest;

import alugaveiculoweb.beans.VeiculoBean;
import alugaveiculoweb.entidades.Veiculo;
import java.util.ArrayList;
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
    public void existeVeiculo(){
        Veiculo veiculo = veiculobean.criarVeiculo();
        veiculo.setTipo("Hatch");
        assertTrue(veiculobean.existeVeiculo(veiculo));
    }
     
    @Test
    public void persistiVeiculo(){
        Veiculo veiculo = veiculobean.criarVeiculo();
        veiculo.setAnofabricacao("2000");
        veiculo.setCapacidade(6);
        veiculo.setFabricante("Honda");
        veiculo.setImagem(Byte.MIN_VALUE);
        veiculo.setModelo("F156");
        veiculo.setPorte("Grande");
        veiculo.setTipo("Esportivo");
        veiculo.setDescricao("Descrição legal para um carro");
        List<String> placas = new ArrayList();
        placas.add("AXM52");
        placas.add("V5C0D3");
        veiculo.setPlacas(placas);
        
        veiculobean.persistirVeiculo(veiculo);
        assertNotNull(veiculo.getId());
        
    }
    
    @Test
    public void atualizaVeiculo() {
        Veiculo veiculo = veiculobean.consultarPorId(new Long(1));
        assertEquals(5,veiculo.getCapacidade());
        
        veiculo.setCapacidade(3); 
        veiculobean.atualizaVeiculo(veiculo);
        veiculo = veiculobean.consultarPorId(new Long(1));
        assertEquals(3, veiculo.getCapacidade());
    }
    
    
    
    @Test
    public void VeiculoPorId() {
        assertNotNull(veiculobean.consultarPorId(new Long(1)));
    }
    
    @Test
    public void consultaVeiculosComMotorista(){
        List<Veiculo> veiculos = veiculobean.consultaVeiculosComMotorista();
        assertEquals(veiculos.size(), 3);
        
    }
    
    @Test
    public void consultaVeiculosPorTipo(){
        List<Veiculo> veiculos = veiculobean.consultaVeiculosPorTipo("Hatch");
        assertEquals(veiculos.size(), 2);
        
    }
    
}
