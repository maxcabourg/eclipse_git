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
public class ModeleDonneesResa extends AbstractTableModel{

	private Reservation[] donnees; //Ce seront les donnees a afficher
	private final String[] entetes = {"Id", "Utilisateur","Jeu", "Extensions", "Date Reservation", "Date Rendu", "Venu chercher","Editer","Supprimer"};	//Titres des colonnes
	
	public ModeleDonneesResa(){	
		super();
		int nombre_resa = 0;
		donnees = null;
		BDD bdd = new BDD();
		try {		
				ResultSet nbReservations = bdd.getConnection().createStatement().executeQuery("SELECT COUNT(*) as nbResas FROM Reservation");		
				while(nbReservations.next()){
					nombre_resa = nbReservations.getInt("nbResas");
				}
				nbReservations.close();
				ResultSet reser = bdd.getConnection().createStatement().executeQuery("SELECT * FROM Reservation");
				donnees = new Reservation[nombre_resa];	
			//Recuperation des utilisateurs
				int compteur = 0;
				while(reser.next()){
					Reservation resa = new Reservation(reser.getInt("IdReservation"), reser.getInt("IdUtilisateur"), reser.getInt("IdJeu"), reser.getString("idExtensions"), reser.getDate("DateReservation"), reser.getDate("DateRendu"), reser.getInt("VenuChercher"));
					donnees[compteur] = resa;
					compteur++;			
			}
			reser.close();
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
			return donnees[ligne].getIdR();
		case 1:
			try {
				return Reservation.getNomU(donnees[ligne].getIdU());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		case 2:
			try {
				return Reservation.getJeu(donnees[ligne].getIdJeuReserve());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 3:
			return donnees[ligne].getIdsExtensionsReservees();
		case 4:
			return donnees[ligne].getDateReservation();
		case 5:
			return donnees[ligne].getDateRendu();
		case 6:
			return donnees[ligne].isVenuChercher();
		case 7:
			return donnees[ligne].getEditer();
		case 8:
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
        return column == 7 || column == 8;
    }
}