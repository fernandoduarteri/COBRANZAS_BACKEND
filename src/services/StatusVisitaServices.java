package services;

import dao.StatusVisitasDAO;
import model.ObjectReturn;
import model.StatusVisita;

public class StatusVisitaServices {
StatusVisitasDAO objStatusVisitaDao = new StatusVisitasDAO(StatusVisita.class);
	
	public void setStatus(ObjectReturn objReturn) throws Exception{
		objStatusVisitaDao.setStatusVisita(objReturn);
	}
}
