package service;

import java.util.List;

import db.TareaDAO;
import model.Tarea;

public class TareaService implements TareaInterfaceService {

	private TareaDAO tareaDAO;

	public TareaService() {
		this.tareaDAO = new TareaDAO();
	}

	@Override
	public List<Tarea> getAllTarea() {
		return this.tareaDAO.findAll();
	}

	@Override
	public void createTarea(Tarea t) {
		if (t == null) {
			throw new RuntimeException("Instancia de tarea no puede ser nula");
		}
		this.tareaDAO.crearTarea(t);

	}

	@Override
	public void deleteTarea(Tarea t) {
		if (t == null) {
			throw new RuntimeException("Instancia de tarea no puede ser nula");
		}
		this.tareaDAO.borrarTarea(t);
	}

	@Override
	public void updateTarea(Tarea t) {
		if (t == null) {
			throw new RuntimeException("Instancia de tarea no puede ser nula");
		}
		this.tareaDAO.updateTarea(t);

	}

	@Override
	public void updateTareaEstado(Tarea t) {
		if (t == null) {
			throw new RuntimeException("Instancia de tarea no puede ser nula");
		}
		this.tareaDAO.updateTarea(t);		
	}

}
