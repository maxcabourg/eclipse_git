package vues;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Donnees.BDD;
import Donnees.Jeu;
import Donnees.ModeleDonneesJeux;


public class boiteEditerJeu extends JDialog implements ActionListener{
	
	private JTextField tf1;
	private JTextField tf2;
	private JComboBox anne;
	private JComboBox agereco;
	private JTextField tf5;
	private JTextField tf6;
	private ModeleDonneesJeux mdj = new ModeleDonneesJeux();
	private Jeu jeu;
	
	public boiteEditerJeu(Jeu jeu){
		
		this.jeu = jeu;
		setSize(500, 500);
		setTitle("Editer le jeu");
		setModal(true);
		//add(new JLabel("TODO"));
		int lignejeu = jeu.getId() - 1;		
		
		// Les zones de Textes
	    String name = (String) mdj.getValueAt(lignejeu, 1);
		tf1 = new JTextField(name,JTextField.CENTER);
	    tf1.setPreferredSize(new Dimension(300,25));
	   
	    String edit = (String) mdj.getValueAt(lignejeu, 2);
	    tf2 = new JTextField(edit,JTextField.CENTER);
	    tf2.setPreferredSize(new Dimension(300,25));
	    tf2.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            tf2.setText("");
	        }
	    });
	    
	    int an = (int) mdj.getValueAt(lignejeu,3);
	    anne = new JComboBox();
	    anne.setPreferredSize(new Dimension(100,25));
	    for (int andepart=2016; andepart>=1901; andepart--){
	    	anne.addItem(andepart);
	    }
	    anne.setSelectedItem(an);
	   	    
	    int ager = (int)mdj.getValueAt(lignejeu, 4);
	    agereco = new JComboBox();
	    agereco.setPreferredSize(new Dimension(100,25));
	    for (int a=3; a<=99; a++){
	    	agereco.addItem(a);
	    }
	    agereco.setSelectedItem(ager);
	    
	    String nbrj = (String) mdj.getValueAt(lignejeu, 5);
	    tf5 = new JTextField(nbrj,JTextField.CENTER);
	    tf5.setPreferredSize(new Dimension(300,25));
	    tf5.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            tf5.setText("");
	        }
	    });
	    
	    String ext = (String) mdj.getValueAt(lignejeu, 6);
	    tf6 = new JTextField(ext,JTextField.CENTER);
	    tf6.setPreferredSize(new Dimension(300,25));
	    //Lorsque l'on clique sur le textfield le texte qui était présent disparait
	    tf6.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            tf6.setText("");
	        }
	    });
	 
	    // Création des labels
	    JLabel titre = new JLabel("Titre");
	    titre.setLabelFor(tf1);       // Définir le composant qui es labelé
	 
	    JLabel editeur = new JLabel("Editeur");
	    editeur.setLabelFor(tf2);
	 
	    JLabel annee = new JLabel("Annee du jeu");
	    annee.setLabelFor(anne);
	    
	 
	    JLabel age = new JLabel("Age recommandé");
	    age.setLabelFor(agereco);
	   
	 
	    JLabel nbjoueur = new JLabel("Nombre de joueurs");
	    nbjoueur.setLabelFor(tf5);
	    
	    JLabel extens = new JLabel("Extensions");
	    extens.setLabelFor(tf6);
	    
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
	    add(nbjoueur,gc5);
	    gc5.gridx = 2;
	    gc5.gridy = 5;
	    gc5.gridwidth = GridBagConstraints.REMAINDER;
	    gc5.anchor = GridBagConstraints.CENTER;
	    gc5.fill = GridBagConstraints.HORIZONTAL;
	    add(tf5,gc5);
	    
	    GridBagConstraints gc6 = new GridBagConstraints();
	    gc6.anchor = GridBagConstraints.NONE;
	    gc6.fill = GridBagConstraints.NONE;
	    gc6.gridx = 1;
	    gc6.gridy = 6;
	    gc6.anchor = GridBagConstraints.CENTER;
	    add(extens,gc6);
	    gc6.gridx = 2;
	    gc6.gridy = 6;
	    gc6.gridwidth = GridBagConstraints.REMAINDER;
	    gc6.anchor = GridBagConstraints.CENTER;
	    gc6.fill = GridBagConstraints.HORIZONTAL;
	    add(tf6,gc6);
	    
	    GridBagConstraints gc7 =new GridBagConstraints();
	    gc7.fill = GridBagConstraints.NONE;
	    gc7.gridy = 7;
	    gc7.gridwidth = 3;
	    gc7.fill = GridBagConstraints.HORIZONTAL;
	    add(valider,gc7);
	 
	    valider.addActionListener(this); //On ajoute au bouton un gestionnaire d'evenements
	    valider.setActionCommand("Valider"); //On definit le nom de l'evenement envoye par le bouton
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Valider")){
			BDD bdd = new BDD();
			try{
				//Ici on récupère les saisies dans les JTextField et le JComboBox
				String namejeu = tf1.getText();
				String editjeu = tf2.getText();
				int anneejeu = (int) anne.getSelectedItem();
				int agejeu = (int) agereco.getSelectedItem();
				String nbj = tf5.getText();
				String exts = tf6.getText();
				int refj = 0;
				int stj = 0;
				
				if(!namejeu.equals("")){
				
				PreparedStatement requete = bdd.getConnection().prepareStatement("UPDATE Jeu SET NomJeu = ?,EditeurJeu = ?,AnneeJeu = ?,AgeJeu=?,NombreJoueurs=?,ExtensionJeu=?,ReferenceJeu=?,StatutJeu=? WHERE IdJeu = ?");
				
				
				requete.setString(1,namejeu);
				requete.setString(2,editjeu);
				requete.setInt(3, anneejeu);
				requete.setInt(4, agejeu);
				requete.setString(5, nbj);
				requete.setString(6, exts);
				requete.setInt(7, refj);
				requete.setInt(8, stj);
				requete.setInt(9, jeu.getId());
				
				requete.executeUpdate();
				JOptionPane.showMessageDialog(this, "Votre jeu '"+namejeu+"' à bien été modifié !");
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
	private void setDefaultValueOf(JTextField tf12, String string) {
		// TODO Auto-generated method stub
		
	}
}
