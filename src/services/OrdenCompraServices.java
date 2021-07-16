package services;

import dao.OrdenCompraDAO;
import model.ObjectReturn;
import model.OrdenCompra;

public class OrdenCompraServices {

	
OrdenCompraDAO objStatusVisitaDao = new OrdenCompraDAO(OrdenCompra.class);
	
	public void setOrdenCompra(ObjectReturn objReturn) throws Exception{
		objStatusVisitaDao.setOrdenCompra(objReturn);
	}
}
