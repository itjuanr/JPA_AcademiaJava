import javax.persistence.*;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double valorTotal;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id") // Define o nome da coluna de chave estrangeira na tabela de pedidos
    private Cliente cliente;
    
    @Override
    public String toString() {
        return "Pedido [id=" + id + ", valorTotal=" + valorTotal + "]";
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public double getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
