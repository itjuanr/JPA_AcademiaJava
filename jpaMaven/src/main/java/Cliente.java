import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos = new ArrayList<>();
    
    // Construtor vazio (necessário para JPA)
    public Cliente() {
    }
    
    // Construtor com parâmetros
    public Cliente(String nome) {
        this.nome = nome;
    }
    
    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
        pedido.setCliente(this); // Estabelece a associação bidirecional
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + "]";
    }
}
