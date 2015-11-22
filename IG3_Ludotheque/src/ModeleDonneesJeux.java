import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeleDonneesJeux extends AbstractTableModel{

	private Jeu[] donnees;
	private final String[] entetes = {"Id", "Titre", "Editeur", "Annee de parution", "Age recommand�", "Nombre de joueurs", "Extensions", "Reference", "Statut"};	
	
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
					Jeu jeu = new Jeu(jeux.getInt("IdJeu"), jeux.getString("NomJeu"), jeux.getString("EditeurJeu"), jeux.getInt("AnneeJeu"), jeux.getInt("AgeJeu"), jeux.getString("NombreJoueurs"), jeux.getString("ExtensionJeu"), jeux.getInt("ReferenceJeu"), jeux.getInt("StatutJeu"));
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
	public Object getValueAt(int ligne, int colonne) {
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
			return donnees[ligne].getNbJoueurs();
		case 6:
			return donnees[ligne].getExtensions();
		case 7:
			return donnees[ligne].getReference();
		case 8:
			return donnees[ligne].getStatut();
		default:
			return null;
		}
		
	}

}
