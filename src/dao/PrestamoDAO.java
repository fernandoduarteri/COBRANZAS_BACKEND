package dao;

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
	
}
