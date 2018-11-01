package gestioneproprietari;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PopUpFrame extends JFrame {

    public static final int POPUP_WIDTH = 350;
    public static final int POPUP_HEIGHT = 400;
    public static final Point center = GestionePropietari.center;
    private JScrollPane sp = new JScrollPane();
    private JTextArea ta = new JTextArea();

    public PopUpFrame(ArrayList list) {
        super("Elenco proprietari");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(POPUP_WIDTH, POPUP_HEIGHT));
        center.x += 40;
        center.y += 45;
        setBounds(center.x, center.y, POPUP_WIDTH, POPUP_HEIGHT);
        setLayout(new BorderLayout());
        String s = "";
        for (Object o : list) {
            s += (String) o + " - \n";
        }

        ta.setLineWrap(true);
        ta.setFont(new Font("Tahoma", 0, 18));
        ta.setText(s);
        sp.setViewportView(ta);
        getContentPane().add(sp, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public static void test() {
        ArrayList l = new ArrayList();
        l.add("uno");
        l.add("due");
        l.add("tre");
        l.add("quattro");
        PopUpFrame p = new PopUpFrame(l);
    }
}
