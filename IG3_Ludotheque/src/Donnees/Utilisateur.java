package Donnees;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import vues.boiteEditerUser;

/**
 * Classe représentant un utilisateur du logiciel
 */
public class Utilisateur {
	
	/**
	 * Identifiant de l'utilisateur
	 */
	private int id;
	/**
	 * Prenom de l'utilisateur
	 */
	private String prenom;
	/**
	 * Nom de l'utilisateur
	 */
	private String nom;
	/**
	 * Pseudo de l'utilisateur
	 */
	private String pseudo;
	/*
	 * Mot de passe de l'utilisateur encrypté
	 */
	private String mdp;
	/**
	 * Mail de l'utilisateur
	 */
	private String mail;
	/**
	 * Numero de telephone de l'utilisateur
	 */
	private String tel;
	/**
	 * Adresse de l'utilisateur
	 */
	private String adresse;
	/**
	 * Booleen definissant si un utilisateur a les droits d'administration
	 */
	private boolean admin;
	/**
	 * Date de fin d'adhesion de l'adherent
	 */
	private Date dateFinAdhesion;
	/**
	 * Booleen definissant si un utilisateur a le droit d'emprunter / reserver
	 */
	private boolean droitEmprunter;
	/**
	 * Nombre total de jours de retard de l'utilisateur
	 */
	private int joursRetardCumule;
	/**
	 * Nombre de retards de l'utilisateur
	 */
	private int nbrRetards;
	/**
	 * Nombre de jeux que l'utilisateur n'a pas recupere
	 */
	private int nbrJeuxNonRecuperes;
	private String editer;
	private String supprimer;
	
	/**
	 * Constructeur principal de la classe
	 * @param _id id de l'utilisateur
	 * @param _prenom prenom de l'utilisateur
	 * @param _nom nom de l'utilisateur
	 * @param _pseudo pseudo de l'utilisateur
	 * @param _mdp Mot de passe de l'utilisateur encrypté
	 * @param _mail Mail de l'utilisateur
	 * @param_tel Numero de telephone de l'utilisateur
	 * @param _adresse Adresse de l'utilisateur
	 * @param _admin Droit d'administration de l'utilisateur 1 pour oui 0 pour non
	 * @param date Date de fin d'adhesion de l'utilisateur
	 * @param droit Droit d'emprunt / reservation de l'utilisateur : 1 pour oui 0 pour non
	 * @param jrc Jours de retards cumulés de l'utilisateur
	 * @param retards Nombre de retards total de l'utilisateur
	 * @param nbrJeux Nombre de jeux non récupérés par l'utilisateur
	 */
	public Utilisateur(int _id, String _prenom, String _nom, String _pseudo, String _mdp, String _mail, String _tel, String _adresse, int _admin, Date date, int droit, int jrc, int retards, int nbrJeux){
		id = _id;
		prenom = _prenom;
		nom = _nom;
		pseudo = _pseudo;
		mdp = _mdp;
		mail = _mail;
		tel = _tel;
		adresse = _adresse;
		admin = _admin == 1;
		dateFinAdhesion = date;
		droitEmprunter = droit == 1;
		joursRetardCumule = jrc;
		nbrRetards = retards;
		nbrJeuxNonRecuperes = nbrJeux;
		editer = "Editer";
		supprimer = "Supprimer";
	}
	/**
	 * Vérifie si un couple (pseudo, mot de passe) existe dans la base de données.
	 * @param base Base de donnees dans laquelle chercher
	 * @param _pseudo Pseudo a chercher
	 * @param _mdp Mot de passe NON ENCRYPTE a chercher
	 * @throws SQLException Si la requête échoue
	 * @throws NoSuchAlgorithmException s'il y a un problème avec le SHA1
	 * @return True si le couple existe, faux sinon
	 */
	
