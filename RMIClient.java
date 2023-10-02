import java.rmi.Naming; 
//import java.rmi.RemoteException; 

public class RMIClient {
    public static void main(String[] args) {
        String name;
        String topic;

        if (args.length < 2) { // Verifica se foram fornecidos argumentos suficientes na linha de comando.
            System.out.println("Uso: <nome> <sala>");
            return;
        }

        name = args[0]; // Obtém o primeiro argumento como o nome do cliente.
        topic = args[1]; // Obtém o segundo argumento como o tópico.

        try {
            // Procura o objeto remoto "Publisher" no registro RMI no localhost.
            Publisher publisher = (Publisher) Naming.lookup("rmi://localhost/Publisher");

            // Cria uma instância de SubscriberImpl com o nome do cliente.
            Subscriber subscriber = new SubscriberImpl(name);

            // Inscreve o cliente no tópico no servidor RMI.
            publisher.subscribe(topic, subscriber);
            
            Reader reader = new Reader(publisher, subscriber, topic);
            Thread t_reader = new Thread(reader);
            t_reader.start();

            try {
                t_reader.join(); // Aguarda o fim da thread de leitura
                System.out.println("Você se desconectou!");
                System.exit(0); // Finaliza o programa
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (Exception e) { // Captura qualquer exceção que possa ocorrer.
            e.printStackTrace(); // Imprime informações sobre a exceção no console.
        }
    }
}