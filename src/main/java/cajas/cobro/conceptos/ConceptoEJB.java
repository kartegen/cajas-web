package cajas.cobro.conceptos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import cajas.persistence.query.ConceptoActivoClaveQuery;

@Stateless
public class ConceptoEJB {
	
	@Inject
	private ConceptoActivoClaveQuery conceptoActivoClaveQuery;

	protected void registrarConcepto(Concepto concepto) {
		// Validar que los datos requeridos no est�n vac�os
		validarConcepto(concepto);

		// Validar que no exista un concepto registrado activo con el ejericico
		// fiscal y la clave

		// Creas la entidad y la registras

	}

	protected List<Concepto> consultarConceptosPorClave(String clave) {
		// Validar que no venga vacia la clave y lanzar excepci�n si est� vac�a
		// Consultar todos los conceptos registrados con la clave especificada

		return null;
	}

	private void validarConcepto(Concepto concepto) {
		// Validaci�n campos, lanzar excepci�n, basarse en la validaci�n que
		// est� en el c�lculo estatal.
	}
}
