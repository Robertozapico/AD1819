/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examendistancia.mapeo;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alumnop
 */
public class objetoDao {

    private Session sesion;
    private Transaction tx;

    private void iniciarOperacion() {
        sesion = HibernateUtil.getSession().openSession();
        tx = sesion.beginTransaction();
    }
}
