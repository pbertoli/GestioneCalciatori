package gestionecalciatori;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GestioneCalciatori extends JFrame {

    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGHT = 500;
    public static final Point center
            = GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .getCenterPoint();
    JButton b1, b2, b3, b4, b5, b6;
    JLabel l1, l2, l3;
    JTextField t1, t2, t3;

    public JButton getB1() {
        return b1;
    }

    public JButton getB2() {
        return b2;
    }

    public JButton getB3() {
        return b3;
    }

    public JButton getB4() {
        return b4;
    }

    public JButton getB5() {
        return b5;
    }

    public JButton getB6() {
        return b6;
    }

    public JTextField getT1() {
        return t1;
    }

    public JTextField getT2() {
        return t2;
    }

    public JTextField getT3() {
        return t3;
    }

    public GestioneCalciatori() {
        super("Intercettiamo degli eventi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        center.x -= FRAME_WIDTH / 2;
        center.y -= FRAME_HEIGHT / 2;
        setBounds(center.x, center.y, FRAME_HEIGHT, FRAME_HEIGHT);
        setLayout(new BorderLayout());
        b1 = new JButton("OK");
        b1.setPreferredSize(new Dimension(80, 30));
        b2 = new JButton("Annulla");
        b2.setPreferredSize(new Dimension(80, 30));
        b3 = new JButton("Reset");
        b3.setPreferredSize(new Dimension(80, 30));
        b4 = new JButton("Insert");
        b4.setPreferredSize(new Dimension(80, 30));
        b5 = new JButton("Delete");
        b5.setPreferredSize(new Dimension(80, 30));
        b6 = new JButton("Elenco");
        b6.setPreferredSize(new Dimension(80, 30));

        ActionListener onClick = new RispondiAlClick(this);

        b1.addActionListener(onClick);
        b2.addActionListener(onClick);
        b3.addActionListener(onClick);
        b4.addActionListener(onClick);
        b5.addActionListener(onClick);
        b6.addActionListener(onClick);

        JPanel pSouth = new JPanel();
        pSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pSouth.add(b1);
        pSouth.add(b2);
        pSouth.setBackground(Color.pink);
        add(pSouth, BorderLayout.SOUTH);

        JPanel pEast = new JPanel();
        pEast.setLayout(new BoxLayout(pEast, BoxLayout.Y_AXIS));
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        b4.setAlignmentX(Component.CENTER_ALIGNMENT);
        b5.setAlignmentX(Component.CENTER_ALIGNMENT);
        b6.setAlignmentX(Component.CENTER_ALIGNMENT);
        pEast.setBackground(Color.pink);
        pEast.add(b3);
        pEast.add(b4);
        pEast.add(b5);
        pEast.add(b6);
        add(pEast, BorderLayout.EAST);

        JPanel pCenter = new JPanel();
        pCenter.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        l1 = new JLabel("Nome: ");
        l2 = new JLabel("Targa: ");
        l3 = new JLabel("Anno: ");
        l1.setFont(new Font("Tahoma", 0, 20));
        l2.setFont(new Font("Tahoma", 0, 20));
        l3.setFont(new Font("Tahoma", 0, 20));
        t1 = new JTextField("<nome>");
        t2 = new JTextField("<targa>");
        t3 = new JTextField("<anno>");
        t1.setFont(new Font("Tahoma", 0, 18));
        t2.setFont(new Font("Tahoma", 0, 18));
        t3.setFont(new Font("Tahoma", 0, 18));

        ActionListener onInvio = new RispondiAdInvio(this);
        t1.addActionListener(onInvio);
        t2.addActionListener(onInvio);
        t3.addActionListener(onInvio);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.0;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        pCenter.add(l1, c);
        c.gridx = 1;
        pCenter.add(t1, c);
        c.gridx = 0;
        c.gridy = 1;
        pCenter.add(l2, c);
        c.gridx = 1;
        pCenter.add(t2, c);
        c.gridx = 0;
        c.gridy = 2;
        pCenter.add(l3, c);
        c.gridx = 1;
        pCenter.add(t3, c);
        add(pCenter, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public static void closeAll() {
        Window[] wins = JFrame.getWindows();
        for (Window win : wins) {
            if (win != null) {
                win.dispose();
            }
        }
    }

    public static void test() {
        GestioneCalciatori b = new GestioneCalciatori();
    }
}
