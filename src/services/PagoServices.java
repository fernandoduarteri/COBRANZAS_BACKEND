package services;

import dao.PagoDAO;
import model.ObjectReturn;
import model.Pago;

public class PagoServices {
PagoDAO objPagoDao = new PagoDAO (Pago.class);
	
	public void hacerPago(ObjectReturn objReturn) throws Exception{
		objPagoDao.hacerPago(objReturn);

	}
}
