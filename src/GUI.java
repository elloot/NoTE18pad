import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class description
 * 2020-03-12
 * Author: Elliot Duchek
 */
public class GUI {
    private JPanel RootPane;
    private JTextPane textPane1;
    private JMenuBar menuBar;
    private static Dimension preferredSize = new Dimension(400,400);
    private JFileChooser fileChooser;

    public GUI() {
        JFrame frame = new JFrame("Notepad-clone");
        frame.setPreferredSize(preferredSize);
        frame.setContentPane(this.RootPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textPane1);

                fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save file");
                int result = fileChooser.showOpenDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    String fileName = fileChooser.getSelectedFile().getAbsolutePath();
                    String content = textPane1.getText();

                    try {
                        PrintWriter outStream = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
                        outStream.print(content);
                        outStream.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("No file was chosen!");
                    System.exit(0);
                }
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //set look and feel
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        GUI gui = new GUI();


    }

}
