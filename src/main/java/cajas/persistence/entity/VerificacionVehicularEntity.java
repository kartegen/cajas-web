package cajas.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "verificaciones_vehiculo", schema = "vehicular")
public class VerificacionVehicularEntity implements Serializable{

	private static final long serialVersionUID = 4520026756457631203L;
	
	@Id
	@Column( name = "id_verificacion_vehiculo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVerificacionVehiculo;
	
	@Column( name = "ejercicio" )
	private Integer ejercicio;
	
	@Column( name = "no_seguimiento_verificacion" )
	private Integer noSeguimientoVerificacion;
	
	@Column( name = "fecha_verificacion" )
	private Date fechaVerificacion;
	
	@Column( name = "tipo_verificacion" )
	private Integer tipoVerificacion;
	
	@Column( name = "vin_vehiculo" )
	private String vinVehiculo;
	
	@Column( name = "id_marca_vehiculo" )
	private Integer idMarcaVehiculo;
	
	@Column( name = "modelo_vehiculo" )
	private Integer modeloVehiculo;
	
	@Column( name = "id_clase_vehiculo" )
	private Integer idClaseVehiculo;
	
	@Column( name = "id_tipo_vehiculo" )
	private Integer idTipoVehiculo;
	
	@Column( name = "linea_vehiculo" )
	private String lineaVehiculo;
	
	@Column( name = "estatus_verificacion" )
	private Integer estatusVerificacion;
	
	@Column( name = "factura_vehiculo_documentacion" )
	private Integer facturaVehiculoDocumentacion;
	
	@Column( name = "identificacion_oficial_documentacion" )
	private Integer identificacionOficialDocumentacion;
	
	@Column( name = "comprobante_domicilio_documentacion" )
	private Integer comprobanteDomicilioDocumentacion;
	
	@Column( name = "rfc_persona_moral_documentacion" )
	private Integer rfcPersonaMoralDocumentacion;
	
	@Column( name = "identificacion_representante_legal_documentacion" )
	private Integer identificacionRepresentanteLegalDocumentacion;
	
	@Column( name = "nombre_persona_verificacion" )
	private String nombrePersonaVerificacion;
	
	@Column( name = "apellido_paterno_persona_verificacion" )
	private String apellidoPaternoPersonaVerificacion;
	
	@Column( name = "apellido_materno_persona_verificacion" )
	private String apellidoMaternoPersonaVerificacion;
	
	@Column( name = "numero_motor_vehiculo" )
	private String numeroMotorVehiculo;
	
	@Column( name = "id_oficina_verificacion" )
	private Integer idOficinaVerificacion;
	
	@Column( name = "anio1_comprobante_pago" )
	private Integer anio1ComprobantePago;

	@Column( name = "anio2_comprobante_pago" )
	private Integer anio2ComprobantePago;

	@Column( name = "anio3_comprobante_pago" )
	private Integer anio3ComprobantePago;
	
	@Column( name = "anio4_comprobante_pago" )
	private Integer anio4ComprobantePago;	
	
	@Column( name = "anio5_comprobante_pago" )
	private Integer anio5ComprobantePago;
	
	@Column( name = "anio_actual_comprobante_pago" )
	private Integer anioActualComprobantePago;

	public Integer getIdVerificacionVehiculo() {
		return idVerificacionVehiculo;
	}

	public void setIdVerificacionVehiculo(Integer idVerificacionVehiculo) {
		this.idVerificacionVehiculo = idVerificacionVehiculo;
	}

	public Integer getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
	}

	public Integer getNoSeguimientoVerificacion() {
		return noSeguimientoVerificacion;
	}

	public void setNoSeguimientoVerificacion(Integer noSeguimientoVerificacion) {
		this.noSeguimientoVerificacion = noSeguimientoVerificacion;
	}

	public Date getFechaVerificacion() {
		return fechaVerificacion;
	}

	public void setFechaVerificacion(Date fechaVerificacion) {
		this.fechaVerificacion = fechaVerificacion;
	}

	public Integer getTipoVerificacion() {
		return tipoVerificacion;
	}

	public void setTipoVerificacion(Integer tipoVerificacion) {
		this.tipoVerificacion = tipoVerificacion;
	}

	public String getVinVehiculo() {
		return vinVehiculo;
	}

	public void setVinVehiculo(String vinVehiculo) {
		this.vinVehiculo = vinVehiculo;
	}

	public Integer getIdMarcaVehiculo() {
		return idMarcaVehiculo;
	}

	public void setIdMarcaVehiculo(Integer idMarcaVehiculo) {
		this.idMarcaVehiculo = idMarcaVehiculo;
	}

	public Integer getModeloVehiculo() {
		return modeloVehiculo;
	}

	public void setModeloVehiculo(Integer modeloVehiculo) {
		this.modeloVehiculo = modeloVehiculo;
	}

	public Integer getIdClaseVehiculo() {
		return idClaseVehiculo;
	}

	public void setIdClaseVehiculo(Integer idClaseVehiculo) {
		this.idClaseVehiculo = idClaseVehiculo;
	}

