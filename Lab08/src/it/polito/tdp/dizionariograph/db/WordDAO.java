package it.polito.tdp.dizionariograph.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDAO {

	/*
	 * Ritorna tutte le parole di una data lunghezza
	 */
	public List<String> getAllWordsFixedLength(int length) {

		String sql = "SELECT nome FROM parola WHERE LENGTH(nome) = ?";
		List<String> parole = new ArrayList<String>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, length);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				parole.add(res.getString("nome"));
			}
			conn.close();
			return parole;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	public List<String> paroleCheDifferisconoPerUnaLettera(String parola) {

		String sql = "SELECT nome FROM parola WHERE ";
		for(int i =0; i<parola.length(); i++)
			if(i<parola.length()-1)
				sql += "nome LIKE ? OR ";
			else
				sql += "nome LIKE ?";
		
		List<String> likes = this.completaSQL(parola, sql);
		List<String> vicini = new ArrayList<String>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			int j =1;
			for(String s: likes) {
				st.setString(j, s);
				j++;
			}
			ResultSet res = st.executeQuery();

			while (res.next()) {
				vicini.add(res.getString("nome"));
			}
			conn.close();
			return vicini;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<String> completaSQL(String parola, String sql) {

		char subChar;
		char arrayParola[] = parola.toCharArray();
		List<String> likes = new ArrayList<>();
		
		for(int i=0; i<parola.length(); i++) {	
			subChar = arrayParola[i];
			arrayParola[i] = '_';
			likes.add(new String(arrayParola));
			arrayParola[i]= subChar;
		}
		return likes;
	}

	
	

}
