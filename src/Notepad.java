import javax.swing.*;

/**
 * Class description
 * 2020-03-12
 * Author: Elliot Duchek
 */
public class Notepad {
    private JPanel RootPane;
    private JTextPane textPane1;

    public static void main(String[] args) {
        //set look and feel
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        JFrame frame = new JFrame("Notepad");
        frame.setContentPane(new Notepad().RootPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JScrollBar scrollBar1;
}
