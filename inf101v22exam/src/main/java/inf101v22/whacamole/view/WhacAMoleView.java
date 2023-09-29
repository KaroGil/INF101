package inf101v22.whacamole.view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * The main class of the view. Responsible for drawing the entire game of
 * Whac-A-Mole.
 */
public class WhacAMoleView extends JPanel {
    private static final int GAP = 10;

    public WhacAMoleView() {
        this.setLayout(new BorderLayout(GAP, GAP));
        this.setBorder(new EmptyBorder(GAP, GAP, GAP, GAP));

        this.add(new HeadUpDisplay(), BorderLayout.NORTH);
        this.add(new JLabel("TODO"), BorderLayout.CENTER);
        this.add(new ButtonsPanel(), BorderLayout.SOUTH);
    }
}
