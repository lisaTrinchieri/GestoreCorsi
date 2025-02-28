package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {

	public List<Corso> getCorsiByPeriodo(Integer periodo)
	{  String sql= "select * " 
	               + "from corso "
			       + "where pd = ?";
	
	   List<Corso> result = new ArrayList<Corso>();
	   
	   try {
		     Connection conn = DBConnect.getConnection();
		     PreparedStatement st = conn.prepareStatement(sql);
		     st.setInt(1, periodo);
		     
		     ResultSet rs = st.executeQuery();
		     
		     while(rs.next()) {
		    	 Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"),
		    			 rs.getString("nome"), rs.getInt("pd"));
		    	 result.add(c);
		     }
		     
		st.close();
		conn.close();     
		     
	   } catch(SQLException e) {
		   throw new RuntimeException(e);
		   
	   }
	   
	   return result;
	   
	}
	
	public List<String> getCodiceCorsi() {
		
		String sql = "SELECT codins "
				    + "FROM corso ";
		
		List<String> corsi = new ArrayList<String>();
		
		try {   Connection conn = DBConnect.getConnection();
	            PreparedStatement st = conn.prepareStatement(sql);
	            
	            ResultSet rs = st.executeQuery();
			     
			    while(rs.next()) 
			    	corsi.add(rs.getString("codins"));
			     
			     
			    st.close();
			    conn.close(); 
		  } catch(SQLException e) {
			   throw new RuntimeException(e);
			    }
		   
		   return corsi;
		
	}
	
	public Map<Corso, Integer> getIscrittiByPeriodo(Integer periodo) {
		
		String sql= "SELECT c.codins, c.nome, c.crediti, c.pd, COUNT(*) AS tot " 
				    + "FROM corso c, iscrizione i "
				    + "WHERE c.codins = i.codins AND c.pd=1 "
				    + "GROUP BY c.codins, c.nome, c.crediti, c.pd ";
	
	   Map<Corso, Integer> result = new HashMap<Corso, Integer>();
	   
	   try {
		     Connection conn = DBConnect.getConnection();
		     PreparedStatement st = conn.prepareStatement(sql);
		     st.setInt(1, periodo);
		     
		     ResultSet rs = st.executeQuery();
		     
		     while(rs.next()) {
		    	 Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"),
		    			 rs.getString("nome"), rs.getInt("pd"));
		    	 Integer n = rs.getInt("tot");
		    	 result.put(c, n);
		     }
		     
		st.close();
		conn.close();     
		     
	   } catch(SQLException e) {
		   throw new RuntimeException(e);
		   
	   }
	   
	   return result;
	   
	
	}
	
	
}
