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
		onglets.add("Accueil", new PageAccueil());
		onglets.add("Jeux", new OngletJeux(utilisateur));
		onglets.add("Mon compte", new OngletUtilisateur(utilisateur));
		if(utilisateur.isAdmin()){
			onglets.add("Ajouter Jeu", new FormulaireJeu());
			onglets.add("Ajouter Utilsateur", new FormulaireUtilisateur());
			onglets.add("Utilisateurs de Ludotech", new ListeUtilisateurs());
			onglets.add("Liste reservations en cours", new ListeReservation());
		}
		onglets.add("Déconnexion", new OngletDeco(this));
		setSize(1280, 720);
		
		setTitle("Ludotech");
		getContentPane().add(onglets);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
