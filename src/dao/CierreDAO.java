package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import com.google.gson.JsonObject;
import Utilidades.Constantes;
import model.CierreApp;
import model.ObjectReturn;
import persist.JPAEntity;

public class CierreDAO extends JPAEntity<CierreApp> {
	public CierreDAO(Class<CierreApp> entityClass) {
		super(entityClass);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getUltimoCierre(ObjectReturn objReturn) {
		JsonObject objJsonObject;
		objJsonObject = (JsonObject) objReturn.getData();
		String usuario = objJsonObject.get("usuario").getAsString();
		String query = "Select id, fecha_final from cierre_app where usuario="+ "'" + usuario+ "'" + " AND estado='P';";
		try {
			HashMap<String, Object> mapconvert = new HashMap<String,Object>();
			HashMap<String, Object> mapconvert1 = new HashMap<String,Object>();
			List resultList = new ArrayList();
			Vector <HashMap> resultList1 = new Vector<HashMap>();
			List<Object[]> getList  = super.findAllNative(query);
			for(Object[] q1 : getList){
				 mapconvert.put("idUltimoCierre",q1[0]);
				 mapconvert.put("fechaFinalUltimoCierre",q1[1]);
			     }
			query ="Select (Select COUNT(status) FROM status_visitas where status ='CERRADO' AND usuario="+"'" + usuario +"'"+ " AND fecha >" +"'"+mapconvert.get("fechaFinalUltimoCierre") +"'" +" group by status) AS CERRADO, (Select COUNT(status) FROM status_visitas where status ='NO PAGO' AND usuario=" +"'" + usuario +"'"+ " AND fecha >" +"'"+mapconvert.get("fechaFinalUltimoCierre") +"'" +" group by status) as NOPAGO";
			getList  = super.findAllNative(query);
			for(Object[] q1 : getList){
				 mapconvert.put("CERRADO",q1[0]);
				 mapconvert.put("NOPAGO",q1[1]);
			     }
			query = "Select c.cedula as cedula, p.monto_pagado as monto, cc.id as idCuota, CONCAT(c.nombre, ' ', c.apellidos) as nombre, p.fecha_pago as fechaPago from cliente as c, pago as p, cuota as cc, prestamo as pp where p.cuota=cc.id and cc.prestamo=pp.id and pp.cliente=c.id and p.usuario="+"'"+usuario + "'"+ " and p.fecha_pago >" +"'"+mapconvert.get("fechaFinalUltimoCierre") +"'"; 
			getList  = super.findAllNative(query);
			Double montoTotal = (double) 0;
			for(Object[] q1 : getList){
				 mapconvert1 = new HashMap<String,Object>();
				 mapconvert1.put("cedula",q1[0]);
				 mapconvert1.put("monto",q1[1]);
				 mapconvert1.put("idCuota",q1[2]);
				 mapconvert1.put("nombre",q1[3]);
				 mapconvert1.put("fechaPago",q1[4]);
				 montoTotal = montoTotal +(double) q1[1];
				 resultList1.add(mapconvert1);
			     }
			mapconvert.put("montoTotalCobrado", montoTotal);
			mapconvert.put("cobros", resultList1);
			resultList.add(mapconvert);
			objReturn.setData(resultList.get(0));
			objReturn.setMensaje("Exito");
			objReturn.setExito(Constantes.FLAG_EXITO_EXITO);
			objReturn.setTotal(mapconvert.size());
		}
		catch(Exception e) {
				objReturn.setData("");
				objReturn.setMensaje(e.getMessage());
				objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
				objReturn.setTotal(0);
			}
		
	}
	
}
