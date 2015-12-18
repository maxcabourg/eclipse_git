package vues;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import Donnees.BDD;
import Donnees.Jeu;
import Donnees.ModeleDonneesJeux;

//Onglet qui présentera les differents jeux
public class OngletJeux extends JPanel {

	private BDD base;
	private JTextField champRecherche;
	private JButton rechercher;
	private Box layoutBouton = Box.createHorizontalBox();
	
	private Box box = Box.createVerticalBox();
	OngletJeux(){
		base = new BDD();
		box.setPreferredSize(new Dimension(800, 450));
		champRecherche = new JTextField();
		rechercher = new JButton("Rechercher");
		layoutBouton.add(champRecherche);
		layoutBouton.add(rechercher);
		JTable viewJeux = new JTable(new ModeleDonneesJeux());
		viewJeux.setMinimumSize(new Dimension(1000, 800));
		viewJeux.setDefaultRenderer(JTable.class, new Renderer());// for the rendering of cell
		
		//viewJeux.getColumn("Editer").setCellRenderer(new Renderer());
		viewJeux.getColumnModel().getColumn(0).setPreferredWidth(250);
		viewJeux.getColumnModel().getColumn(1).setPreferredWidth(175);
		viewJeux.getColumnModel().getColumn(3).setPreferredWidth(50);
		viewJeux.getColumnModel().getColumn(4).setPreferredWidth(50);
		viewJeux.getColumnModel().getColumn(5).setPreferredWidth(250);
		viewJeux.getColumnModel().getColumn(6).setPreferredWidth(50);
		viewJeux.removeColumn(viewJeux.getColumnModel().getColumn(7));
		
		Action delete = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	JTable table = (JTable)e.getSource();
		    	int ligne = Integer.valueOf( e.getActionCommand() ); //recupere le numero de la ligne sachant qu'elle commence a 0
		    	try {
		    		System.out.println(ligne+1);
		    		Jeu j = Jeu.getById(base, ligne+1); //Recuperer le jeu correspondant au num de la ligne +1
					j.showEdit(); //Affiche les differentes infos
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		};
		
		ButtonColumn buttonColumn = new ButtonColumn(viewJeux, delete, 8);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		box.add(new JScrollPane(viewJeux), BorderLayout.CENTER);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(layoutBouton);
		add(box);
		
	}
}
