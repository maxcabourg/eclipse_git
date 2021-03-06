package Donnees;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;

import vues.boiteEditerResa;
import vues.boiteEditerUser;

public class Reservation {
	private int idR;
	private int idU;
	private int idJeuReserve;
	private String idsExtensionsReservees; // Contient les id des extensions réservées sur cette même réservation
	private Date dateReservation;
	private Date dateRendu;
	private boolean venuChercher;
	private String editer;
	private String supprimer;
	private String retour;
	
	public Reservation(int _idR, int _idU, int _idJeuReserve, String _idsExtensionsReservees, Date _dateReservation, Date _dateRendu, int _venuChercher) {
		idR = _idR;
		idU = _idU;
		idJeuReserve = _idJeuReserve;
		idsExtensionsReservees = _idsExtensionsReservees;
		dateReservation = _dateReservation;
		dateRendu = _dateRendu;
		venuChercher = _venuChercher ==1;
		editer= "Modifier dates et statut";
		supprimer= "Supprimer";
		retour="Retour jeu";
	}
	
	public void ajouterReservation (BDD bdd) throws SQLException {
		PreparedStatement requete = bdd.getConnection().prepareStatement("INSERT INTO Reservation (IdUtilisateur, IdJeu, idExtensions, DateReservation, DateRendu, VenuChercher) VALUES (?,?,?,?,?,?);");
		requete.setInt(1, idU);
		requete.setInt(2, idJeuReserve);
		requete.setString(3, idsExtensionsReservees);
		java.sql.Date sqlDate = new java.sql.Date(dateReservation.getTime());
		requete.setDate(4,sqlDate);
		java.sql.Date sqlDate2 = new java.sql.Date(dateRendu.getTime());
		requete.setDate(5,sqlDate2);
		requete.setBoolean(6, venuChercher);
		requete.executeUpdate();
		requete.close();
		//On enleve un exemplaire du jeu que l'on remettra quand le jeu sera rendu
		int baisseNbExemplaire = bdd.getConnection().createStatement().executeUpdate("UPDATE Jeu SET StatutJeu = StatutJeu - 1 WHERE IdJeu = "+idJeuReserve);
		
	}
	
	public void supprimeReservationById (BDD bdd, int idR) throws SQLException {
		PreparedStatement requete = bdd.getConnection().prepareStatement("DELETE FROM Reservation WHERE idReservation = ?");
		requete.setInt(1,  idR);
		requete.executeUpdate();
		requete.close();
	}
	
	public void modifierVenuRecupererById (BDD bdd, boolean nouveauVenuChercher, int idR) throws SQLException {
		PreparedStatement requete = bdd.getConnection().prepareStatement("UPDATE Reservation SET VenuChercher = ? WHERE IdReservation = ?");
		requete.setBoolean(1, nouveauVenuChercher);
		requete.setInt(2, idR);
		requete.executeUpdate();
		requete.close();		
	}
	
	public void modifierDateReservationById (BDD bdd, Date nouvelleDate, int idR) throws SQLException{
		PreparedStatement requete = bdd.getConnection().prepareStatement("UPDATE Reservation SET DateReservation = ? WHERE IdReservation = ?");
		java.sql.Date sqlDate = new java.sql.Date(nouvelleDate.getTime());
		requete.setDate(1,  sqlDate);
		requete.setInt(2, idR);
		requete.executeUpdate();
		requete.close();
	}
	
	public void modifierDateRenduById (BDD bdd, Date nouvelleDate, int idR) throws SQLException{
		PreparedStatement requete = bdd.getConnection().prepareStatement("UPDATE Reservation SET DateRendu = ? WHERE IdReservation = ?");
		java.sql.Date sqlDate = new java.sql.Date(nouvelleDate.getTime());
		requete.setDate(1,  sqlDate);
		requete.setInt(2, idR);
		requete.executeUpdate();
		requete.close();
	}

