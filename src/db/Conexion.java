package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static final String USER = "root";
	private static final String PASSWD = "";

	private static Conexion instance;
	private final Connection connection;

	private Conexion() throws SQLException, ClassNotFoundException {
		String URL = "jdbc:mysql://localhost:3306/manejador-tareas";
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = (Connection) DriverManager.getConnection(URL, USER, PASSWD);
	}

	public static Connection getInstance() {
		if (instance == null) {
			try {
				instance = new Conexion();
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		return instance.connection;
	}
}