/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb_01.logica;

import java.io.File;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import jaxb.albaran.PedidoType;

/**
 *
 * @author alumnop
 */
public interface Interfaz {

    public JAXBElement unmarshalizar(File documentoXML, String nombrePaqueteDeLasClasesGeneradasPorElXML);

    public Marshaller marshalizar(JAXBElement esquema);

    public boolean annadirProducto(PedidoType pedido, String codigo, String nombreProducto, int cantidad, double precio, String comentario, int dia, int mes, int anyo);

    public boolean modificarDireccion(PedidoType factura, String nombre, String calle, String ciudad, String provincia);

    public double calcularImporte(PedidoType factura);
    
    public boolean borrarProducto(PedidoType factura, String codigoProductoABorrar);

}
