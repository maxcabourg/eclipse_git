import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.*;

//Onglet qui présentera les differents jeux
public class OngletJeux extends JPanel{

	private JTextField champRecherche;
	private JButton rechercher;
	private Box layoutBouton = Box.createHorizontalBox();
	
	private Box box = Box.createVerticalBox();
	OngletJeux(){
		box.setPreferredSize(new Dimension(800, 450));
		champRecherche = new JTextField();
		rechercher = new JButton("Rechercher");
		layoutBouton.add(champRecherche);
		layoutBouton.add(rechercher);
		JTable viewJeux = new JTable(new ModeleDonneesJeux());
		viewJeux.setMinimumSize(new Dimension(1000, 800));
		viewJeux.removeColumn(viewJeux.getColumnModel().getColumn(0));
		viewJeux.getColumnModel().getColumn(0).setPreferredWidth(250);
		viewJeux.getColumnModel().getColumn(1).setPreferredWidth(175);
		viewJeux.getColumnModel().getColumn(3).setPreferredWidth(50);
		viewJeux.getColumnModel().getColumn(4).setPreferredWidth(50);
		viewJeux.getColumnModel().getColumn(5).setPreferredWidth(250);
		viewJeux.getColumnModel().getColumn(6).setPreferredWidth(50);
		viewJeux.removeColumn(viewJeux.getColumnModel().getColumn(7));
		box.add(new JScrollPane(viewJeux), BorderLayout.CENTER);
		box.add(Box.createRigidArea(new Dimension(0, 20)));
		box.add(layoutBouton);
		add(box);
		
	}
}
