package dao;

import Utilidades.Constantes;
import model.ObjectReturn;
import model.OrdenCompra;
import persist.JPAEntity;

public class OrdenCompraDAO extends JPAEntity<OrdenCompra> {
	public OrdenCompraDAO(Class<OrdenCompra> entityClass) {
		super(entityClass);
	}

	
	public void setOrdenCompra(ObjectReturn objReturn) {
		OrdenCompra ordenCompra = (OrdenCompra)objReturn.getData();
		try {
			super.create(ordenCompra);
			objReturn.setData("Orden de Compra insertada con exito");
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
