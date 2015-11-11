package aloja.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import aloja.clases.Cliente;
import aloja.management.ClienteManagement;
import aloja.vista.ViewAlojamiento;
import aloja.vista.ViewCliente;
import aloja.vista.ViewConsumo;
import aloja.vista.ViewLiquidacion;
import aloja.vista.ViewPrincipal;

public class BuscarCliente extends JDialog implements MouseListener,
		ActionListener, KeyListener {

	private ViewCliente viewCliente;
	private ViewAlojamiento viewAlojamiento;
	private ViewConsumo viewConsumo;
	private ViewLiquidacion viewLiquidacion;

	JPanel pnlCliente = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTable tbCliente;
	private DefaultTableModel modelCliente;
	private JButton btnBuscar;
	private JLabel lblCdigo;
	private JLabel lblNombre;
	private JScrollPane scrollEmpleado;

	ClienteManagement manageCli = new ClienteManagement();

	public BuscarCliente(ViewCliente viewCliente,
			ViewAlojamiento viewAlojamiento, ViewConsumo viewConsumo,
			ViewLiquidacion viewLiquidacion) {
		this.viewCliente = viewCliente;
		this.viewAlojamiento = viewAlojamiento;
		this.viewConsumo = viewConsumo;
		this.viewLiquidacion = viewLiquidacion;

		setModal(true);
		setTitle("Buscar Cliente");
		setResizable(false);

		pnlCliente = new JPanel();
		pnlCliente.setPreferredSize(new Dimension(360, 270));
		pnlCliente.setBounds(0, 0, 700, 340);
		pnlCliente.setLayout(null);
		pnlCliente.setVisible(true);
		getContentPane().add(pnlCliente, BorderLayout.NORTH);

		lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(37, 23, 56, 14);
		pnlCliente.add(lblCdigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(103, 20, 95, 20);
		pnlCliente.add(txtCodigo);
		txtCodigo.setColumns(10);
		txtCodigo.addKeyListener(this);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(37, 48, 46, 14);
		pnlCliente.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(103, 45, 188, 20);
		pnlCliente.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.addKeyListener(this);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(202, 19, 89, 23);
		btnBuscar.addActionListener(this);
		pnlCliente.add(btnBuscar);

		scrollEmpleado = new JScrollPane();
		scrollEmpleado.setBounds(10, 73, 340, 181);
		pnlCliente.add(scrollEmpleado);

		tbCliente = new JTable();
		modelCliente = new DefaultTableModel(new Object[][] {}, new String[] {
				"C\u00F3digo", "Nombre", "Ape. Paterno", "Ape. Materno" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tbCliente.setModel(modelCliente);
		tbCliente.getColumnModel().getColumn(0).setResizable(false);
		tbCliente.addMouseListener(this);
		scrollEmpleado.setViewportView(tbCliente);
		llenarTabla();
		pack();

		setLocationRelativeTo(ViewPrincipal.view);
		setVisible(true);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		Tecla t = new Tecla();
		if (e.getSource() == txtCodigo)
			if (t.soloNumeros(e, txtCodigo, 3) == false)
				;
		if (e.getSource() == txtNombre)
			if (t.soloLetras(e, txtNombre, 50) == false)
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
	public void actionPerformed(ActionEvent e) {
		buscarCliente();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == tbCliente && e.getClickCount() == 2) {
			if (viewCliente != null) {
				viewCliente.cargarBusqueda(enviarCodigo());
			}
			if (viewAlojamiento != null) {
				viewAlojamiento.cargarBusquedaCliente(enviarCodigo());
			}
			if (viewConsumo != null) {
				viewConsumo.cargarBusquedaCliente(enviarCodigo());
			}
			if (viewLiquidacion != null) {
				viewLiquidacion.cargarBusquedaCliente(enviarCodigo());
			}

			dispose();

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

	public void buscarCliente() {
		if (!txtCodigo.getText().equals("")) {
			limpiarTabla();
			Cliente cli = new Cliente();
			cli = manageCli.buscarCliente(getCodigo());

			Object datos[] = { cli.getCod_cli(), cli.getNom_cli(),
					cli.getApe_pat(), cli.getApe_mat(), cli.getTel_cli(),
					cli.getDni_cli() };
			modelCliente.addRow(datos);
		} else if (!txtNombre.getText().equals("")) {
			limpiarTabla();
			ArrayList<Cliente> clientes = manageCli.buscarCliente(getNombres());
			for (Cliente cli : clientes) {
				Object datos[] = { cli.getCod_cli(), cli.getNom_cli(),
						cli.getApe_pat(), cli.getApe_mat(), cli.getTel_cli(),
						cli.getDni_cli() };
				modelCliente.addRow(datos);
			}
		} else {
			Mensaje.mensajeError("Ingrese criterio de búsqueda", "Error");
		}
	}

	public int enviarCodigo() {
		try {
			int row = tbCliente.getSelectedRow();
			int cod = Integer.parseInt(tbCliente.getValueAt(row, 0) + "");
			return cod;
		} catch (Exception e) {
		}
		return -1;
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
		txtNombre.setText("");

	}

	public int getCodigo() {
		return Integer.parseInt(txtCodigo.getText());
	}

	public String getNombres() {
		return txtNombre.getText();
	}
}
