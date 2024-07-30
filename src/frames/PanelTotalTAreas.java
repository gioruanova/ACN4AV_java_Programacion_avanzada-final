package frames;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TareaController;
import model.Tarea;
import utils.ButtonHoverEffect;
import utils.TareaControllerSingleton;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.awt.event.ActionEvent;
import java.awt.List;
import javax.swing.JLabel;
import java.awt.Font;

public class PanelTotalTAreas extends JFrame {

	private static final long serialVersionUID = 1L;
	private TareaController tc;

	private JPanel contentPane;
	private final String dogeNormal = "/assets/icon-app.png";
	private List listaTareas = new List();
	private JButton btnBorrarTarea = new JButton("BORRAR");
	private JButton btnEditarTarea = new JButton("EDITAR");
	private JButton btnVolverMain = new JButton("VOLVER");
	JButton btnEstadoUpdate = new JButton("UPDATE");

	JLabel labelTotalTareas = new JLabel("Total tareas registradas: ");
	JLabel totalTareasConteo = new JLabel("0");

	private Tarea tareaSeleccionada = null;
	private Tarea tareaAuxiliar;
	private boolean tareaAActualizar;

	public static void main(String[] args) {
		generarPanel();
	}

	public static void generarPanel() {
		PanelTotalTAreas frame = new PanelTotalTAreas();
		frame.setVisible(true);
	}

	public PanelTotalTAreas() {
		tc = TareaControllerSingleton.getInstance().getTareaController();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Listado de tareas");
		setIconImage(new ImageIcon(getClass().getResource(dogeNormal)).getImage());
		listaTareas.setBounds(10, 10, 389, 261);
		listaTareas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(listaTareas);
		agregarBotones();
		volverMenuPrincipal();
		mostrarTareas();
		elegirTarea();
		borrarTarea();
		editarTarea();
		editarEstadoRapido();
		setLocationRelativeTo(null);
	}

	private void actualizarBotonTexto() {
		if (!(tareaSeleccionada.getEstado())) {
			btnEstadoUpdate.setVisible(true);
			btnEstadoUpdate.setText("RESOLVER TAREA");
			btnEstadoUpdate.setBackground(new Color(152, 251, 152));
			ButtonHoverEffect.getInstance().applyHoverEffect(btnEstadoUpdate);
			tareaAActualizar = true;

		} else {
			btnEstadoUpdate.setVisible(true);
			btnEstadoUpdate.setText("REABRIR TAREA");
			btnEstadoUpdate.setBackground(new Color(255, 121, 121));
			ButtonHoverEffect.getInstance().applyHoverEffect(btnEstadoUpdate);
			tareaAActualizar = false;
		}
	}

	private void totalTareas() {
		int totalTareas = listaTareas.getItemCount();
		totalTareasConteo.setText(String.valueOf(totalTareas));
	}

	private void elegirTarea() {
		listaTareas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int index = listaTareas.getSelectedIndex();
					if (index != -1) {
						tareaSeleccionada = tc.getAllTareas().get(index);
					}
				}
				actualizarBotonTexto();
			}
		});
	}

	public void borrarTarea() {
		btnBorrarTarea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tc.deleteTarea(tareaSeleccionada);
				listaTareas.removeAll();
				mostrarTareas();
			}
		});
	}

	private void editarEstadoRapido() {
		btnEstadoUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tareaAuxiliar = tareaSeleccionada;
				tareaAuxiliar.setEstado(tareaAActualizar);
				tareaAuxiliar.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				tc.updateTareaRapida(tareaAuxiliar);
				tareaAuxiliar = null;
				listaTareas.removeAll();
				btnEstadoUpdate.setVisible(false);
				mostrarTareas();
			}
		});
	}

	public void editarTarea() {
		btnEditarTarea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tareaSeleccionada != null) {
					dispose();
					EditarTarea frame = new EditarTarea(tareaSeleccionada);
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Primero debe seleccionar una tarea", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void mostrarTareas() {
		int counter = 1;
		for (Tarea t : tc.getAllTareas()) {
			String estado = !(t.getEstado()) ? "PENDIENTE" : "RESUELTA";
			listaTareas.add(counter + ". " + t.getNombre() + " (" + t.getTipoTarea() + ") - | " + estado + " | ");
			counter++;
		}
		totalTareas();
	}

	private void agregarBotones() {
		btnBorrarTarea.setBounds(405, 73, 158, 52);
		btnBorrarTarea.setBackground(new Color(255, 121, 121));
		btnBorrarTarea.setBorder(null);
		contentPane.add(btnBorrarTarea);

		btnEditarTarea.setBounds(405, 10, 158, 52);
		btnEditarTarea.setBackground(new Color(152, 251, 152));
		btnEditarTarea.setBorder(null);
		contentPane.add(btnEditarTarea);

		btnVolverMain.setBounds(405, 219, 158, 52);
		btnVolverMain.setBorder(null);
		btnVolverMain.setBackground(new Color(255, 160, 122));
		contentPane.add(btnVolverMain);

		btnEstadoUpdate.setBorder(null);
		btnEstadoUpdate.setBackground(new Color(255, 255, 128));
		btnEstadoUpdate.setBounds(405, 132, 158, 52);
		btnEstadoUpdate.setVisible(false);
		contentPane.add(btnEstadoUpdate);

		ButtonHoverEffect.getInstance().applyHoverEffect(btnBorrarTarea);
		ButtonHoverEffect.getInstance().applyHoverEffect(btnEditarTarea);
		ButtonHoverEffect.getInstance().applyHoverEffect(btnVolverMain);
		ButtonHoverEffect.getInstance().applyHoverEffect(btnEstadoUpdate);

		labelTotalTareas.setBounds(10, 277, 155, 14);
		contentPane.add(labelTotalTareas);
		totalTareasConteo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		totalTareasConteo.setBounds(156, 276, 46, 14);
		contentPane.add(totalTareasConteo);
	}

	private void volverMenuPrincipal() {
		btnVolverMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrameMain frame = new JFrameMain();
				frame.setVisible(true);
			}
		});
	}
}
