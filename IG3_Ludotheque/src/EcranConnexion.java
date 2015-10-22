import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.*;

public class EcranConnexion extends JFrame{

	/*Les layout sont des conteneurs qui accueillent les composants de l'interface.
	 * Ils peuvent être de types verticaux ou horizontaux (beaucoup plus complexes aussi).
	 * Ils peuvent contenir d'autres layout, on peut donc faire des assemblages de layout
	 * Par exemple ici, je deux layout horizontaux, un pour mettre le label du pseudo + la zone de texte
	 * et un autre pour le label Mot de passe et la zone de texte, puis je les assemble dans un layout general
	 * de type vertical.
	 */
	private Box layout = new Box(BoxLayout.PAGE_AXIS); //layout vertical
	private Box pseudoLayout = new Box(BoxLayout.LINE_AXIS); //Layout horizontal
	private Box mdpLayout = new Box(BoxLayout.LINE_AXIS); //Layout horizontal
	private JTextField pseudo; //Zone de texte pour rentrer le pseudo
	private JPasswordField mdp; //Zone de texte pour rentrer le mot de passe
	private JButton connexion; //Bouton de connexion
	
	EcranConnexion(){
		//Parametrages des composants
		pseudo = new JTextField();
		pseudo.setMaximumSize(new Dimension(200, 50));
		mdp = new JPasswordField();
		mdp.setMaximumSize(new Dimension(200, 50));
		connexion = new JButton("Connexion"); //On passe en parametre le texte que l'on veut associer au bouton
		pseudoLayout.add(new JLabel("Pseudo : "));
		pseudoLayout.add(pseudo);
		mdpLayout.add(new JLabel("Mot de passe : "));
		mdpLayout.add(mdp);
		layout.add(Box.createRigidArea(new Dimension(0, 10)));
		layout.add(pseudoLayout);
		layout.add(Box.createRigidArea(new Dimension(0, 10)));
		layout.add(mdpLayout);
		layout.add(Box.createRigidArea(new Dimension(0, 10)));
		layout.add(connexion);
		layout.add(Box.createRigidArea(new Dimension(0, 20)));
		add(layout);
		setTitle("Ludotech - Connexion");
		setSize(300, 150);
		setResizable(false);
		setVisible(true);
		this.setState(1);
	}
}
