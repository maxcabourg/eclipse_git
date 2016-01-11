package vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import Donnees.BDD;
import Donnees.ModeleDonneesUser;
import Donnees.Utilisateur;

public class boiteEditerUser extends JDialog implements ActionListener{

	
	private Utilisateur utilisateur;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	//private JTextField tf4;
	private JTextField tf5;
	private JFormattedTextField tf6;
	private JTextField tf7;
	private JDateChooser tf8;
	private JCheckBox adm = new JCheckBox("Administrateur");
	private JCheckBox droitE = new JCheckBox("Droit d'emprunter");
	JComboBox nbjC = new JComboBox();
	JComboBox nbj = new JComboBox();
	JComboBox nbjnr = new JComboBox();
	//private JButton reinitialiser;
	private String mdpUser;
	
	private ModeleDonneesUser mdu = new ModeleDonneesUser();
	
	

	
	
	
	public boiteEditerUser(Utilisateur u){
		utilisateur = u;
		setSize(600, 600);
		setTitle("Modifier l'utilisateur");
		setModal(true);
		
		int ligneu = u.getId() - 1;
		String prenom = (String) mdu.getValueAt(ligneu, 1);
		tf1 = new JTextField(prenom,JTextField.CENTER);
	    tf1.setPreferredSize(new Dimension(400,25));
	   
	    String nom = (String) mdu.getValueAt(ligneu, 2);
	    tf2 = new JTextField(nom,JTextField.CENTER);
	    tf2.setPreferredSize(new Dimension(400,25));
	    tf2.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            tf2.setText("");
	        }
	    });
	    
	    String pseudo = (String) mdu.getValueAt(ligneu, 3);
	    tf3 = new JTextField(pseudo,JTextField.CENTER);
	    tf3.setPreferredSize(new Dimension(400,25));
	    tf3.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            tf3.setText("");
	        }
	    });
	   	 
	    mdpUser= (String) mdu.getValueAt(ligneu, 4);
	    JButton reinitialiser = new JButton("Reinitialiser Mot de Passe");
	    
	    
	    
	    String mail = (String) mdu.getValueAt(ligneu, 5);
	    tf5 = new JTextField(mail,JTextField.CENTER);
	    tf5.setPreferredSize(new Dimension(400,25));
	    tf5.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            tf5.setText("");
	        }
	    });
	    
	    String tel = (String) mdu.getValueAt(ligneu, 6);
	    tf6 = new JFormattedTextField(tel);
	    tf6.setPreferredSize(new Dimension(400,25));
	    tf6.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	        	MaskFormatter mtel;
				try {
					mtel = new MaskFormatter("##-##-##-##-##");
				
	        	tf6 = new JFormattedTextField(mtel);
	        }catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
	    }});
	    
	    String adr = (String) mdu.getValueAt(ligneu, 7);
	    tf7 = new JTextField(adr,JTextField.CENTER);
	    tf7.setPreferredSize(new Dimension(400,25));
	    tf7.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            tf7.setText("");
	        }
	    });
	    
	    Boolean admin = (Boolean) mdu.getValueAt(ligneu, 8);
	    adm.setSelected(admin);
	    
	    Date finadhe = (Date) mdu.getValueAt(ligneu, 9);
	    tf8 = new JDateChooser(finadhe);
	    tf8.setPreferredSize(new Dimension(400,25));
	  
	    Boolean droitEmp = (Boolean) mdu.getValueAt(ligneu, 10);
	    droitE.setSelected(droitEmp);
	    
	    int nbjoursRetardCumule = (int) mdu.getValueAt(ligneu,11);
	    nbjC.setPreferredSize(new Dimension(100,25));
	    for (int nbjr=0; nbjr<=150; nbjr++){
	    	nbjC.addItem(nbjr);
	    }
	    nbjC.setSelectedItem(nbjoursRetardCumule);
	    
	    
	    int nbjoursRetard = (int) mdu.getValueAt(ligneu,12);
	    nbj.setPreferredSize(new Dimension(100,25));
	    for (int nbjr=0; nbjr<=150; nbjr++){
	    	nbj.addItem(nbjr);
	    }
	    nbj.setSelectedItem(nbjoursRetard);
	    
	    int jeuNonRecup = (int) mdu.getValueAt(ligneu,13);
	    nbjnr.setPreferredSize(new Dimension(100,25));
	    for (int nbjr=0; nbjr<=150; nbjr++){
	    	nbjnr.addItem(nbjr);
	    }
	    nbjnr.setSelectedItem(jeuNonRecup);
	    
	 
	    // Cr�ation des labels
	    JLabel title = new JLabel("Modification de l'utilisateur");
		Font f = new Font(null, Font.PLAIN, 25); 
		title.setFont(f);
	    
	    
	    JLabel prenomU = new JLabel("Prenom");
	    prenomU.setLabelFor(tf1);       // D�finir le composant qui es label�
	 
	    JLabel nomU = new JLabel("Nom");
	    nomU.setLabelFor(tf2);
	 
	    JLabel pseudoU = new JLabel("Pseudo");
	    pseudoU.setLabelFor(tf3);
	    
	 
	    JLabel mdpU = new JLabel("Mot de passe");
	    mdpU.setLabelFor(reinitialiser);
	   
	 
	    JLabel mailU = new JLabel("Mail");
	    mailU.setLabelFor(tf5);
	    
	    JLabel telU = new JLabel("Telephone");
	    telU.setLabelFor(tf6);
	    
	    JLabel adrU = new JLabel("Adresse");
	    adrU.setLabelFor(tf7);
	    
	    JLabel fadU = new JLabel("Fin adhésion");
	    fadU.setLabelFor(tf8);
	    
	    JLabel nbjrC = new JLabel("Jours de retards cumules");
	    nbjrC.setLabelFor(nbjC);
	    
	    JLabel nbr = new JLabel("Nombre de retards");
	    nbr.setLabelFor(nbj);
	    
	    JLabel nbjeunonr = new JLabel("Jeux non recuperes");
	    nbjeunonr.setLabelFor(nbjnr);
	    
	    JButton valider = new JButton("Valider");
	    
	    //Mise en place des elements
	    setLayout(new GridBagLayout());
	    GridBagConstraints gc0 =new GridBagConstraints();
	    gc0.gridx = 0;//Case (1,0)
	    gc0.gridy = 0;
	    gc0.anchor = GridBagConstraints.CENTER;
	    gc0.gridwidth = 3;
	    add(title,gc0);
	    
	    GridBagConstraints gc1 =new GridBagConstraints();
	    gc1.gridx = 1;//Case (1,1)
	    gc1.gridy = 1;
	    gc1.anchor = GridBagConstraints.BASELINE;
	    add(prenomU,gc1);
	    gc1.gridx = 2;//Case(2,1)
	    gc1.gridy = 1;
	    gc1.gridwidth = GridBagConstraints.REMAINDER; 
	    gc1.anchor = GridBagConstraints.BASELINE;
	    add(tf1,gc1);
	    
	    GridBagConstraints gc2 =new GridBagConstraints();
	    gc2.gridx = 1;
	    gc2.gridy = 2;
	    gc2.anchor = GridBagConstraints.BASELINE;
	    add(nomU,gc2);
	    gc2.gridx = 2;
	    gc2.gridy = 2;
	    gc2.gridwidth = GridBagConstraints.REMAINDER; 
	    gc2.anchor = GridBagConstraints.BASELINE;
	    add(tf2,gc2);

	    GridBagConstraints gc3 =new GridBagConstraints();
	    gc3.gridx = 1;
	    gc3.gridy = 3;
	    gc3.anchor = GridBagConstraints.CENTER;
	    add(pseudoU,gc3);
	    gc3.gridx = 2;
	    gc3.gridy = 3;
	    gc3.gridwidth = GridBagConstraints.REMAINDER;
	    gc3.anchor = GridBagConstraints.CENTER;
	    gc3.fill = GridBagConstraints.HORIZONTAL;
	    add(tf3,gc3);
	   
	    GridBagConstraints gc4 =new GridBagConstraints();
	    gc4.gridx = 1;
	    gc4.gridy = 4;
	    gc4.anchor = GridBagConstraints.CENTER;
	    add(mdpU,gc4);
	    gc4.gridx = 2;
	    gc4.gridy = 4;
	    gc4.gridwidth = GridBagConstraints.REMAINDER;
	    gc4.anchor = GridBagConstraints.CENTER;
	    gc4.fill = GridBagConstraints.HORIZONTAL;
	    add(reinitialiser,gc4);
	    
	    GridBagConstraints gc5 =new GridBagConstraints();
	    gc5.anchor = GridBagConstraints.NONE;
	    gc5.fill = GridBagConstraints.NONE;
	    gc5.gridx = 1;
	    gc5.gridy = 5;
	    gc5.anchor = GridBagConstraints.CENTER;
	    add(mailU,gc5);
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
	    add(telU,gc6);
	    gc6.gridx = 2;
	    gc6.gridy = 6;
	    gc6.gridwidth = GridBagConstraints.REMAINDER;
	    gc6.anchor = GridBagConstraints.CENTER;
	    gc6.fill = GridBagConstraints.HORIZONTAL;
	    add(tf6,gc6);
	    
	    GridBagConstraints gc7 = new GridBagConstraints();
	    gc7.anchor = GridBagConstraints.NONE;
	    gc7.fill = GridBagConstraints.NONE;
	    gc7.gridx = 1;
	    gc7.gridy = 7;
	    gc7.anchor = GridBagConstraints.CENTER;
	    add(adrU,gc7);
	    gc7.gridx = 2;
	    gc7.gridy = 7;
	    gc7.gridwidth = GridBagConstraints.REMAINDER;
	    gc7.anchor = GridBagConstraints.CENTER;
	    gc7.fill = GridBagConstraints.HORIZONTAL;
	    add(tf7,gc7);
	    
	    GridBagConstraints gc8 = new GridBagConstraints();
	    gc8.anchor = GridBagConstraints.NONE;
	    gc8.fill = GridBagConstraints.NONE;
	    gc8.gridx = 1;
	    gc8.gridy = 8;
	    gc8.anchor = GridBagConstraints.CENTER;
	    add(adm,gc8);
	    
	    GridBagConstraints gc8bis = new GridBagConstraints();
	    gc8bis.gridx = 1;
	    gc8bis.gridy = 9;
	    gc8bis.gridwidth = GridBagConstraints.REMAINDER;
	    gc8bis.anchor = GridBagConstraints.CENTER;
	    gc8bis.fill = GridBagConstraints.HORIZONTAL;
	    add(droitE,gc8bis);
	    
	
	    
	    GridBagConstraints gc9 = new GridBagConstraints();
	    gc9.anchor = GridBagConstraints.NONE;
	    gc9.fill = GridBagConstraints.NONE;
	    gc9.gridx = 1;
	    gc9.gridy = 10;
	    gc9.anchor = GridBagConstraints.CENTER;
	    add(fadU,gc9);
	    gc9.gridx = 2;
	    gc9.gridy = 10;
	    gc9.gridwidth = GridBagConstraints.REMAINDER;
	    gc9.anchor = GridBagConstraints.CENTER;
	    gc9.fill = GridBagConstraints.HORIZONTAL;
	    add(tf8,gc9);
	    
	    GridBagConstraints gc10 = new GridBagConstraints();
	    gc10.anchor = GridBagConstraints.NONE;
	    gc10.fill = GridBagConstraints.NONE;
	    gc10.gridx = 1;
	    gc10.gridy = 11;
	    gc10.anchor = GridBagConstraints.CENTER;
	    add(nbjrC,gc10);
	    gc10.gridx = 2;
	    gc10.gridy = 11;
	    gc10.gridwidth = GridBagConstraints.REMAINDER;
	    gc10.anchor = GridBagConstraints.CENTER;
	    gc10.fill = GridBagConstraints.HORIZONTAL;
	    add(nbjC,gc10);
	    
	    GridBagConstraints gc11 = new GridBagConstraints();
	    gc11.anchor = GridBagConstraints.NONE;
	    gc11.fill = GridBagConstraints.NONE;
	    gc11.gridx = 1;
	    gc11.gridy = 12;
	    gc11.anchor = GridBagConstraints.CENTER;
	    add(nbr,gc11);
	    gc11.gridx = 2;
	    gc11.gridy = 12;
	    gc11.gridwidth = GridBagConstraints.REMAINDER;
	    gc11.anchor = GridBagConstraints.CENTER;
	    gc11.fill = GridBagConstraints.HORIZONTAL;
	    add(nbj,gc11);
	    
	    GridBagConstraints gc12 = new GridBagConstraints();
	    gc12.anchor = GridBagConstraints.NONE;
	    gc12.fill = GridBagConstraints.NONE;
	    gc12.gridx = 1;
	    gc12.gridy = 13;
	    gc12.anchor = GridBagConstraints.CENTER;
	    add(nbjeunonr,gc12);
	    gc12.gridx = 2;
	    gc12.gridy = 13;
	    gc12.gridwidth = GridBagConstraints.REMAINDER;
	    gc12.anchor = GridBagConstraints.CENTER;
	    gc12.fill = GridBagConstraints.HORIZONTAL;
	    add(nbjnr,gc12);
	    
	    GridBagConstraints gc13 =new GridBagConstraints();
	    gc13.fill = GridBagConstraints.NONE;
	    gc13.gridy = 14;
	    gc13.gridwidth = 3;
	    gc13.fill = GridBagConstraints.HORIZONTAL;
	    add(valider,gc13);
	 
	    valider.addActionListener(this); //On ajoute au bouton un gestionnaire d'evenements
	    valider.setActionCommand("Valider"); //On definit le nom de l'evenement envoye par le bouton
	    reinitialiser.addActionListener(this); //On ajoute au bouton un gestionnaire d'evenements
	    reinitialiser.setActionCommand("Reinitialiser"); //On definit le nom de l'evenement envoye par le bouton

	}
  
	//Action du bouton reinitialiser mdp et Valider
	public void actionPerformed(ActionEvent arg0){
		if(arg0.getActionCommand().equals("Reinitialiser")){
				try {
					mdpUser = Utilisateur.sha1(tf2.getText());
					JLabel l = new JLabel("<html>Le mot de passe à bien été réinitialisé !<br>Le nouveau mot de passe est le nom de l'utilisateur.</html>");
					l.setHorizontalAlignment(JLabel.CENTER);
					JOptionPane.showMessageDialog(this,l);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		if(arg0.getActionCommand().equals("Valider")){
			BDD bdd = new BDD();
			try{
				//Ici on r�cup�re les saisies dans les JTextField et le JComboBox
				String prenomu = tf1.getText();
				String nomu = tf2.getText();
				String pseudou = tf3.getText();
				String mdpu = mdpUser;
				String mailu = tf5.getText();
				String telu = tf6.getText();
				String adru = tf7.getText();
				Boolean admu = adm.isSelected();
				java.util.Date fadU = tf8.getDate();
				java.sql.Date sqlDate = new java.sql.Date(fadU.getTime());
				Boolean droitEmpU = droitE.isSelected();
				int nbjoursRetardCumule = (int) nbjC.getSelectedItem();
				int nbjoursRetard = (int) nbj.getSelectedItem();
				int jeuNonRecup = (int) nbjnr.getSelectedItem();
				int refj = 0;
				int stj = 0;
				
				if(!prenomu.equals("")&&!nomu.equals("")&&!pseudou.equals("")){
				
				PreparedStatement requete = bdd.getConnection().prepareStatement("UPDATE Utilisateur SET NomU = ?,PrenomU = ?,PseudoU = ?,MdpU=?,MailU=?,TelU=?,AdresseU=?,Administrateur=?,DateFinAdhesion=?,DroitEmprunter=?,JoursRetardCumule=?,NbrRetards=?,NbrJeuxNonRecupere=? WHERE IdUtilisateur = ?");
				
				
				requete.setString(1,nomu);
				requete.setString(2,prenomu);
				requete.setString(3, pseudou);
				requete.setString(4, mdpu);
				requete.setString(5, mailu);
				requete.setString(6, telu);
				requete.setString(7, adru);
				requete.setBoolean(8, admu);
				requete.setDate(9, sqlDate);
				requete.setBoolean(10, droitEmpU);
				requete.setInt(11, nbjoursRetardCumule);
				requete.setInt(12, nbjoursRetard);
				requete.setInt(13, jeuNonRecup);
				requete.setInt(14, utilisateur.getId());
				
				requete.executeUpdate();
				JOptionPane.showMessageDialog(this, "Votre utilisateur '"+pseudou+"' à bien été modifié !");
				dispose();
				requete.close();
				}	
				else {
					JOptionPane.showMessageDialog(this, "Veuillez remplir les champs de votre utilisateurs !");
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
