package aloja.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;

public class ViewMantenimiento extends JPanel {
	private JTabbedPane tbpMantenimiento;
	private JPanel pnlEmpleado;
	private JPanel pnlCliente;
	private JPanel pnlHabitacion;
	public ViewMantenimiento() {
		setLayout(null);
		
		tbpMantenimiento = new JTabbedPane(JTabbedPane.TOP);
		tbpMantenimiento.setToolTipText("");
		tbpMantenimiento.setBounds(0, 0, 650, 380);
		add(tbpMantenimiento);
		
		pnlEmpleado = new ViewEmpleado();
		tbpMantenimiento.addTab("Empleado", null, pnlEmpleado, null);
		pnlEmpleado.setLayout(null);
		
		pnlCliente = new ViewCliente();
		tbpMantenimiento.addTab("Cliente", null, pnlCliente, null);
		pnlCliente.setLayout(null);
		
		pnlHabitacion = new ViewHabitacion();
		tbpMantenimiento.addTab("Habitaci\u00F3n", null, pnlHabitacion, null);
		pnlHabitacion.setLayout(null);
	}
}
