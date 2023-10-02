//import java.rmi.Naming;  // Importa a classe Naming do pacote java.rmi, que fornece métodos para associar nomes simbólicos a referências de objetos remotos.
//import java.rmi.RemoteException;  // Importa a exceção RemoteException do pacote java.rmi, que é lançada quando ocorre um erro durante uma chamada remota.
import java.rmi.registry.LocateRegistry;  // Importa a classe LocateRegistry do pacote java.rmi.registry, que fornece métodos para obter referências para registros RMI.
import java.rmi.registry.Registry;  // Importa a interface Registry do pacote java.rmi.registry, que representa um registro RMI.

public class RMIServer {
    public static void main(String[] args) {
        try {
            Publisher publisher = new PublisherImpl();  // Cria uma instância de PublisherImpl e a atribui à variável publisher, PublisherImpl é uma implementação de uma interface chamada Publisher.
            Registry registry = LocateRegistry.createRegistry(1099);  // Cria um registro RMI na porta 1099. Isso é onde os objetos remotos podem ser registrados para serem acessíveis pelos clientes.
            registry.rebind("Publisher", publisher);  // Registra o objeto 'publisher' no registro RMI com o nome "Publisher". Isso significa que os clientes podem procurar este objeto usando o nome "Publisher".
            System.out.println("Servidor está pronto!");  // Imprime a mensagem "Servidor está pronto!" no console.
            
        } catch (Exception e) {  // Captura qualquer exceção que possa ser lançada dentro do bloco try.
            e.printStackTrace();  // Imprime informações sobre a exceção no console.
        }
    }
}