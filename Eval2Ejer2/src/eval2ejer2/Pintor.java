/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eval2ejer2;

import java.sql.Date;

/**
 *
 * @author alumnop
 */
public class Pintor {

    private int idPintor;
    private String nombre;
    private Date anyoNacimiento;
    private String estilo;

    public Pintor() {
    }

    public Pintor(int idPintor, String nombre, Date anyoNacimiento, String estilo) {
        this.idPintor = idPintor;
        this.nombre = nombre;
        this.anyoNacimiento = anyoNacimiento;
        this.estilo = estilo;
    }

    public int getIdPintor() {
        return idPintor;
    }

    public void setIdPintor(int idPintor) {
        this.idPintor = idPintor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getAnyoNacimiento() {
        return anyoNacimiento;
    }

    public void setAnyoNacimiento(Date anyoNacimiento) {
        this.anyoNacimiento = anyoNacimiento;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

}
