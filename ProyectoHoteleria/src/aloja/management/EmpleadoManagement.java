package aloja.management;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import aloja.clases.Empleado;
import aloja.util.Conexion;
import aloja.util.Mensaje;

public class EmpleadoManagement {

	private ArrayList<Empleado> arEmpleado;
	private Conexion con = new Conexion();

	public EmpleadoManagement() {
		arEmpleado = new ArrayList<Empleado>();
	}

	public Empleado confirmarAutenticacion(String user, String pass) {
		String sql = "SELECT * FROM tb_empleado WHERE usu_emp = ? AND pas_emp = ?";
		Empleado emp = null;
		try {
			PreparedStatement st = con.getCt().prepareStatement(sql);
			st.setString(1, user);
			st.setString(2, pass);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				emp = new Empleado();
				emp.setCod_emp(rs.getInt(1));
				emp.setNom_emp(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	public Empleado buscarEmpleado(int codEmp) {
		String sql = "select * from tb_empleado where cod_emp = ?";
		Empleado emp = new Empleado();
		try {
			PreparedStatement st = con.getCt().prepareStatement(sql);
			st.setInt(1, codEmp);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				emp.setCod_emp(rs.getInt(1));
				emp.setNom_emp(rs.getString(2));
				emp.setApe_pat(rs.getString(3));
				emp.setApe_mat(rs.getString(4));
				emp.setTip_emp(rs.getInt(5));
				emp.setUsu_emp(rs.getString(6));
				emp.setPas_emp(rs.getString(7));
			}
			return emp;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Empleado> buscarEmpleado(String nomEmp) {
		String sql = "select * from tb_empleado where nom_emp like '%" + nomEmp
				+ "%' or ape_pat like '%" + nomEmp + "%' or ape_mat like '%"
				+ nomEmp + "%'";
		try {
			PreparedStatement st = con.getCt().prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			arEmpleado.clear();
			while (rs.next()) {
				Empleado emp = new Empleado();
				emp.setCod_emp(rs.getInt(1));
				emp.setNom_emp(rs.getString(2));
				emp.setApe_pat(rs.getString(3));
				emp.setApe_mat(rs.getString(4));
				emp.setTip_emp(rs.getInt(5));
				emp.setUsu_emp(rs.getString(6));
				emp.setPas_emp(rs.getString(7));
				arEmpleado.add(emp);
			}
			return arEmpleado;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addEmpleado(Empleado emp) {
		boolean flag = false;
		String sql = "insert into tb_empleado(nom_emp,ape_pat,ape_mat,tip_emp,"
				+ "usu_emp,pas_emp) values (?,?,?,?,?,?)";
		try {
			PreparedStatement pst = con.getCt().prepareStatement(sql);
			pst.setString(1, emp.getNom_emp());
			pst.setString(2, emp.getApe_pat());
			pst.setString(3, emp.getApe_mat());
			pst.setInt(4, emp.getTip_emp());
			pst.setString(5, emp.getUsu_emp());
			pst.setString(6, emp.getPas_emp());

			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean modificarEmpleado(Empleado emp) {
		boolean flag = false;
		String sql = "update tb_empleado set nom_emp =?, ape_pat =?, ape_mat =?,"
				+ "tip_emp=? where cod_emp = ?";
		try {
			PreparedStatement pst = con.getCt().prepareStatement(sql);
			pst.setString(1, emp.getNom_emp());
			pst.setString(2, emp.getApe_pat());
			pst.setString(3, emp.getApe_mat());
			pst.setInt(4, emp.getTip_emp());
			pst.setInt(5, emp.getCod_emp());

			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean deleteEmpleado(int codEmp) {
		boolean flag = false;
		String sql = "delete from tb_empleado where cod_emp = ?";
		try {
			PreparedStatement pst = con.getCt().prepareStatement(sql);
			pst.setInt(1, codEmp);
			pst.executeUpdate();
			flag = true;

		} catch (MySQLIntegrityConstraintViolationException e) {
			Mensaje.mensajeError("El empleado tiene alojamientos registrados",
					"Error");
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public ArrayList<Empleado> listarEmpleado() {
		String sql = "select * from tb_empleado";
		try {
			PreparedStatement st = con.getCt().prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			arEmpleado.clear();
			while (rs.next()) {
				Empleado emp = new Empleado();
				emp.setCod_emp(rs.getInt(1));
				emp.setNom_emp(rs.getString(2));
				emp.setApe_pat(rs.getString(3));
				emp.setApe_mat(rs.getString(4));
				emp.setTip_emp(rs.getInt(5));
				emp.setUsu_emp(rs.getString(6));
				emp.setPas_emp(rs.getString(7));
				arEmpleado.add(emp);
			}

			return arEmpleado;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
