package aloja.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import aloja.clases.Empleado;
import aloja.management.EmpleadoManagement;
import aloja.util.BuscarEmpleado;
import aloja.util.Mensaje;
import aloja.util.Tecla;

public class ViewEmpleado extends JPanel implements ActionListener,
		KeyListener, MouseListener, ItemListener {

	EmpleadoManagement manageEmp = new EmpleadoManagement();

	// Declaracion de Componentes
	private JLabel lblTitulo;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JTextField txtNombres;
	private JTextField txtApePaterno;
	private JTextField txtApeMaterno;
	private JLabel lblNombres;
	private JLabel lblApellidoMaterno;
	private JLabel lblPassword;
	private JComboBox cboTipo;
	private JLabel lblTipo;
	private JLabel lblApellidoPaterno;
	private JPasswordField pwdPassword;
	private JScrollPane scrollEmpleado;
	private JTable tbEmpleado;
	private JButton btnBuscar;
	private JButton btnAceptar;
	private JComboBox cboOpciones;
	private JSeparator separator;
	private JLabel lblOpcion;
	private JButton btnLimpiar;
	private DefaultTableModel modelEmpleado;

	// Metodo Constructor
	public ViewEmpleado() {
		this.setLayout(null);
		this.setSize(650, 343);

		lblTitulo = new JLabel("Mantenimiento de Empleado");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(21, 11, 366, 19);
		add(lblTitulo);

		lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(21, 67, 46, 20);
		add(lblCodigo);

		lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(21, 94, 46, 20);
		add(lblNombres);

		txtCodigo = new JTextField();
		txtCodigo.setEnabled(false);
		txtCodigo.setBounds(110, 67, 162, 20);
		add(txtCodigo);
		txtCodigo.setColumns(10);

		txtNombres = new JTextField();
		txtNombres.setBounds(110, 94, 162, 20);
		add(txtNombres);
		txtNombres.addKeyListener(this);
		txtNombres.setColumns(10);

		lblApellidoPaterno = new JLabel("Ape. Paterno");
		lblApellidoPaterno.setBounds(308, 94, 94, 20);
		add(lblApellidoPaterno);

		txtApePaterno = new JTextField();
		txtApePaterno.setBounds(378, 94, 168, 20);
		add(txtApePaterno);
		txtApePaterno.addKeyListener(this);
		txtApePaterno.setColumns(10);

		lblApellidoMaterno = new JLabel("Ape. Materno");
		lblApellidoMaterno.setBounds(21, 124, 85, 20);
		add(lblApellidoMaterno);

		txtApeMaterno = new JTextField();
		txtApeMaterno.setBounds(110, 124, 162, 20);
		add(txtApeMaterno);
		txtApeMaterno.addKeyListener(this);
		txtApeMaterno.setColumns(10);

		lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(308, 123, 79, 20);
		add(lblTipo);

		cboTipo = new JComboBox();
		cboTipo.setModel(new DefaultComboBoxModel(new String[] {
				"Administrador", "Empleado" }));
		cboTipo.setToolTipText("");
		cboTipo.setBounds(378, 124, 168, 20);
		add(cboTipo);

		separator = new JSeparator();
		separator.setBounds(10, 35, 630, 2);
		add(separator);

		lblPassword = new JLabel("Password");
		lblPassword.setBounds(21, 154, 58, 20);
		add(lblPassword);

		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(110, 154, 162, 20);
		pwdPassword.addKeyListener(this);
		add(pwdPassword);

		scrollEmpleado = new JScrollPane();
		scrollEmpleado.setBounds(10, 185, 622, 148);
		add(scrollEmpleado);

		tbEmpleado = new JTable();
		modelEmpleado = new DefaultTableModel(new Object[][] {}, new String[] {
				"Codigo", "Nombres", "Apellido Paterno", "Apellido Materno",
				"Tipo" }) {
			// esto hace las celdas no editables
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		tbEmpleado.setModel(modelEmpleado);
		tbEmpleado.setAutoCreateRowSorter(true); // Para ordenar asc o desc
		tbEmpleado.getColumnModel().getColumn(0).setPreferredWidth(68);
		tbEmpleado.getColumnModel().getColumn(1).setPreferredWidth(53);
		tbEmpleado.getColumnModel().getColumn(2).setPreferredWidth(93);
		tbEmpleado.getColumnModel().getColumn(3).setPreferredWidth(94);
		tbEmpleado.addMouseListener(this);
		scrollEmpleado.setViewportView(tbEmpleado);

		btnBuscar = new JButton(new ImageIcon("images/search.png"));
		btnBuscar.setBounds(282, 67, 24, 24);
		btnBuscar.addActionListener(this);
		btnBuscar.setEnabled(false);
		add(btnBuscar);

		cboOpciones = new JComboBox();
		cboOpciones.setModel(new DefaultComboBoxModel(new String[] {
				"Ingresar", "Modificar", "Eliminar" }));
		cboOpciones.setBounds(278, 41, 109, 20);
		cboOpciones.addItemListener(this);
		add(cboOpciones);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(318, 153, 89, 23);
		btnAceptar.addActionListener(this);
		add(btnAceptar);

		lblOpcion = new JLabel("Opci\u00F3n");
		lblOpcion.setBounds(226, 41, 46, 20);
		add(lblOpcion);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(417, 153, 89, 23);
		add(btnLimpiar);
		llenarTabla();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAceptar) {
			switch (cboOpciones.getSelectedIndex()) {
			case 0:
				ingresarEmpleado();
				break;
			case 1:
				modificarEmpleado();
				break;
			default:
				int yesNoResponse = JOptionPane.showConfirmDialog(null,
						"Está seguro que desea eliminar un empleado?", "Mensaje",
						JOptionPane.YES_NO_OPTION);
				if (yesNoResponse == JOptionPane.YES_OPTION) {
					eliminarEmpleado();
				} else {

				}
				
				break;
			}
		}
		if(e.getSource() == btnBuscar){
			new BuscarEmpleado(this);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		Tecla t = new Tecla();
		if (e.getSource() == txtCodigo)
			if (t.soloNumeros(e, txtCodigo, 3) == false)
				;
		if (e.getSource() == txtNombres)
			if (t.soloLetras(e, txtNombres, 50) == false)
				;
		if (e.getSource() == txtApePaterno)
			if (t.soloLetras(e, txtApePaterno, 50) == false)
				;
		if (e.getSource() == txtApeMaterno)
			if (t.soloLetras(e, txtApeMaterno, 50) == false)
				;
		if (e.getSource() == pwdPassword)
			if (t.tamanoCadena(e, pwdPassword, 20) == false)
				;
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == tbEmpleado && e.getClickCount() == 1
				&& cboOpciones.getSelectedItem().equals("Modificar")
				|| cboOpciones.getSelectedItem().equals("Eliminar")) {
			cargarDatos();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboOpciones) {
			cambiarOpcion();
		}

	}

	public void ingresarEmpleado() {
		if (!validarDatos()) {
			Mensaje.mensajeError("Datos incorrectos", "Error");
			return;
		}
		Empleado emp = new Empleado();
		emp.setNom_emp(getNombres());
		emp.setApe_pat(getApellidoPaterno());
		emp.setApe_mat(getApellidoMaterno());
		emp.setUsu_emp(getUsuario());
		emp.setPas_emp(getPassword());
		emp.setTip_emp(getTipoEmp());
		boolean result = manageEmp.addEmpleado(emp);
		if (result) {
			Mensaje.mensajeCorrecto("Ingreso correcto\nUsuario: "
					+ getUsuario(), "Correcto");

			limpiarCampos();
			llenarTabla();
		} else {
			Mensaje.mensajeError("Error al ingresar", "Error");
		}
	}

	public void modificarEmpleado() {
		if (!validarDatos()) {
			Mensaje.mensajeError("Rellene todos los datos", "Error");
			return;
		}
		Empleado emp = new Empleado();
		emp.setCod_emp(getCodigo());
		emp.setNom_emp(getNombres());
		emp.setApe_pat(getApellidoPaterno());
		emp.setApe_mat(getApellidoMaterno());
		emp.setUsu_emp(getUsuario());
		emp.setPas_emp(getPassword());
		emp.setTip_emp(getTipoEmp());
		boolean result = manageEmp.modificarEmpleado(emp);
		if (result) {
			limpiarCampos();
			Mensaje.mensajeCorrecto("Modificación correcta", "Correcto");
			llenarTabla();
		} else {
			Mensaje.mensajeError("Error al modificar", "Error");
		}
	}

	public void eliminarEmpleado() {
		if (txtCodigo.getText().equals("")) {
			Mensaje.mensajeError("Escoja el empleado a eliminar", "Error");
			return;
		}
		boolean result = manageEmp.deleteEmpleado(getCodigo());
		if (result) {
			limpiarCampos();
			Mensaje.mensajeCorrecto("Eliminación correcta", "Correcto");
			llenarTabla();

		} else {
		}
	}

	public boolean validarDatos() {
		if (getNombres().equals("") || getApellidoPaterno().equals("")
				|| getApellidoMaterno().equals("") || getUsuario().equals("")
				|| getPassword().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public void cargarDatos() {
		try {

			int row = tbEmpleado.getSelectedRow();
			Empleado emp = new Empleado();
			emp = manageEmp.buscarEmpleado(Integer.parseInt(tbEmpleado
					.getValueAt(row, 0).toString()));
			txtCodigo.setText(emp.getCod_emp() + "");
			txtNombres.setText(emp.getNom_emp());
			txtApePaterno.setText(emp.getApe_pat());
			txtApeMaterno.setText(emp.getApe_mat());
			cboTipo.setSelectedIndex(emp.getTip_emp());
			pwdPassword.setText(emp.getPas_emp());
		} catch (Exception e) {

		}

	}
	
	public void llenarTabla() {
		limpiarTabla();
		ArrayList<Empleado> empleados = manageEmp.listarEmpleado();
	
		for (Empleado emp : empleados) {
			Object datos[] = { emp.getCod_emp(), 
							   emp.getNom_emp(),
							   emp.getApe_pat(), 
							   emp.getApe_mat(), 
							   emp.getDes_tip() 
							   };
			modelEmpleado.addRow(datos);
		}
	}

	public void limpiarTabla() {
		int intL_filas = modelEmpleado.getRowCount();
		intL_filas--;
		for (int i = intL_filas; i >= 0; i--) {
			modelEmpleado.removeRow(i);
		}
	}
	
	public void limpiarCampos() {
		txtCodigo.setText("");
		txtNombres.setText("");
		txtApePaterno.setText("");
		txtApeMaterno.setText("");
		cboTipo.setSelectedIndex(0);
		pwdPassword.setText("");
	}
	
	public void cambiarOpcion() {
		switch (cboOpciones.getSelectedIndex()) {
		case 0:
			txtNombres.setEditable(true);
			txtApePaterno.setEditable(true);
			txtApeMaterno.setEditable(true);
			pwdPassword.setEditable(true);
			cboTipo.setEnabled(true);
			btnBuscar.setEnabled(false);
			limpiarCampos();
			break;
		case 1:
			txtNombres.setEditable(true);
			txtApePaterno.setEditable(true);
			txtApeMaterno.setEditable(true);
			pwdPassword.setEditable(false);
			cboTipo.setEnabled(true);
			btnBuscar.setEnabled(true);
			limpiarCampos();
			break;
		default:
			txtNombres.setEditable(false);
			txtApePaterno.setEditable(false);
			txtApeMaterno.setEditable(false);
			pwdPassword.setEditable(false);
			cboTipo.setEnabled(false);
			btnBuscar.setEnabled(true);
			limpiarCampos();
			break;
		}
	}
	
	public void cargarBusqueda(int codEmp) {
		Empleado emp = new Empleado();
		emp = manageEmp.buscarEmpleado(codEmp);
		txtCodigo.setText(emp.getCod_emp() + "");
		txtNombres.setText(emp.getNom_emp());
		txtApePaterno.setText(emp.getApe_pat());
		txtApeMaterno.setText(emp.getApe_mat());
		cboTipo.setSelectedIndex(emp.getTip_emp());
	}

	// Metodos de Captura datos GUI
	public int getCodigo() {
		return Integer.parseInt(txtCodigo.getText());
	}

	public String getNombres() {
		return txtNombres.getText();
	}

	public String getApellidoPaterno() {
		return txtApePaterno.getText();
	}

	public String getApellidoMaterno() {
		return txtApeMaterno.getText();
	}

	public String getUsuario() {
		String usuario = (getNombres().substring(0, 1)
				.concat(getApellidoPaterno())).toLowerCase();
		return usuario;
	}

	public String getPassword() {
		String pass = new String(pwdPassword.getPassword());
		return pass;
	}

	public int getTipoEmp() {
		return cboTipo.getSelectedIndex();
	}

}
