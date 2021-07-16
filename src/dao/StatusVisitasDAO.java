package dao;

import com.google.gson.JsonObject;

import Utilidades.Constantes;
import model.ObjectReturn;
import model.StatusVisita;
import persist.JPAEntity;

public class StatusVisitasDAO extends JPAEntity<StatusVisita> {
	public StatusVisitasDAO(Class<StatusVisita> entityClass) {
		super(entityClass);
	}
	
	public void setStatusVisita(ObjectReturn objReturn) {
		StatusVisita statusVisita = (StatusVisita)objReturn.getData();
		try {
			super.create(statusVisita);
			objReturn.setData("Status Visita insertada con exito");
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
