package Donnees;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
/*
 * Cette classe va definir les donnees a afficher dans la JTable d'OngletJeux
 */
public class ModeleDonneesUser extends AbstractTableModel{

	private Utilisateur[] donnees; //Ce seront les donnees a afficher
	private final String[] entetes = {"Id", "Prenom", "Nom", "Pseudo", "Mot de passe", "Mail", "Telephone", "Adresse", "Administrateur", "Fin d'adhesion", "Droit d'emprunt", "Jours de retards cumules", "Nombre de retards", "Jeux non recuperes", "Editer", "Supprimer"};	//Titres des colonnes
	
	public ModeleDonneesUser(){	
		super();
		int nombre_users = 0;
		donnees = null;
		BDD bdd = new BDD();
		try {		
				ResultSet nbUtilisateurs = bdd.getConnection().createStatement().executeQuery("SELECT COUNT(*) as nbUsers FROM Utilisateur");		
				while(nbUtilisateurs.next()){
					nombre_users = nbUtilisateurs.getInt("nbUsers");
				}
				nbUtilisateurs.close();
				ResultSet users = bdd.getConnection().createStatement().executeQuery("SELECT * FROM Utilisateur");
				donnees = new Utilisateur[nombre_users];	
			//Recuperation des utilisateurs
				int compteur = 0;
				while(users.next()){
					Utilisateur user = new Utilisateur(users.getInt("IdUtilisateur"), users.getString("NomU"), users.getString("PrenomU"), users.getString("PseudoU"), users.getString("MdpU"), users.getString("MailU"), users.getString("TelU"), users.getString("AdresseU"), users.getInt("Administrateur"), users.getDate("DateFinAdhesion"), users.getInt("DroitEmprunter"), users.getInt("JoursRetardCumule"), users.getInt("NbrRetards"), users.getInt("NbrJeuxNonRecupere"));
					donnees[compteur] = user;
					compteur++;			
			}
			users.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return donnees.length;
	}
	
	public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

	@Override
	public Object getValueAt(final int ligne, int colonne) {
		switch(colonne){
		case 0:
			return donnees[ligne].getId();
		case 1:
			return donnees[ligne].getNom();
		case 2:
			return donnees[ligne].getPrenom();
		case 3:
			return donnees[ligne].getPseudo();
		case 4:
			return donnees[ligne].getMdp();
		case 5:
			return donnees[ligne].getMail();
		case 6:
			return donnees[ligne].getTel();
		case 7:
			return donnees[ligne].getAdresse();
		case 8:
			return donnees[ligne].isAdmin();
		case 9:
			return donnees[ligne].getDateFinAdhesion();
		case 10:
			return donnees[ligne].DroitEmprunter();
		case 11:
			return donnees[ligne].getJoursRetardCumule();
		case 12:
			return donnees[ligne].getNbrRetards();
		case 13:
			return donnees[ligne].getNbrJeuxNonRecuperes();
		case 14:
			return donnees[ligne].getEditer();
		case 15:
			return donnees[ligne].getSupprimer();
		default:
			return null;
		}
		
	}
	@Override	
	public Class getColumnClass(int columnIndex) {
        if(columnIndex == 0)return getValueAt(0, columnIndex).getClass();
 
        else return super.getColumnClass(columnIndex);
 
    }
	//Seule la colonne du bouton editer est "editable"
	@Override
	public boolean isCellEditable(int row, int column)
    {
        return column == 14 || column == 15;
    }
}