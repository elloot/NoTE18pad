import javax.swing.*;
import java.awt.*;
import java.io.*;

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
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(saveItem);
        fileMenu.add(openItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        saveItem.addActionListener(e -> {
            fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save file");
            int result = fileChooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                String file = fileChooser.getSelectedFile().getAbsolutePath();
                String content = textPane1.getText();

                try {
                    PrintWriter outStream = new PrintWriter(new BufferedWriter(new FileWriter(file)));
                    outStream.print(content);
                    outStream.flush();
                    outStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("No file was chosen!");
                System.exit(0);
            }
        });

        openItem.addActionListener(e -> {
            fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Open file");
            int result = fileChooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                String file = fileChooser.getSelectedFile().getAbsolutePath();
                //String content = textPane1.getText();

                try {
                     BufferedReader inStream = new BufferedReader(new FileReader(file));
                     StringBuilder fileContent = new StringBuilder();

                     LineNumberReader lnr = new LineNumberReader(new FileReader(file));
                     int lineCount = 0;

                     while (lnr.readLine() != null) {
                         lineCount++;
                     }

                     for (int i = 0; i < lineCount; i++) {
                         fileContent.append(inStream.readLine());
                         if (i != lineCount-1) {
                             fileContent.append("\n");
                         }
                     }

                     textPane1.setText(String.valueOf(fileContent));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("No file was chosen!");
                System.exit(0);
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
