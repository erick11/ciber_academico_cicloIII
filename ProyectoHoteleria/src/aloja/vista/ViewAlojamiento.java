package aloja.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import aloja.clases.Alojamiento;
import aloja.clases.AlojamientoDetalle;
import aloja.clases.Cliente;
import aloja.clases.Empleado;
import aloja.clases.Habitacion;
import aloja.management.AlojamientoManagement;
import aloja.management.ClienteManagement;
import aloja.management.HabitacionManagement;
import aloja.util.BuscarCliente;
import aloja.util.DatePicker;
import aloja.util.Mensaje;
import aloja.util.Tecla;

public class ViewAlojamiento extends JPanel implements ActionListener,
		MouseListener, KeyListener, ItemListener {

	private JTextField txtCodCli;
	private JTextField txtNomCli;
	public static JTextField txtNomEmp;
	private JTextField txtCodAloja;
	private JTextField txtFecLlegada;
	private JTextField txtFecSalida;
	private JTable tbHabitacion;
	private JButton btnBuscarCli;
	private JComboBox cboTipo;
	private JComboBox cboCategoria;
	private JButton btnAgregar;
	private JButton btnQuitar;
	private JScrollPane scrollHabitacion;
	private JLabel lblCategoria;
	private JPanel pnlHabitacion;
	private JPanel pnlCliente;
	private JLabel lblCodCli;
	private JLabel lblNombre;
	private JLabel lblHabitacionesDisponibles;
	private JLabel lblEmpleado;
	private JPanel pnlAlojamiento;
	private JLabel lblCdigo;
	private JLabel lblFecLlegada;
	private JLabel lblHorLlegada;
	private JLabel lblFecSalida;
	private JLabel lblHorSalida;
	private JButton btnProcesar;
	private JButton btnLimpiar;
	private JSpinner spHoraLle;
	private JSpinner spMinLle;
	private JSpinner spHorSal;
	private JSpinner spMinSal;
	private DefaultTableModel modelHabitacion;
	private DefaultTableModel modelReservada;
	public static JTextField txtCodEmp;
	private JTable tbReservada;

	private static final long serialVersionUID = 960068713361447741L;
	private ClienteManagement manageCli = new ClienteManagement();
	private AlojamientoManagement manageAlo = new AlojamientoManagement();
	private HabitacionManagement manageHab = new HabitacionManagement();
	
	Tecla t = new Tecla();
	
	public ViewAlojamiento() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.setLayout(null);

		pnlCliente = new JPanel();
		pnlCliente.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Cliente",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCliente.setBounds(20, 67, 327, 84);
		add(pnlCliente);
		pnlCliente.setLayout(null);

		lblCodCli = new JLabel("C\u00F3digo");
		lblCodCli.setBounds(9, 25, 72, 14);
		pnlCliente.add(lblCodCli);

		txtCodCli = new JTextField();
		txtCodCli.setEditable(false);
		txtCodCli.setBounds(71, 25, 154, 20);
		pnlCliente.add(txtCodCli);
		txtCodCli.setColumns(10);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(9, 50, 72, 14);
		pnlCliente.add(lblNombre);

		txtNomCli = new JTextField();
		txtNomCli.setEditable(false);
		txtNomCli.setBounds(71, 50, 240, 20);
		pnlCliente.add(txtNomCli);
		txtNomCli.setColumns(10);

		btnBuscarCli = new JButton(new ImageIcon("images/search.png"));
		btnBuscarCli.setBounds(235, 25, 24, 24);
		btnBuscarCli.addActionListener(this);
		pnlCliente.add(btnBuscarCli);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 35, 630, 2);
		add(separator);

		JLabel lblTitulo = new JLabel("Alojamiento");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(20, 11, 172, 19);
		add(lblTitulo);

		pnlHabitacion = new JPanel();
		pnlHabitacion.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Habitaciones",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlHabitacion.setBounds(20, 156, 406, 213);
		add(pnlHabitacion);
		pnlHabitacion.setLayout(null);

		lblCategoria = new JLabel("Categor\u00EDa");
		lblCategoria.setBounds(10, 24, 68, 14);
		pnlHabitacion.add(lblCategoria);

		cboCategoria = new JComboBox();
		cboCategoria.setModel(new DefaultComboBoxModel(new String[] { "A", "B",
				"C" }));
		cboCategoria.setBounds(88, 19, 48, 20);
		cboCategoria.addItemListener(this);
		pnlHabitacion.add(cboCategoria);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(146, 24, 32, 14);
		pnlHabitacion.add(lblTipo);

		cboTipo = new JComboBox();
		cboTipo.setModel(new DefaultComboBoxModel(new String[] { "Simple",
				"Doble", "Triple", "Matrimonial" }));
		cboTipo.setBounds(188, 19, 136, 20);
		cboTipo.addItemListener(this);
		pnlHabitacion.add(cboTipo);

		lblHabitacionesDisponibles = new JLabel("Habitaciones disponibles");
		lblHabitacionesDisponibles.setBounds(11, 53, 167, 14);
		pnlHabitacion.add(lblHabitacionesDisponibles);

		btnAgregar = new JButton(new ImageIcon("images/right.png"));
		btnAgregar.setBounds(263, 99, 37, 32);
		btnAgregar.addActionListener(this);
		pnlHabitacion.add(btnAgregar);

		btnQuitar = new JButton(new ImageIcon("images/left.png"));
		btnQuitar.setBounds(263, 135, 37, 32);
		btnQuitar.addActionListener(this);
		pnlHabitacion.add(btnQuitar);

		scrollHabitacion = new JScrollPane();
		scrollHabitacion.setBounds(10, 68, 243, 134);
		pnlHabitacion.add(scrollHabitacion);

		tbHabitacion = new JTable();
		modelHabitacion = new DefaultTableModel(new Object[][] {},
				new String[] { "N\u00FAmero", "S/.", "Tipo", "Categoría" }) {
			// esto hace las celdas no editables
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		tbHabitacion.setModel(modelHabitacion);
		scrollHabitacion.setViewportView(tbHabitacion);

		JScrollPane scrollReservada = new JScrollPane();
		scrollReservada.setBounds(310, 68, 68, 134);
		pnlHabitacion.add(scrollReservada);

		tbReservada = new JTable();
		modelReservada = new DefaultTableModel(new Object[][] {},
				new String[] { "N\u00FAmero" }) {
			boolean[] columnEditables = new boolean[] { false, true, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		tbReservada.setModel(modelReservada);
		scrollReservada.setViewportView(tbReservada);

		lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setBounds(420, 48, 66, 14);
		add(lblEmpleado);

		txtNomEmp = new JTextField();
		txtNomEmp.setEditable(false);
		txtNomEmp.setBounds(531, 45, 98, 20);
		add(txtNomEmp);
		txtNomEmp.setColumns(10);

		pnlAlojamiento = new JPanel();
		pnlAlojamiento.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Datos de alojamiento",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlAlojamiento.setBounds(436, 67, 204, 159);
		add(pnlAlojamiento);
		pnlAlojamiento.setLayout(null);

		lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(10, 22, 75, 14);
		pnlAlojamiento.add(lblCdigo);

		txtCodAloja = new JTextField();
		txtCodAloja.setEditable(false);
		txtCodAloja.setBounds(95, 17, 99, 20);
		pnlAlojamiento.add(txtCodAloja);
		txtCodAloja.setColumns(10);

		lblFecLlegada = new JLabel("Fec. Llegada");
		lblFecLlegada.setBounds(10, 47, 85, 14);
		pnlAlojamiento.add(lblFecLlegada);

		lblHorLlegada = new JLabel("Hor. Llegada");
		lblHorLlegada.setBounds(10, 72, 85, 14);
		pnlAlojamiento.add(lblHorLlegada);

		lblFecSalida = new JLabel("Fec. Salida");
		lblFecSalida.setBounds(10, 97, 85, 14);
		pnlAlojamiento.add(lblFecSalida);

		lblHorSalida = new JLabel("Hor. Salida");
		lblHorSalida.setBounds(10, 122, 85, 14);
		pnlAlojamiento.add(lblHorSalida);

		txtFecLlegada = new JTextField();
		txtFecLlegada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtFecLlegada.setText(new DatePicker(ViewPrincipal.view)
						.setPickedDate());
			}
		});
		txtFecLlegada.setColumns(10);
		txtFecLlegada.setBounds(95, 42, 99, 20);
		pnlAlojamiento.add(txtFecLlegada);

		txtFecSalida = new JTextField();
		txtFecSalida.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getSource() == txtFecSalida)
					if (t.readable(e, txtFecSalida) == false)
						;
			}
		});
		txtFecSalida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtFecSalida.setText(new DatePicker(ViewPrincipal.view)
						.setPickedDate());
			}
		});
		txtFecSalida.setColumns(10);
		txtFecSalida.setBounds(95, 92, 99, 20);
		pnlAlojamiento.add(txtFecSalida);

		spHoraLle = new JSpinner();
		spHoraLle.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		spHoraLle.setBounds(95, 69, 39, 20);
		pnlAlojamiento.add(spHoraLle);

		spMinLle = new JSpinner();
		spMinLle.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spMinLle.setBounds(144, 69, 50, 20);
		pnlAlojamiento.add(spMinLle);

		spHorSal = new JSpinner();
		spHorSal.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		spHorSal.setBounds(95, 119, 39, 20);
		pnlAlojamiento.add(spHorSal);

		spMinSal = new JSpinner();
		spMinSal.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spMinSal.setBounds(144, 119, 50, 20);
		pnlAlojamiento.add(spMinSal);

		btnProcesar = new JButton("Procesar");
		btnProcesar.setBounds(465, 252, 89, 30);
		btnProcesar.addActionListener(this);
		add(btnProcesar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(465, 291, 89, 30);
		btnLimpiar.addActionListener(this);
		add(btnLimpiar);

		txtCodAloja.setText(manageAlo.getCodigoAlojamiento() + "");

		txtCodEmp = new JTextField();
		txtCodEmp.setEditable(false);
		txtCodEmp.setBounds(481, 45, 44, 20);
		add(txtCodEmp);
		txtCodEmp.setColumns(10);

		buscarHabitacion();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboTipo || e.getSource() == cboCategoria) {
			buscarHabitacion();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

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
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscarCli) {
			new BuscarCliente(null, this, null, null);
		}
		if (e.getSource() == btnProcesar) {
			registrarAlojamiento();
		}
		if (e.getSource() == btnAgregar) {
			agregarHabitacion();
		}
		if (e.getSource() == btnQuitar) {
			removerHabitacion();
		}

	}

	public void registrarAlojamiento() {
		if (!validarDatos()) {
			Mensaje.mensajeError("Datos faltantes", "Error");
			return;
		}
		Alojamiento alo = new Alojamiento();
		alo.setCod_cli(Integer.parseInt(getCodCliente()));
		alo.setCod_emp(Integer.parseInt(getCodEmpleado()));
		alo.setFec_lle(getFechaLlegada());
		alo.setHor_lle(getHoraLlegada());
		alo.setFec_sal(getFechaSalida());
		alo.setHor_sal(getHoraSalida());
		boolean result = false;

		result = manageAlo.addAlojamiento(alo);
		if (result) {
			registrarDetalleAlojamiento();
			limpiarCampos();
			Mensaje.mensajeCorrecto("Alojamiento registrado", "Correcto");
			txtCodAloja.setText(manageAlo.getCodigoAlojamiento() + "");
			buscarHabitacion();
		} else {
			Mensaje.mensajeError("Error al ingresar alojamiento", "Error");
		}

	}

	public void registrarDetalleAlojamiento() {
		for (int i = tbReservada.getRowCount() - 1; i >= 0; i--) {
			AlojamientoDetalle det = new AlojamientoDetalle();
			det.setCod_alo(Integer.parseInt(txtCodAloja.getText()));
			det.setCod_hab(Integer.parseInt(tbReservada.getValueAt(i, 0) + ""));
			manageAlo.addDetalleAlojamiento(det);
			manageHab.cambiarEstadoOcupada(det.getCod_hab());
		}
	}

	public void agregarHabitacion() {
		try {
			int row = tbHabitacion.getSelectedRow();
			String newHab = tbHabitacion.getValueAt(row, 0).toString();
			List<String> arr = new ArrayList<String>();

			for (int i = tbReservada.getRowCount() - 1; i >= 0; i--) {
				arr.add(tbReservada.getValueAt(i, 0).toString());
			}

			if (arr.contains(newHab)) {
				Mensaje.mensajeError("Ya se agregó habitación", "Error");
				return;
			}
			Object datos[] = { newHab };
			modelReservada.addRow(datos);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removerHabitacion() {
		try {
			int row = tbReservada.getSelectedRow();
			modelReservada.removeRow(row);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public void cargarBusquedaCliente(int codCli) {
		Cliente cli = new Cliente();
		cli = manageCli.buscarCliente(codCli);
		txtCodCli.setText(cli.getCod_cli() + "");
		txtNomCli.setText(cli.getNom_cli() + " " + cli.getApe_pat() + " "
				+ cli.getApe_mat());
	}

	public void buscarHabitacion() {
		limpiarTablaHabitacion();
		ArrayList<Habitacion> habs = manageHab.buscarHabitacionTipoCategoria(
				cboTipo.getSelectedIndex(), cboCategoria.getSelectedIndex());
		for (Habitacion hab : habs) {
			Object datos[] = { hab.getNum_hab(), hab.getPre_dia(),
					hab.getDes_tip(), hab.getDes_cat() };
			modelHabitacion.addRow(datos);
		}
	}

	public boolean validarDatos() {
		if (txtCodCli.getText().equals("") || txtCodEmp.getText().equals("")
				|| txtCodAloja.getText().equals("")
				|| txtFecLlegada.getText().equals("")
				|| txtFecSalida.getText().equals("")
				|| tbReservada.getRowCount() < 1) {
			return false;
		}
		return true;
	}

	public void limpiarTablaHabitacion() {
		int intL_filas = modelHabitacion.getRowCount();
		intL_filas--;
		for (int i = intL_filas; i >= 0; i--) {
			modelHabitacion.removeRow(i);
		}
	}

	public void limpiarCampos() {
		txtCodCli.setText("");
		txtNomCli.setText("");
		txtFecLlegada.setText("");
		txtFecSalida.setText("");
		spHoraLle.setValue(0);
		spMinLle.setValue(0);
		spHorSal.setValue(0);
		spMinSal.setValue(0);
	}

	public String getCodAlojamiento() {
		return txtCodAloja.getText();
	}

	public String getFechaLlegada() {
		return txtFecLlegada.getText();
	}

	public String getHoraLlegada() {
		return spHoraLle.getValue().toString() + ":"
				+ spMinLle.getValue().toString();
	}

	public String getFechaSalida() {
		return txtFecSalida.getText();
	}

	public String getHoraSalida() {
		return spHorSal.getValue().toString() + ":"
				+ spMinSal.getValue().toString();
	}

	public String getCodCliente() {
		return txtCodCli.getText();
	}

	public String getCodEmpleado() {
		return txtCodEmp.getText();
	}
}
