package ws;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import model.CierreApp;
import model.ObjectReturn;
import model.StatusVisita;
import services.CierreServices;
import services.StatusVisitaServices;

@Path("cierre")
public class CierreWS {
	
	@Path("/getUltimoCierre")
	@POST
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public String getUltimoCierre(String strData) {
		
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
				
		// [Inicio] Realizando el servicio setStatusVisita
				
				objReturn.setData(objJsonObject);
				try {
					CierreServices objCierreervice = new CierreServices();
					objCierreervice.getUltimoCierre(objReturn);
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
		// [Fin] Realizando el servicio setStatusVisita
		
	}


	@Path("/actualizarUltimoCierre")
	@POST
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public String actualizarUltimoCierre(String strData) {
		
		// [Inicio] Declaracion de variables --
				ObjectReturn objReturn = new ObjectReturn();
				String resultado = "";
				JsonObject objJsonObject;
				Gson objJSON = new Gson();
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
				
		// [Inicio] Realizando el servicio setStatusVisita
				CierreApp cierreApp = objJSON.fromJson(objJsonObject, CierreApp.class);
				SimpleDateFormat pars = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
				try {
					Date d = pars.parse(objJsonObject.get("fechaInicio").getAsString());
					cierreApp.setFechaInicio(new Timestamp(d.getTime()));
					cierreApp.setFechaFinal(new Timestamp(d.getTime()));
					objReturn.setData(cierreApp);
					CierreServices objCierreService = new CierreServices();
					objCierreService.actualizarUltimoCierre(objReturn);
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
		// [Fin] Realizando el servicio setStatusVisita
		
	}
	
}
