package Utilidades;

import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.core.HttpHeaders;

import org.glassfish.jersey.internal.util.Base64;

import com.google.gson.JsonObject;

import model.ObjectReturn;

public class Validaciones  {
	private static final String AUTHORIZATION_PROPERTY = "Autorizacion";
	private static final String CONTENT_TYPE_PROPERTY = "Tipo-Contenido";
	private static final String AUTHENTICATION_SCHEME = "Basic";
	HttpHeaders headers;

	public void validarCabeceras(ObjectReturn objReturn)  {
		try {
			headers=(HttpHeaders) objReturn.getData();
			String strAuthorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY).get(0);
			String strContentType = headers.getRequestHeader(CONTENT_TYPE_PROPERTY).get(0);
			if (strAuthorization == null || strAuthorization.isEmpty() || strAuthorization.equals(AUTHORIZATION_PROPERTY) || strContentType == null || strContentType.isEmpty() || strContentType.equals(CONTENT_TYPE_PROPERTY)) {
				throw new Exception("Excepcion de cabeceras fallidas... revise la guia del API");
			}
			objReturn.setExito(true);
		} catch (Exception e) {
			objReturn.setMensaje(e.getMessage());
			objReturn.setData("Código de Error 1 - Excepción de Cabeceras");
			objReturn.setExito(false);
		}

	}
	
	
	public void validarAutorizacion(ObjectReturn objReturn) {
		 JsonObject objJsonObject = (JsonObject) objReturn.getData();
		try {
			String strAuthorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY).get(0);
			String strEncodedUserPassword = strAuthorization.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
			String strUsernameAndPassword = new String(Base64.decode(strEncodedUserPassword.getBytes()));
            StringTokenizer strTokenizer = new StringTokenizer(strUsernameAndPassword, ":");
            String strGS1Nicaragua = strTokenizer.nextToken();
            String strGLNCliente = strTokenizer.nextToken();
            if(strGS1Nicaragua.equals("GS1 Nicaragua") && strGLNCliente.equals(objJsonObject.get("gln­proveedor").getAsString())) {
            	objReturn.setExito(Constantes.FLAG_EXITO_EXITO);
            }else {
            	throw new Exception("Excepcion de Autorización fallidas... revise la guia del API");
            }
		}catch(Exception e) {
			objReturn.setMensaje(e.getMessage());
			objReturn.setData("Código de Error 3 - Excepción de Autorización");
			objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
		}
	}

	

}
