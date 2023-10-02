import java.rmi.Remote; 
import java.rmi.RemoteException; 

// Define uma interface chamada Publisher que estende a interface Remote, indicando que seus métodos podem ser chamados remotamente.
public interface Publisher extends Remote {

    // Método para publicar uma mensagem em um tópico específico. 
    void publish (String sender, String topic, String message) throws RemoteException;

    // Método para se inscrever em um tópico e receber mensagens quando são publicadas. 
    void subscribe (String topic, Subscriber subscriber) throws RemoteException;

    // Método para disconectar um subscriber.
    public void disconnect(Subscriber subscriber, String topic) throws RemoteException;
}