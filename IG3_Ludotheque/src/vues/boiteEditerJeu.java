package vues;
import javax.swing.JDialog;
import javax.swing.JLabel;

import Donnees.Jeu;

public class boiteEditerJeu extends JDialog{
	
	public boiteEditerJeu(Jeu jeu){
		
		setSize(500, 500);
		setTitle("Editer le jeu");
		setModal(true);
		add(new JLabel("Jeu numero : "+Integer.toString(jeu.getId())));
	}
}
