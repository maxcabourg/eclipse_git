package Donnees;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import vues.boiteEditerJeu;

public class Jeu{

	private int id;
	private String nom;
	private String editeur;
	private int annee;
	private int age;
	private int nombreJoueursMin;
	private int nombreJoueursMax;
	private String extensions;
	private int reference;
	private int nombreExemplaires;
	private String editer;
	private String supprimer;
	
	public Jeu(int _id, String _nom, String _editeur, int _annee, int _age, int min, int max, int _reference, int _nbEx){
		super();
		id = _id;
		nom = _nom;
		editeur = _editeur;
		annee = _annee;
		age = _age;
		nombreJoueursMin = min;
		nombreJoueursMax = max;
		reference = _reference;
		nombreExemplaires = _nbEx;
		editer = "editer";
		supprimer = "supprimer";
	}
	
	//Prend un parametre un id et renvoie le jeu correspondant � cet id
	public static Jeu getById(BDD base, int id) throws SQLException
	{
		//Il faut gerer le cas ou l'id est negatif, on sait jamais.
		if(id > 0)
		{
			ResultSet requete = base.getConnection().createStatement().executeQuery("SELECT * FROM Jeu WHERE IdJeu = "+id);
			String NomJeu = "", EditeurJeu = "";
			int idJeu = 0, annee = 0, age = 0, nbEx = 0, reference = 0, min = 0, max = 0;
			while(requete.next())
			{
				idJeu = requete.getInt("IdJeu");
				NomJeu = requete.getString("NomJeu");
				EditeurJeu = requete.getString("EditeurJeu");
				min = requete.getInt("NombreJoueursMin");
				max = requete.getInt("NombreJoueursMax");
				annee = requete.getInt("AnneeJeu");
				age = requete.getInt("AgeJeu");
				nbEx = requete.getInt("StatutJeu");
				reference = requete.getInt("ReferenceJeu");
			}
			requete.close();
			return new Jeu(idJeu, NomJeu, EditeurJeu, annee, age, min, max, reference, nbEx);
		}
		else
			return null;
	}
	
	public void showEdit()
	{
		new boiteEditerJeu(this).setVisible(true);
	}
	
	public void delete(BDD base)
	{
		try {
			int suppression = base.getConnection().createStatement().executeUpdate("DELETE FROM Jeu WHERE IdJeu = "+id); //Tout d'abord on supprime le jeu de la base
			int baisseId = base.getConnection().createStatement().executeUpdate("UPDATE Jeu SET IdJeu = IdJeu - 1 WHERE IdJeu > "+id); /*ensuite on baisse de 1 tous les id superieurs
			pour pas qu'il y ait de creux entre les id */
			ResultSet req = base.getConnection().createStatement().executeQuery("SELECT MAX(IdJeu) as maximum FROM Jeu"); //Et on fait repartir l'auto increment a la valeur maximale qui existe
			int maxId = 0;
			while(req.next())
				maxId = req.getInt("maximum")+1;
			int remiseId = base.getConnection().createStatement().executeUpdate("ALTER TABLE Jeu AUTO_INCREMENT = "+maxId);
			//fenetre pour informer l'utilisateur que le jeu est supprimé
			JOptionPane.showMessageDialog(null, "Votre jeu '"+nom+"' à bien été supprimé !");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertInto(BDD bdd) throws SQLException
	{
		PreparedStatement requete = bdd.getConnection().prepareStatement("INSERT INTO Jeu (NomJeu,EditeurJeu,AnneeJeu,AgeJeu,NombreJoueursMin,NombreJoueursMax,ReferenceJeu,StatutJeu) VALUES (?,?,?,?,?,?,?,?);");
		requete.setString(1,nom);
		requete.setString(2,editeur);
		requete.setInt(3, annee);
		requete.setInt(4, age);
		requete.setInt(5, nombreJoueursMin);
		requete.setInt(6, nombreJoueursMax);
		requete.setInt(7, reference);
		requete.setInt(8, nombreExemplaires);
		requete.executeUpdate();
		requete.close();
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getEditeur() {
		return editeur;
	}

	public int getAnnee() {
		return annee;
	}

	public int getAge() {
		return age;
	}

	public int getNbJoueursMin() {
		return nombreJoueursMin;
	}
	
	public int getNbJoueursMax(){
		return nombreJoueursMax;
	}

	public int getReference() {
		return reference;
	}

	public int getNombreExemplaires() {
		return nombreExemplaires;
	}
	
	public String getEditer() {
		return editer;
	}
	
	public String getSupprimer(){
		return supprimer;
	}
}
