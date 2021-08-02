package dao;

import Utilidades.Constantes;
import model.ObjectReturn;
import model.OrdenCompra;
import model.ReciboPendiente;
import persist.JPAEntity;

public class ReciboPendienteDAO extends JPAEntity<ReciboPendiente> {
	public ReciboPendienteDAO(Class<ReciboPendiente> entityClass) {
		super(entityClass);
	}

	
	public void setOrdenCompra(ObjectReturn objReturn) {
		ReciboPendiente ordenCompra = (ReciboPendiente)objReturn.getData();
		try {
			super.create(ordenCompra);
			objReturn.setData("Recibo insertado con exito");
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
