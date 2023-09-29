package inf101v22.whacamole;

import javax.swing.JFrame;

import inf101v22.whacamole.view.WhacAMoleView;

/**
 * The main class for the Whac-A-Mole game.
 */
public class App {

    public static void main(String[] args) {
        new App();
    }

    public App() {
        JFrame frame = new JFrame("INF101 Whac-A-Mole");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        WhacAMoleView view = new WhacAMoleView();

        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
    }
}
