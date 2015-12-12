package Donnees;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	Utilisateur(String _prenom, String _nom, String _pseudo, String _mdp, String _mail, String _tel, String _adresse, int _admin){
		prenom = _prenom;
		nom = _nom;
		pseudo = _pseudo;
		mdp = _mdp;
		mail = _mail;
		tel = _tel;
		adresse = _adresse;
		admin = _admin == 0;
	}
	//Fonction verifiant si un couple (pseudo, mot de passe) existe dans la base de données.
	//Renvoie true s'il existe, false s'il n'existe pas
	//Une methode "static" est une methode qu'on peut appeler sans instancier un objet de la classe
	public static boolean estValide(BDD base, String _pseudo, String _mdp) throws SQLException, NoSuchAlgorithmException{
		//Une requete preparee permet d'eviter les injections SQL cad du code SQL inséré dans la requete
		PreparedStatement requete = base.getConnection().prepareStatement("Select MdpU FROM Utilisateur WHERE PseudoU = ?");
		requete.setString(1, _mdp);
		//Execution de la requete
		ResultSet resultat = requete.executeQuery();
		String mdp_base = "";
		while(resultat.next())
			mdp_base = resultat.getString("MdpU");
		
		return mdp_base.compareTo(sha1(_mdp)) == 0;
			
	}
	
	//Prend un parametre un id et renvoie l'utilisateur correspondant à cet id
	public static Utilisateur getById(BDD base, int id) throws SQLException
	{
		//Il faut gerer le cas ou l'id est negatif, on sait jamais.
		if(id > 0)
		{
			ResultSet requete = base.getConnection().createStatement().executeQuery("SELECT * FROM Utilisateur WHERE IdUtilisateur = "+id);
			String prenom = "", nom = "", pseudo = "", mdp = "", mail = "", tel = "", adresse = "";
			int admin = 0;
			while(requete.next())
			{
				id = requete.getInt("IdUtilisateur");
				prenom = requete.getString("PrenomU");
				nom = requete.getString("NomU");
				pseudo = requete.getString("PseudoU");
				mdp = requete.getString("MdpU");
				mail = requete.getString("MailU");
				tel = requete.getString("TelU");
				adresse = requete.getString("AdresseU");
				admin = requete.getInt("Administrateur");
			}
			return new Utilisateur(prenom, nom, pseudo, mdp, mail, tel, adresse, admin);
		}
		else
			return null;
	}
	
	//Algorithme de cryptage du SHA1
	private static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }        
        return sb.toString();
    }

}
