package ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import Utilidades.Constantes;
import model.ObjectReturn;
import services.LoginServices;
import services.RecorridoServices;

@Path("recorrido")
public class RecorridoWS {
	
	@Path("/getRutas")
	@POST
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public String getRutas(String strData) {
		
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
				
		// [Inicio] Realizando el servicio ZONAS
				objReturn.setData(objJsonObject);
				try {
					RecorridoServices objRecorridoService = new RecorridoServices();
					objRecorridoService.getRutas(objReturn);
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
		// [Fin] Realizando el servicio ZONAS
		
	}

}
