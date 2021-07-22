package services;

import dao.CierreDAO;
import dao.LoginDAO;
import model.CierreApp;
import model.ObjectReturn;


public class CierreServices {
	CierreDAO objCierreDao = new CierreDAO(CierreApp.class);

	public void getUltimoCierre(ObjectReturn objReturn) {
		// TODO Auto-generated method stub
		objCierreDao.getUltimoCierre(objReturn);
	}

}
