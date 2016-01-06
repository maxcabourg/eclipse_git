package vues;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PageAccueil extends JPanel{
	
	PageAccueil() {
		JLabel texte = new JLabel("Ajouter un utilisateur");
		Box mainLayout = Box.createVerticalBox();
		mainLayout.add(texte);
	    add(mainLayout);
	}
	
}
