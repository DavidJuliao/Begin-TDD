import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import venda.*;

public class PedidoVendaTest {

    private Pedido pedido;

    @Before
    public void setup(){
        CalculadoraFaixaDesconto calculadoraFaixaDesconto =
                new CalculadoraDescontoTerceiraFaixa(
                        new CalculadoraDescontoSegundaFaixa(
                                new CalculadoraDescontoPrimeiraFaixa(
                                        new CalculadoraSemDesconto(null)
                                )
                        )
                );
        pedido = new Pedido(calculadoraFaixaDesconto);
    }


    @Test
    public void devePermitirAdicionarUmItemNoPedido() throws Exception{
        pedido.adicionarItem(new ItemPedido("sabonete", 3.0, 10));
    }

    @Test
    public void deveCalcularValortotalParaPedidoVazio() throws Exception{
        ResumoPedido resumo = pedido.getResumopedido();
        assertEquals(0.0,resumo.getValorTotal(), 0.0001);

    }

    @Test
    public void deveCalcularValortotalEDescontoParaPedidoVazio() throws Exception{
        double valorTotal = 0.0;
        double desconto = 0.0;

        assertResumoPedido(valorTotal,desconto);
    }

    private void assertResumoPedido(double valorTotal, double desconto) {
        ResumoPedido resumo = pedido.getResumopedido();
        assertEquals(valorTotal,resumo.getValorTotal(), 0.0001);
        assertEquals(desconto, resumo.getDesconto(), 0.0001);
    }

    @Test
    public void deveCalcularResumoParaUmItemSemDesconto()throws Exception{
        pedido.adicionarItem(new ItemPedido("sabonete", 5.0, 5));
        assertResumoPedido(25.0,0.0);

    }

    @Test
    public void deveCalcularResumoParaDoisPedidosSemDesconto()throws Exception{
        pedido.adicionarItem(new ItemPedido("sabonete",3.0,3));
        pedido.adicionarItem(new ItemPedido("creme dental",7.0,3));

        assertResumoPedido(30,0.0);
    }

    @Test
    public void deveAplicarDescontoNaPrimeiraFaixa() throws Exception{
        pedido.adicionarItem(new ItemPedido("desodorante", 17.0,25));
        assertResumoPedido(425,17);
    }

    @Test
    public void deveAplicarDescontoNaSegundaFaixa() throws Exception{
        pedido.adicionarItem(new ItemPedido("barbeador", 15.0,30));
        pedido.adicionarItem(new ItemPedido("creme de barbear", 15.0,30));
        assertResumoPedido(900,54.0);
    }

    @Test
    public void deveAplicarDescontoNaTerceiraFaixa() throws Exception{
        pedido.adicionarItem(new ItemPedido("barbeador", 15.0,30));
        pedido.adicionarItem(new ItemPedido("creme de barbear", 15.0,30));
        pedido.adicionarItem(new ItemPedido("gilette", 10.0,30));
        assertResumoPedido(1200,96.0);
    }
}
