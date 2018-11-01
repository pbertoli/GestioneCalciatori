package gestionecalciatori;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class RispondiAlClick implements ActionListener {

    GestioneCalciatori frame;

    public RispondiAlClick(GestioneCalciatori frame) {
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        Object b = e.getSource();
        if (b == frame.getB1()) {
            System.out.println("Sono bottone " + e.getActionCommand());
            frame.closeAll();
        } else if (b == frame.getB2()) {
            System.out.println("Sono il bottone " + e.getActionCommand());
            frame.getT1().setText("<nome>");
            frame.getT2().setText("<targa>");
            frame.getT3().setText("<anno>");
        } else if (b == frame.getB3()) {
            System.out.println("Sono il bottone " + e.getActionCommand());
            frame.getT1().setText("");
            frame.getT2().setText("");
            frame.getT3().setText("");
        } else if (b == frame.getB4()) {
            System.out.println("Sono il bottone " + e.getActionCommand());
            String nome = frame.getT1().getText();
            String targa = frame.getT2().getText();
            String strAnno = frame.getT3().getText();
            int anno = 0;
            try {
                anno = Integer.parseInt(strAnno);
                DbUtils.insertProprietario(nome, targa, anno);
            } catch (NumberFormatException ne) {
                JOptionPane.showMessageDialog(null, "Attento al "
                        + "formato dell'anno.\n" + ne);
            }
        } else if (b == frame.getB5()) {
            System.out.println("Sono il bottone " + e.getActionCommand());
            String nome = frame.getT1().getText();
            String targa = frame.getT2().getText();
            DbUtils.deleteProprietario(nome, targa);
        } else if (b == frame.getB6()) {
            System.out.println("Sono il bottone " + e.getActionCommand());
            ArrayList list = DbUtils.elencaProprietari();
            new PopUpFrame(list);
        } else {
            System.out.println("Non so chi sono. Bohh " + e.getActionCommand());
        }
    }
}
