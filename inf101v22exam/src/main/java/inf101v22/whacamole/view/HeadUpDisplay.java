package inf101v22.whacamole.view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Draws the head-up display for a game of Whac-A-Mole, which contains the game
 * message/clock and the score.
 */
public class HeadUpDisplay extends JComponent {

    public HeadUpDisplay() {

        // Message
        JLabel message = new JLabel("TODO");

        // Score
        JLabel score = new JLabel("TODO");

        // Combining elements
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.add(message);
        this.add(Box.createHorizontalGlue());
        this.add(score);
    }
}
