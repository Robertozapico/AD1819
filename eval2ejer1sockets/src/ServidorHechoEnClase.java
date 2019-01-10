
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alumnop
 */
public class ServidorHechoEnClase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket skServer;
        System.out.println("Arranca servidor");
        try {
            //Hecho en clase
            skServer = new ServerSocket(2000);
            for (int numeroDeCliente = 0; numeroDeCliente < 3; numeroDeCliente++) {
                Socket skDinamicoDeComunicacionConElCliente = skServer.accept();

                System.out.println("Ha llegado petición del cliente número " + numeroDeCliente);
                //.... Enviar información al cliente
                String msg = "Hola cliente " + numeroDeCliente;
                OutputStream fileSalidaSocket = skDinamicoDeComunicacionConElCliente.getOutputStream();
                DataOutputStream dataSocketDataOutputStream = new DataOutputStream(fileSalidaSocket);
                dataSocketDataOutputStream.writeUTF(msg);
                //
                skDinamicoDeComunicacionConElCliente.close();
            }
            skServer.close();
            System.out.println("");
        } catch (IOException ex) {
            System.out.println("Error en la comunicación");
        }

    }

}
