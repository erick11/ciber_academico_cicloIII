package aloja.util;

import java.awt.event.*;

import javax.swing.*;

public class Tecla {
	public Tecla() {

	}

	public boolean soloLetras(KeyEvent e, JTextField txt, int maximo) {
		char x = e.getKeyChar();
		if (Character.isDigit(x) && x != ' ' && x != '\b') {
			e.consume();
			return false;
		}
		if (txt.getText().length() == maximo) {
			e.consume();
			return false;
		}
		return true;
	}

	public boolean soloNumeros(KeyEvent e, JTextField txt, int maximo) {
		char x = e.getKeyChar();
		if (!Character.isDigit(x) && x != '\b') {
			e.consume();
			return false;
		}
		if (txt.getText().length() == maximo) {
			e.consume();
		}
		return true;
	}

	public boolean tamanoCadena(KeyEvent e, JTextField txt, int maximo) {
		if (txt.getText().length() == maximo) {
			e.consume();
		}
		return true;
	}

	public boolean tamanoCadena(KeyEvent e, JTextArea txt, int maximo) {
		if (txt.getText().length() == maximo) {
			e.consume();
		}
		return true;
	}

	public boolean readable(KeyEvent e, JTextField txt) {
		char x = e.getKeyChar();

		
		e.consume();
		return true;
	}

	public boolean soloDecimal(KeyEvent e, JTextField txt, int maximo) {
		char x = e.getKeyChar();
		if (!Character.isDigit(x) && x != '.' && x != '\b') {
			e.consume();
			return false;
		}
		if (txt.getText().length() == maximo) {
			e.consume();
		}
		return true;
	}

	public boolean soloRuc(KeyEvent e, JTextField txt, int maximo) {
		char x = e.getKeyChar();
		if (!Character.isDigit(x) && x != '\b') {
			e.consume();
			return false;
		}
		if (txt.getText().length() == maximo) {
			e.consume();
		}
		return true;
	}
}
