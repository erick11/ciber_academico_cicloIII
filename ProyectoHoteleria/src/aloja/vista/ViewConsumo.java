package aloja.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import aloja.clases.Cliente;
import aloja.clases.Consumo;
import aloja.clases.ConsumoDetalle;
import aloja.clases.Producto;
import aloja.management.ClienteManagement;
import aloja.management.ConsumoManagement;
import aloja.management.ProductoManagement;
import aloja.util.BuscarCliente;
import aloja.util.DatePicker;
import aloja.util.Mensaje;

public class ViewConsumo extends JPanel implements ActionListener,
		MouseListener {
	
	// Declaracion de Componestes
	private JTextField txtCantidad;
	private JTable tbCompra;
	private JLabel lblCdigo;
	private JTextField txtCodCon;
	private JTextField txtCodCli;
	private JTextField txtNomCli;
	private JTextField txtFecPedido;
	private JTextField txtTotal;
	private JTable tbProducto;
	private JButton btnProcesar;
	private JButton btnBuscarCli;
	private JPanel pnlCliente;
	private JLabel lblCodCli;
	private JLabel lblNomCli;
	private JPanel pnlConsumo;
	private JLabel lblNewLabel;
	private JComboBox cboServicio;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JScrollPane scrollProducto;
	private JButton btnAgregar;
	private JButton btnQuitar;
	private JLabel lblTotal;
	private JButton btnLimpiar;
	private JSeparator separator;
	private DefaultTableModel modelProducto;
	private DefaultTableModel modelComprada;
	//
	ClienteManagement cliManage = new ClienteManagement();
	ProductoManagement managePro = new ProductoManagement();
	ConsumoManagement manageCon = new ConsumoManagement();

	public ViewConsumo() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(null);

		JLabel lblTitulo = new JLabel("Registro de Consumo");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(21, 11, 237, 19);
		add(lblTitulo);

		separator = new JSeparator();
		separator.setBounds(10, 35, 630, 2);
		add(separator);

		pnlCliente = new JPanel();
		pnlCliente.setLayout(null);
		pnlCliente.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Cliente",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCliente.setBounds(142, 41, 355, 78);
		add(pnlCliente);

		lblCodCli = new JLabel("C\u00F3digo");
		lblCodCli.setBounds(9, 25, 33, 14);
		pnlCliente.add(lblCodCli);

		txtCodCli = new JTextField();
		txtCodCli.setEditable(false);
		txtCodCli.setColumns(10);
		txtCodCli.setBounds(63, 22, 154, 20);
		pnlCliente.add(txtCodCli);

		lblNomCli = new JLabel("Nombre");
		lblNomCli.setBounds(9, 50, 46, 14);
		pnlCliente.add(lblNomCli);

		txtNomCli = new JTextField();
		txtNomCli.setEditable(false);
		txtNomCli.setColumns(10);
		txtNomCli.setBounds(63, 47, 268, 20);
		pnlCliente.add(txtNomCli);

		btnBuscarCli = new JButton(new ImageIcon("images/search.png"));
		btnBuscarCli.addActionListener(this);
		btnBuscarCli.setBounds(227, 19, 24, 24);
		pnlCliente.add(btnBuscarCli);

		pnlConsumo = new JPanel();
		pnlConsumo.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Datos de Consumo",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlConsumo.setBounds(38, 127, 565, 193);
		add(pnlConsumo);
		pnlConsumo.setLayout(null);

		lblNewLabel = new JLabel("Servicio");
		lblNewLabel.setBounds(154, 24, 46, 14);
		pnlConsumo.add(lblNewLabel);

		cboServicio = new JComboBox();
		cboServicio.setModel(new DefaultComboBoxModel(new String[] { "Comedor",
				"Habitaci\u00F3n" }));
		cboServicio.setBounds(204, 21, 88, 20);
		pnlConsumo.add(cboServicio);

		lblNewLabel_1 = new JLabel("Fec. Pedido");
		lblNewLabel_1.setBounds(315, 24, 72, 14);
		pnlConsumo.add(lblNewLabel_1);

		txtFecPedido = new JTextField();
		txtFecPedido.setBounds(380, 21, 96, 20);
		pnlConsumo.add(txtFecPedido);
		txtFecPedido.addMouseListener(this);
		txtFecPedido.setColumns(10);

		lblNewLabel_2 = new JLabel("Productos");
		lblNewLabel_2.setBounds(21, 46, 72, 14);
		pnlConsumo.add(lblNewLabel_2);

		btnAgregar = new JButton(new ImageIcon("images/right.png"));
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(257, 113, 37, 32);
		pnlConsumo.add(btnAgregar);

		btnQuitar = new JButton(new ImageIcon("images/left.png"));
		btnQuitar.addActionListener(this);
		btnQuitar.setBounds(257, 147, 37, 32);
		pnlConsumo.add(btnQuitar);

		lblTotal = new JLabel("Total");
		lblTotal.setBounds(431, 162, 32, 14);
		pnlConsumo.add(lblTotal);

		txtTotal = new JTextField("0");
		txtTotal.setEditable(false);
		txtTotal.setBounds(466, 159, 78, 20);
		pnlConsumo.add(txtTotal);
		txtTotal.setColumns(10);

		scrollProducto = new JScrollPane();
		scrollProducto.setBounds(21, 62, 227, 120);
		pnlConsumo.add(scrollProducto);

		tbProducto = new JTable();

		modelProducto = new DefaultTableModel(new Object[][] {}, new String[] {
				"C\u00F3d.", "Descripci\u00F3n", "S/.", "Stock" }) {
			// esto hace las celdas no editables
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		tbProducto.setModel(modelProducto);
		tbProducto.getColumnModel().getColumn(0).setPreferredWidth(41);
		tbProducto.getColumnModel().getColumn(1).setPreferredWidth(137);
		tbProducto.getColumnModel().getColumn(2).setPreferredWidth(55);
		tbProducto.getColumnModel().getColumn(3).setPreferredWidth(57);
		scrollProducto.setViewportView(tbProducto);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(252, 90, 42, 20);
		pnlConsumo.add(txtCantidad);
		txtCantidad.setColumns(10);

		JLabel lblCant = new JLabel("Cantidad");
		lblCant.setBounds(252, 74, 46, 14);
		pnlConsumo.add(lblCant);

		JScrollPane scrollCompra = new JScrollPane();
		scrollCompra.setBounds(298, 62, 246, 96);
		pnlConsumo.add(scrollCompra);

		tbCompra = new JTable();
		modelComprada = new DefaultTableModel(new Object[][] {}, new String[] {
				"Cod. Prod", "Cantidad", "P.U.", "Sub-Total" }) {
			// esto hace las celdas no editables
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		tbCompra.setModel(modelComprada);
		scrollCompra.setViewportView(tbCompra);

		lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(21, 24, 46, 14);
		pnlConsumo.add(lblCdigo);

		txtCodCon = new JTextField();
		txtCodCon.setEditable(false);
		txtCodCon.setEnabled(false);
		txtCodCon.setBounds(70, 21, 63, 20);
		pnlConsumo.add(txtCodCon);
		txtCodCon.setColumns(10);

		btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(this);
		btnProcesar.setBounds(250, 331, 89, 23);
		add(btnProcesar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(349, 331, 89, 23);
		add(btnLimpiar);
		txtCodCon.setText(manageCon.getCodigoConsumo() + "");
		
		llenarTablaProductos();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnBuscarCli)) {
			new BuscarCliente(null, null, this, null);
		}
		if (e.getSource() == btnAgregar) {
			agregarProducto();
		}
		if (e.getSource() == btnQuitar) {
			removerProducto();
		}
		if (e.getSource() == btnProcesar) {
			registrarConsumo();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		txtFecPedido
				.setText(new DatePicker(ViewPrincipal.view).setPickedDate());

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

	public void registrarConsumo() {
		if (!validarDatos()) {
			Mensaje.mensajeError("Datos faltantes", "Error");
			return;
		}
		Consumo con = new Consumo();
		con.setCod_cli(Integer.parseInt(getCodcli()));
		con.setSer_con(cboServicio.getSelectedIndex());
		con.setFec_ped(getFecha());
		con.setTot_con(Double.parseDouble(getTotal()));

		boolean result = false;

		result = manageCon.addConsumo(con);
		if (result) {
			registrarDetalleConsumo();
			limpiarCampos();
			Mensaje.mensajeCorrecto("Consumo registrado", "Correcto");
			txtCodCon.setText(manageCon.getCodigoConsumo() + "");
		} else {
			Mensaje.mensajeError("Error al ingresar consumo", "Error");
		}

	}

	public void registrarDetalleConsumo() {
		for (int i = tbCompra.getRowCount() - 1; i >= 0; i--) {
			ConsumoDetalle det = new ConsumoDetalle();
			det.setCod_con(Integer.parseInt(getCodConsumo()));
			det.setCod_pro(Integer.parseInt(tbCompra.getValueAt(i, 0) + ""));
			det.setCan_det(Integer.parseInt(tbCompra.getValueAt(i, 1) + ""));
			det.setPre_uni(Double.parseDouble(tbCompra.getValueAt(i, 2) + ""));
			det.setSub_tot(Double.parseDouble(tbCompra.getValueAt(i, 3) + ""));
			manageCon.addDetalleConsumo(det);

		}
		limpiarTablaCompra();
	}

	public void agregarProducto() {
		try {
			if (getCantidad().equals("")) {
				Mensaje.mensajeError("Ingrese cantidad", "Error");
				return;
			}
			int row = tbProducto.getSelectedRow();
			String codProd = tbProducto.getValueAt(row, 0).toString();
			List<String> arr = new ArrayList<String>();

			for (int i = tbCompra.getRowCount() - 1; i >= 0; i--) {
				arr.add(tbCompra.getValueAt(i, 0).toString());
			}

			if (arr.contains(codProd)) {
				Mensaje.mensajeError("Ya se agregó producto", "Error");
				return;
			}
			Producto prod = new Producto();
			prod = managePro.buscarProducto(Integer.parseInt(codProd));
			double subT = prod.getPre_pro() * Integer.parseInt(getCantidad());
			double total = Double.parseDouble(getTotal());
			txtTotal.setText((total + subT) + "");
			Object datos[] = { prod.getCod_pro(), getCantidad(),
					prod.getPre_pro(), subT };
			modelComprada.addRow(datos);

		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public void removerProducto() {
		try {
			int row = tbCompra.getSelectedRow();
			double subT = Double.parseDouble(tbCompra.getValueAt(row, 3) + "");
			double total = Double.parseDouble(getTotal());
			txtTotal.setText((total - subT) + "");
			modelComprada.removeRow(row);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public void limpiarCampos() {
		txtCodCli.setText("");
		txtNomCli.setText("");
		txtCantidad.setText("");
		txtFecPedido.setText("");
		txtTotal.setText("0");

	}

	public boolean validarDatos() {
		if (getCodcli().equals("") || getCodConsumo().equals("")
				|| getFecha().equals("") || getTotal().equals("")
				|| getCantidad().equals("") || tbCompra.getRowCount() < 1) {
			return false;
		}
		return true;
	}

	// Metodos logicos
	public void cargarBusquedaCliente(int codCli) {
		Cliente cli = new Cliente();
		cli = cliManage.buscarCliente(codCli);
		txtCodCli.setText(cli.getCod_cli() + "");
		txtNomCli.setText(cli.getNom_cli() + " " + cli.getApe_pat() + " "
				+ cli.getApe_mat());
	}

	public void llenarTablaProductos() {
		limpiarTabla();
		ArrayList<Producto> prods = managePro.listarProducto();
		for (Producto pro : prods) {
			Object datos[] = { pro.getCod_pro(), pro.getDes_pro(),
					pro.getPre_pro() + "", pro.getSto_pro() };
			modelProducto.addRow(datos);
		}
	}

	public void limpiarTabla() {
		int intL_filas = modelProducto.getRowCount();
		intL_filas--;
		for (int i = intL_filas; i >= 0; i--) {
			modelProducto.removeRow(i);
		}
	}

	public void limpiarTablaCompra() {
		int intL_filas = modelComprada.getRowCount();
		intL_filas--;
		for (int i = intL_filas; i >= 0; i--) {
			modelComprada.removeRow(i);
		}
	}

	private String getCantidad() {
		return txtCantidad.getText();
	}

	private String getCodConsumo() {
		return txtCodCon.getText();
	}

	private String getCodcli() {
		return txtCodCli.getText();
	}

	private String getFecha() {
		return txtFecPedido.getText();
	}

	private String getTotal() {
		return txtTotal.getText();
	}
}
