package ws;

import javax.persistence.StoredProcedureQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import Utilidades.Constantes;
import model.ObjectReturn;
import model.Situacion;
import services.ClienteServices;


@Path("clientes")
public class ClientesWS {

	@Path("/getClientes")
	@POST
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public String getClientes(String strData) {
		
		// [Inicio] Declaracion de variables --
				ObjectReturn objReturn = new ObjectReturn();
				String resultado = "";
				JsonObject objJsonObject;
				Gson objJSON = new GsonBuilder() 
				        .setPrettyPrinting()
				        .serializeNulls()
				        .create(); 
		// [Fin] Declaracion de variables --
				
		// [Inicio] Validando JSON Enviado en cuanto a formato --

				JsonParser objJsonParser = new JsonParser();
				try {
					objJsonObject = objJsonParser.parse(strData).getAsJsonObject();
				} catch (JsonSyntaxException e) {
					e.printStackTrace();
					objReturn.setMensaje(e.getMessage());
					objReturn.setData("Código de Error 2 - Excepción de sintaxis en el JSON");
					objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
					resultado = objJSON.toJson(objReturn);
					return resultado;
				}
		// [Fin] Validando JSON Enviado en cuanto a formato --
				
		// [Inicio] Realizando el servicio CLIENTE
				objReturn.setData(objJsonObject);
				try {
					ClienteServices objClienteService = new ClienteServices();
					objClienteService.getClientes(objReturn);
					if (!objReturn.getExito()) {
						throw new Exception(objReturn.getMensaje());
					}

					resultado = objJSON.toJson(objReturn);
					return resultado;
					
				} catch (Exception e) {
					
					objReturn.setMensaje(e.getMessage());
					objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
					objReturn.setTotal(0);
					resultado = objJSON.toJson(objReturn);
					return resultado;
				}
		// [Fin] Realizando el servicio CLIENTE
		
	}
	
	
	@Path("/getClientesFiltered")
	@POST
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public String getClientesFiltered(String strData) {
		
		// [Inicio] Declaracion de variables --
				ObjectReturn objReturn = new ObjectReturn();
				String resultado = "";
				JsonObject objJsonObject;
				Gson objJSON = new GsonBuilder()
				        .setPrettyPrinting()
				        .serializeNulls()
				        .create(); 
		// [Fin] Declaracion de variables --
				
		// [Inicio] Validando JSON Enviado en cuanto a formato --

				JsonParser objJsonParser = new JsonParser();
				try {
					objJsonObject = objJsonParser.parse(strData).getAsJsonObject();
				} catch (JsonSyntaxException e) {
					e.printStackTrace();
					objReturn.setMensaje(e.getMessage());
					objReturn.setData("Código de Error 2 - Excepción de sintaxis en el JSON");
					objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
					resultado = objJSON.toJson(objReturn);
					return resultado;
				}
		// [Fin] Validando JSON Enviado en cuanto a formato --
				
		// [Inicio] Realizando el servicio CLIENTE
				objReturn.setData(objJsonObject);
				try {
					ClienteServices objClienteService = new ClienteServices();
					objClienteService.getClientesFiltered(objReturn);
					
					if (!objReturn.getExito()) {
						throw new Exception(objReturn.getMensaje());
					}

					resultado = objJSON.toJson(objReturn);
					return resultado;
					
				} catch (Exception e) {
					
					objReturn.setMensaje(e.getMessage());
					objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
					objReturn.setTotal(0);
					resultado = objJSON.toJson(objReturn);
					return resultado;
				}
		// [Fin] Realizando el servicio CLIENTE
		
	}
	
	@Path("/situacionCliente")
	@POST
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public String getSituacionCliente(String strData) {
		
		// [Inicio] Declaracion de variables --
				ObjectReturn objReturn = new ObjectReturn();
				String resultado = "";
				JsonObject objJsonObject;
				Gson objJSON = new GsonBuilder()
				        .setPrettyPrinting()
				        .serializeNulls()
				        .create(); 
		// [Fin] Declaracion de variables --
				
		// [Inicio] Validando JSON Enviado en cuanto a formato --

				JsonParser objJsonParser = new JsonParser();
				try {
					objJsonObject = objJsonParser.parse(strData).getAsJsonObject();
				} catch (JsonSyntaxException e) {
					e.printStackTrace();
					objReturn.setMensaje(e.getMessage());
					objReturn.setData("Código de Error 2 - Excepción de sintaxis en el JSON");
					objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
					resultado = objJSON.toJson(objReturn);
					return resultado;
				}
		// [Fin] Validando JSON Enviado en cuanto a formato --
				
		// [Inicio] Realizando el servicio CLIENTE
				objReturn.setData(objJsonObject);
				try {
					System.out.println("viene");
					ClienteServices objClienteService = new ClienteServices();
					objClienteService.getSituacionCliente(objReturn);
					
					if (!objReturn.getExito()) {
						throw new Exception(objReturn.getMensaje());
					}

					objReturn.setExito(Constantes.FLAG_EXITO_EXITO);
					
					//objReturn.setData(s);

					resultado = objJSON.toJson(objReturn);
					return resultado;
					
				} catch (Exception e) {
					
					objReturn.setMensaje(e.getMessage());
					objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
					objReturn.setTotal(0);
					resultado = objJSON.toJson(objReturn);
					return resultado;
				}
		// [Fin] Realizando el servicio CLIENTE
		
	}
	
}
