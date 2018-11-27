/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import jaxb.clientes.Clientes;
import jaxb.clientes.Clientes.Cliente.Nombre;
import jaxb.clientes.TipoDireccion;

/**
 *
 * @author alumnop
 */
public class LogicaMetodos implements Interfaz {

    private JAXBElement jaxbElement = null;
    private javax.xml.bind.JAXBContext jaxbCtx = null;
    private Marshaller m = null;

    @Override
    public JAXBElement unmarshalizar(File documentoXML, String nombrePaqueteDeLasClasesGeneradasPorElXML) {
        try {
            jaxbCtx = javax.xml.bind.JAXBContext.newInstance(nombrePaqueteDeLasClasesGeneradasPorElXML);
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            jaxbElement = (JAXBElement) unmarshaller.unmarshal(new StreamSource(new java.io.File(documentoXML.toString())), Clientes.class); //NOI18N
            //Clientes clientes = (Clientes) new Clientes();
            //clientes = (Clientes) jaxbElement.getValue();
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        return jaxbElement;
    }

    @Override
    public Marshaller marshalizar(JAXBElement esquema) {
        try {
            m = jaxbCtx.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(jaxbElement, System.out);
        } catch (JAXBException ex) {
            Logger.getLogger(LogicaMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    @Override
    public int numTotalClientes(Clientes clientes) {
        return clientes.getCliente().size();
    }

    @Override
    public int numTotalClientesDeUnaProvincia(Clientes clientes, int codPostal) {
        int numTotal = 0;
        boolean perteneceALaProvincia = false;
        codPostal /= 1000;
        for (Clientes.Cliente cliente : clientes.getCliente()) {
            for (TipoDireccion direccion : cliente.getDireccion()) {
                int direccionCliente = direccion.getCp();
                direccionCliente /= 1000;
                if (direccionCliente == codPostal) {
                    perteneceALaProvincia = true;
                }
            }
            if (perteneceALaProvincia == true) {
                numTotal++;
                perteneceALaProvincia = false;
            }
        }
        return numTotal;
    }

    @Override
    public boolean borrarCliente(Clientes clientes, String apellido1, String apellido2) {
        int clientesBorrados = 0;
        Iterator<Clientes.Cliente> iteratorCliente;
        Clientes.Cliente clientillo = null;
        iteratorCliente = clientes.getCliente().iterator();
        while (iteratorCliente.hasNext()) {
            clientillo = iteratorCliente.next();
            //System.out.println(clientillo.getApellido());
            if (clientillo.getApellido().get(0).equals(apellido1) && clientillo.getApellido().get(1).equals(apellido2)) {
                //System.out.println("Se va a borrar a: " + clientillo.getApellido());           
                iteratorCliente.remove();
                clientesBorrados++;
            }
        }
        return true;
    }

    @Override
    public boolean annadirCliente(Clientes cliente, String apellido1, String apellido2, int telefono, String nombre, String calle, int numero, int piso, String escalera, int cp, String ciudad) {
        Clientes.Cliente clienteNuevo = new Clientes.Cliente();
        Nombre nombreNuevoCliente = new Nombre();
        nombreNuevoCliente.setLenguaje(nombre);
        clienteNuevo.getApellido().add(apellido1);
        clienteNuevo.getApellido().add(apellido2);
        clienteNuevo.setNombre(nombreNuevoCliente);
        clienteNuevo.setTelefono(Integer.toString(telefono));

        TipoDireccion dirNuevoCliente = new TipoDireccion();
        dirNuevoCliente.setCalle(calle);
        dirNuevoCliente.setCiudad(ciudad);
        dirNuevoCliente.setCp(cp);
        dirNuevoCliente.setEscalera(escalera);
        dirNuevoCliente.setNumero(Integer.toString(numero));
        dirNuevoCliente.setPiso(piso);
        clienteNuevo.getDireccion().add(dirNuevoCliente);
        cliente.getCliente().add(clienteNuevo);
        return true;
    }

    @Override
    public boolean annadirDireccionAUnCliente(Clientes.Cliente cliente, String calle, int numero, int piso, String escalera, int cp, String ciudad) {
        TipoDireccion dirNuevoCliente = new TipoDireccion();
        dirNuevoCliente.setCalle(calle);
        dirNuevoCliente.setCiudad(ciudad);
        dirNuevoCliente.setCp(cp);
        dirNuevoCliente.setEscalera(escalera);
        dirNuevoCliente.setNumero(Integer.toString(numero));
        dirNuevoCliente.setPiso(piso);
        if (cliente.getDireccion().size() < 3) {
            cliente.getDireccion().add(dirNuevoCliente);
            return true;
        } else {
            System.out.println("El cliente ha alcanzado el número máximo de direcciones");
            return false;
        }
    }

    @Override
    public boolean borrarDireccionSinCP(Clientes clientes) {
        for (Clientes.Cliente cliente : clientes.getCliente()) {
            Iterator<TipoDireccion> iteratorDireccionCliente = cliente.getDireccion().iterator();
            while (iteratorDireccionCliente.hasNext()) {
                TipoDireccion Direccion = iteratorDireccionCliente.next();
                if (Direccion.getCp() == 0) {
                    iteratorDireccionCliente.remove();
                }
            }
        }
        return true;
    }
}