	public boolean estValide(Reservation reservation, BDD bdd){
		boolean valide = true;
		int i = 0;
		int nbReservationsEnMemeTemps = 0;
		
		// verifie qu'elle n'empiete pas sur une autre reservation de la même personne
		try {
			List lDatesResasUtilisateur = getByIdUtilisateur(reservation.idU, bdd);
			while ((i < (lDatesResasUtilisateur.size()/2)-1) && (valide) ){
				valide = seSuperposent(reservation.dateReservation, reservation.dateRendu, (Date)lDatesResasUtilisateur.get(i*2), (Date)lDatesResasUtilisateur.get(2*i+1));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// verifie qu'elle n'empiete pas sur une autre reservation du même jeu
		try {
			List lDatesResasJeu = getByIdJeu(reservation.idJeuReserve, bdd);
			while (i < (lDatesResasJeu.size()/2)-1){
				if (seSuperposent(reservation.dateReservation, reservation.dateRendu, (Date)lDatesResasJeu.get(i*2), (Date)lDatesResasJeu.get(2*i+1)))
					nbReservationsEnMemeTemps++;
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (valide){
			try {
				Jeu jeuReserve = Jeu.getById(bdd, reservation.idJeuReserve);
				int nbExemplairesJeuReserve = jeuReserve.getNombreExemplaires();
				valide = (nbReservationsEnMemeTemps < nbExemplairesJeuReserve);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return valide;
	}
	

	/* 
	 * @return true si les périodes débutant respectivement à la Date dateDebut1 et dateDebut2, et finissant aux Date dateFin1 et dateFin2
	 * se supperposent. Et false si les periodes ne se superposent pas.
	 * @param dateDebut1 le début de la période 1
	 * @param dateFin1 la fin de la période 1
	 * @param dateDebut2 le début de la période 2
	 * @param dateFin2 la fin de la période 2
	 */
	private boolean seSuperposent(Date dateDebut1, Date dateFin1, Date dateDebut2, Date dateFin2){
		boolean seSuperposent;
		seSuperposent = !(dateDebut1.after(dateFin2) || dateDebut1.after(dateFin1));
		return seSuperposent;
	}
	
	
	private List getByIdUtilisateur(int idUtilisateur, BDD base) throws SQLException {
		List liste  = new ArrayList();
		ResultSet requete = base.getConnection().createStatement().executeQuery("SELECT dateReservation, dateRendu FROM Reservation WHERE idU = "+idUtilisateur);
		Date dateReservation = new Date();
		Date dateRendu = new Date();
		while(requete.next()){
			dateReservation = requete.getDate("dateReservation");
			liste.add(dateReservation);
			dateRendu = requete.getDate("dateRendu");
			liste.add(dateRendu);
		}
		return liste;
	}
	
	private List getByIdJeu(int idJeu, BDD base) throws SQLException {
		List liste  = new ArrayList();
		ResultSet requete = base.getConnection().createStatement().executeQuery("SELECT dateReservation, dateRendu FROM Reservation WHERE idJeu = "+idJeu);
		Date dateReservation = new Date();
		Date dateRendu = new Date();
		while(requete.next()){
			dateReservation = requete.getDate("dateReservation");
			liste.add(dateReservation);
			dateRendu = requete.getDate("dateRendu");
			liste.add(dateRendu);
		}
		return liste;
	}
	

	// fonction pour les JSONArray et leur utilisation!	
	private static List<Integer> jsonToList(JSONArray array){
		List<Integer> res = new ArrayList<Integer>();
		for (int i=0; i<array.length(); i++) {
			try {
				res.add(Integer.parseInt(array.getString(i)));
			} catch (NumberFormatException | JSONException e) {
				e.printStackTrace();
			}
		}		
		return res;
	}
	
	public static String ListToString (List<Integer> liste){
		JSONArray liste2 = new JSONArray();
		for (int value : liste) {
			liste2.put(value);
		}
		return liste2.toString();
	}
	
	public static List<Integer> stringToList(String txt){
		List<Integer> res = new ArrayList<Integer>();
		try {
			JSONArray liste = new JSONArray(txt);
			res = jsonToList(liste);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static boolean estUnMardi (Date date){
		int jour;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		jour = cal.get(Calendar.DAY_OF_WEEK);
		return (jour == Calendar.TUESDAY);
	}

	public static boolean estUnJeudi (Date date){
		int jour;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		jour = cal.get(Calendar.DAY_OF_WEEK);	
		return (jour == Calendar.THURSDAY);
	}
	
	public static Reservation getById(BDD base, int id) throws SQLException
	{
		//Il faut gerer le cas ou l'id est negatif, on sait jamais.
		if(id > 0)
		{
			ResultSet requete = base.getConnection().createStatement().executeQuery("SELECT * FROM Reservation WHERE IdReservation = "+id);
			String idExtension = "";
			int identifiant=0,venuchercher=0,idU=0, idj=0;
			Date dateReservation=null,dateRendu=null;
			while(requete.next())
			{
				identifiant = requete.getInt("IdReservation");
				idExtension = requete.getString("idExtensions");
				venuchercher = requete.getInt("VenuChercher");
				idU = requete.getInt("IdUtilisateur");
				idj = requete.getInt("IdJeu");
				dateReservation = requete.getDate("DateReservation");
				dateRendu = requete.getDate("DateRendu");

			}
			return new Reservation(identifiant, idU, idj,idExtension, dateReservation, dateRendu, venuchercher);
		}
		else
			return null;
	}
	
	public void delete(BDD base){
		try {
			int suppression = base.getConnection().createStatement().executeUpdate("DELETE FROM Reservation WHERE IdReservation = "+idR); //Tout d'abord on supprime le jeu de la base
			int baisseId = base.getConnection().createStatement().executeUpdate("UPDATE Reservation SET IdReservation = IdReservation - 1 WHERE IdReservation > "+idR); /*ensuite on baisse de 1 tous les id superieurs
			pour pas qu'il y ait de creux entre les id */
			ResultSet req = base.getConnection().createStatement().executeQuery("SELECT MAX(IdReservation) as maximum FROM Reservation"); //Et on fait repartir l'auto increment a la valeur maximale qui existe
			int maxId = 0;
			while(req.next())
				maxId = req.getInt("maximum")+1;
			int remiseId = base.getConnection().createStatement().executeUpdate("ALTER TABLE Reservation AUTO_INCREMENT = "+maxId);
			//On remet un exemplaire du jeu suite au rendu de la reservation
			int hausseNbExemplaire = base.getConnection().createStatement().executeUpdate("UPDATE Jeu SET StatutJeu = StatutJeu + 1 WHERE IdJeu = "+idJeuReserve);
			
			//fenetre pour informer l'utilisateur que le jeu est supprimé
			JOptionPane.showMessageDialog(null, "La reservation de "+ getNomU(idU) +" a bien ete supprimee!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showEdit() {
		new boiteEditerResa(this).setVisible(true);
		
	}
	
	public void retour(BDD base) throws SQLException {
		//int retourresa = base.getConnection().createStatement().executeUpdate("UPDATE Reservation SET VenuChercher = 1 WHERE IdReservation > "+idR);//On met le statut de la reservation a est venu cherhche "oui"
		java.util.Date datejour = new Date();
		ResultSet req = base.getConnection().createStatement().executeQuery("SELECT DateRendu FROM Reservation WHERE IdReservation="+idR);//On recupere la date de rendu qui a ete choisie lors de la reservation
		java.util.Date daterendu = new java.util.Date();
		while(req.next()){
		daterendu = req.getDate("DateRendu");
		}
		//java.sql.Date daterendu = req.getDate("DateRendu");
		if (datejour.after(daterendu)){//Si la date de rendu prevu est avant la date du jour on implémente les variables de retard de l'utilisateur
			ResultSet requete = base.getConnection().createStatement().executeQuery("SELECT IdUtilisateur FROM Reservation WHERE IdReservation="+idR);//Selectionne l'utilisateur concerné par la reservation
			int uConcerne;
			while(requete.next()){
			uConcerne= requete.getInt("IdUtilisateur");
			int hausseretard = base.getConnection().createStatement().executeUpdate("UPDATE Utilisateur SET NbrRetards = NbrRetards + 1 WHERE IdUtilisateur = "+uConcerne);
			long dureeretard= (datejour.getTime()- daterendu.getTime())/86400000 ;
			int haussenbjourretard = base.getConnection().createStatement().executeUpdate("UPDATE Utilisateur SET JoursRetardCumule = JoursRetardCumule + "+dureeretard+" WHERE IdUtilisateur = "+uConcerne);
			}
			JOptionPane.showMessageDialog(null, "La reservation de "+ getNomU(idU) +" a été rendue en retard !");
		}
		else{
			JOptionPane.showMessageDialog(null, "La reservation de "+ getNomU(idU) +" a été rendue dans les temps !");
		}
	
	}

	
	public static String getJeu(int id) throws SQLException{
		BDD base=new BDD();
		if(id > 0)
		{
			ResultSet requete = base.getConnection().createStatement().executeQuery("SELECT NomJeu FROM Jeu WHERE IdJeu = "+id);
			String nomJeu = "";
			while(requete.next())
			{
				nomJeu=requete.getString("NomJeu");

			}
			return nomJeu;
		}
		else
			return null;
	}
	
	public static String getNomU(int id) throws SQLException{
		BDD base=new BDD();
		if(id > 0)
		{
			ResultSet requete = base.getConnection().createStatement().executeQuery("SELECT PseudoU FROM Utilisateur WHERE IdUtilisateur = "+id);
			String nomU = "";
			while(requete.next())
			{
				nomU=requete.getString("PseudoU");

			}
			return nomU;
		}
		else
			return null;
	}
/*	
private static ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(String stringValue : stringArray) {
            
                //Convert String to Integer, and store it into integer array list.
                result.add(Integer.parseInt(stringValue.trim()));

        }       
        return result;
    }	
	
	
public static List<Integer> stringAlist(String s){  
	  
	s = s.replaceAll("\\[|\\]","");
	System.out.println(s);
	ArrayList<String> items = new ArrayList<>();
	Collections.addAll(items, s.split("\\s*,\\s*"));
	System.out.println(items);
	List<Integer> result = getIntegerArray(items);
	System.out.println(result);
	return result;
}
*/


	//getters et setters
	public int getIdR() {
		return idR;
	}

	public void setIdR(int idR) {
		this.idR = idR;
	}

	public int getIdU() {
		return idU;
	}

	public void setIdU(int idU) {
		this.idU = idU;
	}

	public int getIdJeuReserve() {
		return idJeuReserve;
	}

	public void setIdJeuReserve(int idJeuReserve) {
		this.idJeuReserve = idJeuReserve;
	}

	public String getIdsExtensionsReservees() {
		return idsExtensionsReservees;
	}

	public void setIdsExtensionsReservees(String idsExtensionsReservees) {
		this.idsExtensionsReservees = idsExtensionsReservees;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Date getDateRendu() {
		return dateRendu;
	}

	public void setDateRendu(Date dateRendu) {
		this.dateRendu = dateRendu;
	}

	public boolean isVenuChercher() {
		return venuChercher;
	}

	public void setVenuChercher(boolean venuChercher) {
		this.venuChercher = venuChercher;
	}
	public String getEditer(){
		return editer;
	}
	public String getSupprimer(){
		return supprimer;
	}

	public String getRetour() {
		return retour;
	}



}
