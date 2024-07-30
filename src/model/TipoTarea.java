package model;

public enum TipoTarea {
	RECORDATORIO("R"), VENCIMIENTO("V");

	private final String code;

	TipoTarea(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
