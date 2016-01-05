package vues;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
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
	
	JLabel titre = new JLabel("Informations Utilisateur");
	Font f = new Font(null, Font.PLAIN, 25); 
	titre.setFont(f); 

	
	JLabel nomU = new JLabel("Nom  :   ");
	nomU.setFont(nomU.getFont().deriveFont(Font.BOLD)); //Met en gras ce JLabel
	JLabel nomUs = new JLabel(utilisateur.getNom());
	
	JLabel prenomU = new JLabel("Prenom :   ");
	prenomU.setFont(prenomU.getFont().deriveFont(Font.BOLD));
	JLabel prenomUs = new JLabel(utilisateur.getPrenom());
	
	JLabel pseudoU = new JLabel("Pseudo :   ");
	pseudoU.setFont(pseudoU.getFont().deriveFont(Font.BOLD));
	JLabel pseudoUs = new JLabel(utilisateur.getPseudo());
	
	JLabel mailU = new JLabel("Mail :   ");
	mailU.setFont(mailU.getFont().deriveFont(Font.BOLD));
	JLabel mailUs = new JLabel(utilisateur.getMail());
	
	JLabel telU = new JLabel("Telephone :   ");
	telU.setFont(telU.getFont().deriveFont(Font.BOLD));
	JLabel telUs = new JLabel(utilisateur.getTel());
	
	JLabel adresseU = new JLabel("Adresse :   ");
	adresseU.setFont(adresseU.getFont().deriveFont(Font.BOLD));
	JLabel adresseUs = new JLabel(utilisateur.getAdresse());
	
	@SuppressWarnings("deprecation") //Suppression d'un warning inutile
	JLabel dateFinAdhesionU = new JLabel("Date de fin d'adhesion :   ");
	dateFinAdhesionU.setFont(dateFinAdhesionU.getFont().deriveFont(Font.BOLD));
	JLabel dateFinAdhesionUs = new JLabel(utilisateur.getDateFinAdhesion().toLocaleString());
	
	JLabel droitEmprunterU = new JLabel("Droit d'emprunter :   ");
	droitEmprunterU.setFont(droitEmprunterU.getFont().deriveFont(Font.BOLD));
	JLabel droitEmprunterUs = new JLabel();
	if(utilisateur.DroitEmprunter()) //Permet d'afficher oui ou non au lieu de 1 ou 0
		droitEmprunterUs.setText("oui");
	else
		droitEmprunterUs.setText("non");
	
	JLabel joursRetardCumuleU = new JLabel("Jours de retard cumules :   ");
	joursRetardCumuleU.setFont(joursRetardCumuleU.getFont().deriveFont(Font.BOLD));
	JLabel joursRetardCumuleUs = new JLabel(String.valueOf(utilisateur.getJoursRetardCumule()));
	
	JLabel nbrRetardsU = new JLabel("Nombre de retards :   ");
	nbrRetardsU.setFont(nbrRetardsU.getFont().deriveFont(Font.BOLD));
	JLabel nbrRetardsUs = new JLabel(String.valueOf(utilisateur.getNbrRetards()));
	
	JLabel nbrJeuxNonRecupereU = new JLabel("Nombre de jeux non recuperes :   ");
	nbrJeuxNonRecupereU.setFont(nbrJeuxNonRecupereU.getFont().deriveFont(Font.BOLD));
	JLabel nbrJeuxNonRecupereUs = new JLabel(String.valueOf(utilisateur.getNbrJeuxNonRecuperes()));
	
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
	GridBagConstraints gc0 =new GridBagConstraints();
    gc0.gridx = 0;//Case (1,0)
    gc0.gridy = 0;
    gc0.anchor = GridBagConstraints.CENTER;
    gc0.gridwidth = 3;
    add(titre,gc0);
	
	GridBagConstraints gc1 =new GridBagConstraints();
    gc1.gridx = 1;//Case (1,1)
    gc1.gridy = 1;
    gc1.anchor = GridBagConstraints.EAST;
    add(nomU,gc1);
    gc1.gridx = 2;//Case(2,1)
    gc1.gridy = 1;
    gc1.gridwidth = GridBagConstraints.REMAINDER; 
    gc1.anchor = GridBagConstraints.WEST;
    add(nomUs,gc1);
    
    GridBagConstraints gc2 =new GridBagConstraints();
    gc2.gridx = 1;
    gc2.gridy = 2;
    gc2.anchor = GridBagConstraints.EAST;
    add(prenomU,gc2);
    gc2.gridx = 2;
    gc2.gridy = 2;
    gc2.gridwidth = GridBagConstraints.REMAINDER; 
    gc2.anchor = GridBagConstraints.WEST;
    add(prenomUs,gc2);
   
    GridBagConstraints gc3 =new GridBagConstraints();
    gc3.gridx = 1;
    gc3.gridy = 3;
    gc3.anchor = GridBagConstraints.EAST;
    add(pseudoU,gc3);
    gc3.gridx = 2;
    gc3.gridy = 3;
    gc3.gridwidth = GridBagConstraints.REMAINDER;
    gc3.anchor = GridBagConstraints.WEST;
    add(pseudoUs,gc3);
   
    GridBagConstraints gc4 =new GridBagConstraints();
    gc4.gridx = 1;
    gc4.gridy = 4;
    gc4.anchor = GridBagConstraints.EAST;
    add(mailU,gc4);
    gc4.gridx = 2;
    gc4.gridy = 4;
    gc4.gridwidth = GridBagConstraints.REMAINDER;
    gc4.anchor = GridBagConstraints.WEST;
    add(mailUs,gc4);
    
    GridBagConstraints gc5 =new GridBagConstraints();
    gc5.anchor = GridBagConstraints.NONE;
    gc5.fill = GridBagConstraints.NONE;
    gc5.gridx = 1;
    gc5.gridy = 5;
    gc5.anchor = GridBagConstraints.EAST;
    add(telU,gc5);
    gc5.gridx = 2;
    gc5.gridy = 5;
    gc5.gridwidth = GridBagConstraints.REMAINDER;
    gc5.anchor = GridBagConstraints.WEST;
    add(telUs,gc5);
    
    GridBagConstraints gc6 = new GridBagConstraints();
    gc6.anchor = GridBagConstraints.NONE;
    gc6.fill = GridBagConstraints.NONE;
    gc6.gridx = 1;
    gc6.gridy = 6;
    gc6.anchor = GridBagConstraints.EAST;
    add(adresseU,gc6);
    gc6.gridx = 2;
    gc6.gridy = 6;
    gc6.gridwidth = GridBagConstraints.REMAINDER;
    gc6.anchor = GridBagConstraints.WEST;
    add(adresseUs,gc6);
    
    GridBagConstraints gc7 = new GridBagConstraints();
    gc7.anchor = GridBagConstraints.NONE;
    gc7.fill = GridBagConstraints.NONE;
    gc7.gridx = 1;
    gc7.gridy = 7;
    gc7.anchor = GridBagConstraints.EAST;
    add(dateFinAdhesionU,gc7);
    gc7.gridx = 2;
    gc7.gridy = 7;
    gc7.gridwidth = GridBagConstraints.REMAINDER;
    gc7.anchor = GridBagConstraints.WEST;
    add(dateFinAdhesionUs,gc7);
    
    
    GridBagConstraints gc8 = new GridBagConstraints();
    gc8.anchor = GridBagConstraints.NONE;
    gc8.fill = GridBagConstraints.NONE;
    gc8.gridx = 1;
    gc8.gridy = 8;
    gc8.anchor = GridBagConstraints.EAST;
    add(droitEmprunterU,gc8);
    gc8.gridx = 2;
    gc8.gridy = 8;
    gc8.gridwidth = GridBagConstraints.REMAINDER;
    gc8.anchor = GridBagConstraints.WEST;
    add(droitEmprunterUs,gc8);
    
    
    GridBagConstraints gc9 = new GridBagConstraints();
    gc9.anchor = GridBagConstraints.NONE;
    gc9.fill = GridBagConstraints.NONE;
    gc9.gridx = 1;
    gc9.gridy = 9;
    gc9.anchor = GridBagConstraints.EAST;
    add(joursRetardCumuleU,gc9);
    gc9.gridx = 2;
    gc9.gridy = 9;
    gc9.gridwidth = GridBagConstraints.REMAINDER;
    gc9.anchor = GridBagConstraints.WEST;
    add(joursRetardCumuleUs,gc9);
    
    GridBagConstraints gc10 = new GridBagConstraints();
    gc10.anchor = GridBagConstraints.NONE;
    gc10.fill = GridBagConstraints.NONE;
    gc10.gridx = 1;
    gc10.gridy = 10;
    gc10.anchor = GridBagConstraints.EAST;
    add(nbrRetardsU,gc10);
    gc10.gridx = 2;
    gc10.gridy = 10;
    gc10.gridwidth = GridBagConstraints.REMAINDER;
    gc10.anchor = GridBagConstraints.WEST;
    add(nbrRetardsUs,gc10);
    
    GridBagConstraints gc11 = new GridBagConstraints();
    gc11.anchor = GridBagConstraints.NONE;
    gc11.fill = GridBagConstraints.NONE;
    gc11.gridx = 1;
    gc11.gridy = 11;
    gc11.anchor = GridBagConstraints.EAST;
    add(nbrJeuxNonRecupereU,gc11);
    gc11.gridx = 2;
    gc11.gridy = 11;
    gc11.gridwidth = GridBagConstraints.REMAINDER;
    gc11.anchor = GridBagConstraints.WEST;
    add(nbrJeuxNonRecupereUs,gc11);
    
    // Ajout Bouton pour modifier Mdp
	JButton modifMdp = new JButton("Modifier mot de passe");
	add(modifMdp);
       
	}
	


}

