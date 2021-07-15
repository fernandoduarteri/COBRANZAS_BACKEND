package dao;

import com.google.gson.JsonObject;

import Utilidades.Constantes;
import model.ObjectReturn;
import model.Usuario;
import persist.JPAEntity;

public class LoginDAO extends JPAEntity<Usuario> {
	public LoginDAO(Class<Usuario> entityClass) {
		super(entityClass);
	}



	public void getLogin(ObjectReturn objReturn) {
		JsonObject objJsonObject;
		objJsonObject = (JsonObject) objReturn.getData();
		String username = objJsonObject.get("username").getAsString();
		String password = objJsonObject.get("password").getAsString();
		String query = "SELECT u FROM Usuario u WHERE u.username ="+ "'"+username+"'" + " AND u.password =" + "'"+password+"'";
		try {
			objReturn.setData(super.getLogin(query));
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
