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
import javax.persistence.OneToOne;

/**
 *
 * @author alumnop
 */
@Entity
public class Terminal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IMEITerminal;
    private String Modelo;
    private String Marca;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
    private SIMLinea simLinea;

    public Terminal() {
    }

    public int getIMEITerminal() {
        return IMEITerminal;
    }

    public void setIMEITerminal(int IMEITerminal) {
        this.IMEITerminal = IMEITerminal;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public SIMLinea getSimLinea() {
        return simLinea;
    }

    public void setSimLinea(SIMLinea simLinea) {
        this.simLinea = simLinea;
    }

}
