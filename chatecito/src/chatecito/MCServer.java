/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatecito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

/**
 *
 * @author alumnop
 */
public class MCServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        System.out.println("Arrancando el servidor multicast...\n");
        //Creamos el MulticastSocket sin especificar puerto.
        MulticastSocket s = new MulticastSocket();
        // Creamos el grupo multicast:
        InetAddress group = InetAddress.getByName("231.0.0.1");

        // Creamos un datagrama vacío en principio:
        byte[] vacio = new byte[0];
        DatagramPacket dgp = new DatagramPacket(vacio, 0, group, 10000);
        
        String mensaje = null;
        
//Cogemos los datos a encapsular de la entrada estándar (el teclado)
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String linea = br.readLine();
//El servidor enviará los datos que lea por teclado hasta que se escriba "salir":
//Creamos el buffer a enviar
        
        while (!"salir".equals(mensaje)) {
            System.out.println("Introduce mensaje:");
            mensaje = new Scanner(System.in).nextLine();
            byte[] buffer = mensaje.getBytes();
//Pasamos los datos al datagrama
            dgp.setData(buffer);
//Establecemos la longitud
            dgp.setLength(buffer.length);
//Y por último enviamos:
            s.send(dgp);
//Leemos de la entrada estandar para evitar bucles infinitos
            
        }
        // Cerramos el socket.
        s.close();
    }
}
