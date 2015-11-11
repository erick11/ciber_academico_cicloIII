package aloja.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JSeparator;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewReporte extends JPanel implements ActionListener {
	private JComboBox cboOpciones;
	private JSeparator separator;
	private JLabel lblTitulo;

	public ViewReporte() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(null);

		lblTitulo = new JLabel("Reportes");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(20, 11, 172, 19);
		add(lblTitulo);

		separator = new JSeparator();
		separator.setBounds(10, 35, 630, 2);
		add(separator);

		cboOpciones = new JComboBox();
		cboOpciones.addActionListener(this);
		cboOpciones.setModel(new DefaultComboBoxModel(new String[] {
				"Clientes Alojados", "Clientes Liquidados",
				"Relacion de Consumo Pendiente por Clientes ",
				"Relacion de Empleados que han efectuado Alojamiento" }));
		cboOpciones.setBounds(30, 48, 585, 20);
		add(cboOpciones);

	}

	// Eventos
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(cboOpciones)) {
			switch (cboOpciones.getSelectedIndex()) {
			case 0:
				reporteClientesAlojados();
				System.out.println("primero");
				break;
			case 1:
				reporteClientesLiquidados();
				System.out.println("segundo");
				break;
			case 2:
				reporteRelacionConsumoPendienteClientes();
				System.out.println("tercero");
				break;

			default:
				reporteRelacionEmpleadosefectuadoAlojamiento();
				System.out.println("cuarto");
				break;
			}
		}

	}

	private void reporteRelacionEmpleadosefectuadoAlojamiento() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 97, 589, 238);
		add(scrollPane);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"New column", "New column", "New column", "New column" }));
		scrollPane.setViewportView(table);

	}

	private void reporteRelacionConsumoPendienteClientes() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 97, 589, 238);
		add(scrollPane);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"New column", "New column", "New column" }));
		scrollPane.setViewportView(table);

	}

	private void reporteClientesLiquidados() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 97, 589, 238);
		add(scrollPane);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"New column", "New column" }));
		scrollPane.setViewportView(table);

	}

	private void reporteClientesAlojados() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 97, 589, 238);
		add(scrollPane);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "New column" }));
		scrollPane.setViewportView(table);

	}
}
