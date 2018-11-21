/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb_01.logica;

import java.io.File;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import jaxb.albaran.Articulos.Articulo;
import jaxb.albaran.PedidoType;

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
            jaxbElement = (JAXBElement) unmarshaller.unmarshal(new java.io.File(documentoXML.toString())); //NOI18N
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
    public boolean annadirProducto(PedidoType pedido, String codigo, String nombreProducto, int cantidad, double precio, String comentario, int dia, int mes, int anyo) {
        XMLGregorianCalendar fecha = null;
        if (!comprobarFecha(anyo, mes, dia)) {
            try {
                fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar();

                fecha.setYear(anyo);
                fecha.setMonth(mes);
                fecha.setDay(dia);
            } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(LogicaMetodos.class.getName()).log(Level.SEVERE, null, ex);
            }

            Articulo articulo = new Articulo();
            articulo.setCodigo(codigo);
            articulo.setNombreProducto(nombreProducto);
            articulo.setCantidad(cantidad);
            articulo.setPrecio(new BigDecimal(precio));
            articulo.setComentario(codigo);

            articulo.setFechaEnvio(fecha);
            /*if(fecha.getDay()>31&&fecha.getDay()<1||fecha.getMonth()>12&&fecha.getMonth()<1||fecha.getYear()>2030&&fecha.getYear()<1989){
                throw new IllegalArgumentException("La fecha no es correcta");
            }*/

            pedido.getArticulos().getArticulo().add(articulo);
            return true;
        } else {
            try{
             throw new ExcepcionPropia.ErrorFecha();   
            } catch (ExcepcionPropia.ErrorFecha ex) {
                System.out.println("Fecha erronea");
            }
            return false;
        }

    }

    @Override
    public boolean modificarDireccion(PedidoType factura, String nombre,
            String calle, String ciudad,
            String provincia
    ) {
        factura.getFacturarA().setNombre(nombre);
        factura.getFacturarA().setCalle(calle);
        factura.getFacturarA().setCiudad(ciudad);
        factura.getFacturarA().setProvincia(provincia);
        return true;
    }

    @Override
    public double calcularImporte(PedidoType factura
    ) {
        Double importe = 0.0;

        for (Articulo articulo : factura.getArticulos().getArticulo()) {
            importe += (articulo.getPrecio().doubleValue()) * articulo.getCantidad();
        }
        return importe;
    }

    @Override
    public boolean borrarProducto(PedidoType factura, String codigoProductoABorrar
    ) {
        Articulo articuloABorrar = new Articulo();
        articuloABorrar.setCodigo(codigoProductoABorrar);
        for (Articulo articulo : factura.getArticulos().getArticulo()) {
            if (articulo.getCodigo().equals(articuloABorrar.getCodigo())) {
                articuloABorrar = articulo;
            }
        }
        factura.getArticulos().getArticulo().remove(articuloABorrar);

        return true;
    }

    private boolean comprobarFecha(int anyo, int mes, int dia) {
        if (0 > dia && dia < 32 && 0 > mes && mes < 13 && 1989 > anyo && anyo < 2031) {
            return true;
        } else {
            return false;
        }
    }

}
