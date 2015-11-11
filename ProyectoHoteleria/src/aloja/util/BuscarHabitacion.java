package aloja.util;

import javax.swing.JDialog;

import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import aloja.clases.Habitacion;
import aloja.management.HabitacionManagement;
import aloja.vista.ViewAlojamiento;
import aloja.vista.ViewHabitacion;
import aloja.vista.ViewPrincipal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class BuscarHabitacion extends JDialog implements ActionListener,
		MouseListener {

	// Declaracion de Componentes
	private ViewHabitacion viewHabitacion;

	private JTextField txtNumeroHabitacion;
	private JTable tbHabitacion;
	private DefaultTableModel dtmHabitacion;
	private JLabel lblNumero;
	private JButton btnBuscar;
	private JScrollPane scrollHabitacion;

	// Componente control
	private HabitacionManagement habManage = new HabitacionManagement();

	// Constructor
	public BuscarHabitacion(ViewHabitacion viewHabitacion) {
		this.viewHabitacion = viewHabitacion;
		this.setTitle("Buscar Habitacion");
		this.setLocationRelativeTo(ViewPrincipal.view);
		this.setSize(360, 290);
		this.setVisible(true);
		this.setModal(true);
		this.getContentPane().setLayout(null);

		JPanel pnlDatos = new JPanel();
		pnlDatos.setBounds(0, 0, 344, 252);
		getContentPane().add(pnlDatos);
		pnlDatos.setLayout(null);

		lblNumero = new JLabel("Numero de Habitacion");
		lblNumero.setBounds(10, 28, 112, 14);
		pnlDatos.add(lblNumero);

		txtNumeroHabitacion = new JTextField();
		txtNumeroHabitacion.setBounds(131, 25, 122, 20);
		pnlDatos.add(txtNumeroHabitacion);
		txtNumeroHabitacion.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(263, 24, 72, 23);
		pnlDatos.add(btnBuscar);

		scrollHabitacion = new JScrollPane();
		scrollHabitacion.setBounds(10, 53, 325, 190);
		pnlDatos.add(scrollHabitacion);

		tbHabitacion = new JTable();
		dtmHabitacion = new DefaultTableModel(new Object[][] {}, new String[] {
				"Numero", "Categoria", "Tipo", "Precio" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tbHabitacion.setModel(dtmHabitacion);
		tbHabitacion.addMouseListener(this);
		scrollHabitacion.setViewportView(tbHabitacion);

		llenarTabla();
	}

	// Eventos
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnBuscar)) {
			if (!txtNumeroHabitacion.getText().equals("")) {
				limpiarTabla();
				Habitacion hab = new Habitacion();
				hab = habManage.buscarHabitacion(getNumero());

				Object[] arDatos = { hab.getNum_hab(), hab.getDes_cat(),
						hab.getDes_tip(), hab.getPre_dia() };
				dtmHabitacion.addRow(arDatos);
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == tbHabitacion && e.getClickCount() == 2) {
			if (viewHabitacion != null) {
				viewHabitacion.cargarBusqueda(enviarCodigo());
			}

			dispose();

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// Metodos
	public int enviarCodigo() {
		try {
			int row = tbHabitacion.getSelectedRow();
			int cod = Integer.parseInt(tbHabitacion.getValueAt(row, 0) + "");
			return cod;
		} catch (Exception e) {
		}
		return -1;
	}

	public void llenarTabla() {
		limpiarTabla();
		ArrayList<Habitacion> arHabitacion = new ArrayList<Habitacion>();
		arHabitacion = habManage.listarHabitacion();
		for (Habitacion hab : arHabitacion) {
			Object datos[] = { hab.getNum_hab(), hab.getDes_cat(),
					hab.getDes_tip(), hab.getPre_dia() };
			dtmHabitacion.addRow(datos);
		}
	}

	public void limpiarTabla() {
		int intL_filas = dtmHabitacion.getRowCount();
		intL_filas--;
		for (int i = intL_filas; i >= 0; i--) {
			dtmHabitacion.removeRow(i);
		}
	}

	public void limpiarCampos() {
		txtNumeroHabitacion.setText("");
	}

	// Captura de Datos
	public int getNumero() {
		return Integer.parseInt(txtNumeroHabitacion.getText());
	}

}
