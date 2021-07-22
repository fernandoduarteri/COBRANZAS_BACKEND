package dao;

import java.util.Vector;

import com.google.gson.JsonObject;

import Utilidades.Constantes;
import model.Cliente;
import model.ObjectReturn;
import persist.JPAEntity;

public class ClientesDAO extends JPAEntity<Cliente> {
	public ClientesDAO(Class<Cliente> entityClass) {
		super(entityClass);
	}
	
	@SuppressWarnings("rawtypes")
	public void getClientes(ObjectReturn objReturn) {
		JsonObject objJsonObject;
		objJsonObject = (JsonObject) objReturn.getData();
		String zona = objJsonObject.get("zona").getAsString();
		String query = "Select * from cliente where zona IN("+zona+ ") order by orden ASC;";
		try {
			objReturn.setData(super.findAllNativve(query));
			
			Vector arry  = (Vector) objReturn.getData();
			objReturn.setMensaje("Exito");
			objReturn.setExito(Constantes.FLAG_EXITO_EXITO);
			objReturn.setTotal(arry.size());
		}
		catch(Exception e) {
				objReturn.setData("");
				objReturn.setMensaje(e.getMessage());
				objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
				objReturn.setTotal(0);
			}
		
	}
	@SuppressWarnings("rawtypes")
	public void getClientesFiltered(ObjectReturn objReturn) {
		JsonObject objJsonObject;
		objJsonObject = (JsonObject) objReturn.getData();
		String filter = objJsonObject.get("query").getAsString();
		String query = "Select * from cliente where nombre LIKE "+"'"+filter+ "%'  OR apellidos LIKE "+"'"+filter+ "%' OR direccion LIKE " + "'" +filter +"%' order by orden ASC;";
		try {
			objReturn.setData(super.findAllNativve(query));
			Vector arry  = (Vector) objReturn.getData();
			objReturn.setMensaje("Exito");
			objReturn.setExito(Constantes.FLAG_EXITO_EXITO);
			objReturn.setTotal(arry.size());
		}
		catch(Exception e) {
				objReturn.setData(""); 
				objReturn.setMensaje(e.getMessage());
				objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
				objReturn.setTotal(0);
			}
		
	}

}
