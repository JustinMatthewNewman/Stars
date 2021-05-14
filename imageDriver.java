
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * DRIVER TO RUN THE CODE.
 * 
 * @author justinnewman
 * @version 11/24/2020
 */
public class imageDriver implements ActionListener {
    static JTextField linkSpaceTextField;

    // Frame is the window space
    protected static JFrame frame;
    static JFrame frame2;
    static JFrame frame3;
    static JComboBox primaryColor;
    static JComboBox secondaryColor;
    static JComboBox typeOfGraphic;
    static JTextField logoLink;
    static JButton scan;

    /**
     * 
     * @param args
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        // Creating the Frame
        frame = new JFrame("Statistic Team Atheletic Rendering Software. ®");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(620, 150);
        JPanel panel = new JPanel(); // the panel is the section of the window
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JLabel label = new JLabel("Paste link to college sports roster :");
        linkSpaceTextField = new JTextField(20);
        scan = new JButton("SCAN");
        JButton render = new JButton("RENDER");
        String[] colorOptionsArray = {" - select Color - ", "JMU Purple",
                "JMU Gold", "Custom(R,G,B)"};
        String[] graphicOptionsArray = {" - select Graphic - ", "Today Graphic",
                "Hero Graphic", "Bio Graphic", "ESPN", "DATA-SCAN"};
        // primary color combo box
        primaryColor = new JComboBox(colorOptionsArray);
        primaryColor.setEditable(true);
        // secondary color combo box
        secondaryColor = new JComboBox(colorOptionsArray);
        secondaryColor.setEditable(true);
        // type of graphic combo box
        typeOfGraphic = new JComboBox(graphicOptionsArray);
        // adding logoInput
        JLabel logoLabel = new JLabel("Paste link to team logo :");
        logoLink = new JTextField(20);
        
        // add each panel to the frame

        panel.add(typeOfGraphic);
        panel.add(secondaryColor);
        panel.add(primaryColor);
        panel2.add(label);
        panel2.add(linkSpaceTextField);
        panel.add(scan);
        panel3.add(logoLabel);
        panel3.add(logoLink);        
        scan.addActionListener(new imageDriver());
 
        // Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel3);
        frame.getContentPane().add(BorderLayout.SOUTH, panel2);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // Scanner in = new Scanner(System.in);
        System.out.println(
                "\n\n\tAthletic Statistical Graphic Software. ®\n\n\t\tAlpha "
                        + "version 1.0\n\n\t\t\tAll rights to Justin Newman\n\n\n");
        // redirect standard output stream to the TextAreaOutputStream

        Color c = new Color(255, 255, 255).darker();
        c.darker();
        Color c2 = Color.LIGHT_GRAY.brighter();
        // PRIMARY COLORS
        if (primaryColor.getSelectedItem().equals("JMU Purple")) {
            c = new Color(69, 0, 132);
        }
        if (primaryColor.getSelectedItem().equals("JMU Gold")) {
            c = new Color(203, 182, 119);
        }
        if (primaryColor.getSelectedItem().toString().contains(("Custom"))) {
            String rgb = primaryColor.getSelectedItem().toString().substring(7, primaryColor.getSelectedItem().toString().length() - 1);
            Scanner in = new Scanner(rgb);
            in.useDelimiter(",");
            int r = 255, g = 255, b = 255;
            while(in.hasNext()) {
                r = Integer.parseInt(in.next());
                g = Integer.parseInt(in.next());
                b = Integer.parseInt(in.next());
            }
            c = new Color(r, g, b);
        }
        // SECONDARY COLORS
        if (secondaryColor.getSelectedItem().equals("JMU Purple")) {
            c2 = new Color(69, 0, 132);
        }
        if (secondaryColor.getSelectedItem().equals("JMU Gold")) {
            c2 = new Color(203, 182, 119);
        }
        if (secondaryColor.getSelectedItem().toString().contains(("Custom"))) {
            String rgb = secondaryColor.getSelectedItem().toString().substring(7, secondaryColor.getSelectedItem().toString().length() - 1);
            Scanner in = new Scanner(rgb);
            in.useDelimiter(",");
            int r = 255, g = 255, b = 255;
            while(in.hasNext()) {
                r = Integer.parseInt(in.next());
                g = Integer.parseInt(in.next());
                b = Integer.parseInt(in.next());
            }
            c2 = new Color(r, g, b);
        }
        Team a = null;
        try {
            String url = linkSpaceTextField.getText();
            a = new Team(url , c, c2);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (typeOfGraphic.getSelectedItem().equals("Today Graphic")) {
            try {
                imageWriter.writeToday(a);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (typeOfGraphic.getSelectedItem().equals("Hero Graphic")) {
            try {
                imageWriter.writeHero(a);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (typeOfGraphic.getSelectedItem().equals("DATA-SCAN")) {
            try {
                imageWriter.dataOutput(a);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (typeOfGraphic.getSelectedItem().equals("Bio Graphic")) {
            try {
                imageWriter.writeBio(a);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (typeOfGraphic.getSelectedItem().equals("ESPN")) {
            try {
                imageWriter.writeESPN(a);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        frame.setVisible(false);
        System.exit(0);

    }
    

}
