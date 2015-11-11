package aloja.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import aloja.clases.Empleado;
import aloja.management.EmpleadoManagement;
import aloja.util.Mensaje;
import aloja.util.MostrarPanel;
import aloja.util.Tecla;

public class ViewLogin extends JPanel implements ActionListener, KeyListener {

	EmpleadoManagement manageEmp = new EmpleadoManagement();

	// Declaracion de Componentes
	private JTextField txtUsuario;
	private JPasswordField pwdPassword;
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JLabel lblTitulo;
	private JPanel pnlDatos;
	private JButton btnAceptar;
	private JButton btnCancelar;

	// Clase para el manejo de paneles
	private MostrarPanel mostrarPanel = new MostrarPanel();

	// Metodo Constructor
	public ViewLogin() {
		this.setLayout(null);
		this.setSize(879, 470);

		pnlDatos = new JPanel();
		pnlDatos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlDatos.setBounds(235, 96, 403, 242);
		add(pnlDatos);
		pnlDatos.setLayout(null);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(114, 177, 89, 23);
		pnlDatos.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(213, 177, 89, 23);
		pnlDatos.add(btnCancelar);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(96, 96, 46, 20);
		pnlDatos.add(lblUsuario);

		lblPassword = new JLabel("Password");
		lblPassword.setBounds(96, 135, 65, 20);
		pnlDatos.add(lblPassword);

		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(this);
		txtUsuario.setBounds(159, 96, 156, 20);
		pnlDatos.add(txtUsuario);
		txtUsuario.setColumns(10);

		pwdPassword = new JPasswordField();
		pwdPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Tecla t = new Tecla();
				if (e.getSource() == pwdPassword)
					if (t.tamanoCadena(e, pwdPassword, 10) == false)
						;
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getSource() == pwdPassword
						&& e.getKeyCode() == KeyEvent.VK_ENTER) {
					ingresarSistema();
				}
			}
		});
		pwdPassword.setBounds(159, 135, 156, 20);
		pnlDatos.add(pwdPassword);

		lblTitulo = new JLabel("BIENVENIDO");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(166, 46, 149, 27);
		pnlDatos.add(lblTitulo);

		JLabel lblImagen = new JLabel(new ImageIcon("images/login.png"));
		lblImagen.setBounds(114, 21, 64, 64);
		pnlDatos.add(lblImagen);

	}

	// EVENTOS
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAceptar)) {
			ingresarSistema();
		}
		if (e.getSource().equals(btnCancelar)) {
			cancelar();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		Tecla t = new Tecla();
		if (e.getSource().equals(txtUsuario)) {
			if (t.tamanoCadena(e, txtUsuario, 10) == false)
				;
		}
		if (e.getSource().equals(pwdPassword))
			if (t.tamanoCadena(e, pwdPassword, 10) == false)
				;
	}

	// Fin de Eventos de Teclado //FIN DE EVENTOS

	// Metodos Logicos
	public void ingresarSistema() {
		Empleado emp = new Empleado();
		emp = manageEmp.confirmarAutenticacion(getUsuario(), getPassword());
		if (emp != null) {
			mostrarPanel.ingresar();
			ViewAlojamiento.txtCodEmp.setText(emp.getCod_emp() + "");
			ViewAlojamiento.txtNomEmp.setText(emp.getNom_emp());
			ViewLiquidacion.txtCodEmp.setText(Integer.toString(emp.getCod_emp()));
			ViewLiquidacion.txtNomEmp.setText(emp.getNom_emp());
		} else {
			Mensaje.mensajeError("Error de autenticacion", "Error");
		}
		limpiarDatos();
	}

	public void cancelar() {
		limpiarDatos();
	}

	private void limpiarDatos() {
		txtUsuario.setText("");
		pwdPassword.setText("");
		txtUsuario.requestFocus();
	}

	// Metodos de Captura de Datos GUI
	public String getUsuario() {
		return txtUsuario.getText();
	}

	public String getPassword() {
		String pass = new String(pwdPassword.getPassword());
		return pass;
	}

}
