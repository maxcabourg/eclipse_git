package Donnees;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;

import vues.boiteEditerJeu;

public class Jeu{

	private int id;
	private String nom;
	private String editeur;
	private int annee;
	private int age;
	private String nbJoueurs;
	private String extensions;
	private int reference;
	private int statut;
	private String editer;
	
	public Jeu(int _id, String _nom, String _editeur, int _annee, int _age, String _nbJoueurs,String _extensions, int _reference, int _statut){
		super();
		id = _id;
		nom = _nom;
		editeur = _editeur;
		annee = _annee;
		age = _age;
		nbJoueurs = _nbJoueurs;
		extensions = _extensions;
		reference = _reference;
		statut = _statut;
		editer = "editer";
	}
	
	//Prend un parametre un id et renvoie le jeu correspondant � cet id
		public static Jeu getById(BDD base, int id) throws SQLException
		{
			//Il faut gerer le cas ou l'id est negatif, on sait jamais.
			if(id > 0)
			{
				ResultSet requete = base.getConnection().createStatement().executeQuery("SELECT * FROM Jeu WHERE IdJeu = "+id);
				String NomJeu = "", EditeurJeu = "", NombreJoueurs = "", extensions = "";
				int idJeu = 0, annee = 0, age = 0, statut = 0, reference = 0;
				while(requete.next())
				{
					idJeu = requete.getInt("IdJeu");
					NomJeu = requete.getString("NomJeu");
					EditeurJeu = requete.getString("EditeurJeu");
					NombreJoueurs = requete.getString("NombreJoueurs");
					extensions = requete.getString("ExtensionJeu");
					annee = requete.getInt("AnneeJeu");
					age = requete.getInt("AgeJeu");
					statut = requete.getInt("StatutJeu");
					reference = requete.getInt("ReferenceJeu");
				}
				return new Jeu(idJeu, NomJeu, EditeurJeu, annee, age, NombreJoueurs, extensions, reference, statut);
			}
			else
				return null;
		}
		
		public void showEdit()
		{
			new boiteEditerJeu(this).setVisible(true);
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

	public String getNbJoueurs() {
		return nbJoueurs;
	}

	public String getExtensions() {
		return extensions;
	}

	public int getReference() {
		return reference;
	}

	public int getStatut() {
		return statut;
	}
	
	public String getEditer() {
		return editer;
	}
}
