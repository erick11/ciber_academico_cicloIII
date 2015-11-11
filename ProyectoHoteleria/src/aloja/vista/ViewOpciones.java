package aloja.vista;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;

import aloja.util.MostrarPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewOpciones extends JPanel implements ActionListener {
	
	//Declaracion de Componentes
	private JButton btnConsumo;
	private JButton btnMantenimiento;
	private JButton btnAlojamiento;
	private JButton btnLiquidacion;
	private JButton btnReportes;
	private JButton btnSalir;
	
	
	private MostrarPanel mostrarPanel = new MostrarPanel();
	
	public ViewOpciones() {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null), "Men\u00FA", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		setLayout(null);

		btnMantenimiento = new JButton(new ImageIcon("images/support.png"));
		btnMantenimiento.setText("Mantenimiento");
		btnMantenimiento.addActionListener(this);
		btnMantenimiento.setBounds(10, 31, 163, 40);
		add(btnMantenimiento);
		
		btnAlojamiento = new JButton(new ImageIcon("images/aloja.png"));
		btnAlojamiento.setText("Alojamiento");
		btnAlojamiento.addActionListener(this);
		btnAlojamiento.setBounds(10, 82, 163, 40);
		add(btnAlojamiento);

		btnConsumo = new JButton(new ImageIcon("images/consume.png"));
		btnConsumo.setText("Reg. de Consumo");
		btnConsumo.addActionListener(this);
		btnConsumo.setBounds(10, 133, 163, 40);
		add(btnConsumo);

		btnLiquidacion = new JButton(new ImageIcon("images/pay.png"));
		btnLiquidacion.setText("Liquidaci\u00F3n");
		btnLiquidacion.addActionListener(this);
		btnLiquidacion.setBounds(10, 184, 163, 40);
		add(btnLiquidacion);

		btnReportes = new JButton(new ImageIcon("images/chart.png"));
		btnReportes.setText("Reportes");
		btnReportes.addActionListener(this);
		btnReportes.setBounds(10, 235, 163, 40);
		add(btnReportes);

		btnSalir = new JButton(new ImageIcon("images/exit.png"));
		btnSalir.setText("Salir");
		btnSalir.addActionListener(this);
		btnSalir.setBounds(10, 286, 163, 30);
		add(btnSalir);
	}

	//Eventos
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnMantenimiento)){
			mostrarPanel.mostrarPanel(ViewPrincipal.view.viewMantenimiento);
		}
		if(e.getSource().equals(btnAlojamiento)){
			mostrarPanel.mostrarPanel(ViewPrincipal.view.viewAlojamiento);
		}
		if(e.getSource().equals(btnConsumo)){
			mostrarPanel.mostrarPanel(ViewPrincipal.view.viewConsumo);
		}
		if(e.getSource().equals(btnLiquidacion)){
			mostrarPanel.mostrarPanel(ViewPrincipal.view.viewLiquidacion);
		}
		if(e.getSource().equals(btnReportes)){
			mostrarPanel.mostrarPanel(ViewPrincipal.view.viewReporte);
		}
		if(e.getSource().equals(btnSalir)){
			salir();
		}
	}
	
	public void salir() {
		mostrarPanel.salir();
	}


}
