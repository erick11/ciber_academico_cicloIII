package aloja.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

	private final static String drv = "com.mysql.jdbc.Driver";
	private final static String db = "jdbc:mysql://localhost:3306/bd_ser_hotel";
	private final static String user = "root";
	private final static String pass = "mysql";
	private Connection ct;

	public Conexion() {
		try {
			Class.forName(drv);
			ct = DriverManager.getConnection(db, user, pass);
		} catch (ClassNotFoundException e) {
			System.out.println("No existe jar de conexión");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Connection getCt() {
		return ct;
	}

	public void setCt(Connection ct) {
		this.ct = ct;
	}

}
