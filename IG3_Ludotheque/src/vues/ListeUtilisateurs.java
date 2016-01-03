package vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.*;

import Donnees.BDD;
import Donnees.Jeu;
import Donnees.ModeleDonneesUser;
import vues.Renderer;

//Liste des utilisateurs de l'application
public class ListeUtilisateurs extends JPanel{

	private BDD base;
	private JTable viewUsers;
	private Box layout;
	private Box box = Box.createVerticalBox();
	
	ListeUtilisateurs(){
		base = new BDD();
		box.setPreferredSize(new Dimension(1200, 450));
		viewUsers = new JTable(new ModeleDonneesUser());
		viewUsers.setMinimumSize(new Dimension(1500, 800));
		viewUsers.setDefaultRenderer(JTable.class, new Renderer());// for the rendering of cell
		
		Action modifierUser = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	System.out.println("modifierUser");
		    }
		};
		
		Action supprimerUser = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
		    	System.out.println("supprimerUser");
			}
		};
		
		ButtonColumn buttonColumn = new ButtonColumn(viewUsers, modifierUser, 14);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		ButtonColumn buttonColumn2 = new ButtonColumn(viewUsers, supprimerUser, 15);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
		box.add(new JScrollPane(viewUsers), BorderLayout.CENTER);
		add(box);
		
	}
}
