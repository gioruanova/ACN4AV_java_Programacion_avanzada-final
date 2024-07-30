package db;

import java.util.List;

import model.Tarea;

public interface TareaInterfaceDAO {
	List<Tarea> findAll();

	void crearTarea(Tarea t);

	void borrarTarea(Tarea t);

	void updateTarea(Tarea t);
	
	void updateTareaEstado(Tarea t);

}
