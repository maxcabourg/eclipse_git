package vues;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import Donnees.BDD;
import Donnees.ModeleDonneesResa;
import Donnees.ModeleDonneesUser;
import Donnees.Reservation;

public class boiteEditerResa extends JDialog implements ActionListener{

	private Reservation reservation;
	private JDateChooser dateresa;
	private JDateChooser daterendu;
	private JLabel utr;
	private JLabel jr;
	private JCheckBox venuc = new JCheckBox("Venu chercher");
	
	private ModeleDonneesResa mdr = new ModeleDonneesResa();
	
	public boiteEditerResa(Reservation r){
		reservation=r;
		setSize(600, 350);
		setTitle("Modifier les dates et le statut de la réservation");
		setModal(true);
		int ligner = r.getIdR() - 1;
		
		String utilr = (String) mdr.getValueAt(ligner, 1);
		utr = new JLabel(utilr);
		
		String jeur = (String) mdr.getValueAt(ligner, 2);
		jr = new JLabel(jeur);
		
		Date dater = (Date) mdr.getValueAt(ligner, 4);
		dateresa = new JDateChooser(dater);
	    dateresa.setPreferredSize(new Dimension(200,25));
	    
	    Date drendu = (Date) mdr.getValueAt(ligner, 5);
	    daterendu = new JDateChooser(drendu);
	    daterendu.setPreferredSize(new Dimension(200,25));
	    
	    Boolean venu = (Boolean) mdr.getValueAt(ligner, 6);
	    venuc.setSelected(venu);
	    
	    //String extensr = (String) mdr.getValueAt(ligner, 3);
	    
	    //Jlabels
	    JLabel title = new JLabel("Modification de la date de réservation");
		Font f = new Font(null, Font.PLAIN, 25); 
		title.setFont(f);
		
		JLabel blanc = new JLabel("                                       ");//Label pour creer un espace
		Font f2 = new Font(null, Font.PLAIN, 25); 
		title.setFont(f2);
		
		JLabel ut = new JLabel("Utilisateur :");
		ut.setFont(ut.getFont().deriveFont(Font.BOLD));
	    ut.setLabelFor(utr);       // D�finir le composant qui es label�
	 
	    JLabel jeu = new JLabel("Jeu réservé :");
	    jeu.setFont(jeu.getFont().deriveFont(Font.BOLD));
	    jeu.setLabelFor(jr);
	 
	    JLabel datere = new JLabel("Date de réservation :");
	    datere.setFont(datere.getFont().deriveFont(Font.BOLD));
	    datere.setLabelFor(dateresa);
	    
	    JLabel darendu = new JLabel("Date de rendu :");
	    darendu.setFont(darendu.getFont().deriveFont(Font.BOLD));
	    darendu.setLabelFor(daterendu);
		
	    JButton valider = new JButton("Valider");
	    valider.addActionListener(this); //On ajoute au bouton un gestionnaire d'evenements
	    valider.setActionCommand("Valider"); //On definit le nom de l'evenement envoye par le bouton

	    
	    
	    
	    setLayout(new GridBagLayout());
	    GridBagConstraints gc0 =new GridBagConstraints();
	    gc0.gridx = 0;//Case (1,0)
	    gc0.gridy = 0;
	    gc0.anchor = GridBagConstraints.CENTER;
	    gc0.gridwidth = 3;
	    add(title,gc0);
	    
	    GridBagConstraints gcblanc =new GridBagConstraints();
	    gcblanc.gridx = 0;//Case (1,0)
	    gcblanc.gridy = 2;
	    gcblanc.anchor = GridBagConstraints.CENTER;
	    gcblanc.fill = GridBagConstraints.BOTH;
	    gcblanc.gridwidth = 3;
	    add(blanc,gcblanc);
	    
	    

	    
	    GridBagConstraints gc1 =new GridBagConstraints();
	    gc1.gridx = 1;//Case (1,1)
	    gc1.gridy = 3;
	    gc1.anchor = GridBagConstraints.EAST;
	    add(ut,gc1);
	    gc1.gridx = 2;//Case(2,1)
	    gc1.gridy = 3;
	    gc1.gridwidth = GridBagConstraints.REMAINDER; 
	    gc1.anchor = GridBagConstraints.BASELINE;
	    add(utr,gc1);
	    
	    GridBagConstraints gc2 =new GridBagConstraints();
	    gc2.gridx = 1;
	    gc2.gridy = 4;
	    gc2.anchor = GridBagConstraints.EAST;
	    add(jeu,gc2);
	    gc2.gridx = 2;
	    gc2.gridy = 4;
	    gc2.gridwidth = GridBagConstraints.REMAINDER; 
	    gc2.anchor = GridBagConstraints.BASELINE;
	    add(jr,gc2);

	    GridBagConstraints gc3 =new GridBagConstraints();
	    gc3.gridx = 1;
	    gc3.gridy = 5;
	    gc3.anchor = GridBagConstraints.EAST;
	    add(datere,gc3);
	    gc3.gridx = 2;
	    gc3.gridy = 5;
	    gc3.gridwidth = GridBagConstraints.REMAINDER;
	    gc3.anchor = GridBagConstraints.CENTER;
	    gc3.fill = GridBagConstraints.HORIZONTAL;
	    add(dateresa,gc3);
	    
	    GridBagConstraints gc4 =new GridBagConstraints();
	    gc4.gridx = 1;
	    gc4.gridy = 6;
	    gc4.anchor = GridBagConstraints.EAST;
	    add(darendu,gc4);
	    gc4.gridx = 2;
	    gc4.gridy = 6;
	    gc4.gridwidth = GridBagConstraints.REMAINDER;
	    gc4.anchor = GridBagConstraints.CENTER;
	    gc4.fill = GridBagConstraints.HORIZONTAL;
	    add(daterendu,gc4);
	    
	    GridBagConstraints gc5 = new GridBagConstraints();
	    gc5.anchor = GridBagConstraints.NONE;
	    gc5.fill = GridBagConstraints.NONE;
	    gc5.gridx = 2;
	    gc5.gridy = 7;
	    gc5.anchor = GridBagConstraints.WEST;
	    add(venuc,gc5);
	    
	   
	    GridBagConstraints gc4bis =new GridBagConstraints();
	    gc4bis.fill = GridBagConstraints.NONE;
	    gc4bis.gridy = 8;
	    gc4bis.gridwidth = 3;
	    gc4bis.fill = GridBagConstraints.HORIZONTAL;
	    add(valider,gc4bis);
	    
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Valider")){
			BDD bdd = new BDD();
			try{
				
					java.util.Date dtr = dateresa.getDate();
					java.sql.Date sqlDate = new java.sql.Date(dtr.getTime());
					java.util.Date dtrendu = daterendu.getDate();
					java.sql.Date sqlDate2 = new java.sql.Date(dtrendu.getTime());
					PreparedStatement requete = bdd.getConnection().prepareStatement("UPDATE Reservation SET DateReservation = ?,DateRendu=?,VenuChercher=? WHERE IdReservation = ?");
					Boolean vc = venuc.isSelected();
					
					
					requete.setDate(1,sqlDate);
					requete.setDate(2,sqlDate2);
					requete.setBoolean(3, vc);
					requete.setInt(4, reservation.getIdR());
					
					if(!(Reservation.estUnMardi(dateresa.getDate()) || Reservation.estUnJeudi(dateresa.getDate()))){
						JOptionPane.showMessageDialog(this, "La date de réservation doit etre un mardi ou un jeudi", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
					else {
					requete.executeUpdate();
					JOptionPane.showMessageDialog(this, "La reservation de  '"+utr.getText()+"' à bien été modifiée !");
					dispose();
					requete.close();
					}
					
			}catch (SQLException e1) {
				e1.printStackTrace();
			}catch (HeadlessException e1) {
				e1.printStackTrace();
			}
		
	}
//TODO
}
}