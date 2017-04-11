package cajas.impuestos.declaracion.estatal;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cajas.actualizacionesrecargos.calculo.ActualizacionRecargo;
import cajas.actualizacionesrecargos.calculo.ActualizacionesRecargosService;
import cajas.actualizacionesrecargos.calculo.ContribucionFiscal;
import cajas.exception.BusinessException;
import cajas.impuestos.calculo.CalculoImpuestoService;
import cajas.persistence.entity.CalculoTemporalEstatalEntity;
import cajas.persistence.entity.ContribuyenteEntity;
import cajas.util.FechaUtil;

public class CalculoEstatalService {
	@PersistenceContext(name = "sitDS")
	private EntityManager entityManager;

	@Inject
	private ActualizacionesRecargosService actualizacionesRecargosService;

	@Inject
	private CalculoImpuestoService calculoImpuestoService;

	protected ImpuestoEstatal calcularImpuesto(DeclaracionEstatal declaracion) {
		Integer idUsuarioLogeado = null;// Obtener al usuario logeado

		// Validar datos requeridos
		validarDeclaracion(declaracion);

		// Validar contribuyente, asignaci�n obligacion y sucursales
		validarAsignacion(declaracion.getIdContribuyente(), declaracion.getIdSucursal(), declaracion.getIdObligacion());

		// Validar periodo declarado
		int ejercicioFiscalDeclaracion = declaracion.getEjercicioFiscal();
		int mesDeclaracion = 0; // obtener el mes del periodo
		int mesActual = FechaUtil.mesActual();

		if (ejercicioFiscalDeclaracion == FechaUtil.ejercicioActual()) {
			if (mesActual < mesDeclaracion || mesActual == mesDeclaracion) {
				throw new BusinessException("El periodo que intenta declarar es improcedente");
			}
		}

		// Verificar los tipos de datos
		BigDecimal impuesto = calculoImpuestoService.impuestoEstatal(declaracion.getTotalErogaciones(), "",
				declaracion.getEjercicioFiscal(), "", null);

		BigDecimal uaz = calculoImpuestoService.impuestoEstatal(impuesto, "", declaracion.getEjercicioFiscal(), "",
				null);
		ContribucionFiscal contribucionFiscal = new ContribucionFiscal();
		contribucionFiscal.setaFiscalAdeudo(declaracion.getEjercicioFiscal());

		ActualizacionRecargo actualizacionRecargo = actualizacionesRecargosService
				.calculoActualizacion(contribucionFiscal);
		BigDecimal actualizaciones = actualizacionRecargo.getImporteActualizacion();
		BigDecimal recargos = actualizacionRecargo.getImporteRecargo();

		BigDecimal total = impuesto.add(uaz).add(actualizaciones).add(recargos);

		CalculoTemporalEstatalEntity calculoTemporal = new CalculoTemporalEstatalEntity();
		calculoTemporal.setActualizaciones(actualizaciones);
		calculoTemporal.setBaseGravable(declaracion.getTotalErogaciones());
		calculoTemporal.setEjercicioFiscal(declaracion.getEjercicioFiscal());
		calculoTemporal.setFechaCalculo(FechaUtil.fechaActual());
		calculoTemporal.setIdContribuyente(declaracion.getIdContribuyente());
		calculoTemporal.setIdObligacion(declaracion.getIdObligacion());
		calculoTemporal.setIdPeriodo(declaracion.getPeriodo());
		calculoTemporal.setIdSucursal(declaracion.getIdSucursal());
		calculoTemporal.setIdUsuario(idUsuarioLogeado);
		calculoTemporal.setImpuesto(impuesto);
		calculoTemporal.setNumeroEmpleados(declaracion.getNumeroEmpleados());
		calculoTemporal.setRecargos(recargos);
		calculoTemporal.setTipoDeclaracion(declaracion.getIdTipoDeclaracion());
		calculoTemporal.setTotal(total);
		calculoTemporal.setUaz(uaz);
		entityManager.persist(calculoTemporal);

		ImpuestoEstatal impuestoEstatal = new ImpuestoEstatal();
		impuestoEstatal.setIdCalculoTemporal(calculoTemporal.getIdCalculoTemporal());
		return impuestoEstatal;
	}

	private void validarDeclaracion(DeclaracionEstatal declaracion) {

	}

	private void validarAsignacion(Integer idContribuyente, Integer idSucursal, Integer idObligacion) {
		ContribuyenteEntity contribuyente = null;// Agregar consulta
													// contribuyente por id

		// validar estatus del contribuyente lanzar excepcion si no est� activo

		// Consultar que el contribuyente tenga la obligaci�n asignada activa.

		// Consultar que tenga sucursales activas en la obligaci�n

	}

}
