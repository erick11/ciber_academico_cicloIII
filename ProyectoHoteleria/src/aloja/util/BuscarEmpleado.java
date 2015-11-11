package aloja.util;

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

import aloja.clases.Empleado;
import aloja.management.EmpleadoManagement;
import aloja.vista.ViewEmpleado;
import aloja.vista.ViewPrincipal;

import java.awt.BorderLayout;

public class BuscarEmpleado extends JDialog implements MouseListener,
		ActionListener, KeyListener {

	EmpleadoManagement arregloEmp = new EmpleadoManagement();
	JPanel pnlEmpleado = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTable tbEmpleado;
	private DefaultTableModel modelEmpleado;
	private ViewEmpleado parent;
	private JButton btnBuscar;
	private JLabel lblCdigo;
	private JLabel lblNombre;
	private JScrollPane scrollEmpleado;

	public BuscarEmpleado(ViewEmpleado parent) {
		this.parent = parent;
		setModal(true);
		setTitle("Buscar empleado");
		setResizable(false);

		pnlEmpleado = new JPanel();
		pnlEmpleado.setPreferredSize(new Dimension(360, 270));
		pnlEmpleado.setBounds(0, 0, 700, 340);
		pnlEmpleado.setLayout(null);
		pnlEmpleado.setVisible(true);
		getContentPane().add(pnlEmpleado, BorderLayout.NORTH);

		lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(37, 23, 56, 14);
		pnlEmpleado.add(lblCdigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(103, 20, 95, 20);
		pnlEmpleado.add(txtCodigo);
		txtCodigo.setColumns(10);
		txtCodigo.addKeyListener(this);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(37, 48, 46, 14);
		pnlEmpleado.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(103, 45, 188, 20);
		pnlEmpleado.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.addKeyListener(this);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(202, 19, 89, 23);
		btnBuscar.addActionListener(this);
		pnlEmpleado.add(btnBuscar);

		scrollEmpleado = new JScrollPane();
		scrollEmpleado.setBounds(10, 73, 340, 181);
		pnlEmpleado.add(scrollEmpleado);

		tbEmpleado = new JTable();
		modelEmpleado = new DefaultTableModel(new Object[][] {}, new String[] {
				"C\u00F3digo", "Nombre", "Ape. Paterno", "Ape. Materno" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tbEmpleado.setModel(modelEmpleado);
		tbEmpleado.getColumnModel().getColumn(0).setResizable(false);
		tbEmpleado.addMouseListener(this);
		scrollEmpleado.setViewportView(tbEmpleado);
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
		buscarEmpleado();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == tbEmpleado && e.getClickCount() == 2) {
			parent.cargarBusqueda(enviarCodigo());
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

	public void buscarEmpleado() {
		if (!txtCodigo.getText().equals("")) {
			limpiarTabla();
			Empleado emp = new Empleado();
			emp = arregloEmp.buscarEmpleado(getCodigo());

			Object datos[] = { emp.getCod_emp(), emp.getNom_emp(),
					emp.getApe_pat(), emp.getApe_mat() };
			modelEmpleado.addRow(datos);
		} else if (!txtNombre.getText().equals("")) {
			limpiarTabla();
			ArrayList<Empleado> empleados = arregloEmp
					.buscarEmpleado(getNombres());
			for (Empleado emp : empleados) {
				Object datos[] = { emp.getCod_emp(), emp.getNom_emp(),
						emp.getApe_pat(), emp.getApe_mat() };
				modelEmpleado.addRow(datos);
			}
		} else {
			Mensaje.mensajeError("Ingrese criterio de búsqueda", "Error");
		}
	}

	public int enviarCodigo() {
		try {
			int row = tbEmpleado.getSelectedRow();
			int cod = Integer.parseInt(tbEmpleado.getValueAt(row, 0) + "");
			return cod;
		} catch (Exception e) {
		}
		return -1;
	}

	public void llenarTabla() {
		limpiarTabla();
		ArrayList<Empleado> empleados = arregloEmp.listarEmpleado();
		for (Empleado emp : empleados) {
			Object datos[] = { emp.getCod_emp(), emp.getNom_emp(),
					emp.getApe_pat(), emp.getApe_mat() };
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
		txtNombre.setText("");

	}

	public int getCodigo() {
		return Integer.parseInt(txtCodigo.getText());
	}

	public String getNombres() {
		return txtNombre.getText();
	}
}
