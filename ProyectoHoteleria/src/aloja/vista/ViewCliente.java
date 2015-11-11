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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import aloja.clases.Cliente;
import aloja.management.ClienteManagement;
import aloja.util.BuscarCliente;
import aloja.util.Mensaje;
import aloja.util.Tecla;

public class ViewCliente extends JPanel implements ActionListener, KeyListener,
		MouseListener, ItemListener {

	private ClienteManagement manageCli = new ClienteManagement();
	// Declaracion de Componentes
	private JTextField txtCodigo;
	private JTextField txtNombres;
	private JTextField txtApePat;
	private JTextField txtApeMat;
	private JTextField txtTelefono;
	private JTextField txtDni;
	private JButton btnAceptar;
	private JButton btnLimpiar;
	private JComboBox cboOpciones;
	private JLabel lblOpcin;
	private JLabel lblCodCli;
	private JButton btnBuscar;
	private JLabel lblNombre;
	private JLabel lblApePaterno;
	private JLabel lblApeMaterno;
	private JLabel lblTelefono;
	private JLabel lblDni;
	private JTable tbCliente;
	private DefaultTableModel modelCliente;

	// Constructor
	public ViewCliente() {
		this.setLayout(null);
		this.setSize(650, 343);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 35, 630, 2);
		add(separator);

		JLabel lblTitulo = new JLabel("Mantenimiento de Cliente");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(21, 11, 237, 19);
		add(lblTitulo);

		lblOpcin = new JLabel("Opci\u00F3n");
		lblOpcin.setBounds(228, 51, 46, 14);
		add(lblOpcin);

		cboOpciones = new JComboBox();
		cboOpciones.setModel(new DefaultComboBoxModel(new String[] {
				"Registrar", "Modificar", "Eliminar" }));
		cboOpciones.setBounds(266, 48, 86, 20);
		cboOpciones.addItemListener(this);
		add(cboOpciones);

		lblCodCli = new JLabel("C\u00F3digo");
		lblCodCli.setBounds(30, 78, 91, 14);
		add(lblCodCli);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(117, 73, 157, 20);
		add(txtCodigo);
		txtCodigo.addKeyListener(this);
		txtCodigo.setColumns(10);

		lblNombre = new JLabel("Nombres");
		lblNombre.setBounds(321, 79, 72, 14);
		add(lblNombre);

		txtNombres = new JTextField();
		txtNombres.setBounds(409, 73, 171, 20);
		add(txtNombres);
		txtNombres.addKeyListener(this);
		txtNombres.setColumns(10);

		lblApePaterno = new JLabel("Ape. Paterno");
		lblApePaterno.setBounds(30, 103, 91, 14);
		add(lblApePaterno);

		lblApeMaterno = new JLabel("Ape. Materno");
		lblApeMaterno.setBounds(321, 104, 94, 14);
		add(lblApeMaterno);

		txtApePat = new JTextField();
		txtApePat.setColumns(10);
		txtApePat.setBounds(117, 98, 157, 20);
		txtApePat.addKeyListener(this);
		add(txtApePat);

		txtApeMat = new JTextField();
		txtApeMat.setColumns(10);
		txtApeMat.setBounds(409, 98, 171, 20);
		txtApeMat.addKeyListener(this);
		add(txtApeMat);

		lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setBounds(30, 128, 91, 14);
		add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(117, 123, 157, 20);
		txtTelefono.addKeyListener(this);
		add(txtTelefono);

		lblDni = new JLabel("D.N.I.");
		lblDni.setBounds(321, 129, 86, 14);
		add(lblDni);

		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(409, 123, 171, 20);
		txtDni.addKeyListener(this);
		add(txtDni);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(228, 154, 89, 23);
		btnAceptar.addActionListener(this);
		add(btnAceptar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(327, 154, 89, 23);
		btnLimpiar.addActionListener(this);
		add(btnLimpiar);

		btnBuscar = new JButton(new ImageIcon("images/search.png"));
		btnBuscar.setBounds(276, 73, 24, 24);
		btnBuscar.addActionListener(this);
		add(btnBuscar);

		JScrollPane scrollCliente = new JScrollPane();
		scrollCliente.setBounds(10, 182, 630, 150);
		add(scrollCliente);
		modelCliente = new DefaultTableModel(new Object[][] {}, new String[] {
				"C\u00F3digo", "Nombre", "Ape. Pat.", "Ape. Mat.",
				"Tel\u00E9fono", "D.N.I." }) {
			// esto hace las celdas no editables
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		tbCliente = new JTable();
		tbCliente.setModel(modelCliente);
		tbCliente.addMouseListener(this);
		scrollCliente.setViewportView(tbCliente);
		llenarTabla();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAceptar) {
			switch (cboOpciones.getSelectedIndex()) {
			case 0:
				ingresarCliente();
				break;
			case 1:
				modificarCliente();
				break;
			default:
				int yesNoResponse = JOptionPane.showConfirmDialog(null,
						"Está seguro que desea eliminar un cliente?",
						"Mensaje", JOptionPane.YES_NO_OPTION);
				if (yesNoResponse == JOptionPane.YES_OPTION) {
					eliminarCliente();
				} else {

				}

				break;
			}
		}
		if (e.getSource() == btnBuscar) {
			new BuscarCliente(this, null, null, null);
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
		if (e.getSource() == txtApePat)
			if (t.soloLetras(e, txtApePat, 50) == false)
				;
		if (e.getSource() == txtApeMat)
			if (t.soloLetras(e, txtApeMat, 50) == false)
				;
		if (e.getSource() == txtDni)
			if (t.soloNumeros(e, txtDni, 8) == false)
				;
		if (e.getSource() == txtTelefono)
			if (t.soloNumeros(e, txtTelefono, 10) == false)
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
		if (e.getSource() == tbCliente && e.getClickCount() == 1
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

	public void ingresarCliente() {
		if (!validarDatos()) {
			Mensaje.mensajeError("Datos incorrectos", "Error");
			return;
		}
		Cliente cli = new Cliente();
		cli.setNom_cli(getNombres());
		cli.setApe_pat(getApellidoPaterno());
		cli.setApe_mat(getApellidoMaterno());
		cli.setTel_cli(getTelefono());
		cli.setDni_cli(getDni());
		boolean result = manageCli.addCliente(cli);
		if (result) {
			Mensaje.mensajeCorrecto("Ingreso correcto", "Correcto");
			limpiarCampos();
			llenarTabla();
		} else {
			Mensaje.mensajeError("Error al ingresar", "Error");
		}
	}

	public void modificarCliente() {
		if (!validarDatos()) {
			Mensaje.mensajeError("Rellene todos los datos", "Error");
			return;
		}
		Cliente cli = new Cliente();
		cli.setCod_cli(getCodigo());
		cli.setNom_cli(getNombres());
		cli.setApe_pat(getApellidoPaterno());
		cli.setApe_mat(getApellidoMaterno());
		cli.setTel_cli(getTelefono());
		cli.setDni_cli(getDni());
		boolean result = manageCli.modificarCliente(cli);
		if (result) {
			limpiarCampos();
			Mensaje.mensajeCorrecto("Modificación correcta", "Correcto");
			llenarTabla();
		} else {
			Mensaje.mensajeError("Error al modificar", "Error");
		}
	}

	public void eliminarCliente() {
		if (txtCodigo.getText().equals("")) {
			Mensaje.mensajeError("Escoja cliente a eliminar", "Error");
			return;
		}
		boolean result = manageCli.deleteCliente(getCodigo());

		if (result) {
			limpiarCampos();
			Mensaje.mensajeCorrecto("Eliminación correcta", "Correcto");
			llenarTabla();

		} else {
			Mensaje.mensajeError("Error al eliminar", "Error");
		}
	}

	public boolean validarDatos() {
		if (getNombres().equals("") || getApellidoPaterno().equals("")
				|| getApellidoMaterno().equals("") || getTelefono().equals("")
				|| getDni().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public void cargarDatos() {
		try {

			int row = tbCliente.getSelectedRow();
			Cliente cli = new Cliente();
			cli = manageCli.buscarCliente(Integer.parseInt(tbCliente
					.getValueAt(row, 0).toString()));
			txtCodigo.setText(cli.getCod_cli() + "");
			txtNombres.setText(cli.getNom_cli());
			txtApePat.setText(cli.getApe_pat());
			txtApeMat.setText(cli.getApe_mat());
			txtTelefono.setText(cli.getTel_cli());
			txtDni.setText(cli.getDni_cli());
		} catch (Exception e) {

		}

	}

	public void llenarTabla() {
		limpiarTabla();
		ArrayList<Cliente> clientes = manageCli.listarCliente();
		for (Cliente cli : clientes) {
			Object datos[] = { cli.getCod_cli(), cli.getNom_cli(),
					cli.getApe_pat(), cli.getApe_mat(), cli.getTel_cli(),
					cli.getDni_cli() };
			modelCliente.addRow(datos);
		}
	}

	public void limpiarTabla() {
		int intL_filas = modelCliente.getRowCount();
		intL_filas--;
		for (int i = intL_filas; i >= 0; i--) {
			modelCliente.removeRow(i);
		}
	}

	public void limpiarCampos() {
		txtCodigo.setText("");
		txtNombres.setText("");
		txtApePat.setText("");
		txtApeMat.setText("");
		txtTelefono.setText("");
		txtDni.setText("");
	}

	public void cambiarOpcion() {
		switch (cboOpciones.getSelectedIndex()) {
		case 0:
			txtNombres.setEditable(true);
			txtApePat.setEditable(true);
			txtApeMat.setEditable(true);
			txtTelefono.setEditable(true);
			txtDni.setEditable(true);
			btnBuscar.setEnabled(false);
			limpiarCampos();
			break;
		case 1:
			txtNombres.setEditable(true);
			txtApePat.setEditable(true);
			txtApeMat.setEditable(true);
			txtTelefono.setEditable(true);
			txtDni.setEditable(true);
			btnBuscar.setEnabled(false);
			btnBuscar.setEnabled(true);
			limpiarCampos();
			break;
		default:
			txtNombres.setEditable(true);
			txtApePat.setEditable(true);
			txtApeMat.setEditable(true);
			txtTelefono.setEditable(true);
			txtDni.setEditable(true);
			btnBuscar.setEnabled(false);
			btnBuscar.setEnabled(true);
			limpiarCampos();
			break;
		}
	}

	public void cargarBusqueda(int codCli) {
		Cliente cli = new Cliente();
		cli = manageCli.buscarCliente(codCli);
		txtCodigo.setText(cli.getCod_cli() + "");
		txtNombres.setText(cli.getNom_cli());
		txtApePat.setText(cli.getApe_pat());
		txtApeMat.setText(cli.getApe_mat());
		txtTelefono.setText(cli.getTel_cli());
		txtDni.setText(cli.getDni_cli());
	}

	public void cargarData() {

	}

	// Metodos de Captura GUI
	public int getCodigo() {
		return Integer.parseInt(txtCodigo.getText());
	}

	public String getNombres() {
		return txtNombres.getText();
	}

	public String getApellidoPaterno() {
		return txtApePat.getText();
	}

	public String getApellidoMaterno() {
		return txtApeMat.getText();
	}

	public String getTelefono() {
		return txtTelefono.getText();
	}

	public String getDni() {
		return txtDni.getText();
	}

}
