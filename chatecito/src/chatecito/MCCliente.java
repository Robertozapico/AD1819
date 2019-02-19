/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatecito;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author alumnop
 */
public class MCCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

//Creamos un socket multicast en el puerto 10000:
        MulticastSocket s = new MulticastSocket(10000);
//Configuramos el grupo (IP) a la que nos conectaremos:
        InetAddress group = InetAddress.getByName("231.0.0.1");
//Nos unimos al grupo:
        s.joinGroup(group);
        String salida = new String();
        while (!salida.equals("salir")) {
// Los paquetes enviados son de 256 bytes de maximo (es adaptable)
            byte[] buffer = new byte[256];
//Creamos el datagrama en el que recibiremos el paquete
//del socket:
            DatagramPacket dgp = new DatagramPacket(buffer, buffer.length);
// Recibimos el paquete del socket:
            s.receive(dgp);
// Adaptamos la información al tamaño de lo que se envió
//(por si se envió menos de 256):
            byte[] buffer2 = new byte[dgp.getLength()];
// Copiamos los datos en el nuevo array de tamaño adecuado:
            System.arraycopy(dgp.getData(), 0, buffer2, 0, dgp.getLength());
//Vemos los datos recibidos por pantalla:
            salida = new String(buffer2);
            System.out.println(salida);
        }
//Salimos del grupo multicast
        s.leaveGroup(group);
// Cerramos el socket:
        s.close();
    }
}




