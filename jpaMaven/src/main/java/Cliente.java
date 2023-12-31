import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;
	private String endereco;
	private String telefone;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Pedido> pedidos = new ArrayList<>();

	// Construtor vazio (necessário para JPA)
	public Cliente() {
	}

	public Cliente(String nome, String email, String endereco, String telefone) {
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.telefone = telefone;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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