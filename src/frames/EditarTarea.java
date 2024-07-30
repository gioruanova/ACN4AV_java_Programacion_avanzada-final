package frames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TareaController;
import model.Tarea;
import model.TipoTarea;
import utils.ButtonHoverEffect;
import utils.TareaControllerSingleton;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.text.SimpleDateFormat;

public class EditarTarea extends JFrame {
	private static final long serialVersionUID = 1L;
	private TareaController tc;

	private JPanel contentPane;
	private JTextField inputNombre;
	private JCheckBox inputEstado = new JCheckBox("Resuelta?");
	private JTextArea inputDescripcion = new JTextArea();
	private final String dogeNormal = "/assets/icon-app.png";
	private JLabel labelNombre = new JLabel("Nombre");
	private JLabel labelTipoTarea = new JLabel("Tipo De Tarea");
	private JLabel labelDescripcion = new JLabel("Descripcion");
	private JLabel labelEstado = new JLabel("Estado");

	private JButton btnGuardarEdicion = new JButton("Guardar Cambios");
	private JButton btnCancelarEditar = new JButton("Cancelar");
	private JComboBox<TipoTarea> comboTipoTarea;
	private Tarea tareaAuxiliar;
	private final JLabel labelFechaInicial = new JLabel("Fecha Creacion");
	private final JLabel labelFechaActualizacion = new JLabel("Ultima Actualizacion");
	private final JLabel inputFechaInicial = new JLabel("-");
	private final JLabel inputFechaUpdate = new JLabel("--");

	public static void main(String[] args) {
		EditarTarea frame = new EditarTarea(null);
		frame.setVisible(true);
	}

	public EditarTarea(Tarea t) {
		tc = TareaControllerSingleton.getInstance().getTareaController();
		setResizable(false);
		this.tareaAuxiliar = t;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Editar tarea");
		setIconImage(new ImageIcon(getClass().getResource(dogeNormal)).getImage());
		setContentPane(contentPane);
		contentPane.setLayout(null);

		generarOpciones();
		agregarBotones();
		generarCamposEditables();
		guardarCambios();
		cancelarEdicion();
		setLocationRelativeTo(null);

	}

	private void generarCamposEditables() {
		inputNombre.setText(tareaAuxiliar.getNombre());
		comboTipoTarea.setSelectedItem(tareaAuxiliar.getTipoTarea());
		inputDescripcion.setText(tareaAuxiliar.getDescripcion());

		if (tareaAuxiliar.getEstado()) {
			inputEstado.setSelected(true);
		} else {
			inputEstado.setSelected(false);
		}
		extraccionFechas();

	}

	private void extraccionFechas() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		inputFechaInicial.setText(sdf.format(tareaAuxiliar.getFechaCreacion()));
		inputFechaUpdate.setText(sdf.format(tareaAuxiliar.getFechaActualizacion()));
	}

	private void generarOpciones() {
		labelNombre.setBounds(10, 107, 89, 14);
		contentPane.add(labelNombre);

		inputNombre = new JTextField();
		inputNombre.setBounds(105, 104, 229, 20);
		contentPane.add(inputNombre);
		inputNombre.setColumns(10);

		// ----

		labelTipoTarea.setBounds(10, 139, 89, 14);
		contentPane.add(labelTipoTarea);

		comboTipoTarea = new JComboBox<>();
		comboTipoTarea.setBounds(105, 135, 229, 22);
		contentPane.add(comboTipoTarea);
		for (TipoTarea tipo : TipoTarea.values()) {
			comboTipoTarea.addItem(tipo);
		}
		// ----

		labelDescripcion.setBounds(10, 199, 89, 14);
		contentPane.add(labelDescripcion);

		inputDescripcion.setLineWrap(true);
		inputDescripcion.setBounds(105, 194, 229, 86);
		contentPane.add(inputDescripcion);

		// ----

		labelEstado.setBounds(10, 168, 89, 14);
		contentPane.add(labelEstado);

		inputEstado.setBounds(107, 164, 97, 23);
		contentPane.add(inputEstado);

	}

	public void cancelarEdicion() {
		btnCancelarEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PanelTotalTAreas frame = new PanelTotalTAreas();
				frame.setVisible(true);
			}
		});
	}

	public void guardarCambios() {
		btnGuardarEdicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = inputNombre.getText();
				String descripcion = inputDescripcion.getText();
				TipoTarea tipoTareaSeleccionado = (TipoTarea) comboTipoTarea.getSelectedItem();
				boolean estado = inputEstado.isSelected();

				tareaAuxiliar.setNombre(nombre);
				tareaAuxiliar.setDescripcion(descripcion);
				tareaAuxiliar.setTipoTarea(tipoTareaSeleccionado);
				tareaAuxiliar.setEstado(estado);
				tareaAuxiliar.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));

				tc.updateTarea(tareaAuxiliar);

				dispose();
				PanelTotalTAreas frame = new PanelTotalTAreas();
				frame.setVisible(true);
			}
		});
	}

	public void agregarBotones() {

		btnGuardarEdicion.setBounds(10, 291, 132, 52);
		btnGuardarEdicion.setBackground(new Color(152, 251, 152));
		btnGuardarEdicion.setBorder(null);
		ButtonHoverEffect.getInstance().applyHoverEffect(btnGuardarEdicion);
		contentPane.add(btnGuardarEdicion);

		btnCancelarEditar.setBounds(188, 291, 146, 52);
		btnCancelarEditar.setBackground(new Color(255, 160, 122));
		btnCancelarEditar.setBorder(null);
		ButtonHoverEffect.getInstance().applyHoverEffect(btnCancelarEditar);
		contentPane.add(btnCancelarEditar);
		labelFechaInicial.setBounds(10, 11, 132, 14);

		contentPane.add(labelFechaInicial);
		labelFechaActualizacion.setBounds(10, 53, 132, 14);

		contentPane.add(labelFechaActualizacion);
		inputFechaInicial.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		inputFechaInicial.setBounds(170, 11, 164, 14);

		contentPane.add(inputFechaInicial);
		inputFechaUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		inputFechaUpdate.setBounds(170, 53, 164, 14);

		contentPane.add(inputFechaUpdate);
	}

}
