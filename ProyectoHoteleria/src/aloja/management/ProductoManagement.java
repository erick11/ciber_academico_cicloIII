package aloja.management;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import aloja.clases.Empleado;
import aloja.clases.Habitacion;
import aloja.clases.Producto;
import aloja.util.Conexion;

public class ProductoManagement {

	private Conexion cn = new Conexion();

	public ProductoManagement() {

	}

	public ArrayList<Producto> listarProducto() {
		String sql = "Select * from tb_producto";
		ArrayList<Producto> arProducto = new ArrayList<Producto>();
		try {
			PreparedStatement pst = cn.getCt().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Producto pro = new Producto();
				pro.setCod_pro(rs.getInt(1));
				pro.setDes_pro(rs.getString(2));
				pro.setPre_pro(rs.getDouble(3));
				pro.setSto_pro(rs.getInt(4));
				arProducto.add(pro);
			}
			return arProducto;
		} catch (Exception e) {
			System.out.println("Error en el metodo listar(Producto)"
					+ e.toString());
		}
		return null;
	}

	public Producto buscarProducto(int codProd) {
		//cambio
		String sql = "select * from tb_producto where cod_pro = ?";
		Producto pro = new Producto();
		try {
			PreparedStatement st = cn.getCt().prepareStatement(sql);
			st.setInt(1, codProd);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				pro.setCod_pro(rs.getInt(1));
				pro.setDes_pro(rs.getString(2));
				pro.setPre_pro(rs.getDouble(3));
				pro.setSto_pro(rs.getInt(4));
			}
			return pro;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Producto> buscarProductosCliente(int codCli) {
		String query = "select tpr.cod_pro, tpr.des_pro, tpr.pre_pro, tcp.can_det "
				+ "from tb_con_pro tcp "
				+ "inner join tb_consumo tco "
				+ "on tcp.cod_con= tco.cod_con "
				+ "inner join tb_producto tpr "
				+ "on tcp.cod_pro=tpr.cod_pro "
				+ "where tco.cod_cli =? and tco.est_con=?";
		ArrayList<Producto> arProducto = new ArrayList<Producto>();
		try {
			PreparedStatement pst = cn.getCt().prepareStatement(query);
			pst.setInt(1, codCli);
			pst.setInt(2, 0);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Producto pro = new Producto();
				pro.setCod_pro(rs.getInt(1));
				pro.setDes_pro(rs.getString(2));
				pro.setPre_pro(rs.getDouble(3));
				pro.setSto_pro(rs.getInt(4));
				arProducto.add(pro);
			}
			return arProducto;
		} catch (Exception e) {
			System.out.println("Error Buscar Producto por Cliente: "
					+ e.toString());
		}
		return null;
	}

	public boolean actualizarStock(int codPro, int cant) {
		boolean flag = false;
		String sql = "update tb_producto "
					+"set sto_pro = sto_pro - ? "
					+"where cod_pro= ?";

		try {

			PreparedStatement pst = cn.getCt().prepareStatement(sql);
			pst.setInt(1, cant);
			pst.setInt(2, codPro);
			pst.executeUpdate();
			flag = true;

		} catch (SQLException e) {
			System.out.println("(Manage) No se pudo actualizar el Stock "
					+ e.toString());
			flag = false;
		}

		return flag;
	}
}
