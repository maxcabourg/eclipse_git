package vues;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;

import javax.swing.*;

import Donnees.BDD;
import Donnees.Utilisateur;

public class EcranConnexion extends JFrame implements ActionListener{

	/*Les layout sont des conteneurs qui accueillent les composants de l'interface.
	 * Ils peuvent �tre de types verticaux ou horizontaux (beaucoup plus complexes aussi).
	 * Ils peuvent contenir d'autres layout, on peut donc faire des assemblages de layout
	 * Par exemple ici, je deux layout horizontaux, un pour mettre le label du pseudo + la zone de texte
	 * et un autre pour le label Mot de passe et la zone de texte, puis je les assemble dans un layout general
	 * de type vertical.
	 */
	private BDD base;
	private Box layout = new Box(BoxLayout.PAGE_AXIS); //layout vertical
	private Box pseudoLayout = new Box(BoxLayout.LINE_AXIS); //Layout horizontal
	private Box mdpLayout = new Box(BoxLayout.LINE_AXIS); //Layout horizontal
	private JTextField pseudo; //Zone de texte pour rentrer le pseudo
	private JPasswordField mdp; //Zone de texte pour rentrer le mot de passe
	private JButton connexion; //Bouton de connexion
	
	public EcranConnexion(){
		base = new BDD();
		//Parametrages des composants
		pseudo = new JTextField(); //On initialise les composants
		pseudo.setMaximumSize(new Dimension(200, 50)); 
		mdp = new JPasswordField();
		mdp.setMaximumSize(new Dimension(200, 50));
		connexion = new JButton("Connexion"); //On passe en parametre le texte que l'on veut associer au bouton
		connexion.addActionListener(this); //On ajoute au bouton un gestionnaire d'evenements
		connexion.setActionCommand("Connexion"); //On definit le nom de l'evenement envoye par le bouton 
		pseudoLayout.add(new JLabel("Pseudo : ")); //On ajoute au layout du pseudo le Label...
		pseudoLayout.add(pseudo);//...puis la zone de texte
		mdpLayout.add(new JLabel("Mot de passe : ")); //De meme pour la zone de mot de passe
		mdpLayout.add(mdp);
		//Ensuite on va ajouter les 2 layouts dans un layout general de type vertical
		layout.add(Box.createRigidArea(new Dimension(0, 10))); //Ajout d'une zone vide de 10 pixels, pas important...
		layout.add(pseudoLayout); //On ajoute tout d'abord le layout de la zone de pseudo
		layout.add(Box.createRigidArea(new Dimension(0, 10))); //Sert a espacer les layouts
		layout.add(mdpLayout); //Ensuite on ajoute la layout de MDP qui va se retrouver dessous le layout de pseudo
		layout.add(Box.createRigidArea(new Dimension(0, 10)));//Espacement
		layout.add(connexion);//Puis on ajoute le bouton de connexion en dessous du layout de mot de passe
		layout.add(Box.createRigidArea(new Dimension(0, 20)));
		add(layout); //On dit ici que la fenetre va utiliser le layout general
		//Differents parametres...
		setTitle("Ludotech - Connexion");
		setSize(300, 150);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Connexion")){
			String pseudo_recupere = pseudo.getText();
			char[] mdp_recupere = mdp.getPassword();
			String mdp_recup = new String(mdp_recupere);
			//Gestion d'exceptions
			try {
				//Si l'utilisateur ne correspond pas on affiche un message d'erreur
					if(!Utilisateur.estValide(base, pseudo_recupere, mdp_recup)){
						JOptionPane.showMessageDialog(this, "Mauvais pseudo ou mot de passe", "Erreur", JOptionPane.ERROR_MESSAGE);//On recupere le mot de passe sous forme d'une chaine de caractere
					}
				//Sinon on affiche un message de bienvenue.
					else {
						JOptionPane.showMessageDialog(this, "Bienvenu "+pseudo_recupere+" !");
						setVisible(false);
						dispose(); //Detruit la fenetre de connexion
						ResultSet resultat = base.getConnection().createStatement().executeQuery("SELECT IdUtilisateur FROM Utilisateur WHERE PseudoU = '"+pseudo_recupere+"'");
						int id = 0;
						while(resultat.next())
							id = resultat.getInt("IdUtilisateur");
						Utilisateur u = Utilisateur.getById(base, id);
						new FenetrePrincipale(u, base).setVisible(true);
					}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}					
		}
	}
	
	
}
