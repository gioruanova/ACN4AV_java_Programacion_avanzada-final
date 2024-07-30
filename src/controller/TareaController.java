package controller;

import java.util.List;

import javax.swing.JOptionPane;

import model.Tarea;
import service.TareaService;

public class TareaController {

	private TareaService tareaService;

	public TareaController() {
		this.tareaService = new TareaService();
	}

	public List<Tarea> getAllTareas() {
		return tareaService.getAllTarea();
	}

	public void createTarea(Tarea t) {
		try {
			tareaService.createTarea(t);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteTarea(Tarea t) {
		try {
			tareaService.deleteTarea(t);
			JOptionPane.showMessageDialog(null, "Tarea borrada exitosamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateTarea(Tarea t) {
		try {
			tareaService.updateTarea(t);
			JOptionPane.showMessageDialog(null, "Tarea editada exitosamente.");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateTareaRapida(Tarea t) {
		try {
			tareaService.updateTareaEstado(t);
			JOptionPane.showMessageDialog(null, "Estado de la tarea actualizado exitosamente.");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
