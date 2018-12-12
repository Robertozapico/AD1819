/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examencomponente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JButton;

/**
 *
 * @author alumnop
 */
public class JButtonExam extends JButton implements Serializable{
    private int numMaxClicks;
    private int currentNumClicks;

    public JButtonExam(){        
        updateText();
        //At this point numMaxClicks will always be 0.
        //if(numMaxClicks <= 0)
        //    setEnabled(false);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClick();
            }
        });
    }
    
    private void onClick(){
        currentNumClicks++;
        updateText();
        if(currentNumClicks == numMaxClicks){
            this.setEnabled(false);
        }            
    }
    
    private void updateText(){
        setText(numMaxClicks-currentNumClicks +" clicks restantes");
    }
    
    public int getNumMaxClicks() {
        return numMaxClicks;
    }

    public void setNumMaxClicks(int numMaxClicks) {
        this.numMaxClicks = numMaxClicks;
    }    
}
