package application;

import java.time.Month;
import java.util.List;

import db.Dao;

public class Model {
	
	private Dao dao= new Dao();
	
	public List<Month> getMesi(){
		List<Month> all= dao.getMesi();
		return all;
	}
	
	public List<String> getElenco(Month mese){
		List<String> all= dao.getUmiditaMedia(mese);
		return all;
	}

}
