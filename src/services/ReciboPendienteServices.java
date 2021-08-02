package services;


import dao.ReciboPendienteDAO;
import model.ObjectReturn;
import model.OrdenCompra;
import model.ReciboPendiente;

public class ReciboPendienteServices {

	
ReciboPendienteDAO objStatusVisitaDao = new ReciboPendienteDAO(ReciboPendiente.class);
	
	public void setOrdenCompra(ObjectReturn objReturn) throws Exception{
		objStatusVisitaDao.setOrdenCompra(objReturn);
	}
}
