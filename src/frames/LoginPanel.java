package frames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import utils.ButtonHoverEffect;

public class LoginPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private final String dogeNormal = "/assets/icon-app.png";
	private final String errorPass = "/assets/error.gif";
	private JPanel loginPanel;
	private String userName = "jorge";
	private final char[] userPassword = "1234".toCharArray();
	private JTextField userInput;
	private JPasswordField passwordInput;
	private JPanel loginForm;
	private JPanel panelError;
	private JLabel errorImagen;
	JLabel usuarioLabel = new JLabel("Usuario:");
	JLabel passLabel = new JLabel("Contraseña:");
	JLabel lblNewLabel = new JLabel("Contraseña invalida");

	JButton btnIngresar = new JButton("LOG IN");
	JButton btnReintentar = new JButton("REINTENTAR");

	public LoginPanel() {
		generalEstructuraLogin();
		generarPanelLogin();
		metodoBotonVolver();
	}

	private void generarPanelLogin() {
		estilosElementos();
		eventosSubmit();
	}

	private void generalEstructuraLogin() {
		setTitle("Bienvenido al administrador Tareas");
		setIconImage(new ImageIcon(getClass().getResource(dogeNormal)).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 397);
		loginPanel = new JPanel();
		loginPanel.setBorder(null);
		setContentPane(loginPanel);
		loginPanel.setLayout(null);
		setLocationRelativeTo(null);

	}

	private void estilosElementos() {
		panelError = new JPanel();
		panelError.setBounds(25, 11, 417, 339);
		panelError.setLayout(null);
		panelError.setVisible(true);
		panelError.setVisible(false);

		errorImagen = new JLabel("");
		loginForm = new JPanel();
		userInput = new JTextField();
		passwordInput = new JPasswordField();

		errorImagen.setIcon(new ImageIcon(getClass().getResource(errorPass)));
		errorImagen.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usuarioLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));

		errorImagen.setBounds(27, 34, 351, 244);
		btnReintentar.setBounds(112, 289, 176, 39);
		lblNewLabel.setBounds(60, 11, 270, 14);
		loginForm.setBounds(140, 86, 176, 162);
		userInput.setBounds(0, 27, 176, 29);
		passwordInput.setBounds(0, 83, 176, 29);
		btnIngresar.setBounds(0, 123, 176, 39);
		usuarioLabel.setBounds(0, 0, 176, 29);
		passLabel.setBounds(0, 54, 176, 29);

		loginForm.setLayout(null);

		btnReintentar.setBorder(null);
		btnIngresar.setBorder(null);

		btnReintentar.setBackground(new Color(152, 251, 152));
		btnIngresar.setBackground(new Color(152, 251, 152));

		ButtonHoverEffect.getInstance().applyHoverEffect(btnIngresar);
		ButtonHoverEffect.getInstance().applyHoverEffect(btnReintentar);

		loginPanel.add(loginForm);
		loginForm.add(userInput);
		loginForm.add(passwordInput);
		loginForm.add(btnIngresar);
		loginForm.add(passLabel);
		loginForm.add(usuarioLabel);
		loginPanel.add(panelError);
		panelError.add(lblNewLabel);
		panelError.add(btnReintentar);
		panelError.add(errorImagen);
	}

	private void eventosSubmit() {
		userInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginUser();
				}
			}
		});

		btnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginUser();
			}
		});

		passwordInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginUser();
				}
			}
		});
	}

	private void loginUser() {
		String nombreUsuario = userInput.getText().trim().toLowerCase();
		char[] passUser = passwordInput.getPassword();

		if (nombreUsuario.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Necesito identificarte con algún nombre de usuario",
					"Usuario es requerido", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!nombreUsuario.equals(userName)) {
			JOptionPane.showMessageDialog(this, "Mis disculpas.\nNo reconozco ese usuario", "Algo sucedio",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (passUser.length == 0) {
			JOptionPane.showMessageDialog(this, "Necesito que ingreses una contraseña", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			return;

		}

		if (validacionPassword(passUser)) {
			try {
				JFrameMain frame = new JFrameMain();
				frame.setVisible(true);
				dispose();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			loginForm.setVisible(false);
			panelError.setVisible(true);
			passwordInput.setText("");
			passwordInput.requestFocusInWindow();

		}
	}

	private boolean validacionPassword(char[] inputPassword) {
		if (inputPassword.length != userPassword.length) {
			return false;
		} else {
			for (int i = 0; i < inputPassword.length; i++) {
				if (inputPassword[i] != userPassword[i]) {
					return false;
				}
			}
		}
		return true;
	}

	private void metodoBotonVolver() {
		btnReintentar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelError.setVisible(false);
				loginForm.setVisible(true);
			}
		});
		btnReintentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

}
