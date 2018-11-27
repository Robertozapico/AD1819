/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import jaxb.clientes.Clientes;

/**
 *
 * @author alumnop
 */
public interface Interfaz {

    public JAXBElement unmarshalizar(File documentoXML, String nombrePaqueteDeLasClasesGeneradasPorElXML);

    public Marshaller marshalizar(JAXBElement esquema);

    public int numTotalClientes(Clientes clientes);

    public int numTotalClientesDeUnaProvincia(Clientes clientes, int codPostal);

    public boolean borrarCliente(Clientes cliente, String apellido1, String apellido2);

    public boolean annadirCliente(Clientes cliente, String apellido1, String apellido2, int telefono, String nombre, String calle, int numero, int piso, String escalera, int cp, String ciudad);

    public boolean annadirDireccionAUnCliente(Clientes.Cliente cliente, String calle, int numero, int piso, String escalera, int cp, String ciudad);
    
    public boolean borrarDireccionSinCP(Clientes clientes);
}
