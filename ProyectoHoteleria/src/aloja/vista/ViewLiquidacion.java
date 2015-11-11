package aloja.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.border.EtchedBorder;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

import aloja.clases.Cliente;
import aloja.clases.Habitacion;
import aloja.clases.Producto;
import aloja.management.AlojamientoManagement;
import aloja.management.ClienteManagement;
import aloja.management.ConsumoManagement;
import aloja.management.HabitacionManagement;
import aloja.management.ProductoManagement;
import aloja.util.BuscarCliente;
import aloja.util.ManejadorFechas;
import aloja.util.Mensaje;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

public class ViewLiquidacion extends JPanel implements ActionListener, MouseListener {

	// Declaracion de Componentes
	public static JTextField txtCodEmp;
	public static JTextField txtNomEmp;

	private JLabel lblTitulo;
	private JTextField txtCodCliente;
	private JTextField txtFechaSistema;
	private JTextField txtHoraSistema;
	private JLabel lblCodigoCliente;
	private JButton btnBuscar;
	private JLabel lblFechaDeSistema;
	private JLabel lblHoraDeSistema;
	private JButton btnDetalle;
	private JList lstLiquiHabitacion;
	private DefaultListModel dlmLiquiHabitacion;
	private JList lstLiquiProducto;
	private DefaultListModel dlmLiquiProducto;
	private JScrollPane scrollLiquiProducto;
	private JScrollPane scrollLiquiHabitacion;
	private JTextField txtTotal;
	private JSeparator separator;
	private JButton btnLiquidar;
	private JLabel lblTotal;
	private JLabel lblProductos;
	private JLabel lblHabitacin;
	private JLabel lblEmpleado;

	ClienteManagement cliManage = new ClienteManagement();
	AlojamientoManagement aloManage = new AlojamientoManagement();
	ConsumoManagement conManage = new ConsumoManagement();
	HabitacionManagement habManage = new HabitacionManagement();
	ProductoManagement proManage = new ProductoManagement();

	// Constructor
	public ViewLiquidacion() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(null);

		lblTitulo = new JLabel("Liquidaci\u00F3n");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(20, 11, 172, 19);
		add(lblTitulo);

		separator = new JSeparator();
		separator.setBounds(10, 35, 630, 2);
		add(separator);

		lblCodigoCliente = new JLabel("Codigo Cliente");
		lblCodigoCliente.setBounds(20, 67, 81, 20);
		add(lblCodigoCliente);

		txtCodCliente = new JTextField();
		txtCodCliente.setBounds(128, 67, 117, 20);
		add(txtCodCliente);
		txtCodCliente.setColumns(10);

		btnBuscar = new JButton();
		btnBuscar.addActionListener(this);
		btnBuscar.setIcon(new ImageIcon("images/search.png"));
		btnBuscar.setBounds(254, 65, 24, 24);
		add(btnBuscar);

		lblFechaDeSistema = new JLabel("Fecha de Sistema");
		lblFechaDeSistema.setBounds(20, 98, 100, 20);
		add(lblFechaDeSistema);

		txtFechaSistema = new JTextField();
		txtFechaSistema.setBounds(128, 98, 117, 20);
		add(txtFechaSistema);
		txtFechaSistema.setText(ManejadorFechas.getFechaActual());
		txtFechaSistema.setColumns(10);

		lblHoraDeSistema = new JLabel("Hora de Sistema");
		lblHoraDeSistema.setBounds(20, 129, 81, 20);
		add(lblHoraDeSistema);

		txtHoraSistema = new JTextField();
		txtHoraSistema.setBounds(128, 129, 117, 20);
		txtHoraSistema.setText(ManejadorFechas.getHoraActual());
		add(txtHoraSistema);
		txtHoraSistema.setColumns(10);

		scrollLiquiHabitacion = new JScrollPane();
		scrollLiquiHabitacion.setBounds(20, 185, 258, 147);
		add(scrollLiquiHabitacion);

		dlmLiquiHabitacion = new DefaultListModel();
		lstLiquiHabitacion = new JList();
		lstLiquiHabitacion.setModel(dlmLiquiHabitacion);
		scrollLiquiHabitacion.setViewportView(lstLiquiHabitacion);

		btnDetalle = new JButton("Ver Detalle");
		btnDetalle.addActionListener(this);
		btnDetalle.setBounds(256, 128, 89, 23);
		add(btnDetalle);

		scrollLiquiProducto = new JScrollPane();
		scrollLiquiProducto.setBounds(368, 181, 266, 151);
		add(scrollLiquiProducto);

		dlmLiquiProducto = new DefaultListModel();
		lstLiquiProducto = new JList();
		lstLiquiProducto.setModel(dlmLiquiProducto);
		scrollLiquiProducto.setViewportView(lstLiquiProducto);

		lblHabitacin = new JLabel("Habitaci\u00F3n(es)");
		lblHabitacin.setBounds(20, 160, 81, 14);
		add(lblHabitacin);

		lblProductos = new JLabel("Producto(s)");
		lblProductos.setBounds(368, 156, 81, 14);
		add(lblProductos);

		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(274, 343, 86, 20);
		add(txtTotal);
		txtTotal.setColumns(10);

