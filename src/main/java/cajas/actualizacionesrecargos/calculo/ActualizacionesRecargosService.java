package cajas.actualizacionesrecargos.calculo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import cajas.exception.BusinessException;
import cajas.persistence.entity.INPCEntity;
import cajas.persistence.query.INPCQuery;
import cajas.util.FechaUtil;

public class ActualizacionesRecargosService {

	@Inject
	private INPCQuery inpcQuery;

	/************************************* ACTUALIZACIONES *************************************/

	/************* Calcula el importe de la actualizaci�n **********/
	public ActualizacionRecargo calculoActualizacion(ContribucionFiscal contribucionFiscal) {

		ActualizacionRecargo actualizacionRecargo = new ActualizacionRecargo();
		BigDecimal actualizacion = BigDecimal.ZERO;
		BigDecimal inpcAnterior = BigDecimal.ZERO;
		BigDecimal inpcActual = BigDecimal.ZERO;
		BigDecimal porcentajeRecargo = BigDecimal.ZERO;
		BigDecimal importeRecargo = BigDecimal.ZERO;
		Boolean aplicaRecargo = Boolean.FALSE;

		/*******
		 * Obtengo el INPC del mes anterior en el que se debio efectuar el pago
		 * 
		 */
		inpcAnterior = obtenerINPC(contribucionFiscal.getaFiscalAdeudo(), contribucionFiscal.getMesFiscalAdeudo());

		/*******
		 * Obtengo el INPC del mes anterior en el que se va a realizar el pago
		 * 
		 */
		contribucionFiscal.setMesFiscalPago(FechaUtil.mesActual() - 1);
		inpcActual = obtenerINPC(FechaUtil.ejercicioActual(), contribucionFiscal.getMesFiscalPago());

		/***
		 * Si el INPC del mes en el que se va a efectuar el pago no devuelve
		 * ningun resultado obtengo el INPC del mes anterior al solicitado
		 * inicialmente
		 */
		if (inpcActual == null) {
			contribucionFiscal.setMesFiscalPago(contribucionFiscal.getMesFiscalPago() - 1);
			inpcActual = obtenerINPC(contribucionFiscal.getaFiscalPago(), contribucionFiscal.getMesFiscalPago());
		}

		/***
		 * Bien ahora que ya tenemos los dos INPC, procedemos a realizar el
		 * calculo del factor de actualizaci�n
		 */

		BigDecimal factorActualizacion = BigDecimal.ZERO;
		factorActualizacion = factorActualizacion(inpcAnterior, inpcActual);

		/*****
		 * Factor de actualizaci�n Si el factor de actualizaci�n es menor a 1 se
		 * toma como factor 1
		 */
		factorActualizacion = (factorActualizacion.compareTo(BigDecimal.ONE) == -1) ? BigDecimal.ONE
				: factorActualizacion;

		/*****
		 * Ahora si calculamos el monto de la actualizaci�n multiplicando el
		 * importe recibido por el factor de la actualizaci�n
		 */
		actualizacion = contribucionFiscal.getCantidadAdeuda().multiply(factorActualizacion).setScale(0,
				RoundingMode.UP);

		/*****
		 * Calculo del recargo
		 * 
		 */
		importeRecargo = calculoRecargo(contribucionFiscal.getaFiscalAdeudo(), contribucionFiscal.getMesFiscalAdeudo(),
				contribucionFiscal.getaFiscalPago(), contribucionFiscal.getMesFiscalPago(),
				contribucionFiscal.getPagoVencido(), actualizacion, contribucionFiscal.getTipoRecargo());

		/***
		 * Set Valores calculados
		 * 
		 */
		actualizacionRecargo.setInpcInicio(inpcAnterior);
		actualizacionRecargo.setInpcFinal(inpcActual);
		actualizacionRecargo.setFactorActualizacion(factorActualizacion);

		/****
		 * Si el factor de actualizaci�n es menor a uno solo se pagan recargos
		 */
		if (factorActualizacion.compareTo(BigDecimal.ONE) == -1) {
			actualizacionRecargo.setImporteActualizacion(null);
		} else {
			actualizacionRecargo.setImporteActualizacion(actualizacion);
		}
		actualizacionRecargo.setPorcentajeRecargo(porcentajeRecargo);
		actualizacionRecargo.setImporteRecargo(importeRecargo);
		actualizacionRecargo.setAplicaRecargo(aplicaRecargo);

		return actualizacionRecargo;
	}

	/********** Calculo factor de la actualizaci�n **********/
	private BigDecimal factorActualizacion(BigDecimal inpcAnterior, BigDecimal inpcActual) {
		BigDecimal factorActualizacion = BigDecimal.ZERO;

		factorActualizacion = inpcActual.divide(inpcAnterior, 4, RoundingMode.DOWN);
		return factorActualizacion;
	}

	/********** Obtener INPC *************/
	private BigDecimal obtenerINPC(Integer aFiscal, Integer mesFiscal) {
		try {
			INPCEntity inpcEntity = inpcQuery.inpcEntity(aFiscal, mesFiscal);
			return inpcEntity.getInpc();
		} catch (NoResultException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/************************************* RECARGOS *************************************/

	/********* Calculo del recargo ***********/
	private BigDecimal calculoRecargo(Integer aFiscalInicio, Integer mesFiscalInicio, Integer aFiscalFinal,
			Integer mesFiscalFinal, Boolean vencioPago, BigDecimal importeActualizacion, String tipoRecargo) {

		BigDecimal recargo = BigDecimal.ZERO;
		try {

			recargo = obtenerTasaRecargo(aFiscalInicio, mesFiscalInicio, aFiscalFinal, mesFiscalFinal, vencioPago,
					tipoRecargo);
			recargo.setScale(2, RoundingMode.HALF_EVEN);

		} catch (NoResultException ex) {
			ex.printStackTrace();
			throw new BusinessException("Ocurrio un problema no se encontrar�n resultados.");
		}
		BigDecimal montoRecargo = BigDecimal.ZERO;

		montoRecargo = importeActualizacion.multiply(recargo);

		montoRecargo = montoRecargo.divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);

		return montoRecargo;
	}

	/********** Obtener INPC *************/
	private BigDecimal obtenerTasaRecargo(Integer aFiscalInicio, Integer mesFiscalInicio, Integer aFiscalFinal,
			Integer mesFiscalFinal, Boolean vencioPago, String tipoRecargo) {

		List<INPCEntity> inpcEntity = inpcQuery.listaINPC(aFiscalInicio, mesFiscalInicio, aFiscalFinal, mesFiscalFinal,
				vencioPago);
		BigDecimal tasaRecargo = BigDecimal.ZERO;
		for (INPCEntity inpc : inpcEntity) {
			if (tipoRecargo.equals("MORA")) {
				tasaRecargo = tasaRecargo.add(inpc.getRecargoMora());
			} else if (tipoRecargo.equals("PRORROGA")) {
				tasaRecargo = tasaRecargo.add(inpc.getRecargoProrroga());
			}
		}
		return tasaRecargo;
	}

}