	//Une methode "static" est une methode qu'on peut appeler sans instancier un objet de la classe
	public static boolean estValide(BDD base, String _pseudo, String _mdp) throws SQLException, NoSuchAlgorithmException{
		//Une requete preparee permet d'eviter les injections SQL cad du code SQL insï¿½rï¿½ dans la requete
		PreparedStatement requete = base.getConnection().prepareStatement("Select MdpU FROM Utilisateur WHERE PseudoU = ?");
		requete.setString(1, _pseudo);
		//Execution de la requete
		ResultSet resultat = requete.executeQuery();
		String mdp_base = "";
		while(resultat.next())
			mdp_base = resultat.getString("MdpU");

		return mdp_base.compareTo(sha1(_mdp)) == 0;
			
	}
	
	/**
	 * Renvoie une instance d'Utilisateur à partir de son id
	 * @param base Base de donnees dans laquelle chercher
	 * @param id Identifiant dont on souhaite chercher le contenu
	 * @throws SQLException Si la requête échoue
	 * @returns Une instance d'Utilisateur associée à l'identifiant en question
	 */
	public static Utilisateur getById(BDD base, int id) throws SQLException
	{
		//Il faut gerer le cas ou l'id est negatif, on sait jamais.
		if(id > 0)
		{
			ResultSet requete = base.getConnection().createStatement().executeQuery("SELECT * FROM Utilisateur WHERE IdUtilisateur = "+id);
			String prenom = "", nom = "", pseudo = "", mdp = "", mail = "", tel = "", adresse = "";
			int identifiant = 0, admin = 0, droit = 0, jrc = 0, retards = 0, nbrJeux = 0;
			Date date = null;
			while(requete.next())
			{
				identifiant = requete.getInt("IdUtilisateur");
				prenom = requete.getString("PrenomU");
				nom = requete.getString("NomU");
				pseudo = requete.getString("PseudoU");
				mdp = requete.getString("MdpU");
				mail = requete.getString("MailU");
				tel = requete.getString("TelU");
				adresse = requete.getString("AdresseU");
				admin = requete.getInt("Administrateur");
				date = requete.getDate("DateFinAdhesion");
				droit = requete.getInt("DroitEmprunter");
				jrc = requete.getInt("JoursRetardCumule");
				retards = requete.getInt("NbrRetards");
				nbrJeux = requete.getInt("NbrJeuxNonRecupere");
			}
			return new Utilisateur(identifiant, prenom, nom, pseudo, mdp, mail, tel, adresse, admin, date, droit, jrc, retards, nbrJeux);
		}
		else
			return null;
	}
	
	public void showEdit(){
		new boiteEditerUser(this).setVisible(true);
	}
	
