package vues;
import javax.swing.*;

import Donnees.BDD;
import Donnees.Utilisateur;
//Fenetre principale de l'application
public class FenetrePrincipale extends JFrame{
	
	private JTabbedPane onglets;//Les differents onglets de la fenetre
	private BDD bdd;
	private Utilisateur utilisateur;
	
	FenetrePrincipale(Utilisateur u, BDD b){
		utilisateur = u;
		bdd = b;
		onglets = new JTabbedPane();
		//Ajout des onglets
		onglets.add("Jeux", new OngletJeux());
		onglets.add("Mon compte", new OngletUtilisateur(utilisateur));
		onglets.add("Ajouter Jeu", new FormulaireJeu());
		if(utilisateur.isAdmin())
			onglets.add("Utilisateurs de Ludotech", new ListeUtilisateurs());
		setSize(1280, 720);
		
		setTitle("Ludotech");
		getContentPane().add(onglets);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
