package vues;

import Donnees.BDD;
import Donnees.Utilisateur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import Donnees.BDD;
import Donnees.Jeu;

public class FormulaireUtilisateur extends JPanel implements ActionListener
	 {	
		private JTextField pseudo;
		private JTextField nom;
		private JTextField prenom;
		private JDateChooser abonne;
		private JFormattedTextField telephone;
		private JTextField add1;
		private JTextField addmail;
		private JTextField ville;
		private JFormattedTextField codepost;
		private JCheckBox admin;
	
	
	
		FormulaireUtilisateur() {
				try{
					
	//-------------MASK FORMATTER-----------------------------------------------------	
					MaskFormatter date = new MaskFormatter("##/##/####");
					MaskFormatter tel = new MaskFormatter("##-##-##-##-##");
					MaskFormatter cp = new MaskFormatter("## ###");
					Font titre = new Font("Arial", Font.BOLD, 26);

	//-------------TEXT FIELD-----------------------------------------------------
					pseudo = new JTextField();
					pseudo.setPreferredSize(new Dimension(150, 30));
						
					nom = new JTextField();
					nom.setPreferredSize(new Dimension(150, 30));
						
					prenom = new JTextField();
					prenom.setPreferredSize(new Dimension(150, 30));
					
					abonne = new JDateChooser();
					abonne.setPreferredSize(new Dimension(100, 30));
					
					telephone = new JFormattedTextField(tel);
					telephone.setPreferredSize(new Dimension(100, 30));
					
					add1 = new JTextField();
					add1.setPreferredSize(new Dimension(150, 30));
					
					addmail = new JTextField();
					addmail.setPreferredSize(new Dimension(150, 30));
					
					ville = new JTextField();
					ville.setPreferredSize(new Dimension(150, 30));
					
					codepost = new JFormattedTextField(cp);
					codepost.setPreferredSize(new Dimension(100, 30));
					
					admin = new JCheckBox("Administrateur");
					
					JButton ajouter = new JButton("Ajouter");
					ajouter.addActionListener((ActionListener) this); //On ajoute au bouton un gestionnaire d'evenements
				    ajouter.setActionCommand("Ajouter"); //On definit le nom de l'evenement envoye par le bouton
					
					
	//-------------LABEL-----------------------------------------------------				
					JLabel ltitre = new JLabel("Ajouter un utilisateur");
					ltitre.setLabelFor(nom);
					ltitre.setFont(titre);

					JLabel lpseudo = new JLabel("Pseudo :   ");
					lpseudo.setLabelFor(pseudo);

					JLabel lnom = new JLabel("Nom :   ");
					lnom.setLabelFor(nom);
			    
				    JLabel lprenom = new JLabel("Prenom :   ");
				    lprenom.setLabelFor(prenom);
				    
				    JLabel labonne = new JLabel("Date de fin d'adhesion :   ");
				    labonne.setLabelFor(abonne);
				    
				    JLabel ltelephone = new JLabel("Telephone :   ");
				    ltelephone.setLabelFor(telephone);
				   
				    JLabel  ladd1 = new JLabel("Adresse :   ");
				    ladd1.setLabelFor(add1);
				    
				    JLabel  laddmail = new JLabel("Adresse mail :   ");
				    laddmail.setLabelFor(addmail);
				    
				    JLabel  lville = new JLabel("Ville :   ");
				    lville.setLabelFor(ville);
				    
				    JLabel  lcodepost = new JLabel("Code postale :   ");
				    lcodepost.setLabelFor(codepost);
				    
	//-------------BOX-----------------------------------------------------			    

				    
				    Box ligne0 = Box.createHorizontalBox();
				    Box ligne1 = Box.createHorizontalBox();
				    Box ligne2 = Box.createHorizontalBox();
				    Box ligne3 = Box.createHorizontalBox();
				    Box ligne4 = Box.createHorizontalBox();
				    Box ligne5 = Box.createHorizontalBox();
				    Box ligne6 = Box.createHorizontalBox();
				    Box ligne7 = Box.createHorizontalBox();
				    Box ligne8 = Box.createHorizontalBox();
				    Box ligne9 = Box.createHorizontalBox();
				    Box ligne10 = Box.createHorizontalBox();
				    Box ligne11 = Box.createHorizontalBox();
				    Box mainLayout = Box.createVerticalBox();

	//-------------MISE EN FORME-----------------------------------------------------
				    ligne0.add(ltitre);
				    ligne1.add(lnom);
				    ligne1.add(nom);
				    ligne2.add(lprenom);
				    ligne2.add(prenom);
				    ligne3.add(labonne);
				    ligne3.add(abonne);
				    ligne4.add(ltelephone);
				    ligne4.add(telephone);
				    ligne5.add(ladd1);
				    ligne5.add(add1);
				    ligne6.add(laddmail);
				    ligne6.add(addmail);
				    ligne7.add(lcodepost);
				    ligne7.add(codepost);
				    ligne8.add(admin);
				    ligne9.add(lpseudo);
				    ligne9.add(pseudo);
				    ligne10.add(lville);
				    ligne10.add(ville);
				    
				    
				    mainLayout.add(ligne0);
				    mainLayout.add(Box.createRigidArea(new Dimension(0,10)));  //espace entre 2 composants
				    mainLayout.add(ligne9);
				    mainLayout.add(Box.createRigidArea(new Dimension(0,10)));
				    mainLayout.add(ligne1);
				    mainLayout.add(Box.createRigidArea(new Dimension(0,10)));
				    mainLayout.add(ligne2);
				    mainLayout.add(Box.createRigidArea(new Dimension(0,10)));
				    mainLayout.add(ligne3);
				    mainLayout.add(Box.createRigidArea(new Dimension(0,10)));
				    mainLayout.add(ligne4);
				    mainLayout.add(Box.createRigidArea(new Dimension(0,10)));
				    mainLayout.add(ligne6);
				    mainLayout.add(Box.createRigidArea(new Dimension(0,10)));
				    mainLayout.add(ligne5);
				    mainLayout.add(Box.createRigidArea(new Dimension(0,10)));
				    mainLayout.add(ligne7);
				    mainLayout.add(Box.createRigidArea(new Dimension(0,10)));
				    mainLayout.add(ligne10);
				    mainLayout.add(Box.createRigidArea(new Dimension(0,10)));
				    mainLayout.add(ligne8);
				    mainLayout.add(Box.createRigidArea(new Dimension(0,10)));
				    mainLayout.add(ajouter);
				    add(mainLayout);
				    
				    
				    
				}catch(ParseException e){e.printStackTrace();}
	  }
 

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getActionCommand().equals("Ajouter")){					
					BDD bdd = new BDD();
					try{
						//Ici on recupere les saisies dans les JTextField
						String pseudoU = pseudo.getText();
						String nomU = nom.getText();
						String prenomU = prenom.getText();
						java.util.Date abonneU = abonne.getDate();
						java.sql.Date sqlDate = new java.sql.Date(abonneU.getTime());
						String telephoneU = telephone.getText();
						String codepostU = codepost.getText();
						String add1U = add1.getText();
						String addmailU = addmail.getText();
						String villeU = ville.getText();
						int adminU = 1;
						if(!admin.isSelected())
						{adminU = 0;}
						Utilisateur utilisateur = new Utilisateur(0,prenomU, nomU, pseudoU, nomU, addmailU, telephoneU, add1U, adminU, sqlDate, 0,0,0,0);
						JOptionPane.showMessageDialog(this, "OK");
						if(!pseudoU.equals("")){			
						utilisateur.insertInto(bdd);
						JOptionPane.showMessageDialog(this, "Merci '"+pseudoU+"' d'avoir crיי un compte !");
						}	
						else {
							JOptionPane.showMessageDialog(this, "Veuillez mettre un nom ֳ  votre jeu !");
						}
					}catch (SQLException e) {
						e.printStackTrace();
					}catch (HeadlessException e) {
						e.printStackTrace();
					}
					
					}
					}
	


			
	}