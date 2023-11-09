import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        // Cria um EntityManagerFactory usando o nome da unidade de persistência definido no persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("academiajava");

        // Cria um EntityManager a partir do EntityManagerFactory
        EntityManager em = emf.createEntityManager();

        // Inicia uma transação
        em.getTransaction().begin();

        // Cria um novo cliente
        Cliente cliente = new Cliente();
        cliente.setNome("Juan");

        // Cria uma lista de pedidos e os associa ao cliente
        Pedido pedido1 = new Pedido();
        pedido1.setValorTotal(140.0); // Define o valor total do primeiro pedido
        pedido1.setCliente(cliente); // Associa o cliente ao primeiro pedido

        Pedido pedido2 = new Pedido();
        pedido2.setValorTotal(200.0); // Define o valor total do segundo pedido
        pedido2.setCliente(cliente); // Associa o cliente ao segundo pedido

        // Adiciona os pedidos à lista de pedidos do cliente
        cliente.setPedidos(List.of(pedido1, pedido2));

        // Persiste o cliente e os pedidos no banco de dados
        em.persist(cliente);
        em.persist(pedido1);
        em.persist(pedido2);

        // Comita a transação para efetivar as mudanças no banco de dados
        em.getTransaction().commit();

        // Recupera o cliente do banco de dados usando o ID
        Cliente clienteRecuperado = em.find(Cliente.class, cliente.getId());

        // Imprime as informações do cliente e seus pedidos recuperados do banco de dados
        System.out.println("Cliente recuperado do banco de dados: " + clienteRecuperado);
        System.out.println("Pedidos do cliente: " + clienteRecuperado.getPedidos());

        // Utiliza a classe ClientePedidoDAO para atualizar o nome do cliente
        ClientePedidoDAO dao = new ClientePedidoDAO(em);
        dao.atualizarCliente(clienteRecuperado.getId(), "João"); // Atualiza o nome do cliente para "João"

        // Re-obtém o cliente do banco de dados após as alterações
        clienteRecuperado = em.find(Cliente.class, clienteRecuperado.getId());

        // Imprime as informações do cliente recuperado do banco de dados após as alterações
        System.out.println("Cliente recuperado do banco de dados após alterações: " + clienteRecuperado);

        // Fecha o EntityManager e o EntityManagerFactory quando não precisar mais deles
        em.close();
        emf.close();
    }
}
