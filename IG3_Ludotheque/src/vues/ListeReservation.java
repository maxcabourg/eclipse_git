package vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Donnees.BDD;
import Donnees.Jeu;
import Donnees.ModeleDonneesJeux;
import Donnees.ModeleDonneesResa;
import Donnees.ModeleDonneesUser;
import Donnees.Reservation;
import Donnees.Utilisateur;
import vues.Renderer;

//Liste des utilisateurs de l'application
public class ListeReservation extends JPanel implements ActionListener{

	private BDD base;
	private JTable viewResas;
	private JButton actualiser;
	private Box layout;
	private Box box = Box.createVerticalBox();
	TableRowSorter<TableModel> trieur;
	
	ListeReservation(){
		base = new BDD();
		box.setPreferredSize(new Dimension(1200, 450));
		viewResas = new JTable(new ModeleDonneesResa());
		viewResas.setMinimumSize(new Dimension(1500, 800));
		viewResas.setDefaultRenderer(JTable.class, new Renderer());// for the rendering of cell
		viewResas.getColumnModel().getColumn(0).setMinWidth(0); //On cache la colonne des id
		viewResas.getColumnModel().getColumn(0).setMaxWidth(0);
		viewResas.getColumnModel().getColumn(0).setWidth(0);
		trieur = new TableRowSorter<TableModel>((TableModel) viewResas.getModel());
		viewResas.setRowSorter(trieur);
		actualiser = new JButton("actualiser");
		actualiser.addActionListener(this);
		
		Action modifierResa = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	JTable table = (JTable)e.getSource();
		    	int ligne = Integer.valueOf( e.getActionCommand() ); //recupere le numero de la ligne sachant qu'elle commence a 0
		    	try {
		    		Reservation r = Reservation.getById(base, ligne+1); //Recuperer le jeu correspondant au num de la ligne +1
					r.showEdit(); //Affiche les differentes infos
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		};
		
		Action supprimerResa = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				JTable table = (JTable)e.getSource();
		    	int ligne = Integer.valueOf( e.getActionCommand() ); //recupere le numero de la ligne sachant qu'elle commence a 0
		    	try {
		    		Reservation r = Reservation.getById(base, ligne+1); //Recuperer le jeu correspondant au num de la ligne +1
					r.delete(base); //Supprime le jeu de la base
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	
			}
		};
		
		//CLASSE INTERNE PERMETTANT D'EFFECTUER DES RECHERCHES DANS LA JTABLE
				class FilterAction extends AbstractAction {
					private FilterAction() {
				        super("Rechercher un utilisateur"); //Appel du constructeur de la classe parent
				    }
				 
				    public void actionPerformed(ActionEvent e) {
				        String regex = JOptionPane.showInputDialog("Quel utilisateur souhaitez-vous rechercher (Pseudo) ? ");
				 
				        trieur.setRowFilter(RowFilter.regexFilter(regex, 0, 3)); //S'occupe de chercher les utilisateurs selon le pseudo correspondant a l'expression rentre par l'utilisateur
				    }
				}
		
		ButtonColumn buttonColumn = new ButtonColumn(viewResas, modifierResa, 7);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		ButtonColumn buttonColumn2 = new ButtonColumn(viewResas, supprimerResa, 8);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
		box.add(new JScrollPane(viewResas), BorderLayout.CENTER);
		//box.add(new JButton(new FilterAction())); //Cr�e un bouton de recherche qui va effectuer l'action decrite dans la classe FilterAction
		box.add(actualiser);
		add(box);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("actualiser")){
			//TODO
			viewResas.setModel(new ModeleDonneesResa()); //On recharge les donnees
			viewResas.setDefaultRenderer(JTable.class, new Renderer());// for the rendering of cell
			viewResas.getColumnModel().getColumn(0).setMinWidth(0); //On cache la colonne des id
			viewResas.getColumnModel().getColumn(0).setMaxWidth(0);
			viewResas.getColumnModel().getColumn(0).setWidth(0);
			trieur = new TableRowSorter<TableModel>((TableModel) viewResas.getModel());//On recharge le trieur  
			viewResas.setRowSorter(trieur);
			Action modifierResa = new AbstractAction()
			{
			    public void actionPerformed(ActionEvent e)
			    {
			    	JTable table = (JTable)e.getSource();
			    	int ligne = Integer.valueOf( e.getActionCommand() ); //recupere le numero de la ligne sachant qu'elle commence a 0
			    	try {
			    		Reservation r = Reservation.getById(base, ligne+1); //Recuperer le jeu correspondant au num de la ligne +1
						r.showEdit(); //Affiche les differentes infos
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			};
			
			Action supprimerResa = new AbstractAction()
			{
				public void actionPerformed(ActionEvent e)
				{
					JTable table = (JTable)e.getSource();
			    	int ligne = Integer.valueOf( e.getActionCommand() ); //recupere le numero de la ligne sachant qu'elle commence a 0
			    	try {
			    		Reservation r = Reservation.getById(base, ligne+1); //Recuperer le jeu correspondant au num de la ligne +1
						r.delete(base); //Supprime le jeu de la base
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	
				}
			};
			
			ButtonColumn buttonColumn = new ButtonColumn(viewResas, modifierResa, 7);
			buttonColumn.setMnemonic(KeyEvent.VK_D);
			ButtonColumn buttonColumn2 = new ButtonColumn(viewResas, supprimerResa, 8);
			buttonColumn.setMnemonic(KeyEvent.VK_D);
		}
		
	}
}