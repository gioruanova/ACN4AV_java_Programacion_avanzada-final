package service;

import java.util.List;

import model.Tarea;

public interface TareaInterfaceService {

	List<Tarea> getAllTarea();

	void createTarea(Tarea t);

	void deleteTarea(Tarea t);

	void updateTarea(Tarea t);
	
	void updateTareaEstado(Tarea t);

}
