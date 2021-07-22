package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.google.gson.JsonObject;

import Utilidades.Constantes;
import model.ObjectReturn;

import persist.JPAEntity;

@SuppressWarnings("rawtypes")
public class PrestamoDAO extends JPAEntity {
	public PrestamoDAO() {
		super();
	}
	
	public void getPrestamo(ObjectReturn objReturn) {
		JsonObject objJsonObject;
		objJsonObject = (JsonObject) objReturn.getData();
		String zona = objJsonObject.get("zona").getAsString();
		
		String query = "select cc.venc_permitido, cc.ld_permitida, cc.id as id_cliente, p.id, p.observaciones, CEIL((p.monto * p.porcentaje_interes / 100) + p.monto + IFNULL(p.porcentaje_mora,0)) as monto, p.cuotas, CEIL((monto_cuota - IFNULL(sum(pp.monto_pagado),0))) as adeudado,"
				+ "               cc.cedula, c.id as id_cuota, c.numero, cc.nombre, cc.apellidos, IFNULL(c.monto_cuota - sum(pp.monto_pagado), c.monto_cuota) as monto_cuota, DATE_FORMAT(p.fecha_vencimiento, '%Y-%d-%m') as fecha_vencimiento, monto_total from prestamo p"
				+ "             inner join cuota c"
				+ "                on c.prestamo = p.id"
				+ "                left join pago pp"
				+ "               on c.id = pp.cuota"
				+ "                inner join cliente cc"
				+ "               on cc.id = p.cliente"
				+ "                where cc.cedula IN(Select cedula from cliente where zona IN("+zona+ ")) and p.pagado = 0 and c.pagada = 0 group by p.id";
		try {
			objReturn.setData(super.findAllNative(query));
			
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
	@SuppressWarnings("unchecked")
	public void getPrestamoCliente(ObjectReturn objReturn) {
		JsonObject objJsonObject;
		objJsonObject = (JsonObject) objReturn.getData();
		String cedula = objJsonObject.get("cedula").getAsString();
		
		String query = "select cc.venc_permitido, cc.ld_permitida, cc.id as id_cliente, p.id, p.observaciones, CEIL((p.monto * p.porcentaje_interes / 100) + p.monto + IFNULL(p.porcentaje_mora,0)) as monto, p.cuotas, CEIL((monto_cuota - IFNULL(sum(pp.monto_pagado),0))) as adeudado,"
				+ "               cc.cedula, c.id as id_cuota, c.numero, cc.nombre, cc.apellidos, IFNULL(c.monto_cuota - sum(pp.monto_pagado), c.monto_cuota) as monto_cuota, DATE_FORMAT(p.fecha_vencimiento, '%Y-%d-%m') as fecha_vencimiento, monto_total from prestamo p"
				+ "             inner join cuota c"
				+ "                on c.prestamo = p.id"
				+ "                left join pago pp"
				+ "               on c.id = pp.cuota"
				+ "                inner join cliente cc"
				+ "               on cc.id = p.cliente"
				+ "                where cc.cedula="+cedula+ " and p.pagado = 0 and c.pagada = 0 group by p.id";
		try {
			
			
			HashMap<String, Object> mapconvert;
			Vector <HashMap> resultList = new Vector<HashMap>();
			List<Object[]> getList  = super.findAllNative(query);
			for(Object[] q1 : getList){
				 mapconvert = new HashMap<String,Object>();
				 mapconvert.put("venc_permitido",q1[0]);
				 mapconvert.put("ld_permitida",q1[1]);
				 mapconvert.put("id_cliente",q1[2]);
				 mapconvert.put("id",q1[3]);
				 mapconvert.put("observaciones",q1[4]);
				 mapconvert.put("monto",q1[5]);
				 mapconvert.put("cuotas",q1[6]);
				 mapconvert.put("adeudado",q1[7]);
				 mapconvert.put("cedula",q1[8]);
				 mapconvert.put("id_cuota",q1[9]);
				 mapconvert.put("numero",q1[10]);
				 mapconvert.put("nombre",q1[11]);
				 mapconvert.put("apellidos",q1[12]);
				 mapconvert.put("monto_cuota",q1[13]);
				 mapconvert.put("fecha_vencimiento",q1[14]);
				 mapconvert.put("monto_total",q1[15]);
				 resultList.add(mapconvert);
			     }
			objReturn.setData(resultList);
			objReturn.setMensaje("Exito");
			objReturn.setExito(Constantes.FLAG_EXITO_EXITO);
			objReturn.setTotal(resultList.size());
		}
		catch(Exception e) {
				objReturn.setData("");
				objReturn.setMensaje(e.getMessage());
				objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
				objReturn.setTotal(0);
			}
		
	}
	
}
