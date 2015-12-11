import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
 
public class FormulaireJeu extends JPanel
 {
  FormulaireJeu() {
 
    // Les zones de Textes
    JTextField tf1 = new JTextField(JTextField.CENTER);
    JTextField tf2 = new JTextField(JTextField.CENTER);
    JTextField tf3 = new JTextField(JTextField.CENTER);
    JTextField tf4 = new JTextField(JTextField.CENTER);
    JTextField tf5 = new JTextField(JTextField.CENTER);
 
    // Création des labels avec mnémoniques
    JLabel titre = new JLabel("Titre");
    titre.setDisplayedMnemonic('N'); // Définir le mnémonique
    titre.setLabelFor(tf1);       // Définir le composant qui es labelé
 
    
    JLabel editeur = new JLabel("Editeur");
    editeur.setDisplayedMnemonic('s');
    editeur.setDisplayedMnemonicIndex(5);
    editeur.setLabelFor(tf2);
 
    JLabel annee = new JLabel("Annee du jeu");
    annee.setDisplayedMnemonic('V');
    annee.setLabelFor(tf3);
 
    JLabel age = new JLabel("Age recommandé");
    age.setDisplayedMnemonic('P');
    age.setLabelFor(tf4);
 
    JLabel nbjoueur = new JLabel("Nombre de joueurs");
    nbjoueur.setDisplayedMnemonic('E');
    nbjoueur.setLabelFor(tf5);
    
    JButton valider = new JButton("Valider");
 
    Box ligne1 = Box.createHorizontalBox();
    Box ligne2 = Box.createHorizontalBox();
    Box ligne3 = Box.createHorizontalBox();
    Box ligne4 = Box.createHorizontalBox();
    Box ligne5 = Box.createHorizontalBox();
    Box mainLayout = Box.createVerticalBox();
    ligne1.add(titre);
    ligne1.add(tf1);
    //p.setLayout(new GridLayout(5, 4, 7, 7));
    ligne2.add(editeur);
    ligne2.add(tf2);
    ligne3.add(annee);
    ligne3.add(tf3);
    ligne4.add(age);
    ligne4.add(tf4);
    ligne5.add(nbjoueur);
    ligne5.add(tf5);
    mainLayout.add(ligne1);
    mainLayout.add(ligne2);
    mainLayout.add(ligne3);
    mainLayout.add(ligne4);
    mainLayout.add(ligne5);
    mainLayout.add(valider);
    mainLayout.setPreferredSize(new Dimension(500, 400));
    add(mainLayout);
    /*add(age);
    add(tf4);
    add(nbjoueur);
    add(tf5); */  
 
    //JFrame f = new JFrame("Création des formulaires en java");
    //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   // f.setSize(500,500);
   // f.setContentPane(p);
    //f.pack( );
    //f.setVisible(true);
    
    
    //f.getContentPane().add(new JButton("Valider"), BorderLayout.SOUTH);
    
  
  }
}
