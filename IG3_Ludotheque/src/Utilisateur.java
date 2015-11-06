//Classe reprensentant un utilisateur Lambda
public class Utilisateur {
	
	private String pseudo;
	private String mdp;
	
	Utilisateur(String _pseudo, String _mdp){
		pseudo = _pseudo;
		mdp = _mdp;
	}
	
	//Fonction verifiant si un couple (pseudo, mot de passe) existe dans la base de données.
	//Renvoie true s'il existe, false s'il n'existe pas
	private static boolean estValide(BDD base, String _pseudo, String _mdp){
		//TODO FOUILLER DANS LA BDD
		return true;
	}

}
