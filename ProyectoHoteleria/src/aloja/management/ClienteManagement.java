package aloja.management;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import aloja.clases.Cliente;
import aloja.util.Conexion;
import aloja.util.Mensaje;

public class ClienteManagement {

	private ArrayList<Cliente> arCliente;
	private Conexion con = new Conexion();

	public ClienteManagement() {
		arCliente = new ArrayList<Cliente>();
	}

	public Cliente buscarCliente(int codCli) {
		String sql = "select * from tb_cliente where cod_cli = ?";
		Cliente cli = new Cliente();
		try {
			PreparedStatement st = con.getCt().prepareStatement(sql);
			st.setInt(1, codCli);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				cli.setCod_cli(rs.getInt(1));
				cli.setNom_cli(rs.getString(2));
				cli.setApe_pat(rs.getString(3));
				cli.setApe_mat(rs.getString(4));
				cli.setTel_cli(rs.getString(5));
				cli.setDni_cli(rs.getString(6));
			}
			return cli;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Cliente> buscarCliente(String nomCli) {
		String sql = "select * from tb_cliente where nom_cli like '%" + nomCli
				+ "%' or ape_pat like '%" + nomCli + "%' or ape_mat like '%"
				+ nomCli + "%'";
		try {
			PreparedStatement st = con.getCt().prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			arCliente.clear();
			while (rs.next()) {
				Cliente cli = new Cliente();
				cli.setCod_cli(rs.getInt(1));
				cli.setNom_cli(rs.getString(2));
				cli.setApe_pat(rs.getString(3));
				cli.setApe_mat(rs.getString(4));
				cli.setTel_cli(rs.getString(5));
				cli.setDni_cli(rs.getString(6));
				arCliente.add(cli);
			}
			return arCliente;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addCliente(Cliente cli) {
		boolean flag = false;
		String sql = "insert into tb_cliente(nom_cli,ape_pat,ape_mat,tel_cli,"
				+ "dni_cli) values (?,?,?,?,?)";
		try {
			PreparedStatement pst = con.getCt().prepareStatement(sql);
			pst.setString(1, cli.getNom_cli());
			pst.setString(2, cli.getApe_pat());
			pst.setString(3, cli.getApe_mat());
			pst.setString(4, cli.getTel_cli());
			pst.setString(5, cli.getDni_cli());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean modificarCliente(Cliente cli) {
		boolean flag = false;
		String sql = "update tb_cliente set nom_cli =?, ape_pat =?, ape_mat =?,"
				+ "tel_cli=?,dni_cli=? where cod_cli = ?";
		try {
			PreparedStatement pst = con.getCt().prepareStatement(sql);
			pst.setString(1, cli.getNom_cli());
			pst.setString(2, cli.getApe_pat());
			pst.setString(3, cli.getApe_mat());
			pst.setString(4, cli.getTel_cli());
			pst.setString(5, cli.getDni_cli());
			pst.setInt(6, cli.getCod_cli());

			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean deleteCliente(int codCli) {
		boolean flag = false;
		String sql = "delete from tb_cliente where cod_cli = ?";
		try {
			PreparedStatement pst = con.getCt().prepareStatement(sql);
			pst.setInt(1, codCli);
			pst.executeUpdate();
			flag = true;

		} catch (MySQLIntegrityConstraintViolationException e) {
			Mensaje.mensajeError("Cliente alojado, no se puede eliminar",
					"Error");
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public ArrayList<Cliente> listarCliente() {
		String sql = "select * from tb_cliente";
		try {
			PreparedStatement st = con.getCt().prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			arCliente.clear();
			while (rs.next()) {
				Cliente cli = new Cliente();
				cli.setCod_cli(rs.getInt(1));
				cli.setNom_cli(rs.getString(2));
				cli.setApe_pat(rs.getString(3));
				cli.setApe_mat(rs.getString(4));
				cli.setTel_cli(rs.getString(5));
				cli.setDni_cli(rs.getString(6));
				arCliente.add(cli);
			}

			return arCliente;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// public static void main(String[] args) {
	// ArregloCliente a= new ArregloCliente();
	// boolean res= a.confirmarAutenticacion("jpepon", "pepon");
	// System.out.println(res);
	//
	//
	// a.buscarCliente(1);
	// }
}
