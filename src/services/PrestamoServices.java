package services;

import dao.PrestamoDAO;
import model.ObjectReturn;



public class PrestamoServices {
PrestamoDAO objPrestamoDao = new PrestamoDAO();
	
	public void getPrestamo(ObjectReturn objReturn) throws Exception{
		objPrestamoDao.getPrestamo(objReturn);
	}
	
	public void getPrestamoCliente(ObjectReturn objReturn) throws Exception{
		objPrestamoDao.getPrestamoCliente(objReturn);
	}
}
