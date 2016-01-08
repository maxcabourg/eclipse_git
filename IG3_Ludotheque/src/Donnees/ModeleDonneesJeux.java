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
public class ModeleDonneesJeux extends AbstractTableModel{

	private Jeu[] donnees; //Ce seront les donnees a afficher
	private final String[] entetes = {"Id", "Titre", "Editeur", "Annee de parution", "Age recommandé", "Nombre de joueurs min", "Nombre de joueurs max", "Reference", "Exemplaires","Reserver", "Editer", "Supprimer"};	//Titres des colonnes
	
	public ModeleDonneesJeux(){	
		super();
		int nombre_jeux = 0;
		donnees = null;
		BDD bdd = new BDD();
		try {		
				ResultSet nbJeux = bdd.getConnection().createStatement().executeQuery("SELECT COUNT(*) as nbJeux FROM Jeu");		
				while(nbJeux.next()){
					nombre_jeux = nbJeux.getInt("nbJeux");
				}
				nbJeux.close();
				ResultSet jeux = bdd.getConnection().createStatement().executeQuery("SELECT * FROM Jeu");
				donnees = new Jeu[nombre_jeux];	
			//Recuperation des jeux
				int compteur = 0;
				while(jeux.next()){
					Jeu jeu = new Jeu(jeux.getInt("IdJeu"), jeux.getString("NomJeu"), jeux.getString("EditeurJeu"), jeux.getInt("AnneeJeu"), jeux.getInt("AgeJeu"), jeux.getInt("NombreJoueursMin"), jeux.getInt("NombreJoueursMax"), jeux.getInt("ReferenceJeu"), jeux.getInt("StatutJeu"));
					donnees[compteur] = jeu;
					compteur++;			
			}
			jeux.close();
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
			return donnees[ligne].getEditeur();
		case 3:
			return donnees[ligne].getAnnee();
		case 4:
			return donnees[ligne].getAge();
		case 5:
			return donnees[ligne].getNbJoueursMin();
		case 6:
			return donnees[ligne].getNbJoueursMax();
		case 7:
			return donnees[ligne].getReference();
		case 8:
			return donnees[ligne].getNombreExemplaires();
		case 9:
			return donnees[ligne].getReserver();
		case 10:
			return donnees[ligne].getEditer();
		case 11:
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
        return column == 9 || column == 10 || column == 11;
    }
}
