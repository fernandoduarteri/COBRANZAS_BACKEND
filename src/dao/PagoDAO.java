package dao;



import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import Utilidades.Constantes;
import model.Cuota;
import model.ObjectReturn;
import model.Pago;
import persist.JPAEntity;

public class PagoDAO extends JPAEntity<Pago>{
	
	public PagoDAO(Class<Pago> entityClass) {
		super(entityClass);
	}
	
	public void hacerPago(ObjectReturn objReturn) {
		Pago pago = (Pago)objReturn.getData();
		try {
			super.create(pago);

			String query = "select fecha_vencimiento, CURDATE() as fecha_actual from cuota where pagada = 0 and prestamo = (select prestamo from cuota where id = "+pago.getCuotaBean().getId()+") limit 1";
			
			HashMap<String, Object> mapconvert;
			
			List<Object[]> getList  = super.findAllNative(query);

			int numberOfDays = 0;
			for(Object[] q1 : getList){
				 Calendar cal1 = Calendar.getInstance();
				 Calendar cal2 = Calendar.getInstance();
				    cal1.setTime((Date) q1[0]);
				    cal2.setTime((Date) q1[1]);

				    while (cal1.before(cal2)) {
				        if (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK)) {
				            numberOfDays++;
				        }
				        cal1.add(Calendar.DATE,1);
				    }

			}
			
			if(numberOfDays != 0)
				objReturn.setData("Pago realizado con exito \n Tiene " + numberOfDays + " dias de atraso");
			else
				objReturn.setData("Pago realizado con exito \n Sus pagos estan al dia");
			objReturn.setMensaje("Exito");
			objReturn.setExito(Constantes.FLAG_EXITO_EXITO);
			objReturn.setTotal(1);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
				objReturn.setData("");
				objReturn.setMensaje(e.getMessage());
				objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
				objReturn.setTotal(0);
			}
		
	}

}
