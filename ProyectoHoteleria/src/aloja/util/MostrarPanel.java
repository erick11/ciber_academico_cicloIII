package aloja.util;

import javax.swing.JPanel;

import aloja.vista.ViewPrincipal;

public class MostrarPanel {

	public MostrarPanel() {

	}

	public void mostrarPanel(JPanel pnlMostrado) {
		ocultarPaneles();
		ViewPrincipal.view.viewOpciones.setVisible(true);
		pnlMostrado.setVisible(true);
	}

	public void salir() {
		ocultarPaneles();
		ViewPrincipal.view.viewLogin.setVisible(true);
	}

	public void ingresar() {
		ocultarPaneles();
		ViewPrincipal.view.viewOpciones.setVisible(true);
	}
	
	public void ocultarPaneles(){
		ViewPrincipal.view.viewMantenimiento.setVisible(false);
		ViewPrincipal.view.viewConsumo.setVisible(false);
		ViewPrincipal.view.viewLogin.setVisible(false);
		ViewPrincipal.view.viewAlojamiento.setVisible(false);
		ViewPrincipal.view.viewLogin.setVisible(false);
		ViewPrincipal.view.viewReporte.setVisible(false);
		ViewPrincipal.view.viewOpciones.setVisible(false);
		ViewPrincipal.view.viewLiquidacion.setVisible(false);
	}
}
