package dao;



import Utilidades.Constantes;
import model.ObjectReturn;
import model.Pago;
import persist.JPAEntity;

public class PagoDAO extends JPAEntity<Pago>{
	
	public PagoDAO(Class<Pago> entityClass) {
		super(entityClass);
	}
	
	public void hacerPago(ObjectReturn objReturn) {
		Pago pago = (Pago)objReturn.getData();
		try {
			super.create(pago);
			objReturn.setData("Pago realizado con exito");
			objReturn.setMensaje("Exito");
			objReturn.setExito(Constantes.FLAG_EXITO_EXITO);
			objReturn.setTotal(1);
		}
		catch(Exception e) {
				objReturn.setData("");
				objReturn.setMensaje(e.getMessage());
				objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
				objReturn.setTotal(0);
			}
		
	}

}
