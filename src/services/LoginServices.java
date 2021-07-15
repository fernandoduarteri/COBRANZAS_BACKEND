package services;


import dao.LoginDAO;
import model.ObjectReturn;
import model.Usuario;

public class LoginServices {
	LoginDAO objLoginDao = new LoginDAO(Usuario.class);
	
	public void getLogin(ObjectReturn objReturn) throws Exception{
		objLoginDao.getLogin(objReturn);
	}
}