	/**
	 * Fonction supprimant une entrée d'Utilisateur dans la base de données
	 * @param base Base de données dans laquelle supprimer l'entrée
	 */
	public void delete(BDD base){
		try {
			int suppression = base.getConnection().createStatement().executeUpdate("DELETE FROM Utilisateur WHERE IdUtilisateur = "+id); //Tout d'abord on supprime le jeu de la base
			int baisseId = base.getConnection().createStatement().executeUpdate("UPDATE Utilisateur SET IdUtilisateur = IdUtilisateur - 1 WHERE IdUtilisateur > "+id); /*ensuite on baisse de 1 tous les id superieurs
			pour pas qu'il y ait de creux entre les id */
			ResultSet req = base.getConnection().createStatement().executeQuery("SELECT MAX(IdUtilisateur) as maximum FROM Utilisateur"); //Et on fait repartir l'auto increment a la valeur maximale qui existe
			int maxId = 0;
			while(req.next())
				maxId = req.getInt("maximum")+1;
			int remiseId = base.getConnection().createStatement().executeUpdate("ALTER TABLE Utilisateur AUTO_INCREMENT = "+maxId);
			//fenetre pour informer l'utilisateur que le jeu est supprimÃ©
			JOptionPane.showMessageDialog(null, "L'utilisateur "+ pseudo +" a bien ete supprime!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Algorithme de cryptage du SHA1
	 * @param input Chaine que l'on souhaite convertir
	 * @throws NoSuchAlgorithmException Si il y a un problème dans l'algorithme
	 * @returns La chaine de caractere après application du SHA1
	 */
	public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }        
        return sb.toString();
    }
	
	/**
	 * Insère une entrée d'Utilisateur dans la base de données
	 * @param bdd Base de données dans laquelle insérer
	 * @throws SQLException Si la requête échoue
	 */
	public void insertInto(BDD bdd) throws SQLException
	{
		PreparedStatement requete = bdd.getConnection().prepareStatement("INSERT INTO Utilisateur (NomU, PrenomU, PseudoU, MdpU, MailU, TelU, AdresseU, Administrateur, DateFinAdhesion, DroitEmprunter, JoursRetardCumule, NbrRetards, NbrJeuxNonRecupere) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);");
		requete.setString(1,nom);
		requete.setString(2,prenom);
		requete.setString(3, pseudo);
		try {
			requete.setString(4, sha1(mdp));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		requete.setString(5, mail);
		requete.setString(6, tel);
		requete.setString(7, adresse);
		requete.setBoolean(8, admin);
		java.sql.Date sqlDate = new java.sql.Date(dateFinAdhesion.getTime());
		requete.setDate(9,sqlDate);
		requete.setBoolean(10, droitEmprunter);
		requete.setInt(11, joursRetardCumule);
		requete.setInt(12, nbrRetards);
		requete.setInt(13, nbrJeuxNonRecuperes);
		requete.executeUpdate();
		requete.close();
	}
	
	/**
	 * Met à jour spécifiquement le mot de passe de l'utilisateur
	 * @param bdd Base de données dans laquelle travailler
	 * @param nouveauMdp Nouveau mot de passe à mettre à jour
	 * @param idUti Identifiant de l'utilisateur dont ou souhaite modifier le mot de passe
	 * @throws SQLException Si la requête échoue
	 */
	public void UpdateMdp(BDD bdd, String nouveauMdp, int idUti) throws SQLException {
		PreparedStatement requeteMdp = bdd.getConnection().prepareStatement("UPDATE Utilisateur SET MdpU = ? WHERE IdUtilisateur = ?");
		requeteMdp.setString(1, nouveauMdp);
		requeteMdp.setInt(2,idUti);
		requeteMdp.executeUpdate();
		requeteMdp.close();
		}
	
	/**
	 * Indique si l'utilisateur peut emprunter ou non
	 * @param u Utilisateur en question
	 * @return True si l'utilisateur peut emprunter, false sinon
	 */
	public boolean peutEmprunter (Utilisateur u){
		boolean res = u.droitEmprunter;
		if (res){
			java.util.Date dateAujourdhui = new java.util.Date();
			java.util.Date dateFinResa = new java.util.Date(u.dateFinAdhesion.getTime());
			res = dateFinResa.after(dateAujourdhui);
		}
		return res;
	}
	

	
	
	//Getters et setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public Date getDateFinAdhesion() {
		return dateFinAdhesion;
	}
	public void setDateFinAdhesion(Date dateFinAdhesion) {
		this.dateFinAdhesion = dateFinAdhesion;
	}
	public boolean DroitEmprunter() {
		return droitEmprunter;
	}
	public void setDroitEmprunter(boolean droitEmprunter) {
		this.droitEmprunter = droitEmprunter;
	}
	public int getJoursRetardCumule() {
		return joursRetardCumule;
	}
	public void setJoursRetardCumule(int joursRetardCumule) {
		this.joursRetardCumule = joursRetardCumule;
	}
	public int getNbrRetards() {
		return nbrRetards;
	}
	public void setNbrRetards(int nbrRetards) {
		this.nbrRetards = nbrRetards;
	}
	public int getNbrJeuxNonRecuperes() {
		return nbrJeuxNonRecuperes;
	}
	public void setNbrJeuxNonRecuperes(int nbrJeuxNonRecuperes) {
		this.nbrJeuxNonRecuperes = nbrJeuxNonRecuperes;
	}
	public String getEditer(){
		return editer;
	}
	public String getSupprimer(){
		return supprimer;
	}
	
	

}
