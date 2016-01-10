package Donnees;


import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.*;
/**
 * Base de donn�es de notre application
 */
public class BDD {
	
	/**
	 * connection reliant l'application et la base de donn�es
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
	 * Recupere la connection � la base de donn�es pour pouvoir effectuer des requ�tes plus tard
	 * @return la connection � la base de donn�es
	 */
	public Connection getConnection() {
		return connection;
	}
	
}
