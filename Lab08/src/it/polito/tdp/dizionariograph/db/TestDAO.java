package it.polito.tdp.dizionariograph.db;

public class TestDAO {
	
	public static void main(String[] args) {
		
		WordDAO wd = new WordDAO();
		
		//System.out.println(wd.getAllWordsFixedLength(4));
		System.out.println(wd.completaSQL("casa", "WHERE "));
		System.out.println(wd.paroleCheDifferisconoPerUnaLettera("casa"));
	
	}

}
