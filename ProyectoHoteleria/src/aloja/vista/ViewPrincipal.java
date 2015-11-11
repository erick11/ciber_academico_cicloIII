package aloja.vista;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class ViewPrincipal extends JFrame {

	// Declaracion de Componentes
	public JPanel viewAlojamiento;
	public JPanel viewConsumo;
	public JPanel viewLogin;
	public JPanel viewOpciones;
	public JPanel viewMantenimiento;
	public JPanel viewReporte;
	public JPanel viewLiquidacion;
	public static ViewPrincipal view;

	// Metodo COnstructor
	public ViewPrincipal() {
		super();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			this.setTitle("Cadena de Hoteles Marte S.A.");

			getContentPane().setLayout(null);

			viewLogin = new ViewLogin();
			this.add(viewLogin);
			viewLogin.setBounds(0, 0, 879, 470);
			viewLogin.setVisible(true);

			viewAlojamiento = new ViewAlojamiento();
			this.add(viewAlojamiento);
			viewAlojamiento.setBounds(209, 30, 650, 380);
			viewAlojamiento.setVisible(false);

			viewConsumo = new ViewConsumo();
			this.add(viewConsumo);
			viewConsumo.setBounds(209, 30, 650, 380);
			viewConsumo.setVisible(false);

			viewOpciones = new ViewOpciones();
			this.add(viewOpciones);
			viewOpciones.setBounds(20, 30, 183, 380);
			viewOpciones.setVisible(false);

			viewMantenimiento = new ViewMantenimiento();
			this.add(viewMantenimiento);
			viewMantenimiento.setBounds(209, 30, 670, 380);
			viewMantenimiento.setVisible(false);

			viewReporte = new ViewReporte();
			this.add(viewReporte);
			viewReporte.setBounds(209, 30, 650, 380);
			viewReporte.setVisible(false);

			viewLiquidacion = new ViewLiquidacion();
			this.add(viewLiquidacion);
			viewLiquidacion.setBounds(209, 30, 650, 380);
			viewLiquidacion.setVisible(false);

			view = this;

			pack();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ViewPrincipal inst = new ViewPrincipal();
				inst.setResizable(false);
				inst.setVisible(true);

				inst.setMinimumSize(new Dimension(879, 470));

			}
		});
	}
}
