package vues;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

public class FormulaireReservation extends JPanel implements ActionListener {
	private JLabel nomJeuReserve;
	private JDateChooser dateReservation;
	private JComboBox tableauExtensions;

	FormulaireReservation() {
		try {
	// -------------MASK FORMATTER-----------------------------------------------------
			MaskFormatter date = new MaskFormatter("##/##/####");
			Font titre = new Font("Arial", Font.BOLD, 26);
			
	// -------------TEXT FIELD-----------------------------------------------------
			dateReservation = new JDateChooser();
			dateReservation.setPreferredSize(new Dimension(150, 30));
			
			tableauExtensions = new JComboBox<String>();
			tableauExtensions.setPreferredSize(new Dimension(150, 30));
			
			JButton valider = new JButton("Valider");
			valider.addActionListener((ActionListener) this); //On ajoute au bouton un gestionnaire d'evenements
		    valider.setActionCommand("Valider"); //On definit le nom de l'evenement envoye par le bouton
			
	//-------------LABEL-----------------------------------------------------------
			JLabel lDateReservation = new JLabel(" Date de réservation :   ");
			lDateReservation.setLabelFor(dateReservation);
			
			JLabel lTableauExtensions = new JLabel(" Extensions réservables avec ce jeu :   ");
			lTableauExtensions.setLabelFor(tableauExtensions);
			
			JLabel tableauExtensions = new JLabel(" Extensions réservables avec ce jeu :   ");
			tableauExtensions.setLabelFor(tableauExtensions);

			nomJeuReserve = new JLabel();
			nomJeuReserve.setPreferredSize(new Dimension(150, 30));
		    
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	void chargement(int idJeu){
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
