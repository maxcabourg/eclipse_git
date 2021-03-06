package vues;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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

import Donnees.BDD;
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
			
			JButton reserver = new JButton("Reserver");
			reserver.addActionListener(this); //On ajoute au bouton un gestionnaire d'evenements
			reserver.setActionCommand("Reserver"); //On definit le nom de l'evenement envoye par le bouton
			
			JButton emprunter = new JButton("Emprunter");
			emprunter.addActionListener(this); //On ajoute au bouton un gestionnaire d'evenements
			emprunter.setActionCommand("Emprunter"); //On definit le nom de l'evenement envoye par le bouton
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
			bouton2layout.add(reserver);
			bouton2layout.add(Box.createRigidArea(new Dimension(20, 0)));
			bouton2layout.add(emprunter);
			
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
		if(e.getActionCommand().equals("Reserver")){
			if(dateReservation.getDate().after(utilisateur.getDateFinAdhesion()))
				JOptionPane.showMessageDialog(this, "Votre adhesion sera arrivee a terme. Veuillez nous contacter pour la prolonger.", "Erreur", JOptionPane.ERROR_MESSAGE);
			else if(!(Reservation.estUnMardi(dateReservation.getDate()) || Reservation.estUnJeudi(dateReservation.getDate())))
				JOptionPane.showMessageDialog(this, "Vous devez reserver pour un mardi ou jeudi", "Erreur", JOptionPane.ERROR_MESSAGE);			
			else if(!utilisateur.DroitEmprunter()){
				JOptionPane.showMessageDialog(this, "Vous n'avez pas le droit d'emprunter.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else{
				ArrayList<Integer> idExtensions = new ArrayList<Integer>();
				for(int i = 0; i<extSelectionnees.size(); i++){
					idExtensions.add(extSelectionnees.get(i).getId());
				}
				Date dateResa = dateReservation.getDate();
				Calendar c = Calendar.getInstance();
				c.setTime(dateResa); // Now use today date.
				c.add(Calendar.DATE, 21); // Adding 21 days
				Reservation res = new Reservation(0, utilisateur.getId(), jeu.getId(), Reservation.ListToString(idExtensions), dateResa, c.getTime(),  0);
				try {
					res.ajouterReservation(new BDD());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, "Votre reservation a bien ete enregistree", "reservation enregistree", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		}
		else if(e.getActionCommand().equals("Emprunter")){
			Date dateDuJour = new Date();
			if(dateDuJour.after(utilisateur.getDateFinAdhesion()))
				JOptionPane.showMessageDialog(this, "Votre adhesion est arrivee a terme. Veuillez nous contacter pour la prolonger.", "Erreur", JOptionPane.ERROR_MESSAGE);
			else if(!(Reservation.estUnMardi(dateDuJour) || Reservation.estUnJeudi(dateDuJour))){
				JOptionPane.showMessageDialog(this, "Vous ne pouvez que emprunter un mardi ou jeudi", "Erreur", JOptionPane.ERROR_MESSAGE);	
			}
			else if(!utilisateur.DroitEmprunter()){
				JOptionPane.showMessageDialog(this, "Vous n'avez pas le droit d'emprunter.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else{
				ArrayList<Integer> idExtensions = new ArrayList<Integer>(); //Recuperation des extensions selectionnees
				for(int i = 0; i<extSelectionnees.size(); i++){
					idExtensions.add(extSelectionnees.get(i).getId());
				}
				Calendar c = Calendar.getInstance();
				c.setTime(dateDuJour);
				c.add(Calendar.DATE, 21); //emprunt = le jour meme
				Reservation res = new Reservation(0, utilisateur.getId(), jeu.getId(), Reservation.ListToString(idExtensions), dateDuJour, c.getTime(),  0);
				try {
					res.ajouterReservation(new BDD());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, "Votre emprunt a bien ete enregistre", "Emprunt enregistre", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		}		
		else if(e.getActionCommand().equals("Extensions")){
			AffichageExtensions ae = new AffichageExtensions(jeu);
			ae.setVisible(true);
			extSelectionnees = ae.getExtensionsSelectionnees();	
			
		}

	}

}
