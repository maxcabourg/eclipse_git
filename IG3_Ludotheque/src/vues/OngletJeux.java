package vues;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Donnees.BDD;
import Donnees.Jeu;
import Donnees.ModeleDonneesJeux;
import Donnees.Utilisateur;

//Onglet qui prÃ©sentera les differents jeux
public class OngletJeux extends JPanel implements ActionListener{

	private Utilisateur utilisateur;
	private BDD base;
	private JButton actualiser;
	private JTable viewJeux;
	TableRowSorter<TableModel> trieur;
	private Box layoutBouton = Box.createHorizontalBox();
	
	private Box box = Box.createVerticalBox();
	OngletJeux(Utilisateur u){
		utilisateur = u;
		base = new BDD();
		box.setPreferredSize(new Dimension(800, 450));
		actualiser = new JButton("Actualiser");
		actualiser.addActionListener(this);
		actualiser.setActionCommand("actualiser");
		viewJeux = new JTable(new ModeleDonneesJeux());
		trieur = new TableRowSorter<TableModel>((TableModel) viewJeux.getModel());   
		viewJeux.setRowSorter(trieur);
		viewJeux.setMinimumSize(new Dimension(1500, 800));
		viewJeux.setDefaultRenderer(JTable.class, new Renderer());// for the rendering of cell
		
		//viewJeux.getColumn("Editer").setCellRenderer(new Renderer());
		viewJeux.getColumnModel().getColumn(0).setPreferredWidth(20);
		viewJeux.getColumnModel().getColumn(1).setPreferredWidth(175);
		viewJeux.getColumnModel().getColumn(3).setPreferredWidth(50);
		viewJeux.getColumnModel().getColumn(4).setPreferredWidth(50);
		viewJeux.getColumnModel().getColumn(5).setPreferredWidth(250);
		viewJeux.getColumnModel().getColumn(6).setPreferredWidth(50);
		if(!utilisateur.isAdmin()){ //Cache les colonnes editer et supprimer quand on est pas admin
				viewJeux.getColumnModel().getColumn(9).setMinWidth(0);
				viewJeux.getColumnModel().getColumn(9).setMaxWidth(0);
				viewJeux.getColumnModel().getColumn(9).setWidth(0);
				viewJeux.getColumnModel().getColumn(10).setMinWidth(0);
				viewJeux.getColumnModel().getColumn(10).setMaxWidth(0);
				viewJeux.getColumnModel().getColumn(10).setWidth(0);
		}
		
		
		Action modifierJeu = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	JTable table = (JTable)e.getSource();
		    	int ligne = Integer.valueOf( e.getActionCommand() ); //recupere le numero de la ligne sachant qu'elle commence a 0
		    	try {
		    		Jeu j = Jeu.getById(base, ligne+1); //Recuperer le jeu correspondant au num de la ligne +1
					j.showEdit(); //Affiche les differentes infos
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		};
		
		Action supprimerJeu = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				JTable table = (JTable)e.getSource();
		    	int ligne = Integer.valueOf( e.getActionCommand() ); //recupere le numero de la ligne sachant qu'elle commence a 0
		    	try {
		    		Jeu j = Jeu.getById(base, ligne+1); //Recuperer le jeu correspondant au num de la ligne +1
					j.delete(base); //Supprime le jeu de la base
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	
			}
		};
		
		
		//CLASSE INTERNE PERMETTANT D'EFFECTUER DES RECHERCHES DANS LA JTABLE
		class FilterAction extends AbstractAction {
			private FilterAction() {
		        super("Rechercher un jeu"); //Appel du constructeur de la classe parent
		    }
		 
		    public void actionPerformed(ActionEvent e) {
		        String regex = JOptionPane.showInputDialog("Quel jeu souhaitez-vous rechercher ? ");
		 
		        trieur.setRowFilter(RowFilter.regexFilter(regex, 0, 1)); //S'occupe de chercher les jeux correspondant a l'expression rentrée par l'utilisateur
		    }
		}
		
		if(utilisateur.isAdmin())
		{
			ButtonColumn buttonColumn = new ButtonColumn(viewJeux, modifierJeu, 9);
			buttonColumn.setMnemonic(KeyEvent.VK_D);
			ButtonColumn buttonColumn2 = new ButtonColumn(viewJeux, supprimerJeu, 10);
			buttonColumn.setMnemonic(KeyEvent.VK_D);
		}	
		box.add(new JScrollPane(viewJeux), BorderLayout.CENTER);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(layoutBouton);
		box.add(actualiser);
		box.add(new JButton(new FilterAction())); //Crée un bouton de recherche qui va effectuer l'action decrite dans la classe FilterAction
		add(box);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("actualiser")){
			//TODO
			viewJeux.setModel(new ModeleDonneesJeux()); //On recharge les donnees
			if(!utilisateur.isAdmin()){ //Cache les colonnes editer et supprimer quand on est pas admin
				viewJeux.getColumnModel().getColumn(9).setMinWidth(0);
				viewJeux.getColumnModel().getColumn(9).setMaxWidth(0);
				viewJeux.getColumnModel().getColumn(9).setWidth(0);
				viewJeux.getColumnModel().getColumn(10).setMinWidth(0);
				viewJeux.getColumnModel().getColumn(10).setMaxWidth(0);
				viewJeux.getColumnModel().getColumn(10).setWidth(0);
		}
			trieur = new TableRowSorter<TableModel>((TableModel) viewJeux.getModel());//On recharge le trieur  
			viewJeux.setRowSorter(trieur);
			Action modifierJeu = new AbstractAction() //On recree le bouton editer
			{
			    public void actionPerformed(ActionEvent e)
			    {
			    	JTable table = (JTable)e.getSource();
			    	int ligne = Integer.valueOf( e.getActionCommand() ); //recupere le numero de la ligne sachant qu'elle commence a 0
			    	try {
			    		Jeu j = Jeu.getById(base, ligne+1); //Recuperer le jeu correspondant au num de la ligne +1
						j.showEdit(); //Affiche les differentes infos
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			};
			
			Action supprimerJeu = new AbstractAction() //On recree le bouton supprimer
			{
				public void actionPerformed(ActionEvent e)
				{
					JTable table = (JTable)e.getSource();
			    	int ligne = Integer.valueOf( e.getActionCommand() ); //recupere le numero de la ligne sachant qu'elle commence a 0
			    	try {
			    		Jeu j = Jeu.getById(base, ligne+1); //Recuperer le jeu correspondant au num de la ligne +1
						j.delete(base); //Supprime le jeu de la base
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	
				}
			};
			
			if(utilisateur.isAdmin())
			{
				ButtonColumn buttonColumn = new ButtonColumn(viewJeux, modifierJeu, 9);
				buttonColumn.setMnemonic(KeyEvent.VK_D);
				ButtonColumn buttonColumn2 = new ButtonColumn(viewJeux, supprimerJeu, 10);
				buttonColumn.setMnemonic(KeyEvent.VK_D);
			}
		}
		
	}
}