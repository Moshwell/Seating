package seatingplanswing;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import seatingplanswing.Controller;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

@SpringBootApplication
public class SwingApp extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SwingApp() {

        initUI();
    }

    private void initUI() {
    	
		JPanel panel = new JPanel(new GridBagLayout());
		JComboBox<?> sallesComboBox = new JComboBox<Object>(Controller.getAllSallesNames().toArray()); 
		JOptionPane.showMessageDialog(null, sallesComboBox, "Choix de la salle à afficher", JOptionPane.QUESTION_MESSAGE);
		panel.add(sallesComboBox);
		int cols = Controller.getColsFromSalle(sallesComboBox.getSelectedItem().toString());
		int rows = Controller.getRowsFromSalle(sallesComboBox.getSelectedItem().toString());
		createLayout(cols, rows);
		
    }

    private void createLayout(int columns, int rows) {
    	
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

    public static void main(String[] args) {

        var ctx = new SpringApplicationBuilder(SwingApp.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {

            var ex = ctx.getBean(SwingApp.class);
            ex.setVisible(true);
        });
    }
}