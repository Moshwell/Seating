package main;
import java.awt.*;
import javax.swing.*;

public class MainWindow {
    public MainWindow(int columns, int rows) {
        JPanel panel = new JPanel(new GridLayout(columns, rows));
        Object[] Personnes = Controller.getAllNomPrenom().toArray();

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                JComboBox<?> NomPrenomComboBox = new JComboBox<Object>(Personnes); 
                panel.add(NomPrenomComboBox);
            }
        }
        final JFrame frame = new JFrame("Placement des utilisateurs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocation(150, 150);
        frame.setVisible(true);
    }
}