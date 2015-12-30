package vues;

import javax.swing.*;

import Donnees.BDD;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.*;
 
public class FormulaireJeu extends JPanel implements ActionListener
 {
	 
	private JTextField tf1;
	private JTextField tf2;
	private JComboBox anne;
	private JComboBox agereco;
	private JComboBox tf5;
	private JComboBox tf5bis;
	private JTextField tf6;
	
	
	FormulaireJeu() {
 
    // Les zones de Textes
    
	tf1 = new JTextField("Entrez le nom de votre jeu",JTextField.CENTER);
    tf1.setPreferredSize(new Dimension(300,25));
    tf1.getFont().deriveFont(Font.ITALIC);
    tf1.setForeground(Color.gray);
    tf1.addMouseListener(new MouseListener() {           
        @Override
        public void mouseReleased(MouseEvent e) {}         
        @Override
        public void mousePressed(MouseEvent e) {}          
        @Override
        public void mouseExited(MouseEvent e) {}           
        @Override
        public void mouseEntered(MouseEvent e) {}          
        @Override
        public void mouseClicked(MouseEvent e) {
            JTextField tf1bis = ((JTextField)e.getSource());
            tf1bis.setText("");
            tf1bis.getFont().deriveFont(Font.PLAIN);
            tf1bis.setForeground(Color.black);
            tf1bis.removeMouseListener(this);
        }
    });
    
    tf2 = new JTextField(JTextField.CENTER);
    tf2.setPreferredSize(new Dimension(300,25));
    
    anne = new JComboBox();
    anne.setPreferredSize(new Dimension(100,25));
    
    agereco = new JComboBox();
    agereco.setPreferredSize(new Dimension(100,25));
    
    tf5 = new JComboBox();
    tf5.setPreferredSize(new Dimension(50,25));
    
    tf5bis = new JComboBox();
    tf5bis.setPreferredSize(new Dimension(50,25));
    
    /*tf6 = new JTextField(JTextField.CENTER);
    tf6.setPreferredSize(new Dimension(300,25));*/
 
    // Création des labels
    JLabel titre = new JLabel("Titre");
    titre.setLabelFor(tf1);       // Définir le composant qui es labelé
 
    JLabel editeur = new JLabel("Editeur");
    editeur.setLabelFor(tf2);
 
    JLabel annee = new JLabel("Annee du jeu");
    for (int andepart=2016; andepart>=1901; andepart--){
    	anne.addItem(andepart);
    }
    annee.setLabelFor(anne);
    
 
    JLabel age = new JLabel("Age recommandé");
    for (int a=3; a<=99; a++){
    	agereco.addItem(a);
    }
    age.setLabelFor(agereco);
   
    JLabel nbjoueurmin = new JLabel("Nombre de joueurs minimum");
    for (int a=0; a<=40; a++){
    	tf5.addItem(a);
    }
    nbjoueurmin.setLabelFor(tf5);
    
    JLabel nbjoueurmax = new JLabel("Nombre de joueurs maximum");
    for (int a=0; a<=40; a++){
    	tf5bis.addItem(a);
    }
    nbjoueurmax.setLabelFor(tf5bis);
    
    
   /* JLabel extens = new JLabel("Extensions");
    extens.setLabelFor(tf6);*/
    
    JButton valider = new JButton("Valider");
    
    //Mise en place des elements
    setLayout(new GridBagLayout());
    GridBagConstraints gc1 =new GridBagConstraints();
    gc1.gridx = 1;//Case (1,1)
    gc1.gridy = 1;
    gc1.anchor = GridBagConstraints.BASELINE;
    add(titre,gc1);
    gc1.gridx = 2;//Case(2,1)
    gc1.gridy = 1;
    gc1.gridwidth = GridBagConstraints.REMAINDER; 
    gc1.anchor = GridBagConstraints.BASELINE;
    add(tf1,gc1);
    
    GridBagConstraints gc2 =new GridBagConstraints();
    gc2.gridx = 1;
    gc2.gridy = 2;
    gc2.anchor = GridBagConstraints.BASELINE;
    add(editeur,gc2);
    gc2.gridx = 2;
    gc2.gridy = 2;
    gc2.gridwidth = GridBagConstraints.REMAINDER; 
    gc2.anchor = GridBagConstraints.BASELINE;
    add(tf2,gc2);

    GridBagConstraints gc3 =new GridBagConstraints();
    gc3.gridx = 1;
    gc3.gridy = 3;
    gc3.anchor = GridBagConstraints.CENTER;
    add(annee,gc3);
    gc3.gridx = 2;
    gc3.gridy = 3;
    gc3.gridwidth = GridBagConstraints.REMAINDER;
    gc3.anchor = GridBagConstraints.CENTER;
    gc3.fill = GridBagConstraints.HORIZONTAL;
    add(anne,gc3);
   
    GridBagConstraints gc4 =new GridBagConstraints();
    gc4.gridx = 1;
    gc4.gridy = 4;
    gc4.anchor = GridBagConstraints.CENTER;
    add(age,gc4);
    gc4.gridx = 2;
    gc4.gridy = 4;
    gc4.gridwidth = GridBagConstraints.REMAINDER;
    gc4.anchor = GridBagConstraints.CENTER;
    gc4.fill = GridBagConstraints.HORIZONTAL;
    add(agereco,gc4);
    
    GridBagConstraints gc5 =new GridBagConstraints();
    gc5.anchor = GridBagConstraints.NONE;
    gc5.fill = GridBagConstraints.NONE;
    gc5.gridx = 1;
    gc5.gridy = 5;
    gc5.anchor = GridBagConstraints.CENTER;
    add(nbjoueurmin,gc5);
    gc5.gridx = 2;
    gc5.gridy = 5;
    gc5.gridwidth = GridBagConstraints.REMAINDER;
    gc5.anchor = GridBagConstraints.CENTER;
    gc5.fill = GridBagConstraints.HORIZONTAL;
    add(tf5,gc5);
    
    GridBagConstraints gc5bis =new GridBagConstraints();
    gc5bis.anchor = GridBagConstraints.NONE;
    gc5bis.fill = GridBagConstraints.NONE;
    gc5bis.gridx = 1;
    gc5bis.gridy = 6;
    gc5bis.anchor = GridBagConstraints.CENTER;
    add(nbjoueurmax,gc5bis);
    gc5bis.gridx = 2;
    gc5bis.gridy = 6;
    gc5bis.gridwidth = GridBagConstraints.REMAINDER;
    gc5bis.anchor = GridBagConstraints.CENTER;
    gc5bis.fill = GridBagConstraints.HORIZONTAL;
    add(tf5bis,gc5bis);
    
    
    
    /*GridBagConstraints gc6 = new GridBagConstraints();
    gc6.anchor = GridBagConstraints.NONE;
    gc6.fill = GridBagConstraints.NONE;
    gc6.gridx = 1;
    gc6.gridy = 7;
    gc6.anchor = GridBagConstraints.CENTER;
    add(extens,gc6);
    gc6.gridx = 2;
    gc6.gridy = 7;
    gc6.gridwidth = GridBagConstraints.REMAINDER;
    gc6.anchor = GridBagConstraints.CENTER;
    gc6.fill = GridBagConstraints.HORIZONTAL;
    add(tf6,gc6);*/
    
    GridBagConstraints gc7 =new GridBagConstraints();
    gc7.fill = GridBagConstraints.NONE;
    gc7.gridy = 8;
    gc7.gridwidth = 3;
    gc7.fill = GridBagConstraints.HORIZONTAL;
    add(valider,gc7);
 
    valider.addActionListener(this); //On ajoute au bouton un gestionnaire d'evenements
    valider.setActionCommand("Valider"); //On definit le nom de l'evenement envoye par le bouton
 
  }   
    @Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Valider")){
			BDD bdd = new BDD();
			try{
				//Ici on récupère les saisies dans les JTextField et le JComboBox
				String namejeu = tf1.getText();
				String editjeu = tf2.getText();
				int anneejeu = (int) anne.getSelectedItem();
				int agejeu = (int) agereco.getSelectedItem();
				int nbjmin = (int) tf5.getSelectedItem();
				int nbjmax = (int) tf5bis.getSelectedItem();
				int refj = 0;
				int stj = 0;
				
				if(!namejeu.equals("")){
				
				PreparedStatement requete = bdd.getConnection().prepareStatement("INSERT INTO Jeu (NomJeu,EditeurJeu,AnneeJeu,AgeJeu,NombreJoueursMin,NombreJoueursMax,ReferenceJeu,StatutJeu) VALUES (?,?,?,?,?,?,?,?);");
				
				
				requete.setString(1,namejeu);
				requete.setString(2,editjeu);
				requete.setInt(3, anneejeu);
				requete.setInt(4, agejeu);
				requete.setInt(5, nbjmin);
				requete.setInt(6, nbjmax);
				requete.setInt(7, refj);
				requete.setInt(8, stj);
				
				requete.executeUpdate();
				JOptionPane.showMessageDialog(this, "Votre jeu '"+namejeu+"' à bien été ajouté !");
				requete.close();
				}	
				else {
					JOptionPane.showMessageDialog(this, "Veuillez mettre un nom à votre jeu !");
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (HeadlessException e) {
				e.printStackTrace();
			}
			
			
		}

    }

    //JFrame f = new JFrame("Création des formulaires en java");
    //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   // f.setSize(500,500);
   // f.setContentPane(p);
    //f.pack( );
    //f.setVisible(true);
    
    
    //f.getContentPane().add(new JButton("Valider"), BorderLayout.SOUTH);
    
  
 


}
