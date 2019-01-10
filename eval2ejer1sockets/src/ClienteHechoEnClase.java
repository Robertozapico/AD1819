
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteHechoEnClase {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 2000;
        System.out.println("Arranco cliente");
        try {
            Socket sk = new Socket(host, port);
            //...Intercambio de información
            InputStream inputStreamSocket = sk.getInputStream();
            DataInputStream dataInputStreamSocket = new DataInputStream(inputStreamSocket);
            String msg = dataInputStreamSocket.readUTF();
            System.out.println(msg);
            inputStreamSocket.close();
            dataInputStreamSocket.close();
            //
            sk.close();
            System.out.println("Finaliza el cliente");
        } catch (IOException ex) {
            System.out.println("Error en la comunicación");
        }
    }

}
