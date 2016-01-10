package Donnees;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe representant l'extension d'un jeu
 */
public class Extension{
	
	/**
	 * Identifiant du jeu auquel est rattaché l'extension
	 */
	int jeuDeBase; //Id faisant reference au jeu de base
	/**
	 * Identifiant de l'extension
	 */
	int id;
	/**
	 * Nom de l'extension
	 */
	String nom;
	
	
	/**
	 * @param jdb Identifiant du jeu de base
	 * @param _id Identifiant de l'extension
	 * @param _nom Nom de l'extension
	 */
	public Extension(int jdb, int _id, String _nom){
		
		jeuDeBase = jdb;
		id = _id;
		nom = _nom;
	}
	
	/**
	 * Renvoie la liste des extensions liées à un certain jeu de base
	 * @param bdd Base de données dans laquelle fouiller
	 * @param idJeu Identifiant du jeu dont on souhaite connaitre les extensions
	 * @return La liste des extensions du jeu, sous forme d'ArrayList
	 */
	public static ArrayList<Extension> getByIdJeu(BDD bdd, int idJeu){
		
		ArrayList<Extension> ext = new ArrayList<Extension>();
		try {
			ResultSet requete = bdd.getConnection().createStatement().executeQuery("SELECT * FROM Extension WHERE IdJeu = "+idJeu);
			int jdb = 0, _id = 0;
			String nom = null;
			while(requete.next()){
				jdb = requete.getInt("IdJeu");
				_id = requete.getInt("IdExtension");
				nom = requete.getString("NomExtension");
				Extension e = new Extension(jdb, _id, nom);
				ext.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ext;
	}

	public int getJeuDeBase() {
		return jeuDeBase;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}
	
}
