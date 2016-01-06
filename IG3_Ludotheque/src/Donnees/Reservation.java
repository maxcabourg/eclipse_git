package Donnees;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

public class Reservation {
	private int idR;
	private int idU;
	private int idJeuReserve;
	private String idsExtensionsReservees; // Contient les id des extensions réservées sur cette même réservation
	private Date dateReservation;
	private Date dateRendu;
	private boolean venuChercher;
	
	Reservation(int _idR, int _idU, int _idJeuReserve, String _idsExtensionsReservees, Date _dateReservation, Date _dateRendu, boolean _venuChercher) {
		idR = _idR;
		idU = _idU;
		idJeuReserve = _idJeuReserve;
		idsExtensionsReservees = _idsExtensionsReservees;
		dateReservation = _dateReservation;
		dateRendu = _dateRendu;
		venuChercher = _venuChercher;
	}
	
	public void ajouterReservation (BDD bdd) throws SQLException {
		PreparedStatement requete = bdd.getConnection().prepareStatement("INSERT INTO Reservation (idR, idU, idJeuReserve, idsExtensionsReservees, dateReservation, dateRendu, venuChercher) VALUES (?,?,?,?,?,?);");
		requete.setInt(1, idR);
		requete.setInt(2, idU);
		requete.setInt(3, idJeuReserve);
		requete.setString(4, idsExtensionsReservees);
		java.sql.Date sqlDate = new java.sql.Date(dateReservation.getTime());
		requete.setDate(5,sqlDate);
		java.sql.Date sqlDate2 = new java.sql.Date(dateRendu.getTime());
		requete.setDate(6,sqlDate2);
		requete.setBoolean(7, venuChercher);
		requete.executeUpdate();
		requete.close();
	}
	
	public void supprimeReservationById (BDD bdd, int idR) throws SQLException {
		PreparedStatement requete = bdd.getConnection().prepareStatement("DELETE FROM Reservation WHERE idR = ?");
		requete.setInt(1,  idR);
		requete.executeUpdate();
		requete.close();
	}

	public boolean estValide(Reservation reservation, BDD bdd){
		boolean valide = true;
		int i = 0;
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
			while ((i < (lDatesResasJeu.size()/2)-1) && (valide) ){
				valide = seSuperposent(reservation.dateReservation, reservation.dateRendu, (Date)lDatesResasJeu.get(i*2), (Date)lDatesResasJeu.get(2*i+1));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
	private List<Integer> jsonToList(JSONArray array){
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
	
	public String ListToString (List<Integer> liste){
		JSONArray liste2 = new JSONArray();
		for (int value : liste) {
			liste2.put(value);
		}
		return liste2.toString();
	}
	
	public List<Integer> stringToList(String txt){
		List<Integer> res = new ArrayList<Integer>();
		try {
			JSONArray liste = new JSONArray(txt);
			res = jsonToList(liste);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return res;
	}
	//TODO
	//prevoir le cas où la liste est vide.

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
	
}
