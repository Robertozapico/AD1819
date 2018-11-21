/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb_01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import jaxb.albaran.Articulos;
import jaxb.albaran.Direccion;
import jaxb.albaran.PedidoType;

/**
 *
 * @author victoriarodriguez
 */
public class JAXB_01 {

       /**
        * @param args the command line arguments
        */
       public static void main(String[] args) throws JAXBException, FileNotFoundException, DatatypeConfigurationException {
/*
              JAXBElement jaxbElement = null;
              javax.xml.bind.JAXBContext jaxbCtx = null;

              try {
                     jaxbCtx = javax.xml.bind.JAXBContext.newInstance("jaxb.albaran");
                     javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
                     jaxbElement = (JAXBElement) unmarshaller.unmarshal(new java.io.File("albaran.xml")); //NOI18N
              } catch (javax.xml.bind.JAXBException ex) {
                     // XXXTODO Handle exception
                     java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
              }

              
              // El método getValue() retorna el modelo de contenido (content model) y el valor de los atributos del elemento
              PedidoType pedidoType = (PedidoType) jaxbElement.getValue();

              // Obtenemos una instancia de tipo PedidoType para obtener un Objeto de tipo Direccion
              Direccion direccion = pedidoType.getFacturarA();

              // Establecemos los datos
              direccion.setNombre("Jose Javier");
              direccion.setCalle("Zafiro 3");
              direccion.setCiudad("Molina");
              direccion.setProvincia("Murcia");
              direccion.setCodigoPostal(new BigDecimal("30500"));

              // Ver ciudad de facturación
              String ciudad = pedidoType.getFacturarA().getCiudad();
              // System.out.println(ciudad);

              List<Articulos.Articulo> listaArticulos = pedidoType.getArticulos().getArticulo();

              // contar total de articulos
              Articulos articulos = pedidoType.getArticulos();
              // List<Articulos.Articulo> listaArticulos = articulos.getArticulo();

              Iterator<Articulos.Articulo> iterator = listaArticulos.iterator();

              while (iterator.hasNext()) {
                     listaArticulos.remove(iterator);
                     Articulos.Articulo next = iterator.next();
                     System.out.println(next.getNombreProducto());
              }

              File file = new File("");

              int totalArticulos = listaArticulos.size();

              // Añadir articulo
              Articulos.Articulo articuloNuevo = new Articulos.Articulo();
              articuloNuevo.setCantidad(4);
              articuloNuevo.setCodigo("AAA");
              articuloNuevo.setNombreProducto("silla");
              articuloNuevo.setPrecio(new BigDecimal(33));
              try {
                     XMLGregorianCalendar fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar();
                     fecha.setYear(2015);
                     fecha.setMonth(11);
                     fecha.setDay(21);
                     articuloNuevo.setFechaEnvio(fecha);
              } catch (DatatypeConfigurationException ex) {

              }
              listaArticulos.add(articuloNuevo);

              // Crear un objeto de tipo Marshaller para posteriormente convertir un
              // el árbol de objetos Java a datos XML
              Marshaller m = jaxbCtx.createMarshaller();

              // El método setProperty(String nombrePropiedad, Object value) recibe en este
              // caso la propiedad "jaxb.formatted.output". Esta propiedad controla si al
              // realizar un marshal, debe formatear el resultado XML con saltos de linea
              // e indentaciones para que las personas podamos leerlo cómodamente. Por defecto
              // su valor es falso es decir el XML creado no está formateado
              // El argumento value en este caso tiene que ser concretamente de tipo Boolean
              // para indicar si queremos que el resultado XML esté formateado o no
              m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

              // El método marshall(Object elementoJAXB, OutputStream os) recibe un objeto
              // de tipo JAXBElement para que su contenido lo muestre en la salida estándar
              // debido a que este método está sobrecargo, si miramos la documentación de
              //la API podemos ver como podemos mostrar o escribir el resultado XML de
              //diferentes maneras
              m.marshal(jaxbElement, System.out);
*/
       }

}
