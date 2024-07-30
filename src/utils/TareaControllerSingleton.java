package utils;

import controller.TareaController;

public class TareaControllerSingleton {

	private static TareaControllerSingleton instance;
	private TareaController tareaController;

	private TareaControllerSingleton() {
		tareaController = new TareaController();
	}

	public static TareaControllerSingleton getInstance() {
		if (instance == null) {
			instance = new TareaControllerSingleton();
		}
		return instance;
	}

	public TareaController getTareaController() {
		return tareaController;
	}
}
