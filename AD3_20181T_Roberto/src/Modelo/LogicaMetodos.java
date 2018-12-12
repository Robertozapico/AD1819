/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.stream.StreamSource;
import jaxb.bibliotecaBinding.Biblioteca;
import jaxb.bibliotecaBinding.LibroType;
import jaxb.bibliotecaBinding.PrestamoType;
import jaxb.bibliotecaBinding.SocioType;

/**
 *
 * @author alumnop
 */
public class LogicaMetodos {

    private JAXBElement jaxbElement = null;
    private javax.xml.bind.JAXBContext jaxbCtx = null;
    private Marshaller m = null;

    /**
     * Convierte el fichero en clases de java
     *
     * @param documentoXML fichero XML que se va a convertir
     * @param nombrePaqueteDeLasClasesGeneradasPorElXML nombre del paquete
     * @return
     */
    public JAXBElement unmarshalizar(File documentoXML, String nombrePaqueteDeLasClasesGeneradasPorElXML) {
        try {
            jaxbCtx = javax.xml.bind.JAXBContext.newInstance(nombrePaqueteDeLasClasesGeneradasPorElXML);
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            jaxbElement = (JAXBElement) unmarshaller.unmarshal(new StreamSource(new java.io.File(documentoXML.toString())), Biblioteca.class); //NOI18N

        } catch (javax.xml.bind.JAXBException ex) {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        return jaxbElement;
    }

    /**
     * Convierte las clases con sus actuales datos en XML
     *
     * @param esquema elemento que vamos a marshalizar
     * @return
     */
    public Marshaller marshalizar(JAXBElement esquema) {
        try {
            m = jaxbCtx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(jaxbElement, new File("FicheroResultado.xml"));
        } catch (JAXBException ex) {
            Logger.getLogger(LogicaMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    /**
     * Comprueba la existencia de un socio por su nombre
     *
     * @param biblioteca
     * @param nombreSocio
     * @return si el socio existe o no
     */
    private boolean comprobarSocioPorNombre(Biblioteca biblioteca, String nombreSocio) {
        boolean existeSocio = false;
        for (SocioType socioType : biblioteca.getSocios().getSocio()) {
            if (socioType.getNombreSocio().equals(nombreSocio)) {
                existeSocio = true;
            }
        }
        return existeSocio;
    }

    /**
     * Comprueba la existencia de un socio por su codigo de socio
     *
     * @param biblioteca
     * @param codigoSocio codigo del socio que se quiere comprobar
     * @return si el socio existe o no
     */
    private boolean comprobarSocioPorCodigoSocio(Biblioteca biblioteca, String codigoSocio) {
        boolean existeSocio = false;
        for (SocioType socioType : biblioteca.getSocios().getSocio()) {
            if (socioType.getCodigoSocio().equals(codigoSocio)) {
                existeSocio = true;
            }
        }
        return existeSocio;
    }

    /**
     * Crea un prestamo
     *
     * @param biblioteca
     * @param isbn
     * @param titulo
     * @param nombreSocio
     * @param apellidoSocio
     * @param dia
     * @param mes
     * @param anyo
     * @return un PrestamoType
     */
    public PrestamoType crearPrestamo(Biblioteca biblioteca, String isbn, String titulo, String codigoSocio, String nombreSocio, String apellidoSocio, int dia, int mes, int anyo) {
        PrestamoType prestamo = new PrestamoType();
        XMLGregorianCalendar fecha = null;

        if (comprobarFecha(anyo, mes, dia) && comprobarSocioPorCodigoSocio(biblioteca, codigoSocio)) {
            try {
                fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar();

                fecha.setYear(anyo);
                fecha.setMonth(mes);
                fecha.setDay(dia);
            } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(LogicaMetodos.class.getName()).log(Level.SEVERE, null, ex);
            }

            prestamo.setISBN(isbn);
            prestamo.setTitulo(titulo);
            prestamo.setCodigoSocio(codigoSocio);
            prestamo.setNombreSocio(nombreSocio);
            prestamo.setApellidoSocio(apellidoSocio);
            prestamo.setFechaDevolucion(fecha);

        } else {
            if (!comprobarFecha(anyo, mes, dia)) {
                throw new IllegalArgumentException("La fecha es erronea");
            } else if (!comprobarSocioPorNombre(biblioteca, nombreSocio)) {
                throw new IllegalArgumentException("El socio no existe");
            }
        }

        return prestamo;
    }

    /**
     * Inserta un prestamo en la lista de prestamos
     *
     * @param biblioteca
     * @param prestamo
     * @return la lista de prestamos
     */
    public List insertarPrestamo(Biblioteca biblioteca, PrestamoType prestamo) {
        biblioteca.getPrestamos().getPrestamo().add(prestamo);
        return biblioteca.getPrestamos().getPrestamo();
    }

    /**
     * Da de baja a un socio
     *
     * @param biblioteca
     * @param codigoSocio
     * @return si la operación ha tenido éxito o no
     */
    public boolean darDeBajaAUnSocio(Biblioteca biblioteca, String codigoSocio) {
        if (comprobarSocioPorCodigoSocio(biblioteca, codigoSocio)) {
            for (Iterator socioIterator = biblioteca.getSocios().getSocio().iterator(); socioIterator.hasNext();) {
                SocioType siguienteSocio = (SocioType) socioIterator.next();
                if (siguienteSocio.getCodigoSocio().equals(codigoSocio)) {
                    for (PrestamoType prestamoType : biblioteca.getPrestamos().getPrestamo()) {
                        if (prestamoType.getCodigoSocio().equals(codigoSocio)) {
                            throw new IllegalArgumentException("El socio tiene un prestamo");
                        }
                    }
                    System.out.println("Socio borrado");
                    socioIterator.remove();
                    return true;
                }
            }
        } else {
            throw new IllegalArgumentException("El socio no existe");
        }
        return false;
    }

    /**
     * Obtiene un listado Map de la lista de socios con sus prestamos K-codigo
     * del socio, V - lista List de los libros prestados
     *
     * @param biblioteca
     * @return
     */
    public Map obtenerListadoDeSociosYSusPrestamos(Biblioteca biblioteca) {

        Map<String, List> mapListadoSociosConPrestamos = new HashMap<String, List>();
        for (SocioType socio : biblioteca.getSocios().getSocio()) {
            List listaLibrosPrestados = new ArrayList();
            for (PrestamoType librosPrestado : socio.getLibrosPrestados().getPrestamo()) {
                listaLibrosPrestados.add(librosPrestado);
            }
            mapListadoSociosConPrestamos.put(socio.getCodigoSocio(), listaLibrosPrestados);
        }
        return mapListadoSociosConPrestamos;
    }

    /**
     * Devuelve el listado de los libros que no han sido devueltos a tiempo de
     * un determinado socio
     *
     * @param biblioteca
     * @param codigoSocio
     * @param dia
     * @param mes
     * @param anyo
     * @return
     */
    public List obtenerListadoLibrosNoDevueltosATiempo(Biblioteca biblioteca, String codigoSocio, int dia, int mes, int anyo) {
        List listaLibrosRetrasado = new ArrayList();
        XMLGregorianCalendar fecha = null;

        if (comprobarFecha(anyo, mes, dia)) {
            try {
                fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar();

                fecha.setYear(anyo);
                fecha.setMonth(mes);
                fecha.setDay(dia);
            } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(LogicaMetodos.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (PrestamoType prestamoType : biblioteca.getPrestamos().getPrestamo()) {
                if (prestamoType.getCodigoSocio().equals(codigoSocio)) {
                    String isbn = prestamoType.getISBN();
                    for (LibroType libroType : biblioteca.getLibros().getLibro()) {
                        if (isbn.equals(libroType.getISBN())) {
                            XMLGregorianCalendar fechaDevolucion = libroType.getFechaDevolucion().getValue();
                            if (fechaDevolucion.getYear() <= fecha.getYear()
                                    && fechaDevolucion.getMonth() <= fecha.getMonth()
                                    && fechaDevolucion.getDay() < fecha.getDay()) {
                                listaLibrosRetrasado.add(libroType);
                            }
                        }
                    }
                }
            }

        }
        return listaLibrosRetrasado;
    }
/**
 * Crea un fichero json con los datos de los socios
 * @param rutaFichero
 * @param biblioteca
 * @throws IOException 
 */
    public void crearFicheroJSONDeSocios(String rutaFichero, Biblioteca biblioteca) throws IOException {
        List<SocioType> socios = new ArrayList<>();
        for (SocioType socio : biblioteca.getSocios().getSocio()) {
            String nombre, apellido1, apellido2;
            nombre = socio.getNombreSocio();
            apellido1 = socio.getApellidoSocio().get(0);
            apellido2 = socio.getApellidoSocio().get(1);
            BigInteger telefono = socio.getTelefono();
            socios.add(socio);
        }
        List<JsonObject> sociosJson = new ArrayList<>();
        for (SocioType socio : biblioteca.getSocios().getSocio()) {
            JsonObject socioJson = Json.createObjectBuilder()
                    .add("nombre", socio.getNombreSocio())
                    .add("apellidos", Json.createArrayBuilder()//creamos el corchete
                            .add(Json.createObjectBuilder()
                                    .add("apellido1", socio.getApellidoSocio().get(0))
                                    .add("apellido2", socio.getApellidoSocio().get(1))
                            )//},   
                    )//],
                    .add("telefono", socio.getTelefono())
                    .build();
            sociosJson.add(socioJson);
        }
        JsonArrayBuilder clientes = Json.createArrayBuilder();
        for (JsonObject objetoJson : sociosJson) {
            clientes.add(objetoJson);
        }

        JsonArray arrayJson = clientes
                .build();
        FileWriter ficheroSalida = new FileWriter(rutaFichero);
        JsonWriter jsonWriter = Json.createWriter(ficheroSalida);
        jsonWriter.writeArray(arrayJson);
        ficheroSalida.flush();
        ficheroSalida.close();
    }

    /**
     * Comprueba la validez de una fecha
     *
     * @param anyo
     * @param mes
     * @param dia
     * @return
     */
    private boolean comprobarFecha(int anyo, int mes, int dia) {
        if (0 > dia && dia < 32 && 0 > mes && mes < 13 && 1989 > anyo && anyo < 2031) {
            return false;
        } else {
            return true;
        }
    }

}
