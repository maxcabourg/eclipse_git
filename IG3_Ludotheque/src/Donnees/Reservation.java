package Donnees;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Reservation {
	private int idR;
	private int idU;
	private int idJeuReserve;
	private int tableauIdExtensionsReservees; // Contient les id des extensions réservées sur cette même réservation
	private Date dateReservation;
	private Date dateRendu;
	private boolean venuChercher;
	
	Reservation(int _idR, int _idU, int _idJeuReserve, int _tableauIdExtensionsReservees, Date _dateReservation, Date _dateRendu, boolean _venuChercher)
	{
		idR = _idR;
		idU = _idU;
		idJeuReserve = _idJeuReserve;
		tableauIdExtensionsReservees = _tableauIdExtensionsReservees;
		dateReservation = _dateReservation;
		dateRendu = _dateRendu;
		venuChercher = _venuChercher;
	}
	
	public void ajouterReservation (BDD bdd) throws SQLException
	{
		PreparedStatement requete = bdd.getConnection().prepareStatement("INSERT INTO Reservation (idR, idU, dateReservation, dateRendu, venuChercher) VALUES (?,?,?,?,?,?,?,?);");
		requete.setInt(1, idR);
		requete.setInt(2, idU);
		requete.setInt(3, idJeuReserve);
		requete.setInt(4, tableauIdExtensionsReservees);
		java.sql.Date sqlDate = new java.sql.Date(dateReservation.getTime());
		requete.setDate(5,sqlDate);
		java.sql.Date sqlDate2 = new java.sql.Date(dateRendu.getTime());
		requete.setDate(6,sqlDate2);
		requete.setBoolean(7, venuChercher);
		requete.executeUpdate();
		requete.close();
	}
	//public ??? getById
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
	
	public Date getDateReservation(){
		return dateReservation;
	}
	
	public Date getDateRendu(){
		return dateRendu;
	}
	
	public boolean getVenuChercher(){
		return venuChercher;
	}
	
}
