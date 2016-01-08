package vues;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PageAccueil extends JPanel{
	
	PageAccueil() {
		JLabel logo = new JLabel(new ImageIcon("accueil.jpg"));  
	    add(logo);
	}
	
}
