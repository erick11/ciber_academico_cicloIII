package aloja.management;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import aloja.clases.Empleado;
import aloja.clases.Habitacion;
import aloja.util.Conexion;

public class HabitacionManagement {

	private Conexion cn = new Conexion();

	public HabitacionManagement() {

	}

	// Metodos Administrativos
	public boolean addHabitacion(Habitacion hab) {
		boolean flag = false;
		String sql = "insert into tb_habitacion(cat_hab,tip_hab, pre_dia, est_hab)values (?,?,?,?)";
		try {
			PreparedStatement pst = cn.getCt().prepareStatement(sql);
			pst.setInt(1, hab.getCat_hab());
			pst.setInt(2, hab.getTip_hab());
			pst.setDouble(3, hab.getPre_dia());
			pst.setInt(4, 0);
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.out.println("No se pudo aguergar la Habitacion"
					+ e.toString());
			flag = false;
		}

		return flag;
	}

	public boolean modifHabitacion(Habitacion hab) {
		boolean flag = false;
		String sql = "update tb_habitacion set cat_hab=?,tip_hab=?,pre_dia=?, est_hab=? where num_hab=?";

		try {

			PreparedStatement pst = cn.getCt().prepareStatement(sql);
			pst.setInt(1, hab.getCat_hab());
			pst.setInt(2, hab.getTip_hab());
			pst.setDouble(3, hab.getPre_dia());
			pst.setInt(4, hab.getEst_hab());
			pst.setInt(5, hab.getNum_hab());
			pst.executeUpdate();
			flag = true;

		} catch (SQLException e) {
			System.out.println("(Manage) No se pudo Modificar la Habitacion "
					+ e.toString());
			flag = false;
		}

		return flag;
	}

	public boolean deleteHabitacion(int numHab) {
		boolean flag = false;
		String sql = "delete from tb_habitacion where num_hab = ?";

		try {
			PreparedStatement pst = cn.getCt().prepareStatement(sql);
			pst.setInt(1, numHab);
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.out.println("Error al eliminar: " + e.toString());
			flag = false;
		}
		return flag;
	}

	// Metodos utiles
	public Habitacion buscarHabitacion(int numHab) {
		String sql = "select * from tb_habitacion where num_hab=?";
		Habitacion hab = new Habitacion();
		try {
			PreparedStatement pst = cn.getCt().prepareStatement(sql);
			pst.setInt(1, numHab);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				hab.setNum_hab(rs.getInt(1));
				hab.setCat_hab(rs.getInt(2));
				hab.setTip_hab(rs.getInt(3));
				hab.setPre_dia(rs.getDouble(4));
				hab.setEst_hab(rs.getInt(5));
			}
			return hab;
		} catch (Exception e) {
			System.out.println("Error Buscar Habitacion: " + e.toString());
		}
		return null;
	}

	public ArrayList<Habitacion> listarHabitacion() {
		String sql = "Select * from tb_habitacion";
		ArrayList<Habitacion> arHabitacion = new ArrayList<Habitacion>();
		try {
			PreparedStatement pst = cn.getCt().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			arHabitacion.clear();
			while (rs.next()) {
				Habitacion hab = new Habitacion();
				hab.setNum_hab(rs.getInt(1));
				hab.setCat_hab(rs.getInt(2));
				hab.setTip_hab(rs.getInt(3));
				hab.setPre_dia(rs.getDouble(4));
				hab.setEst_hab(rs.getInt(5));
				arHabitacion.add(hab);
			}
			return arHabitacion;
		} catch (Exception e) {
			System.out.println("Error en el metodo listar(Habitacion)"
					+ e.toString());
		}
		return null;
	}

	public ArrayList<Habitacion> buscarHabitacionTipoCategoria(int tipo,
			int categoria) {
		String sql = "select * from tb_habitacion where tip_hab=? "
				+ "and cat_hab=? and est_hab = 0 ";
		ArrayList<Habitacion> habs = new ArrayList<Habitacion>();
		try {
			PreparedStatement pst = cn.getCt().prepareStatement(sql);
			pst.setInt(1, tipo);
			pst.setInt(2, categoria);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Habitacion hab = new Habitacion();
				hab.setNum_hab(rs.getInt(1));
				hab.setCat_hab(rs.getInt(2));
				hab.setTip_hab(rs.getInt(3));
				hab.setPre_dia(rs.getDouble(4));
				hab.setEst_hab(rs.getInt(5));
				habs.add(hab);
			}
			return habs;
		} catch (Exception e) {
			System.out.println("Error Buscar Habitacion: " + e.toString());
		}
		return null;
	}

	public ArrayList<Habitacion> buscarHabitacionCliente(int codCli) {
		String query = "select  tah.num_hab,tha.cat_hab, tha.tip_hab, tha.pre_dia, tha.est_hab "
				+ "from tb_alo_hab  tah "
				+ "inner join tb_alojamiento tal "
				+ "on tah.cod_alo=tal.cod_alo "
				+ "inner join tb_habitacion tha "
				+ "on tah.num_hab= tha.num_hab " 
				+ "where tal.cod_cli=? and tha.est_hab=1";

		ArrayList<Habitacion> arHabitacion = new ArrayList<Habitacion>();
		try {
			PreparedStatement pst = cn.getCt().prepareStatement(query);
			pst.setInt(1, codCli);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Habitacion hab = new Habitacion();
				hab.setNum_hab(rs.getInt(1));
				hab.setCat_hab(rs.getInt(2));
				hab.setTip_hab(rs.getInt(3));
				hab.setPre_dia(rs.getDouble(4));
				hab.setEst_hab(rs.getInt(5));
				arHabitacion.add(hab);
			}
			return arHabitacion;
		} catch (Exception e) {
			System.out.println("Error Buscar Habitacion por Cliente: "
					+ e.toString());
		}
		return null;
	}

	public int getUltimoNumeroHabitacion() {
		String query = "select max(num_hab) from tb_habitacion";
		int num = 0;
		try {
			PreparedStatement pst = cn.getCt().prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				num = rs.getInt(1);
			}
			return num;
		} catch (Exception e) {
			System.err.println("Error de Ultimo numero" + e.toString());
		}

		return -1;
	}

	public boolean cambiarEstadoOcupada(int numhab) {
		boolean flag = false;
		String sql = "update tb_habitacion set est_hab=? where num_hab=?";

		try {

			PreparedStatement pst = cn.getCt().prepareStatement(sql);
			pst.setInt(1, 1);
			pst.setInt(2, numhab);
			pst.executeUpdate();
			flag = true;

		} catch (SQLException e) {
			System.out.println("(Manage) No se pudo Modificar la Habitacion "
					+ e.toString());
			flag = false;
		}

		return flag;
	}
	
	public boolean cambiarEstadoLibre(int numhab) {
		boolean flag = false;
		String sql = "update tb_habitacion set est_hab=? where num_hab=?";

		try {

			PreparedStatement pst = cn.getCt().prepareStatement(sql);
			pst.setInt(1, 0);
			pst.setInt(2, numhab);
			pst.executeUpdate();
			flag = true;

		} catch (SQLException e) {
			System.out.println("(Manage) No se pudo Modificar la Habitacion "
					+ e.toString());
			flag = false;
		}

		return flag;
	}
}
