package vues;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Donnees.BDD;
import Donnees.Extension;
import Donnees.Jeu;

public class AffichageExtensions extends JDialog implements ActionListener{

	private BDD bdd;
	private Jeu jeu;
	private ArrayList<Extension> extensions;
	private ArrayList <Extension> extensionsSelectionnees;
	private ArrayList<JCheckBox> checkBoxes;
	
	public AffichageExtensions(Jeu j){
		bdd = new BDD();
		jeu = j;
		extensions = Extension.getByIdJeu(bdd, jeu.getId());
		
		extensionsSelectionnees = new ArrayList<Extension>();
		checkBoxes = new ArrayList<JCheckBox>();
		for(Extension e : extensions){
			checkBoxes.add(new JCheckBox());
		}
		
		
		Box checkLayout = Box.createVerticalBox();
		Box labelLayout = Box.createVerticalBox();
		Box layout = Box.createVerticalBox();
		Box mainLayout = Box.createHorizontalBox();
		
		int i = 0;
		for(Extension e : extensions){
			checkLayout.add(checkBoxes.get(i));
			labelLayout.add(new JLabel(extensions.get(i).getNom()));
			i++;
		}
		
		JButton valider = new JButton("Valider");
		valider.addActionListener(this);
		valider.setActionCommand("Valider");
		
		getContentPane().add(new JScrollPane());
		mainLayout.add(checkLayout);
		mainLayout.add(labelLayout);
		mainLayout.add(Box.createRigidArea(new Dimension(0,150)));
		layout.add(mainLayout);
		layout.add(Box.createRigidArea(new Dimension(0,100)));
		layout.add(valider);
		add(layout);
		
		setModal(true);
		setSize(400, 350);
		setTitle("Extensions de : "+jeu.getNom());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Valider")){
			for(int i = 0; i<checkBoxes.size(); i++) //On regarde quelles extensions sont cochees
			{
				if(checkBoxes.get(i).isSelected()){
					extensionsSelectionnees.add(extensions.get(i));
				}
			}
			dispose();
		}
		
	}

	public BDD getBdd() {
		return bdd;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public ArrayList<Extension> getExtensions() {
		return extensions;
	}

	public ArrayList<Extension> getExtensionsSelectionnees() {
		return extensionsSelectionnees;
	}

	public ArrayList<JCheckBox> getCheckBoxes() {
		return checkBoxes;
	}
	
	
}
