package cajas.actualizacionesrecargos.calculo;

import java.math.BigDecimal;

public class ContribucionFiscal {

	private Periodo periodoActualizacion;
	private Periodo periodoRecargo;
	private BigDecimal cantidadAdeuda;
	private Boolean aplicaActualizacion;
	private Boolean aplicaRecargos;

	/*********** Getters and Setters ***********/

	public BigDecimal getCantidadAdeuda() {
		return cantidadAdeuda;
	}

	public Periodo getPeriodoActualizacion() {
		return periodoActualizacion;
	}

	public void setPeriodoActualizacion(Periodo periodoActualizacion) {
		this.periodoActualizacion = periodoActualizacion;
	}

	public Periodo getPeriodoRecargo() {
		return periodoRecargo;
	}

	public void setPeriodoRecargo(Periodo periodoRecargo) {
		this.periodoRecargo = periodoRecargo;
	}

	public void setCantidadAdeuda(BigDecimal cantidadAdeuda) {
		this.cantidadAdeuda = cantidadAdeuda;
	}

	public Boolean getAplicaActualizacion() {
		return aplicaActualizacion;
	}

	public void setAplicaActualizacion(Boolean aplicaActualizacion) {
		this.aplicaActualizacion = aplicaActualizacion;
	}

	public Boolean getAplicaRecargos() {
		return aplicaRecargos;
	}

	public void setAplicaRecargos(Boolean aplicaRecargos) {
		this.aplicaRecargos = aplicaRecargos;
	}

}