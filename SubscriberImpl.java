import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject; 

// Define a classe SubscriberImpl que estende UnicastRemoteObject e implementa a interface Subscriber.
public class SubscriberImpl extends UnicastRemoteObject implements Subscriber {

    // O nome do inscrito.
    private final String name;

    // Construtor que pode lançar uma RemoteException.
    public SubscriberImpl(String name) throws RemoteException{
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    // Implementação do método notify definido na interface Subscriber.
    public void notify(String sender, String topic, String message) throws RemoteException{
        // Método chamado quando o inscrito é notificado de uma nova mensagem.
        System.out.println(sender + " | " + topic + ": " + message );
    }
}