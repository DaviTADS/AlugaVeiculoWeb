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
import alugaveiculoweb.entidades.PessoaFisica;
import alugaveiculoweb.entidades.Veiculo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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
    private VeiculoBean veiculobean;

    @Before
    public void setUp() throws NamingException {
        aluguelbean = (AluguelBean) container.getContext().lookup("java:global/classes/AluguelBean!alugaveiculoweb.beans.AluguelBean");
        veiculobean = (VeiculoBean) container.getContext().lookup("java:global/classes/VeiculoBean!alugaveiculoweb.beans.VeiculoBean");
    }

    @After
    public void tearDown() {
        aluguelbean = null;
        veiculobean = null;
    }

    @Test
    public void persistirAluguel() {
        Aluguel aluguel = aluguelbean.criarAluguel();
        aluguel.setPreco("R$2000");
        Calendar ci = new GregorianCalendar();
        ci.set(2019, Calendar.MAY, 01);
        aluguel.setDatainicio(ci.getTime());
        Calendar cf = new GregorianCalendar();
        cf.set(2019, Calendar.JUNE, 26);
        aluguel.setDatafinal(cf.getTime());

        List<Veiculo> veiculos = new ArrayList();
        Veiculo veiculo = criarVeiculo();
        veiculos.add(veiculo);
        aluguel.setVeiculos(veiculos);

        PessoaFisica pessoaf = criarPessoaf();
        aluguel.setPessoa(pessoaf);

        aluguelbean.persistirAluguel(aluguel);
        assertNotNull(aluguel.getId());

    }

    @Test
    public void existeAluguel() {
        Aluguel aluguel = aluguelbean.criarAluguel();
        Calendar ci = new GregorianCalendar();
        ci.set(2019, Calendar.MAY, 01);
        aluguel.setDatainicio(ci.getTime());
        assertTrue(aluguelbean.existeAluguel(aluguel));

    }

    @Test
    public void atualizaAluguel() {
        String preco = "R$2600";
        Aluguel aluguel = aluguelbean.consultarAluguelPorId(new Long(2));
        assertEquals(preco, aluguel.getPreco());

        String newpreco = "R$3600";
        aluguel.setPreco(newpreco);
        aluguelbean.atualizaAluguel(aluguel);
        aluguel = aluguelbean.consultarAluguelPorId(new Long(2));
        assertEquals(newpreco, aluguel.getPreco());

    }

    @Test
    public void aluguelPorPessoa() {
        List<Aluguel> alugueis = aluguelbean.aluguelPorPessoa();
        assertEquals(alugueis.size(), 6);

    }

    @Test
    public void consultaAluguelPorPreco() {
        List<Aluguel> alugueis = aluguelbean.aluguelPorPreco("R$3000");
        assertEquals(alugueis.size(), 3);

    }

    @Test
    public void consultaAluguelPorDataInicial() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(new Date());
        Date datainicio = format.parse("2019-01-26");
        List<Aluguel> alugueis = aluguelbean.aluguelPorDataInicial(datainicio);
        assertEquals(alugueis.size(), 1);

    }

    @Test
    public void consultaAluguelPorDataFinal() throws ParseException {
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(new Date());
        Date datafinal = format.parse("2019-05-17");
        List<Aluguel> alugueis = aluguelbean.aluguelPorDataFinal(datafinal);
        assertEquals(alugueis.size(), 1);
    }
    
    
    /////////////////// METODOS DE APOIO ABAIXO ///////////////////////////

    public Veiculo criarVeiculo() {
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

        return veiculo;
    }

    public PessoaFisica criarPessoaf() {

        PessoaFisica pessoa = new PessoaFisica();
        String telefone = "985447213";
        Collection<String> telefones = new ArrayList();
        telefones.add(telefone);
        pessoa.setNome("Anselmo");
        pessoa.setSobrenome("Noronha");
        pessoa.setSenha("Ab8.tryo");
        pessoa.setTelefones(telefones);
        pessoa.setEmail("whatever@gmail.com");
        pessoa.setCpf("938.102.030-25");
        pessoa.setCreditos("200");

        return pessoa;
    }

}
