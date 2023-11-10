import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ClientePedidoDAO {
	private EntityManager em;

	public ClientePedidoDAO(EntityManager em) {
		this.em = em;
	}

	public void atualizarCliente(Long clienteId, String novoNome) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		try {
			Cliente cliente = em.find(Cliente.class, clienteId);
			if (cliente != null) {
				cliente.setNome(novoNome);
			} else {
				System.out.println("Cliente não encontrado com o ID: " + clienteId);
			}

			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}
	
	public void atualizarStatusPedido(Long pedidoId, String novoStatus) {
	    EntityTransaction transaction = em.getTransaction();
	    transaction.begin();

	    try {
	        Pedido pedido = em.find(Pedido.class, pedidoId);
	        if (pedido != null) {
	            pedido.setStatus(novoStatus);
	        } else {
	            System.out.println("Pedido não encontrado com o ID: " + pedidoId);
	        }

	        transaction.commit();
	    } catch (Exception e) {
	        transaction.rollback();
	        e.printStackTrace();
	    }
	}
    //adiciona a funcionalidade de manipulação de dados para a entidade Pedido em relação a um Cliente.
    public void deletarPedidoDeCliente(Long clienteId, Long pedidoId) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
    
        try {
            Cliente cliente = em.find(Cliente.class, clienteId);
            Pedido pedido = em.find(Pedido.class, pedidoId);
    
            if (cliente != null && pedido != null && cliente.getPedidos().contains(pedido)) {
                cliente.getPedidos().remove(pedido); // Remove o pedido da lista de pedidos do cliente
                pedido.setCliente(null); // Remover a referência do cliente no pedido, se for um relacionamento bidirecional
    
                em.remove(pedido); // Deleta o pedido do banco de dados
            } else {
                System.out.println("Cliente ou Pedido não encontrado, ou o Pedido não pertence ao Cliente.");
            }
    
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
    

}
