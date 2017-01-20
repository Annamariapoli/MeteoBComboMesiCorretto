package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Dao {
	
	public List<Month> getMesi(){                     //ok
		List<Month> mesi = new LinkedList<>();
		Connection conn = DBConnect.getConnection();
		//numero dei mesi in ordine:
		String query="select distinct  Month(s.`Data`) as mesi, s.`Data`  "
				+ "from situazione s  "
				+ "order by mesi asc";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()){
				LocalDate data = res.getDate("data").toLocalDate();
				if(!mesi.contains(data.getMonth())){
				    mesi.add(data.getMonth());
				}
			}
		
			conn.close();
			System.out.println(mesi);
			return mesi;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<String> getUmiditaMedia(Month mese){                      //funziona
		String query="select avg (s.Umidita) as media, s.Localita  "
				+ "from situazione s  "
				+ "where Month(s.`Data`)=?  "
				+ "group by s.Localita;";
		List<String> elenco = new LinkedList<>();
		Connection conn = DBConnect.getConnection();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1,  mese.getValue());
			ResultSet res = st.executeQuery();
			while(res.next()){
				elenco.add("Umidita media:  " +res.getDouble("media")+"   Citta:  "+res.getString("localita")+" \n");
			
			}
			conn.close();
			//System.out.println(elenco);
			return elenco;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String [] args){
		Dao dao = new Dao();
		dao.getMesi();
		//dao.getUmiditaMedia(12);
	}

}
