package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {

	public List<Studente> getStudentiByCorso(String codins) {
		
		String sql = "SELECT * "
				   + "FROM studente s, iscrizione i "
				   + "WHERE s.matricola = i.matricola AND i.codins = ? ";
		
		List<Studente> studenti = new ArrayList<Studente>();
		
		try {
	           Connection conn = DBConnect.getConnection();
	           PreparedStatement st = conn.prepareStatement(sql);
			   st.setString(1, codins);
			     
			   ResultSet rs = st.executeQuery();
			   
			   while(rs.next()) {
				   Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
				   studenti.add(s);
			   }
			   
			   st.close();
			   conn.close();
			   
		}  catch(SQLException e) {
			   throw new RuntimeException(e);
		}
		
		return studenti;
	}
	
	public List<Studente> getStudentiByIndirizzo(String codins)  {
		
		String sql = "SELECT s.CDS, s.matricola, s.cognome, s.nome "
				     + "FROM studente s, iscrizione i "
				     + "WHERE s.matricola = i.matricola AND i.codins = ?"
				     + "ORDER BY s.CDS ";
		
        List<Studente> studenti = new ArrayList<Studente>();
		
		try {
	           Connection conn = DBConnect.getConnection();
	           PreparedStatement st = conn.prepareStatement(sql);
			   st.setString(1, codins);
			     
			   ResultSet rs = st.executeQuery();
			   
			   while(rs.next()) {
				   Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
				   studenti.add(s);
			   }
			   
			   st.close();
			   conn.close();
			   
		}  catch(SQLException e) {
			   throw new RuntimeException(e);
		}
		
		return studenti;
	}
}
