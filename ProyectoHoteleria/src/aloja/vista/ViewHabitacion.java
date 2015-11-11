package aloja.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;

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
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import aloja.clases.Cliente;
import aloja.clases.Habitacion;
import aloja.management.HabitacionManagement;
import aloja.util.BuscarCliente;
import aloja.util.BuscarHabitacion;
import aloja.util.Mensaje;
import aloja.util.Tecla;

public class ViewHabitacion extends JPanel implements ActionListener,
		KeyListener, MouseListener, ItemListener {
	// Declaracion de Componentes
	private JLabel lblTitulo;
	private JLabel lblNumero;
	private JTextField txtNumero;
	private JTextField txtPrecioPD;
	private JLabel lblCategoria;
	private JLabel lblPrecioxDia;
	private JComboBox cboTipo;
	private JLabel lblTipo;
	private JScrollPane scrollHabitacion;
	private JTable tbHabitacion;
	private DefaultTableModel dtmbleHabitacion;
	private JButton btnBuscar;
	private JButton btnAceptar;
	private JComboBox cboOpciones;
	private JSeparator separator;
	private JLabel lblOpcin;
	private JComboBox cboCategoria;

	// Componente control
	HabitacionManagement manageHab = new HabitacionManagement();

	// Metodo Constructor
	public ViewHabitacion() {
		this.setLayout(null);
		this.setSize(650, 343);

		lblTitulo = new JLabel("Mantenimiento de  Habitaci\u00F3n");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(21, 11, 281, 19);
		add(lblTitulo);

		lblNumero = new JLabel("N\u00FAmero");
		lblNumero.setBounds(34, 76, 46, 20);
		add(lblNumero);

		lblCategoria = new JLabel("Categor\u00EDa");
		lblCategoria.setBounds(285, 76, 71, 20);
		add(lblCategoria);

		txtNumero = new JTextField();
		txtNumero.setEnabled(false);
		txtNumero.setBounds(76, 76, 162, 20);
		txtNumero.setText(getNumeroAutogenerado());
		add(txtNumero);
		txtNumero.setColumns(10);

		lblPrecioxDia = new JLabel("Precio P/ D  S/.");
		lblPrecioxDia.setBounds(285, 107, 83, 20);
		add(lblPrecioxDia);

		txtPrecioPD = new JTextField();
		txtPrecioPD.setBounds(367, 107, 144, 20);
		add(txtPrecioPD);
		txtPrecioPD.setColumns(10);

		lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(34, 107, 56, 20);
		add(lblTipo);

		cboTipo = new JComboBox();
		cboTipo.setModel(new DefaultComboBoxModel(new String[] { "Simple",
				"Doble", "Triple", "Matrimonial" }));
		cboTipo.setToolTipText("");
		cboTipo.setBounds(76, 107, 162, 20);
		add(cboTipo);

		separator = new JSeparator();
		separator.setBounds(10, 35, 630, 2);
		add(separator);

		scrollHabitacion = new JScrollPane();
		scrollHabitacion.setBounds(10, 165, 622, 167);
		add(scrollHabitacion);

		tbHabitacion = new JTable();
		dtmbleHabitacion = new DefaultTableModel(new Object[][] {},
				new String[] { "N\u00FAmero", "Categor\u00EDa", "Tipo",
						"Precio P/ D" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tbHabitacion.setModel(dtmbleHabitacion);
		tbHabitacion.getColumnModel().getColumn(0).setPreferredWidth(68);
		tbHabitacion.getColumnModel().getColumn(1).setPreferredWidth(53);
		tbHabitacion.getColumnModel().getColumn(2).setPreferredWidth(93);
		tbHabitacion.getColumnModel().getColumn(3).setPreferredWidth(94);
		tbHabitacion.addMouseListener(this);
		scrollHabitacion.setViewportView(tbHabitacion);

		btnBuscar = new JButton(new ImageIcon("images/search.png"));
		btnBuscar.setBounds(248, 75, 24, 24);
		btnBuscar.setEnabled(false);
		btnBuscar.addActionListener(this);
		add(btnBuscar);

		cboOpciones = new JComboBox();
		cboOpciones.setModel(new DefaultComboBoxModel(new String[] {
				"Ingresar", "Modificar", "Eliminar" }));
		cboOpciones.setBounds(278, 41, 109, 20);
		cboOpciones.addItemListener(this);
		add(cboOpciones);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(203, 138, 89, 23);
		btnAceptar.addActionListener(this);
		add(btnAceptar);

		lblOpcin = new JLabel("Opci\u00F3n");
		lblOpcin.setBounds(226, 41, 46, 20);
		add(lblOpcin);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(312, 138, 89, 23);
		add(btnLimpiar);

		cboCategoria = new JComboBox();
		cboCategoria.setModel(new DefaultComboBoxModel(new String[] { "A", "B",
				"C" }));
		cboCategoria.setBounds(367, 76, 144, 20);
		add(cboCategoria);

		llenarTabla();
	}

	// Eventos
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAceptar) {
			switch (cboOpciones.getSelectedIndex()) {
			case 0:
				ingresarHabitacion();
				break;
			case 1:
				modificarHabitacion();
				break;
			default:
				int yesNoResponse = JOptionPane.showConfirmDialog(null,
						"Está seguro que desea eliminar esta Habitacion?",
						"Mensaje", JOptionPane.YES_NO_OPTION);
				if (yesNoResponse == JOptionPane.YES_OPTION) {
					eliminarHabitacion();
				}
				break;
			}
		}
		if (e.getSource() == btnBuscar) {
			new BuscarHabitacion(this);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(cboOpciones)) {
			cambiarOpcion();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == tbHabitacion && e.getClickCount() == 1
				&& cboOpciones.getSelectedItem().equals("Modificar")
				|| cboOpciones.getSelectedItem().equals("Eliminar")) {
			cargarDatos();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

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
		if (e.getSource().equals(txtPrecioPD)) {
			if (t.soloDecimal(e, txtPrecioPD, 2) == false)
				;
		}

	}

	// Metodos Operativos
	public void ingresarHabitacion() {
		if (!validarDatos()) {
			Mensaje.mensajeError("Datos incorrectos", "Error");
			return;
		}
		Habitacion hab = new Habitacion();
		hab.setCat_hab(getCategoria());
		hab.setTip_hab(getTipo());
		hab.setPre_dia(getPrecio());

		boolean result = manageHab.addHabitacion(hab);
		if (result) {
			Mensaje.mensajeCorrecto("Ingreso correcto", "Correcto");

			limpiarCampos();
			llenarTabla();
		} else {
			Mensaje.mensajeError("Error al ingresar", "Error");
		}
	}

	public void modificarHabitacion() {
		if (!validarDatos()) {
			Mensaje.mensajeError("Rellene todos los datos", "Error");
			return;
		}
		Habitacion hab = new Habitacion();
		hab.setNum_hab(getNumero());
		hab.setCat_hab(getCategoria());
		hab.setTip_hab(getTipo());
		hab.setPre_dia(getPrecio());

		boolean result = manageHab.modifHabitacion(hab);
		if (result) {
			limpiarCampos();
			Mensaje.mensajeCorrecto("Modificación correcta", "Correcto");
			llenarTabla();
		} else {
			Mensaje.mensajeError("Error al modificar", "Error");
		}
	}

	public void eliminarHabitacion() {
		if (txtNumero.getText().equals("")) {
			Mensaje.mensajeError("Escoja el empleado a eliminar", "Error");
			return;
		}
		boolean result = manageHab.deleteHabitacion(getNumero());
		if (result) {
			limpiarCampos();
			Mensaje.mensajeCorrecto("Eliminación correcta", "Correcto");
			llenarTabla();

		} else {
		}
	}

	public boolean validarDatos() {
		if (txtPrecioPD.getText().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public void llenarTabla() {
		limpiarTabla();
		ArrayList<Habitacion> arHabitacion = manageHab.listarHabitacion();

		for (Habitacion hab : arHabitacion) {
			Object datos[] = { hab.getNum_hab(), hab.getDes_cat(),
					hab.getDes_tip(), hab.getPre_dia() };
			dtmbleHabitacion.addRow(datos);
		}
	}

	public void limpiarTabla() {
		int can_filas = dtmbleHabitacion.getRowCount();
		can_filas--;
		for (int i = can_filas; i >= 0; i--) {
			dtmbleHabitacion.removeRow(i);
		}
	}

	// Seleccion de Combo
	public void cambiarOpcion() {

		switch (cboOpciones.getSelectedIndex()) {
		case 0:
			cboCategoria.setEnabled(true);
			cboTipo.setEnabled(true);
			txtPrecioPD.setEditable(true);
			btnBuscar.setEnabled(false);
			limpiarCampos();
			break;
		case 1:

			cboCategoria.setEnabled(true);
			cboTipo.setEnabled(true);
			txtPrecioPD.setEditable(true);
			btnBuscar.setEnabled(true);
			limpiarCampos();
			break;
		default:

			cboCategoria.setEnabled(false);
			cboTipo.setEnabled(false);
			txtPrecioPD.setEditable(false);
			btnBuscar.setEnabled(true);
			limpiarCampos();
			break;
		}
	}

	public void limpiarCampos() {
		txtNumero.setText(getNumeroAutogenerado());
		cboCategoria.setSelectedIndex(0);
		cboTipo.setSelectedIndex(0);
		txtPrecioPD.setText("");

	}

	// Tabla
	public void cargarDatos() {
		try {
			int row = tbHabitacion.getSelectedRow();
			Habitacion hab = new Habitacion();
			hab = manageHab.buscarHabitacion(Integer.parseInt(tbHabitacion
					.getValueAt(row, 0).toString()));
			txtNumero.setText(hab.getNum_hab() + "");
			cboCategoria.setSelectedIndex(hab.getCat_hab());
			cboTipo.setSelectedIndex(hab.getTip_hab());
			txtPrecioPD.setText(hab.getPre_dia() + "");
		} catch (Exception e) {

		}

	}

	public void cargarBusqueda(int codHab) {
		Habitacion hab = new Habitacion();
		hab = manageHab.buscarHabitacion(codHab);

		txtNumero.setText(hab.getNum_hab() + "");
		cboCategoria.setSelectedIndex(hab.getCat_hab());
		cboTipo.setSelectedIndex(hab.getTip_hab());
		txtPrecioPD.setText(hab.getPre_dia() + "");
	}

	// Codigo Autogenerado
	private String getNumeroAutogenerado() {
		int num = manageHab.getUltimoNumeroHabitacion() + 1;
		String numHab = num + "";
		return numHab;
	}

	// Metodos de Captura de datos (GUI)
	public int getNumero() {
		return Integer.parseInt(txtNumero.getText());
	}

	public int getCategoria() {
		return cboCategoria.getSelectedIndex();
	}

	public int getTipo() {
		return cboTipo.getSelectedIndex();
	}

	public double getPrecio() {
		return Double.parseDouble(txtPrecioPD.getText());
	}

}
