package Donnees;

import java.sql.PreparedStatement;
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
	
	Reservation(int _idR, int _idU, int _idJeuReserve, String _idsExtensionsReservees, Date _dateReservation, Date _dateRendu, boolean _venuChercher)
	{
		idR = _idR;
		idU = _idU;
		idJeuReserve = _idJeuReserve;
		idsExtensionsReservees = _idsExtensionsReservees;
		dateReservation = _dateReservation;
		dateRendu = _dateRendu;
		venuChercher = _venuChercher;
	}
	
	public void ajouterReservation (BDD bdd) throws SQLException
	{
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
	//TODO
	public boolean estValide(Reservation reservation){
		boolean valide = true;
		
		return valide;
	}

	
	//TODO
	void getByNom() {
		
	}
	
	//getter et setter :
	public int getIdR(){
		return idR;
	}
	
	public int getIdU(){
		return idU;
	}
	
	public int getIdJeuReserve(){
		return idJeuReserve;
	}
	public String getidsExtensionsReservees(){
		return idsExtensionsReservees;
	}

	
	public Date getDateReservation(){
		return dateReservation;
	}
	
	public Date getDateRendu(){
		return dateRendu;
	}
	
	public boolean getVenuChercher(){
		return venuChercher;
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
	
	
	/*
	 * List<Integer> liste = jsontOlist(hkjdshdk);
	 * int coucou = liste.get(0);
	 * for (int valeur : liste) {
	 * 		
	 * }
	 */
}
