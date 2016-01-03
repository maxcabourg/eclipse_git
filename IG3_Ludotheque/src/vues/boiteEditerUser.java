package vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Donnees.Utilisateur;

public class boiteEditerUser extends JDialog implements ActionListener{

	private Utilisateur utilisateur;
	
	public boiteEditerUser(Utilisateur u){
		utilisateur = u;
		add(new JLabel("TODO"));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
