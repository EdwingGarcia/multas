package com.app.multas.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Multa {
	public static long diasExpiracion = 30;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_multa")
	private Long idMulta;

	@Column(name = "id_multado")
	private String idMultado;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "monto")
	private Double monto;

	@Column(name = "fecha_inicio")
	private LocalDateTime fechaInicio = null;

	@Column(name = "fecha_limite")
	private LocalDateTime fechaLimite = null;

	@Column(name = "lugar")
	private String lugar;

	@Column(name = "estado_pago")
	private Boolean estadoPago = false;

	public Multa() {
	}

	public Multa(String idMultado, String descripcion, Double monto, String lugar) {
		this.idMultado = idMultado;
		this.descripcion = descripcion;
		this.monto = monto;
		this.lugar = lugar;
	}

	public Long getIdMulta() {
		return idMulta;
	}

	public void setIdMulta(Long idMulta) {
		this.idMulta = idMulta;
	}

	public String getIdMultado() {
		return idMultado;
	}

	public void setIdMultado(String idMultado) {
		this.idMultado = idMultado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public static long getDiasExpiracion() {
		return diasExpiracion;
	}

	public static void setDiasExpiracion(long diasExpiracion) {
		Multa.diasExpiracion = diasExpiracion;
	}

	public LocalDateTime getfechaLimite() {
		return fechaLimite;
	}

	public void setfechaLimite(LocalDateTime fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Boolean getEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(Boolean estadoPago) {
		this.estadoPago = estadoPago;
	}
}
