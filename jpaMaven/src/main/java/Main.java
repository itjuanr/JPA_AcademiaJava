import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("academiajava");
            em = emf.createEntityManager();

            em.getTransaction().begin();

            Cliente cliente = new Cliente("Juan", "juan@example.com", "Rua ABC, 123", "123456789");

            Pedido pedido1 = new Pedido(140.0, LocalDate.now(), "Em andamento", cliente.getEndereco());
            pedido1.setCliente(cliente);
            Pedido pedido2 = new Pedido(180.0, LocalDate.now(), "Em andamento", cliente.getEndereco());
            pedido2.setCliente(cliente);
            Pedido pedido3 = new Pedido(220.0, LocalDate.now(), "Em andamento", cliente.getEndereco());
            pedido3.setCliente(cliente);

            cliente.addPedido(pedido1);
            cliente.addPedido(pedido2);
            cliente.addPedido(pedido3);

            em.persist(cliente);

            em.getTransaction().commit();

            ClientePedidoDAO dao = new ClientePedidoDAO(em);

            // Recupera o cliente do banco de dados usando o ID
            Cliente clienteRecuperado = em.find(Cliente.class, cliente.getId());

            // Imprime as informações do cliente e seus pedidos recuperados do banco de dados
            System.out.println("Cliente recuperado do banco de dados: " + clienteRecuperado);
            System.out.println("Pedidos do cliente: " + clienteRecuperado.getPedidos());
            
            //Atualiza o status do pedido
            dao.atualizarStatusPedido(pedido2.getId(), "Concluído");
            
            // Atualiza o nome do cliente
            dao.atualizarCliente(clienteRecuperado.getId(), "João");

            // Re-obtém o cliente do banco de dados após as alterações
            clienteRecuperado = em.find(Cliente.class, clienteRecuperado.getId());

            // Imprime as informações do cliente recuperado do banco de dados após as alterações
            System.out.println("Cliente recuperado do banco de dados após alterações: " + clienteRecuperado);

        } catch (Exception e) {
            // Trate a exceção aqui, se necessário
            e.printStackTrace();
        } finally {
            // Fecha o EntityManager e o EntityManagerFactory no bloco finally
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}
