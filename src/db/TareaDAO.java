package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Tarea;
import model.TipoTarea;

public class TareaDAO implements TareaInterfaceDAO {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	@Override
	public List<Tarea> findAll() {
		List<Tarea> tareaAux = new ArrayList<Tarea>();

		try {
			conn = Conexion.getInstance();
			String sql = "SELECT * FROM tareas";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Tarea t = new Tarea();
				t.setId(rs.getString("id"));
				t.setNombre(rs.getString("nombre"));
				t.setDescripcion(rs.getString("descripcion"));
				t.setEstado("true".equalsIgnoreCase(rs.getString("resuelta")) || "1".equals(rs.getString("resuelta")));
				t.setTipoTarea(TipoTarea.valueOf(rs.getString("tipoTarea")));
				t.setFechaCreacion(rs.getTimestamp("created_at"));
				t.setFechaActualizacion(rs.getTimestamp("updated_at"));
				tareaAux.add(t);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tareaAux;
	}

	@Override
	public void crearTarea(Tarea t) {
		String sql = String.format(
				"INSERT INTO tareas (id, nombre, descripcion, resuelta, tipoTarea, created_at, updated_at) VALUES ('%s','%s','%s','%s','%s','%s','%s')",
				t.getId(), t.getNombre(), t.getDescripcion(), 0, t.getTipoTarea(), t.getFechaCreacion(),
				t.getFechaActualizacion());

		try {
			conn = Conexion.getInstance();
			stmt = conn.prepareStatement(sql);
			stmt.execute(sql);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}

	@Override
	public void borrarTarea(Tarea t) {
		String sql = String.format("delete FROM tareas where id = ('%s')", t.getId());
		try {
			conn = Conexion.getInstance();
			stmt = conn.prepareStatement(sql);
			stmt.execute(sql);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}

	@Override
	public void updateTarea(Tarea t) {
		conn = Conexion.getInstance();

		try {
			String sql = "UPDATE tareas SET nombre = ?, descripcion = ?, tipoTarea = ?, resuelta = ?, updated_at = ? WHERE id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, t.getNombre());
			stmt.setString(2, t.getDescripcion());
			stmt.setString(3, t.getTipoTarea().toString());
			stmt.setBoolean(4, t.getEstado());
			stmt.setTimestamp(5, t.getFechaActualizacion());

			stmt.setString(6, t.getId());
			int filasAfectadas = stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void updateTareaEstado(Tarea t) {
		conn = Conexion.getInstance();

		try {
			String sql = "UPDATE tareas SET resuelta = ?, updated_at = ? WHERE id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setBoolean(1, t.getEstado());
			stmt.setTimestamp(2, t.getFechaActualizacion());

			stmt.setString(3, t.getId());
			int filasAfectadas = stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
}
