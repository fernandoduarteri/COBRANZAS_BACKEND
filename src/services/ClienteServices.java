package services;

import dao.ClientesDAO;
import model.Cliente;
import model.ObjectReturn;

public class ClienteServices {
	
ClientesDAO objClienteDao = new ClientesDAO(Cliente.class);
	
	public void getClientes(ObjectReturn objReturn) throws Exception{
		objClienteDao.getClientes(objReturn);
	}

}
