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

//Classe reprensentant un utilisateur Lambda
public class Utilisateur {
	
	private int id;
	private String prenom;
	private String nom;
	private String pseudo;
	private String mdp;
	private String mail;
	private String tel;
	private String adresse;
	private boolean admin;
	private Date dateFinAdhesion;
	private boolean droitEmprunter;
	private int joursRetardCumule;
	private int nbrRetards;
	private int nbrJeuxNonRecuperes;
	private String editer;
	private String supprimer;
	
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
	//Fonction verifiant si un couple (pseudo, mot de passe) existe dans la base de donn�es.
	//Renvoie true s'il existe, false s'il n'existe pas
	//Une methode "static" est une methode qu'on peut appeler sans instancier un objet de la classe
	public static boolean estValide(BDD base, String _pseudo, String _mdp) throws SQLException, NoSuchAlgorithmException{
		//Une requete preparee permet d'eviter les injections SQL cad du code SQL ins�r� dans la requete
		PreparedStatement requete = base.getConnection().prepareStatement("Select MdpU FROM Utilisateur WHERE PseudoU = ?");
		requete.setString(1, _pseudo);
		//Execution de la requete
		ResultSet resultat = requete.executeQuery();
		String mdp_base = "";
		while(resultat.next())
			mdp_base = resultat.getString("MdpU");

		return mdp_base.compareTo(sha1(_mdp)) == 0;
			
	}
	
	//Prend un parametre un id et renvoie l'utilisateur correspondant � cet id
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
			//fenetre pour informer l'utilisateur que le jeu est supprimé
			JOptionPane.showMessageDialog(null, "L'utilisateur "+ pseudo +" a bien ete supprime!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Algorithme de cryptage du SHA1
	public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }        
        return sb.toString();
    }
	
	
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
	
	public void UpdateMdp(BDD bdd, String nouveauMdp, int idUti) throws SQLException {
		PreparedStatement requeteMdp = bdd.getConnection().prepareStatement("UPDATE Utilisateur SET MdpU = ? WHERE IdUtilisateur = ?");
		requeteMdp.setString(1, nouveauMdp);
		requeteMdp.setInt(2,idUti);
		requeteMdp.executeUpdate();
		requeteMdp.close();
		}
	
	
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
