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
	
	
	public void getClientes(ObjectReturn objReturn) {
		JsonObject objJsonObject;
		objJsonObject = (JsonObject) objReturn.getData();
		String zona = objJsonObject.get("zona").getAsString();
		//String zonas[] = String.split(zona,1);
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

}
