package inf101v22.whacamole.view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

/**
 * Draws the panel containing the reset button for a game of Whac-A-Mole
 */
public class ButtonsPanel extends JComponent {

    public ButtonsPanel() {
        // Reset button
        JButton resetButton = new JButton("Reset");

        // Panel of buttons
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        this.add(Box.createHorizontalGlue());
        this.add(resetButton);
        this.add(Box.createHorizontalGlue());
    }
}
