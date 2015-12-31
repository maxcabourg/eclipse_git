package vues;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.*;

import Donnees.Utilisateur;
//Onglet montrant les information sur l'utilisateur
public class OngletUtilisateur extends JPanel{
	
	private Utilisateur utilisateur;
	private Box layout;
	public OngletUtilisateur(Utilisateur u){
		utilisateur = u;	
		//Creation des labels
	layout = Box.createVerticalBox();
	JLabel nomU = new JLabel("Nom  :"+utilisateur.getNom());
	JLabel prenomU = new JLabel("Prenom : "+utilisateur.getPrenom());
	JLabel pseudoU = new JLabel("Pseudo : "+utilisateur.getPseudo());
	JLabel mailU = new JLabel("Mail : "+utilisateur.getMail());
	JLabel telU = new JLabel("Telephone : "+utilisateur.getTel());
	JLabel adresseU = new JLabel("Adresse : "+utilisateur.getAdresse());
	@SuppressWarnings("deprecation") //Suppression d'un warning inutile
	JLabel dateFinAdhesionU = new JLabel("Date de fin d'adhesion : "+utilisateur.getDateFinAdhesion().toLocaleString());
	JLabel droitEmprunterU = new JLabel();
	if(utilisateur.DroitEmprunter())
		droitEmprunterU.setText("Droit d'emprunter : oui");
	else
		droitEmprunterU.setText("Droit d'emprunter : non");
	JLabel joursRetardCumuleU = new JLabel("Jours de retard cumules : "+utilisateur.getJoursRetardCumule());
	JLabel nbrRetardsU = new JLabel("Nombre de retards : "+utilisateur.getNbrRetards());
	JLabel nbrJeuxNonRecupereU = new JLabel("Nombre de jeux non recuperes : "+utilisateur.getNbrJeuxNonRecuperes());
	//Ajout des labels dans le layout
	layout.add(pseudoU);
	layout.add(nomU);
	layout.add(prenomU);
	layout.add(mailU);
	layout.add(telU);
	layout.add(adresseU);
	layout.add(dateFinAdhesionU);
	layout.	add(droitEmprunterU);
	layout.add(joursRetardCumuleU);
	layout.add(nbrRetardsU);
	layout.add(nbrJeuxNonRecupereU);
	//Ajout du layout
	add(layout);
	}

}

