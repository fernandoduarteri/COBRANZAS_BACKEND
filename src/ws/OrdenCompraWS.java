package ws;

import java.util.Calendar;
import java.util.Date;

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
import model.OrdenCompra;
import model.ReciboPendiente;
import services.OrdenCompraServices;
import services.ReciboPendienteServices;

@Path("ordencompra")
public class OrdenCompraWS {

	@Path("/setOrdenCompra")
	@POST
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public String setOrdenCompra(String strData) {
		
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
				
		// [Inicio] Realizando el servicio LOGIN
				OrdenCompra ordenCompra = objJSON.fromJson(objJsonObject,OrdenCompra.class);
				Date datenow = new Date();  
				ordenCompra.setFechaOtorgada(datenow);
				Calendar c = Calendar.getInstance();
		        c.setTime(datenow);
		        c.add(Calendar.DATE, 30);
		        ordenCompra.setFechaVencimiento(c.getTime());
				
				try {
					if(ordenCompra.getEstado().equalsIgnoreCase("R")) {
						objReturn.setData(ordenCompra);
						OrdenCompraServices objOrdenCompraService = new OrdenCompraServices();
						objOrdenCompraService.setOrdenCompra(objReturn);
					}
					else {
						
						ReciboPendiente rp = new ReciboPendiente();
						rp.setCedulaCliente(ordenCompra.getCedulaCliente());
						rp.setCodigoValidacion(ordenCompra.getCodigoValidacion());
						rp.setEstado("R");
						rp.setFechaOtorgada(ordenCompra.getFechaOtorgada());
						rp.setFechaVencimiento(ordenCompra.getFechaVencimiento());
						rp.setMonto(ordenCompra.getMonto());
						objReturn.setData(rp);
						ReciboPendienteServices objOrdenCompraService = new ReciboPendienteServices();
						objOrdenCompraService.setOrdenCompra(objReturn);
					}
					
					
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
		// [Fin] Realizando el servicio LOGIN
		
	}

}
