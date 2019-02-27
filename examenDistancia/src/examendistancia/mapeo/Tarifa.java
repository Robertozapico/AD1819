/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examendistancia.mapeo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author alumnop
 */
@Entity
public class Tarifa implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombreTarifa;
    private int gigasMaxDatos;
    private int minutosMaxLlamadas;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
    private SIMLinea linea;

    public Tarifa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreTarifa() {
        return nombreTarifa;
    }

    public void setNombreTarifa(String nombreTarifa) {
        this.nombreTarifa = nombreTarifa;
    }

    public int getGigasMaxDatos() {
        return gigasMaxDatos;
    }

    public void setGigasMaxDatos(int gigasMaxDatos) {
        this.gigasMaxDatos = gigasMaxDatos;
    }

    public int getMinutosMaxLlamadas() {
        return minutosMaxLlamadas;
    }

    public void setMinutosMaxLlamadas(int minutosMaxLlamadas) {
        this.minutosMaxLlamadas = minutosMaxLlamadas;
    }

    public SIMLinea getLinea() {
        return linea;
    }

    public void setLinea(SIMLinea linea) {
        this.linea = linea;
    }
    
    
    
}
