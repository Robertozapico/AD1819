/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examendistancia.mapeo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author alumnop
 */
@Entity
public class SIMLinea implements Serializable {

    private int numDisponible;
    private int minutosConsumidos;

    //@OneToOne(cascade={CascadeType.PERSIST, CascadeType.PERSIST})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numeroTelefono;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
    private List<Tarifa> tarifa;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
    private Terminal terminal;

    public SIMLinea() {
    }

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public int getNumDisponible() {
        return numDisponible;
    }

    public void setNumDisponible(int numDisponible) {
        this.numDisponible = numDisponible;
    }

    public int getMinutosConsumidos() {
        return minutosConsumidos;
    }

    public void setMinutosConsumidos(int minutosConsumidos) {
        this.minutosConsumidos = minutosConsumidos;
    }

    public List<Tarifa> getTarifa() {
        return tarifa;
    }

    public void setTarifa(List<Tarifa> tarifa) {
        this.tarifa = tarifa;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

}
