package aloja.util;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Mensaje {

	public Mensaje() {

	}

	public static void mensajeCorrecto(String s, String titulo) {
		JOptionPane.showMessageDialog(null, s, titulo,
				JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
						"images/buena.gif"));
	}

	public static void mensajeError(String s, String titulo) {
		JOptionPane.showMessageDialog(null, s, titulo,
				JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/x.png"));
	}

}
