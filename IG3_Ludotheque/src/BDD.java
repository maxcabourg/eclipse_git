/*Classe qui va representer notre base de données
 * C'es tavec cette classe que l'on fera les requetes SQL
 */

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.*;

public class BDD {
	private Connection connection;
	BDD(){
		try {
			//Chargement du pilote mysql
		     Class.forName("com.mysql.jdbc.Driver");
		     connection = DriverManager.getConnection("jdbc:mysql://mysql-projet-ludotheque.alwaysdata.net/projet-ludotheque_piscine","114844_admin", "admin");
		}
		catch (Exception e) {
		      e.printStackTrace();
		}
	}
	//Getter de la connexion
	public Connection getConnection() {
		return connection;
	}
	
}
