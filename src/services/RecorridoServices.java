package services;

import dao.LoginDAO;
import dao.RecorridoDAO;
import model.ObjectReturn;
import model.Recorrido;


public class RecorridoServices {
	
RecorridoDAO objRecorridoDao = new RecorridoDAO(Recorrido.class);
	
	public void getRutas(ObjectReturn objReturn) throws Exception{
		objRecorridoDao.getRutas(objReturn);
	}

}
