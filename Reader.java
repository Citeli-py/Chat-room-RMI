import java.rmi.RemoteException;
import java.util.Scanner;

public class Reader implements Runnable {
    
    private Publisher publisher;
    private String topic;
    private Subscriber subscriber;

    public Reader(Publisher publisher, Subscriber subscriber, String topic){
        this.publisher = publisher;
        this.topic = topic;
        this.subscriber = subscriber;
    }

    @Override
    public void run(){
        Scanner scanner = new Scanner(System.in);
        String message;

        do{
            //System.out.print(topic+ "| " + sender + " > ");
            message = scanner.nextLine();

            if("quit".equals(message)) {
                try{
                    publisher.disconnect(this.subscriber, topic);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            }

            try {
                this.publisher.publish(this.subscriber.getName(), this.topic, message);
            } catch (RemoteException e) {
                // Lidar com a exceção RemoteException aqui
                e.printStackTrace(); // Ou use um tratamento apropriado
            }
            
        }while(true);

        scanner.close();

    }
}
