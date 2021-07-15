package dao;

import java.util.Calendar;
import com.google.gson.JsonObject;
import Utilidades.Constantes;
import model.ObjectReturn;
import model.Recorrido;
import persist.JPAEntity;

public class RecorridoDAO extends JPAEntity<Recorrido> {
	public RecorridoDAO(Class<Recorrido> entityClass) {
		super(entityClass);
	}
	
	
	public void getRutas(ObjectReturn objReturn) {
		JsonObject objJsonObject;
		objJsonObject = (JsonObject) objReturn.getData();
		Integer id = objJsonObject.get("id").getAsInt();
		String[] strDays = new String[] { "DOMINGO", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO" };
		Calendar calendar = Calendar.getInstance();
		String query = "SELECT r FROM Recorrido r WHERE r.usuario ="+ id + " AND r.dia =" + "'"+strDays[calendar.get(Calendar.DAY_OF_WEEK) - 1]+"'";
		try {
			objReturn.setData(super.getOne(query));
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
