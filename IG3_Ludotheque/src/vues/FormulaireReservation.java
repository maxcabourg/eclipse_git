package vues;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import Donnees.Extension;
import Donnees.Jeu;
import Donnees.Reservation;
import Donnees.Utilisateur;

public class FormulaireReservation extends JDialog implements ActionListener {
	private Jeu jeu;
	private Utilisateur utilisateur;
	private JLabel nomJeuReserve;
	private JDateChooser dateReservation;
	private ArrayList<Extension> extSelectionnees;

	FormulaireReservation(Jeu j, Utilisateur u) {
			utilisateur = u;
			jeu = j;
			setModal(true);
			setTitle("Reservation : "+jeu.getNom());
			setSize(600, 350);
			
			extSelectionnees = new ArrayList<Extension>();
			
			JPanel panel = new JPanel();	//creation d'un JPanel pour y mettre le contenu		
			Box layout = Box.createVerticalBox();
			Box dateLayout = Box.createHorizontalBox();
			Font titre = new Font("Arial", Font.BOLD, 26);
			JLabel labelTitre = new JLabel("Formulaire de reservation");
			labelTitre.setFont(titre);
			
			Box titrelayout = Box.createHorizontalBox();
			Box bouton1layout = Box.createHorizontalBox();
			Box bouton2layout = Box.createHorizontalBox();
			
	// -------------TEXT FIELD-----------------------------------------------------
			dateReservation = new JDateChooser();
			dateReservation.setPreferredSize(new Dimension(400, 25));
			
			JButton valider = new JButton("Valider");
			valider.addActionListener(this); //On ajoute au bouton un gestionnaire d'evenements
		    valider.setActionCommand("Valider"); //On definit le nom de l'evenement envoye par le bouton
	//-------------LABEL-----------------------------------------------------------
			JLabel lDateReservation = new JLabel(" Date de reservation :  ");
			lDateReservation.setLabelFor(dateReservation);
			
			JButton selectionnerExtension = new JButton("Reserver aussi une extension");
			selectionnerExtension.addActionListener(this);
			selectionnerExtension.setActionCommand("Extensions");
			
			
			JLabel tableauExtensions = new JLabel(" Extensions reservables avec ce jeu :   ");
			tableauExtensions.setLabelFor(tableauExtensions);

			titrelayout.add(labelTitre);
			dateLayout.add(lDateReservation);
			dateLayout.add(dateReservation);
			dateLayout.add(Box.createVerticalGlue());
			bouton1layout.add(selectionnerExtension);
			bouton2layout.add(valider);
			
			layout.add(titrelayout);
			layout.add(Box.createRigidArea(new Dimension(0,100)));
			layout.add(dateLayout);
			layout.add(Box.createRigidArea(new Dimension(0,50)));
			layout.add(bouton1layout);
			layout.add(Box.createRigidArea(new Dimension(0,10)));
			layout.add(bouton2layout);
			layout.add(Box.createRigidArea(new Dimension(0,10)));
			
			panel.add(layout);
			add(panel);
		    

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Valider")){
			if(utilisateur.getDateFinAdhesion().after(new Date()))
				JOptionPane.showMessageDialog(this, "Votre adhesion sera arrivee a terme. Veuillez nous contacter pour la prolonger.", "Erreur", JOptionPane.ERROR_MESSAGE);
			else if(!(Reservation.estUnMardi(dateReservation.getDate()) || Reservation.estUnJeudi(dateReservation.getDate())))
				JOptionPane.showMessageDialog(this, "Vous devez reserver pour un mardi ou jeudi", "Erreur", JOptionPane.ERROR_MESSAGE);			
			else
				JOptionPane.showMessageDialog(this, "Votre reservation a bien ete enregistree", "reservation enregistree", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getActionCommand().equals("Extensions")){
			AffichageExtensions ae = new AffichageExtensions(jeu);
			ae.setVisible(true);
			extSelectionnees = ae.getExtensionsSelectionnees();
			
			
		}

	}

}
