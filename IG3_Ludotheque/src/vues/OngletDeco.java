package vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class OngletDeco extends JPanel implements ActionListener
{	
	private JFrame fenetre;
	
		OngletDeco(JFrame a)
		{
			JButton ajouter = new JButton("Deconnexion");
			ajouter.addActionListener((ActionListener) this);
		    ajouter.setActionCommand("Deconnexion"); 
		    
		    fenetre = a;
		    
		    Box mainLayout = Box.createVerticalBox();
		    mainLayout.add(ajouter);
		    add(mainLayout);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Deconnexion")){
				JOptionPane.showMessageDialog(this, "A bientot !");
				fenetre.setVisible(false);
				EcranConnexion accueil = new EcranConnexion();
				
			}
			
		}
}