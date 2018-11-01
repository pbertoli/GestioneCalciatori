package gestionecalciatori;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RispondiAdInvio implements ActionListener {

    GestioneCalciatori frame;

    public RispondiAdInvio(GestioneCalciatori frame) {
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        Object t = e.getSource();
        if (t == frame.getT1()) {
            System.out.println("Sono JTextField1 " + e.getActionCommand());
            frame.getT2().requestFocusInWindow();
        } else if (t == frame.getT2()) {
            System.out.println("Sono JTextField2 " + e.getActionCommand());
            frame.getT3().requestFocusInWindow();
        } else if (t == frame.getT3()) {
            System.out.println("Sono JTextField3 " + e.getActionCommand());
            frame.getT1().requestFocusInWindow();
        } else {
            System.out.println("Non so chi sono " + e.getActionCommand());
        }
    }
}
