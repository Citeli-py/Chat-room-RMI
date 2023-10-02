import java.rmi.Remote; 
import java.rmi.RemoteException; 

// Define a interface Subscriber que estende a interface Remote, indicando que seus métodos podem ser chamados remotamente.
public interface Subscriber extends Remote {

    // Método para notificar um inscrito sobre uma mensagem em um tópico específico. 
    void notify(String sender, String topic, String message) throws RemoteException;

    String getName() throws RemoteException;
}