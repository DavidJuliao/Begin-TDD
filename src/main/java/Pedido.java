import venda.CalculadoraFaixaDesconto;
import venda.ItemPedido;
import venda.ResumoPedido;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private List<ItemPedido> pedidos = new ArrayList<>();
    private final CalculadoraFaixaDesconto calculadoraFaixaDesconto;

    public Pedido(CalculadoraFaixaDesconto calculadoraFaixaDesconto) {
        this.calculadoraFaixaDesconto = calculadoraFaixaDesconto;
    }

    public void adicionarItem(ItemPedido itemPedido) {
        pedidos.add(itemPedido);

    }

    public ResumoPedido getResumopedido(){
        double valorTotal = pedidos.stream()
                .mapToDouble( i -> i.getValor()* i.getQuantidade())
                .sum();

        double desconto = calculadoraFaixaDesconto.desconto(valorTotal);

        return  new ResumoPedido(valorTotal, desconto);
    }
}
