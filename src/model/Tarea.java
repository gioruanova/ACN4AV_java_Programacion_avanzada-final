package model;

import java.sql.Timestamp;

import utils.IdGenerator;

public class Tarea {
	private String id;
	private String nombre;
	private String descripcion;
	private TipoTarea tipoTarea;
	private boolean estado;
	private Timestamp fechaCreacion;
	private Timestamp fechaActualizacion;

	public Tarea(String nombre, String descripcion, TipoTarea tipoTarea) {
		super();
		this.id = IdGenerator.generateUUID();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoTarea = tipoTarea;
		this.estado = false;
		this.fechaCreacion = new Timestamp(System.currentTimeMillis());
		this.fechaActualizacion = new Timestamp(System.currentTimeMillis());
	}

	public Tarea() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoTarea getTipoTarea() {
		return tipoTarea;
	}

	public void setTipoTarea(TipoTarea tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Timestamp getFechaActualizacion() {
		return fechaActualizacion;
	}

	@Override
	public String toString() {
		return "[ID: " + id + " | Tarea: " + nombre + " | Tipo: " + tipoTarea + " | Estado: "
				+ (estado ? "Resuelta" : "Pendiente") + " | Fecha: " + fechaCreacion + " | Detalle: " + descripcion
				+ "]";
	}
}
