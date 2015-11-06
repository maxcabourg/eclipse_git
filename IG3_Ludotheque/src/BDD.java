/*Classe qui va representer notre base de données
 * C'es tavec cette classe que l'on fera les requetes SQL
 */

import com.mysql.*;

public class BDD {
	
	BDD(){
		try {
			//Chargement du pilote mysql
		     Class.forName("com.mysql.jdbc.Driver");
		     System.out.println("Driver O.K.");
		}
		catch (Exception e) {
		      e.printStackTrace();
		}
	}

}
