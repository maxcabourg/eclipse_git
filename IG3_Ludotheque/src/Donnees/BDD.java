package Donnees;


import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.*;
/**
 * Base de données de notre application
 */
public class BDD {
	
	/**
	 * connection reliant l'application et la base de données
	 */
	private Connection connection;
	public BDD(){
		try {
			//Chargement du pilote mysql
		     Class.forName("com.mysql.jdbc.Driver");
		     connection = DriverManager.getConnection("jdbc:mysql://mysql-projet-ludotheque.alwaysdata.net/projet-ludotheque_ig3","114844_admin", "admin");
		}
		catch (Exception e) {
		      e.printStackTrace();
		}
	}
	//Getter de la connexion
	
	/**
	 * Recupere la connection à la base de données pour pouvoir effectuer des requêtes plus tard
	 * @return la connection à la base de données
	 */
	public Connection getConnection() {
		return connection;
	}
	
}
