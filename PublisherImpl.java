import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.concurrent.CopyOnWriteArrayList; 

// Define a classe PublisherImpl que estende UnicastRemoteObject e implementa a interface Publisher.
public class PublisherImpl extends UnicastRemoteObject implements Publisher {

    // Um mapa que mapeia tópicos para listas de inscritos.
    private final Map<String, CopyOnWriteArrayList<Subscriber>> subscribers; // (key: topico)

    public PublisherImpl() throws RemoteException{
        subscribers = new HashMap<>();
    }

    @Override
    // Implementação do método publish definido na interface Publisher.
    public void publish(String sender, String topic, String message) throws RemoteException{
        if (subscribers.containsKey(topic)){ // Verifica se existem inscritos para o tópico especificado.
            CopyOnWriteArrayList<Subscriber> topicSubscribers = subscribers.get(topic); // Obtém a lista de inscritos para o tópico.
            for(Subscriber subscriber: topicSubscribers){
                if(!(subscriber.getName().equals(sender))) // Notifica para todos menos quem enviou
                    subscriber.notify(sender, topic, message); // Notifica cada inscrito com a mensagem.
            }
            System.out.println( topic+ "| " + sender + " > "+ message); //Debug ver oq as pessoas estão falando
        }
    }

    @Override
    // Implementação do método subscribe definido na interface Publisher.
    public void subscribe(String topic, Subscriber subscriber) throws RemoteException{
        if (!subscribers.containsKey(topic)){ // Se o tópico não existir, cria uma nova lista de inscritos.
            subscribers.put(topic, new CopyOnWriteArrayList<>());
        }
        subscribers.get(topic).add(subscriber); // Adiciona o inscrito à lista de inscritos do tópico.
        System.out.println(subscriber.getName() + " se conectou ao canal " + topic);
    }

    @Override
    public void disconnect(Subscriber subscriber, String topic) throws RemoteException{
        CopyOnWriteArrayList<Subscriber> subs = this.subscribers.get(topic); // Pega os inscritos no topico
        subs.remove(subscriber); // Remove o inscrito

        System.out.println(subscriber.getName() + " Se desconectou");
        this.publish(subscriber.getName(), topic, subscriber.getName() + " saiu da sala");
    }
    
}