	public Integer getIdTipoVehiculo() {
		return idTipoVehiculo;
	}

	public void setIdTipoVehiculo(Integer idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
	}

	public String getLineaVehiculo() {
		return lineaVehiculo;
	}

	public void setLineaVehiculo(String lineaVehiculo) {
		this.lineaVehiculo = lineaVehiculo;
	}

	public Integer getEstatusVerificacion() {
		return estatusVerificacion;
	}

	public void setEstatusVerificacion(Integer estatusVerificacion) {
		this.estatusVerificacion = estatusVerificacion;
	}

	public Integer getFacturaVehiculoDocumentacion() {
		return facturaVehiculoDocumentacion;
	}

	public void setFacturaVehiculoDocumentacion(Integer facturaVehiculoDocumentacion) {
		this.facturaVehiculoDocumentacion = facturaVehiculoDocumentacion;
	}

	public Integer getIdentificacionOficialDocumentacion() {
		return identificacionOficialDocumentacion;
	}

	public void setIdentificacionOficialDocumentacion(Integer identificacionOficialDocumentacion) {
		this.identificacionOficialDocumentacion = identificacionOficialDocumentacion;
	}

	public Integer getComprobanteDomicilioDocumentacion() {
		return comprobanteDomicilioDocumentacion;
	}

	public void setComprobanteDomicilioDocumentacion(Integer comprobanteDomicilioDocumentacion) {
		this.comprobanteDomicilioDocumentacion = comprobanteDomicilioDocumentacion;
	}

	public Integer getRfcPersonaMoralDocumentacion() {
		return rfcPersonaMoralDocumentacion;
	}

	public void setRfcPersonaMoralDocumentacion(Integer rfcPersonaMoralDocumentacion) {
		this.rfcPersonaMoralDocumentacion = rfcPersonaMoralDocumentacion;
	}

	public Integer getIdentificacionRepresentanteLegalDocumentacion() {
		return identificacionRepresentanteLegalDocumentacion;
	}

	public void setIdentificacionRepresentanteLegalDocumentacion(Integer identificacionRepresentanteLegalDocumentacion) {
		this.identificacionRepresentanteLegalDocumentacion = identificacionRepresentanteLegalDocumentacion;
	}

	public String getNombrePersonaVerificacion() {
		return nombrePersonaVerificacion;
	}

	public void setNombrePersonaVerificacion(String nombrePersonaVerificacion) {
		this.nombrePersonaVerificacion = nombrePersonaVerificacion;
	}

	public String getApellidoPaternoPersonaVerificacion() {
		return apellidoPaternoPersonaVerificacion;
	}

	public void setApellidoPaternoPersonaVerificacion(String apellidoPaternoPersonaVerificacion) {
		this.apellidoPaternoPersonaVerificacion = apellidoPaternoPersonaVerificacion;
	}

	public String getApellidoMaternoPersonaVerificacion() {
		return apellidoMaternoPersonaVerificacion;
	}

	public void setApellidoMaternoPersonaVerificacion(String apellidoMaternoPersonaVerificacion) {
		this.apellidoMaternoPersonaVerificacion = apellidoMaternoPersonaVerificacion;
	}

	public String getNumeroMotorVehiculo() {
		return numeroMotorVehiculo;
	}

	public void setNumeroMotorVehiculo(String numeroMotorVehiculo) {
		this.numeroMotorVehiculo = numeroMotorVehiculo;
	}
	public Integer getIdOficinaVerificacion() {
		return idOficinaVerificacion;
	}

	public void setIdOficinaVerificacion(Integer idOficinaVerificacion) {
		this.idOficinaVerificacion = idOficinaVerificacion;
	}
	
	public Integer getAnio1ComprobantePago() {
		return anio1ComprobantePago;
	}

	public void setAnio1ComprobantePago(Integer anio1ComprobantePago) {
		this.anio1ComprobantePago = anio1ComprobantePago;
	}

	public Integer getAnio2ComprobantePago() {
		return anio2ComprobantePago;
	}

	public void setAnio2ComprobantePago(Integer anio2ComprobantePago) {
		this.anio2ComprobantePago = anio2ComprobantePago;
	}

	public Integer getAnio3ComprobantePago() {
		return anio3ComprobantePago;
	}

	public void setAnio3ComprobantePago(Integer anio3ComprobantePago) {
		this.anio3ComprobantePago = anio3ComprobantePago;
	}

	public Integer getAnio4ComprobantePago() {
		return anio4ComprobantePago;
	}

	public void setAnio4ComprobantePago(Integer anio4ComprobantePago) {
		this.anio4ComprobantePago = anio4ComprobantePago;
	}

	public Integer getAnio5ComprobantePago() {
		return anio5ComprobantePago;
	}

	public void setAnio5ComprobantePago(Integer anio5ComprobantePago) {
		this.anio5ComprobantePago = anio5ComprobantePago;
	}

	public Integer getAnioActualComprobantePago() {
		return anioActualComprobantePago;
	}

	public void setAnioActualComprobantePago(Integer anioActualComprobantePago) {
		this.anioActualComprobantePago = anioActualComprobantePago;
	}

	
	

}