		lblTotal = new JLabel("Total S/.");
		lblTotal.setBounds(218, 343, 46, 20);
		add(lblTotal);

		btnLiquidar = new JButton("Liquidar");
		btnLiquidar.addActionListener(this);
		btnLiquidar.setBounds(378, 343, 89, 23);
		add(btnLiquidar);

		lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setBounds(20, 41, 46, 20);
		add(lblEmpleado);

		txtCodEmp = new JTextField();
		txtCodEmp.setEnabled(false);
		txtCodEmp.setBounds(128, 41, 86, 20);
		add(txtCodEmp);
		txtCodEmp.setColumns(10);

		txtNomEmp = new JTextField();
		txtNomEmp.setEnabled(false);
		txtNomEmp.setBounds(236, 41, 86, 20);
		add(txtNomEmp);
		txtNomEmp.setColumns(10);
	}

	// Eventos
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnBuscar)) {
			new BuscarCliente(null, null, null, this);
		}
		if (e.getSource().equals(btnDetalle)) {
			llenarListas();
		}
		if (e.getSource().equals(btnLiquidar)) {
			liquidarHabPro();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	// Metodos Logicos
	public void llenarListas() {
		limpiarListas();
		ArrayList<Habitacion> arHabitacion = habManage
				.buscarHabitacionCliente(getCodigoCliente());
		ArrayList<Producto> arProducto = proManage
				.buscarProductosCliente(getCodigoCliente());

		double subHab = 0.0;
		double subPro = 0.0;
		double total = 0.0;
		for (Habitacion habitacion : arHabitacion) {
			dlmLiquiHabitacion.addElement("Numero de Habitacion: "
					+ habitacion.getNum_hab());
			dlmLiquiHabitacion.addElement("Categoria: "
					+ habitacion.getDes_cat());
			dlmLiquiHabitacion.addElement("Tipo: " + habitacion.getDes_tip());
			dlmLiquiHabitacion.addElement("Precio: " + habitacion.getPre_dia());
			subHab += habitacion.getPre_dia();
		}
		dlmLiquiHabitacion.addElement("Monto por Habitacion(es): " + subHab);
		for (Producto producto : arProducto) {
			dlmLiquiProducto.addElement("Producto: " + producto.getDes_pro());
			dlmLiquiProducto.addElement("Precio: " + producto.getPre_pro());
			dlmLiquiProducto.addElement("Cantidad: " + producto.getSto_pro());
			subPro += (producto.getSto_pro() * producto.getPre_pro());
		}
		dlmLiquiProducto.addElement("Monto por Producto(s): " + subPro);

		total = subHab + subPro;
		txtTotal.setText(Double.toString(total));
	}

	public void limpiarListas() {
		int can_filas_hab = dlmLiquiHabitacion.getSize();
		int can_filas_pro = dlmLiquiProducto.getSize();

		if (can_filas_hab > 0) {
			dlmLiquiHabitacion.removeElementAt(0);
			// Vacía la JList
			dlmLiquiHabitacion.clear();
		}

		if (can_filas_pro > 0) {
			dlmLiquiProducto.removeElementAt(0);
			// Vacía la JList
			dlmLiquiProducto.clear();
		}
	}

	public void cargarBusquedaCliente(int codCli) {
		Cliente cli = new Cliente();
		cli = cliManage.buscarCliente(codCli);
		txtCodCliente.setText(cli.getCod_cli() + "");

	}

	private void liquidarHabPro() {
		ArrayList<Habitacion> arHab = habManage
				.buscarHabitacionCliente(getCodigoCliente());
		ArrayList<Producto> arPro = proManage
				.buscarProductosCliente(getCodigoCliente());

		boolean r1 = aloManage.cambiarEstadoPagado(getCodigoEmpledo(),getCodigoCliente());
		boolean r2 = conManage.cambiarEstadoPagado(getCodigoCliente());
		
		System.out.println("r2: "+r2);
		for (Habitacion hab : arHab) {
			boolean r = habManage.cambiarEstadoLibre(hab.getNum_hab());
			if (!r) {
				Mensaje.mensajeCorrecto("Su liquidacion no se efectuo","Liquidacion");
			}
		}
		for (Producto pro : arPro) {
			boolean r = proManage.actualizarStock(pro.getCod_pro(),pro.getSto_pro());
			if (!r) {
				Mensaje.mensajeCorrecto("Su liquidacion no se efectuo",
						"Liquidacion");
			}
		}
		if (r1 && r2) {
			Mensaje.mensajeCorrecto("Su liquidacion se efectuo correctamente",
					"Liquidacion");
			limpiarListas();
		} else {
			Mensaje.mensajeCorrecto("Su liquidacion no se efectuo",
					"Liquidacion");
		}

	}

	// Captura de Datos (GUI)

	public int getCodigoEmpledo() {
		return Integer.parseInt(txtCodEmp.getText());
	}

	public int getCodigoCliente() {
		return Integer.parseInt(txtCodCliente.getText());
	}

	public String getFecha() {
		return txtFechaSistema.getText();
	}

	public String getHora() {
		return txtHoraSistema.getText();
	}

	
}
