package vues;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class boiteEditerJeu extends JDialog{
	
	public boiteEditerJeu(){
		
		setSize(500, 500);
		setTitle("Editer le jeu");
		setModal(true);
		add(new JLabel("TODO"));
	}
}
