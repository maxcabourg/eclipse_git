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
import Donnees.Utilisateur;
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
		    	JTable table = (JTable)e.getSource();
		    	int ligne = Integer.valueOf( e.getActionCommand() ); //recupere le numero de la ligne sachant qu'elle commence a 0
		    	try {
		    		Utilisateur u = Utilisateur.getById(base, ligne+1); //Recuperer le jeu correspondant au num de la ligne +1
					u.showEdit(); //Affiche les differentes infos
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
