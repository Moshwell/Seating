package main;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class MainWindow {
	
    final JFrame frame = new JFrame("Placement des utilisateurs");
	
    public MainWindow(int columns, int rows) 
    {
    	JButton addCollaborator = new JButton();
    	JButton deleteCollaborator = new JButton();
    	JTextField addNom = new JTextField();
    	JTextField addPrenom = new JTextField();
    	JPanel panelOptions = new JPanel();
        Object[] Personnes = Controller.getAllNomPrenom().toArray();
        JComboBox<?> NomPrenom = new JComboBox<Object>(Personnes);
        NomPrenom.removeItemAt(0);
    	panelOptions.setBounds(new Rectangle(100, 70, 100, 50));
    	
    	addNom.setBounds(10, 500, 200, 25);
    	addNom.setText("Nom du collaborateur à ajouter");
    	addPrenom.setBounds(10,  535, 200, 25);
    	addPrenom.setText("Prénom du collaborateur à ajouter");
    	addCollaborator.setBounds(10, 570, 200, 25);
    	addCollaborator.setText("Ajouter un collaborateur");
    	deleteCollaborator.setBounds(400, 570, 200, 25);
    	deleteCollaborator.setText("Supprimer un collaborateur");
    	NomPrenom.setBounds(400, 500, 200, 25);

    	
        JPanel panelDesktop = new JPanel(new GridLayout(rows, columns));


        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                JComboBox<?> NomPrenomComboBox = new JComboBox<Object>(Personnes); 
                panelDesktop.add(NomPrenomComboBox);
            }
        }
        
        panelDesktop.setBounds(new Rectangle(10, 10, 1200, 400));
        panelDesktop.revalidate();

        panelOptions.setLayout(null);
        panelOptions.add(addNom, null);
        panelOptions.add(addPrenom, null);
        panelOptions.add(addCollaborator, null);
        panelOptions.add(deleteCollaborator, null);
        panelOptions.add(NomPrenom, null);

        frame.setResizable(false);
        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.add(panelDesktop);
        frame.add(panelOptions);
        frame.setLocationRelativeTo(null);
        frame.setLocation(20, 100);
        frame.setVisible(true);
        
        WindowListener exitListener = new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent e) 
            {
                close();
            }
        };
        frame.addWindowListener(exitListener);
    }
    
    private void close()
    {
    	frame.dispose();
    	Main.main(null);
    }
}