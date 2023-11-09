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

}
