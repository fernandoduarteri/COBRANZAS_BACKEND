package dao;

import java.math.BigInteger;
import java.util.Vector;

import javax.persistence.StoredProcedureQuery;

import com.google.gson.JsonObject;

import Utilidades.Constantes;
import model.Cliente;
import model.ObjectReturn;
import model.Situacion;
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
		String query = "select cliente.id,cliente.nombre,cliente.apellidos,cliente.cedula,cliente.tipo_cliente,cliente.direccion,cliente.ciudad,cliente.zona,cliente.orden,cliente.telefono,cliente.observaciones, cast((select status from status_visitas where cliente = cliente.cedula and fecha > CURDATE() limit 1) is null as unsigned) as activo,cliente.limite_credito,cliente.cedula_real,cliente.ld_permitida,cliente.venc_permitido from cliente left join prestamo on cliente.id = prestamo.cliente \r\n" + 
				"where zona IN("+zona+") and prestamo.pagado = 0  group by cliente.id order by orden ASC;";
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
		String query = "Select * from cliente where nombre LIKE "+"'"+filter+ "%'  OR apellidos LIKE "+"'"+filter+ "%' OR direccion LIKE " + "'" +filter +"%' OR cedula LIKE " + "'" +filter +"%' order by orden ASC;";
		
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
	public void getSituacionCliente(ObjectReturn objReturn) {
		JsonObject objJsonObject;
		objJsonObject = (JsonObject) objReturn.getData();
		String cliente = objJsonObject.get("cliente").getAsString();
		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("situacion_cliente");
		query.setParameter("var_id", Integer.parseInt(cliente));
		query.execute();
		String cedula = (String) query.getOutputParameterValue("var_cedula");
		Long var_limite_credito = (Long) query.getOutputParameterValue("var_limite_credito");
		Double var_total = (Double) query.getOutputParameterValue("var_total");
		Double var_saldo_vencido = (Double) query.getOutputParameterValue("var_saldo_vencido");
		Double var_saldo_no_vencido = (Double) query.getOutputParameterValue("var_saldo_no_vencido");
		String var_estado = (String) query.getOutputParameterValue("var_estado");
		Double var_entrega_minima = (Double) query.getOutputParameterValue("var_entrega_minima");
		Double var_disponible_potencial_en_orden = (Double) query.getOutputParameterValue("var_disponible_potencial_en_orden");
		Double var_servicio_diario_total = (Double) query.getOutputParameterValue("var_linea_directa_permitida");
		Double var_linea_directa_permitida = (Double) query.getOutputParameterValue("var_linea_directa_permitida");
		Double var_monto_ord_pendientes = (Double) query.getOutputParameterValue("var_monto_ord_pendientes");
		
		System.out.println("probando :" + cedula + " " +  var_total + " " + var_saldo_vencido + " " 
				+ var_saldo_no_vencido + " " + var_estado + " " + var_entrega_minima + " " + var_disponible_potencial_en_orden +
				" " + var_servicio_diario_total + " " + var_linea_directa_permitida + " " + var_monto_ord_pendientes);
		try {
			Situacion s = new Situacion();
			s.setHtml("<html><body>"
					+ "<h2>LIMITE: " + var_limite_credito + "</h2>"
					+ "<h2>ST PEND: " + var_saldo_no_vencido + "</h2>"
					+ "<h2>S VENC: " + var_saldo_vencido + "</h2>"
					+ "<h2>ESTADO: " + var_estado + "</h2>"
					+ "<h2>ENTREGA MINIMA: " + var_entrega_minima + "</h2>"
					+ "<h2>DISP POT EN ORDEN: " + var_disponible_potencial_en_orden + "</h2>"
					+ "<h2>LD PERM: " + var_linea_directa_permitida + "</h2>"
					+ "<h2>ORD PEND: " + var_monto_ord_pendientes + "</h2>"
					+ "</body></html>");
			objReturn.setData(s);
			objReturn.setMensaje("Exito");
			objReturn.setExito(Constantes.FLAG_EXITO_EXITO);
			//objReturn.setTotal(arry.size());
		}
		catch(Exception e) {
				objReturn.setData(""); 
				objReturn.setMensaje(e.getMessage());
				objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
				objReturn.setTotal(0);
			}
		
	}

}
