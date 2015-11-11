package aloja.management;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import aloja.clases.Consumo;
import aloja.clases.ConsumoDetalle;
import aloja.util.Conexion;

public class ConsumoManagement {

	Conexion con = new Conexion();

	public ConsumoManagement() {

	}

	public boolean addConsumo(Consumo cons) {
		boolean flag = false;
		String sql = "insert into tb_consumo(cod_cli,fec_ped,"
				+ "ser_con,tot_con,est_con)" + " values (?,?,?,?,?)";
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
			PreparedStatement pst = con.getCt().prepareStatement(sql);
			pst.setInt(1, cons.getCod_cli());
			Date fecPed = null;
			try {
				fecPed = new Date(sdfDate.parse(cons.getFec_ped()).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pst.setDate(2, fecPed);
			pst.setInt(3, cons.getSer_con());
			pst.setDouble(4, cons.getTot_con());
			pst.setInt(5, 0);
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean addDetalleConsumo(ConsumoDetalle conDet) {
		boolean flag = false;
		String sql = "insert into tb_con_pro(cod_con,cod_pro,can_det,pre_uni,sub_tot)"
				+ " values (?,?,?,?,?)";
		try {
			PreparedStatement pst = con.getCt().prepareStatement(sql);
			pst.setInt(1, conDet.getCod_con());
			pst.setInt(2, conDet.getCod_pro());
			pst.setInt(3, conDet.getCan_det());
			pst.setDouble(4, conDet.getPre_uni());
			pst.setDouble(5, conDet.getSub_tot());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	// Metodos Liquidacion
	public boolean cambiarEstadoPagado(int codCli) {
		boolean flag = false;
		String sql = "update tb_consumo" 
						+" set est_con=?" 
						+" where cod_cli=?";

		try {

			PreparedStatement pst = con.getCt().prepareStatement(sql);
			pst.setInt(1, 1);
			pst.setInt(2, codCli);
			flag = true;

		} catch (SQLException e) {
			System.out.println("(Manage) No se pudo Modificar el consumo "
					+ e.toString());
			flag = false;
		}

		return flag;
	}

	public int getCodigoConsumo() {
		String sql = "SELECT cod_con FROM TB_CONSUMO  ORDER BY "
				+ "cod_con DESC LIMIT 1";
		int cod = -1;
		try {
			PreparedStatement st = con.getCt().prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				cod = rs.getInt(1);
			}
			if ((cod + 1) == 0) {
				return 1;
			}
			return cod + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}
}