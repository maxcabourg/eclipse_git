package vues;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
	JLabel nomU = new JLabel("Nom  : "+utilisateur.getNom());
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
	/*//Ajout des labels dans le layout
	layout.add(pseudoU);
	layout.add(nomU);
	layout.add(prenomU);
	layout.add(mailU);
	layout.add(telU);
	layout.add(adresseU);
	layout.add(dateFinAdhesionU);
	layout.add(droitEmprunterU);
	layout.add(joursRetardCumuleU);
	layout.add(nbrRetardsU);
	layout.add(nbrJeuxNonRecupereU);
	//Ajout du layout

	add(layout);
	this.setLayout(new GridLayout(3,2, 5, 5));*/
	
	//Mise en place des JLabels
	setLayout(new GridBagLayout());
    GridBagConstraints gc1 =new GridBagConstraints();
    gc1.gridx = 1;//Case (1,1)
    gc1.gridy = 1;
    gc1.anchor = GridBagConstraints.BASELINE;
    add(nomU,gc1);
    
    GridBagConstraints gc2 =new GridBagConstraints();
    gc2.gridx = 1;
    gc2.gridy = 2;
    gc2.anchor = GridBagConstraints.BASELINE;
    add(prenomU,gc2);
   
    GridBagConstraints gc3 =new GridBagConstraints();
    gc3.gridx = 1;
    gc3.gridy = 3;
    gc3.anchor = GridBagConstraints.CENTER;
    add(pseudoU,gc3);
   
    GridBagConstraints gc4 =new GridBagConstraints();
    gc4.gridx = 1;
    gc4.gridy = 4;
    gc4.anchor = GridBagConstraints.CENTER;
    add(mailU,gc4);
    
    GridBagConstraints gc5 =new GridBagConstraints();
    gc5.anchor = GridBagConstraints.NONE;
    gc5.fill = GridBagConstraints.NONE;
    gc5.gridx = 1;
    gc5.gridy = 5;
    gc5.anchor = GridBagConstraints.CENTER;
    add(telU,gc5);
    
    GridBagConstraints gc6 = new GridBagConstraints();
    gc6.anchor = GridBagConstraints.NONE;
    gc6.fill = GridBagConstraints.NONE;
    gc6.gridx = 1;
    gc6.gridy = 6;
    gc6.anchor = GridBagConstraints.CENTER;
    add(adresseU,gc6);
    
    GridBagConstraints gc7 = new GridBagConstraints();
    gc7.anchor = GridBagConstraints.NONE;
    gc7.fill = GridBagConstraints.NONE;
    gc7.gridx = 1;
    gc7.gridy = 7;
    gc7.anchor = GridBagConstraints.CENTER;
    add(dateFinAdhesionU,gc7);
    
    GridBagConstraints gc8 = new GridBagConstraints();
    gc8.anchor = GridBagConstraints.NONE;
    gc8.fill = GridBagConstraints.NONE;
    gc8.gridx = 1;
    gc8.gridy = 8;
    gc8.anchor = GridBagConstraints.CENTER;
    add(droitEmprunterU,gc8);
    
    GridBagConstraints gc9 = new GridBagConstraints();
    gc9.anchor = GridBagConstraints.NONE;
    gc9.fill = GridBagConstraints.NONE;
    gc9.gridx = 1;
    gc9.gridy = 9;
    gc9.anchor = GridBagConstraints.CENTER;
    add(joursRetardCumuleU,gc9);
    
    GridBagConstraints gc10 = new GridBagConstraints();
    gc10.anchor = GridBagConstraints.NONE;
    gc10.fill = GridBagConstraints.NONE;
    gc10.gridx = 1;
    gc10.gridy = 10;
    gc10.anchor = GridBagConstraints.CENTER;
    add(nbrRetardsU,gc10);
    
    GridBagConstraints gc11 = new GridBagConstraints();
    gc11.anchor = GridBagConstraints.NONE;
    gc11.fill = GridBagConstraints.NONE;
    gc11.gridx = 1;
    gc11.gridy = 11;
    gc11.anchor = GridBagConstraints.CENTER;
    add(nbrJeuxNonRecupereU,gc11);
	}

}

