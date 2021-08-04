package services;

import java.util.HashMap;

import Utilidades.Constantes;
import dao.PagoDAO;
import dao.PrestamoDAO;
import model.Cuota;
import model.ObjectReturn;
import model.Pago;

public class PagoServices {
PagoDAO objPagoDao = new PagoDAO (Pago.class);
	
	public void hacerPago(ObjectReturn objReturn) throws Exception{
		Pago pago = (Pago) objReturn.getData();
		ObjectReturn axObjReturn = new ObjectReturn();
		Integer veces=0;
		String retorno= "";
		double saldo = 0;
		int idPrestamo = Integer.valueOf( pago.getCuotaBean().getPrestamoBean().getId());
		if(pago.getMontoPagado()>pago.getCuotaBean().getMontoCuota()) {
			// Aca hay que tomar en consideracion que si la cuota que viene del frontEnd es un saldo que no corresponde a la cuota normal del prestamo
			// lo cual nos daria unas iteraciones falsas, para pagar ese saldo se debe de sumar esa iteracion al contador
			// Dado que si hay un saldo, al consultar esa cuot en especifico no nos dice la consulta si la cuota normal es mayor, por lo tanto se tiene que buscar el prestamo y verificar
			axObjReturn.setData(idPrestamo);
			getPrestamo(axObjReturn);
			HashMap<String,Object> resultPrestamo = (HashMap<String, Object>) axObjReturn.getData();
			double montoTotal = (double) resultPrestamo.get("monto_total");
			Integer cuotas = (Integer) resultPrestamo.get("cuotas");
			double cuota = montoTotal / cuotas;
			if((montoTotal / cuotas ) == pago.getCuotaBean().getMontoCuota()) {
				//El montoCuota que viene del frontEnd no es un saldo, por tanto se puede calcular el numero de veces como sigue
				veces = (int) Math.ceil( pago.getMontoPagado() / pago.getCuotaBean().getMontoCuota());
				
			}else {
				//El montoCuota que viene del frontEnd es un saldo y el numero de veces se tiene que calcular como sigue
				veces = (int) Math.ceil(1 + (pago.getMontoPagado()- pago.getCuotaBean().getMontoCuota())/cuota);
				
			}
			
			for (int i = 1; i <= veces; i++) {
				axObjReturn = new ObjectReturn();
				Pago pagoMultiple = new Pago();
				pagoMultiple.setUsuario(pago.getUsuario());
				Cuota cuotaMultiple = new Cuota();
				if(i == 1) { //El idcuota es el que viene del frontEnd
					cuotaMultiple.setId(pago.getCuotaBean().getId());
					pagoMultiple.setCuotaBean(cuotaMultiple); // se envia el idCuota que viene del frontend
					pagoMultiple.setMontoPagado(pago.getCuotaBean().getMontoCuota()); // se paga el valor de la cuota que viene del front end, sea cuota norma o saldo
					axObjReturn.setData(idPrestamo);
					getPrestamo(axObjReturn);
					resultPrestamo = (HashMap<String, Object>) axObjReturn.getData();
					axObjReturn.setData(pagoMultiple);
					objPagoDao.hacerPago(axObjReturn);
					saldo = pago.getMontoPagado() - pago.getCuotaBean().getMontoCuota();
					retorno = retorno + "Cuota "+ resultPrestamo.get("numero") +"/" + resultPrestamo.get("cuotas") + " $"+pago.getCuotaBean().getMontoCuota() +"\n";
					continue;
				}
				
				if(i== veces) {
					
					axObjReturn.setData(idPrestamo);
					getPrestamo(axObjReturn);
					resultPrestamo = (HashMap<String, Object>) axObjReturn.getData();
					cuotaMultiple.setId(String.valueOf( resultPrestamo.get("id_cuota"))); //Se obtiene la sigueinte cuoat a pagar
					pagoMultiple.setCuotaBean(cuotaMultiple);
					double montoPagado = saldo;
					pagoMultiple.setMontoPagado(montoPagado); // se paga el valor del remanente del montoPagado
					axObjReturn.setData(pagoMultiple);
					objPagoDao.hacerPago(axObjReturn);
					retorno = retorno + "Cuota "+ resultPrestamo.get("numero") +"/" + resultPrestamo.get("cuotas") + " $"+montoPagado +"\n"+ axObjReturn.getData() + "\n";
					objReturn.setData(retorno);
					objReturn.setMensaje("Exito");
					objReturn.setExito(Constantes.FLAG_EXITO_EXITO);
					objReturn.setTotal(1);
				}else {
					axObjReturn.setData(idPrestamo);
					getPrestamo(axObjReturn);
					resultPrestamo = (HashMap<String, Object>) axObjReturn.getData();
					cuotaMultiple.setId(String.valueOf(resultPrestamo.get("id_cuota"))); //Se obtiene la sigueinte cuoat a pagar
					pagoMultiple.setCuotaBean(cuotaMultiple);
					pagoMultiple.setMontoPagado(cuota); // se paga el valor de la cuota real
					axObjReturn.setData(pagoMultiple);
					objPagoDao.hacerPago(axObjReturn);
					saldo = saldo - cuota;
					retorno = retorno + "Cuota "+ resultPrestamo.get("numero") +"/" + resultPrestamo.get("cuotas") + " $"+cuota +"\n";
				}
			}
		}else {
			objPagoDao.hacerPago(objReturn);	
		}
	}
	
	public void getPrestamo(ObjectReturn objReturn) {
		PrestamoDAO objPrestamoDao = new PrestamoDAO();
		objPrestamoDao.getPrestamoUnicoCliente(objReturn);
	}
}
