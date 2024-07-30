package frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.TareaController;
import model.Tarea;
import model.TipoTarea;
import utils.ButtonHoverEffect;
import utils.TareaControllerSingleton;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;

public class JFrameMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private TareaController tc;

	private JPanel contentPane;
	private JButton btnAgregarTarea = new JButton("AGREGAR TAREA");
	private JButton btnMostrarTareas = new JButton("MOSTRAR TAREAS");
	private JButton btnPrimerTarea = new JButton("TAREA MAS ANTIGUA");
	private JButton btnUltimaTarea = new JButton("TAREA MAS RECIENTE");
	private JButton btnSalirPrograma = new JButton("SALIR");

	private final JPanel panelAgregar = new JPanel();
	private final JLabel nombreTitulo = new JLabel("Nombre");
	private final JLabel tipoTareaTitulo = new JLabel("Tipo");
	private final JLabel detalleTitulo = new JLabel("Detalle");
	private JTextField nombreInput;
	private JComboBox<TipoTarea> tareaElegida;
	private JTextArea detalleInput;
	private final JButton btnGuardarTarea = new JButton("GUARDAR");
	private final JButton btnCancelarTarea = new JButton("CANCELAR");

	JScrollPane scrollPane = new JScrollPane();
	private JLabel labelImagen;
	private final String dogeNormal = "/assets/icon-app.png";
	private final String dogeBoss = "/assets/icon-appBoss.png";
	private final JTextPane moduloTareaIndividual = new JTextPane();

	public JFrameMain() {
		tc = TareaControllerSingleton.getInstance().getTareaController();
		generarPanelPrincipal();
		generarPanelAgregar();
		generarImagenPrincipal();
	}

	private void generarPanelPrincipal() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(169, 169, 169));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setTitle("Administrador Tareas");
		setIconImage(new ImageIcon(getClass().getResource(dogeNormal)).getImage());
		setContentPane(contentPane);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 365);
		setLocationRelativeTo(null);
		agregarBotones();
		estilosBotones();
		iniciarBotones();
		btnSalirPrograma();
	}

	private void generarImagenPrincipal() {
		ImageIcon icon = new ImageIcon(getClass().getResource(dogeNormal));
		labelImagen = new JLabel(icon);
		labelImagen.setBounds(281, 11, 401, 306);
		contentPane.add(labelImagen);

		moduloTareaIndividual.setEditable(false);
		scrollPane = new JScrollPane(moduloTareaIndividual);
		scrollPane.setBounds(282, 11, 400, 304);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVisible(false);
		contentPane.add(scrollPane);
		labelImagen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				labelImagen.setIcon(new ImageIcon(getClass().getResource(dogeBoss)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				labelImagen.setIcon(new ImageIcon(getClass().getResource(dogeNormal)));
			}
		});
	}

	private void generarPanelAgregar() {
		contentPane.add(panelAgregar);
		panelAgregar.setVisible(false);
		panelAgregar.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAgregar.setBounds(282, 13, 400, 304);
		panelAgregar.setLayout(null);
		btnGuardarTarea();
		btnCancelarTarea();
		generarLabelsPanelAgregar();
		generarInputsPanelAgregar();
	}

	private void generarLabelsPanelAgregar() {
		nombreTitulo.setBounds(9, 16, 128, 19);
		tipoTareaTitulo.setBounds(9, 79, 128, 19);
		detalleTitulo.setBounds(6, 142, 128, 19);
		nombreTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tipoTareaTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		detalleTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelAgregar.add(tipoTareaTitulo);
		panelAgregar.add(detalleTitulo);
		panelAgregar.add(nombreTitulo);
	}

	private void generarInputsPanelAgregar() {
		nombreInput = new JTextField();
		detalleInput = new JTextArea();
		detalleInput.setLineWrap(true);
		detalleInput.setRows(4);
		tareaElegida = new JComboBox<>();

		nombreInput.setBounds(6, 36, 384, 32);
		tareaElegida.setBounds(6, 99, 384, 32);
		detalleInput.setBounds(6, 172, 231, 121);
		nombreInput.setColumns(10);

		panelAgregar.add(nombreInput);
		panelAgregar.add(tareaElegida);
		panelAgregar.add(detalleInput);

		for (TipoTarea tipo : TipoTarea.values()) {
			tareaElegida.addItem(tipo);
		}
	}

	private void estilosBotones() {
		btnAgregarTarea.setBounds(10, 11, 261, 52);
		btnAgregarTarea.setBackground(new Color(152, 251, 152));
		btnAgregarTarea.setBorder(null);

		btnMostrarTareas.setBounds(10, 74, 261, 52);
		btnMostrarTareas.setBorder(null);

		btnPrimerTarea.setBounds(10, 137, 261, 52);
		btnPrimerTarea.setBorder(null);

		btnUltimaTarea.setBounds(10, 200, 261, 52);
		btnUltimaTarea.setBorder(null);

		btnSalirPrograma.setBounds(10, 263, 261, 52);
		btnSalirPrograma.setBackground(new Color(255, 160, 122));
		btnSalirPrograma.setBorder(null);

		btnGuardarTarea.setBounds(246, 173, 144, 55);
		btnGuardarTarea.setBorder(null);
		btnGuardarTarea.setBackground(new Color(152, 251, 152));

		btnCancelarTarea.setBounds(247, 238, 143, 55);
		btnCancelarTarea.setBorder(null);
		btnCancelarTarea.setBackground(new Color(255, 160, 122));

		ButtonHoverEffect.getInstance().applyHoverEffect(btnCancelarTarea);
		ButtonHoverEffect.getInstance().applyHoverEffect(btnGuardarTarea);
		ButtonHoverEffect.getInstance().applyHoverEffect(btnSalirPrograma);
		ButtonHoverEffect.getInstance().applyHoverEffect(btnUltimaTarea);
		ButtonHoverEffect.getInstance().applyHoverEffect(btnPrimerTarea);
		ButtonHoverEffect.getInstance().applyHoverEffect(btnMostrarTareas);
		ButtonHoverEffect.getInstance().applyHoverEffect(btnAgregarTarea);
	}

	private void agregarBotones() {
		contentPane.add(btnAgregarTarea);
		contentPane.add(btnMostrarTareas);
		contentPane.add(btnUltimaTarea);
		contentPane.add(btnPrimerTarea);
		contentPane.add(btnSalirPrograma);
		panelAgregar.add(btnCancelarTarea);
		panelAgregar.add(btnGuardarTarea);
	}

	private void iniciarBotones() {
		btnAgregarTarea();
		btnMostrarTareas();
		btnTareaMasAntigua();
		btnTareaMasReciente();
	}

	private void cambiarVisibilidadPanel(JPanel panel, boolean ocultar) {
		panel.setVisible(ocultar);
	}

	private void cambiarVisibilidadPanelScroll(JScrollPane panel, boolean ocultar) {
		panel.setVisible(ocultar);
	}

	private void cambiarVisibilidadImagenPrincipal(boolean ocultar) {
		labelImagen.setVisible(ocultar);
	}

	private void btnSalirPrograma() {
		btnSalirPrograma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	}

	private void btnMostrarTareas() {
		btnMostrarTareas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				PanelTotalTAreas frame = new PanelTotalTAreas();
				frame.setVisible(true);
			}
		});
	}

	private void btnAgregarTarea() {
		btnAgregarTarea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiarVisibilidadPanel(panelAgregar, false);
				cambiarVisibilidadImagenPrincipal(false);
				cambiarVisibilidadPanelScroll(scrollPane, false);
				cambiarVisibilidadPanel(panelAgregar, true);
				generarImagenPrincipal();
			}
		});
	}

	private void btnTareaMasReciente() {
		btnUltimaTarea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiarVisibilidadImagenPrincipal(false);
				cambiarVisibilidadPanel(panelAgregar, false);
				cambiarVisibilidadPanelScroll(scrollPane, true);

				tc.getAllTareas().stream().max((t1, t2) -> t1.getFechaCreacion().compareTo(t2.getFechaCreacion()))
						.ifPresent(tarea -> {
							String tareaDetalles = "Tarea:\n" + tarea.getNombre() + "\n" + "\nTipo:\n"
									+ tarea.getTipoTarea() + "\n" + "\nEstado:\n"
									+ (tarea.getEstado() ? "Resuelta" : "Pendiente") + "\n" + "\nFecha Creacion: \n"
									+ tarea.getFechaCreacion() + "\n" + "\nFecha Actualizacion: \n"
									+ tarea.getFechaActualizacion() + "\n" + "\nDescripción:\n"
									+ tarea.getDescripcion();

							moduloTareaIndividual.setText(tareaDetalles);
						});
			}
		});
	}

	private void btnTareaMasAntigua() {
		btnPrimerTarea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiarVisibilidadImagenPrincipal(false);
				cambiarVisibilidadPanel(panelAgregar, false);
				cambiarVisibilidadPanelScroll(scrollPane, true);

				tc.getAllTareas().stream().min((t1, t2) -> t1.getFechaCreacion().compareTo(t2.getFechaCreacion()))
						.ifPresent(tarea -> {
							String tareaDetalles = "Tarea:\n" + tarea.getNombre() + "\n" + "\nTipo:\n"
									+ tarea.getTipoTarea() + "\n" + "\nEstado:\n"
									+ (tarea.getEstado() ? "Resuelta" : "Pendiente") + "\n" + "\nFecha Creacion: \n"
									+ tarea.getFechaCreacion() + "\n" + "\nFecha Actualizacion: \n"
									+ tarea.getFechaActualizacion() + "\n" + "\nDescripción:\n"
									+ tarea.getDescripcion();

							moduloTareaIndividual.setText(tareaDetalles);
						});
			}
		});
	}

	private void btnGuardarTarea() {
		btnGuardarTarea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = nombreInput.getText().trim();
				String detalle = detalleInput.getText().trim();
				TipoTarea tipoTarea = (TipoTarea) tareaElegida.getSelectedItem();

				if (nombre.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El campo Nombre no puede estar vacío.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (detalle.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El campo Detalle no puede estar vacío.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Tarea nuevaTarea = new Tarea(nombre, detalle, tipoTarea);
					tc.createTarea(nuevaTarea);
					JOptionPane.showMessageDialog(null, "Tarea guardada correctamente.");
					nombreInput.setText("");
					detalleInput.setText("");
					tareaElegida.setSelectedIndex(0);
					cambiarVisibilidadPanel(panelAgregar, false);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error al guardar la tarea: " + ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void btnCancelarTarea() {
		btnCancelarTarea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiarVisibilidadImagenPrincipal(true);
				cambiarVisibilidadPanel(panelAgregar, false);
			}
		});
	}
}
