package main;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JPanel panel = new JPanel(new GridBagLayout());
                JComboBox<?> sallesComboBox = new JComboBox<Object>(Controller.getAllSallesNames().toArray()); 
                JOptionPane.showMessageDialog(null, sallesComboBox, "Choix de la salle à afficher", JOptionPane.QUESTION_MESSAGE);
                panel.add(sallesComboBox);
				int cols = Controller.getColsFromSalle(sallesComboBox.getSelectedItem().toString());
				int rows = Controller.getRowsFromSalle(sallesComboBox.getSelectedItem().toString());
				new MainWindow(cols, rows);
            }
        });
    }
}
