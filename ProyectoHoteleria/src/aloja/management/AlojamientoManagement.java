package aloja.management;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import aloja.clases.Alojamiento;
import aloja.clases.AlojamientoDetalle;
import aloja.util.Conexion;

public class AlojamientoManagement {
	
	private Conexion con = new Conexion();

	public AlojamientoManagement() {
	
	}

	public boolean addAlojamiento(Alojamiento alo) {
		boolean flag = false;
		String sql = "insert into tb_alojamiento(cod_cli,cod_emp,"
				+ "fec_lle,hor_lle,fec_sal,hor_sal,est_alo)"
				+ " values (?,?,?,?,?,?,?)";
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfHour = new SimpleDateFormat("HH:mm");
			PreparedStatement pst = con.getCt().prepareStatement(sql);
			pst.setInt(1, alo.getCod_cli());
			pst.setInt(2, alo.getCod_emp());

			Date fecLle = null;
			Date fecSal = null;
			Date horLle = null;
			Date horSal = null;
			try {
				fecLle = new Date(sdfDate.parse(alo.getFec_lle()).getTime());
				fecSal = new Date(sdfDate.parse(alo.getFec_sal()).getTime());
				horLle = new Date(sdfHour.parse(alo.getHor_lle()).getTime());
				horSal = new Date(sdfHour.parse(alo.getHor_sal()).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pst.setDate(3, fecLle);
			pst.setTime(4, new Time(horLle.getTime()));
			pst.setDate(5, fecSal);
			pst.setTime(6, new Time(horSal.getTime()));
			pst.setInt(7, 1);
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean addDetalleAlojamiento(AlojamientoDetalle aloDet) {
		boolean flag = false;
		String sql = "insert into tb_alo_hab(cod_alo,num_hab)"
				+ " values (?,?)";
		try {
			PreparedStatement pst = con.getCt().prepareStatement(sql);
			pst.setInt(1, aloDet.getCod_alo());
			pst.setInt(2, aloDet.getCod_hab());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	// Metodos Liquidacion
	public boolean cambiarEstadoPagado(int codEmp, int codCli) {
		boolean flag = false;
		String sql = "update tb_alojamiento" 
					 +" set est_alo=?"
					 +" where cod_emp=? and cod_cli=?";

		try {

			PreparedStatement pst = con.getCt().prepareStatement(sql);
			pst.setInt(1, 2);
			pst.setInt(2, codEmp);
			pst.setInt(3, codCli);
			flag = true;

		} catch (SQLException e) {
			System.out.println("(Manage) No se pudo Modificar el Alojamiento "
					+ e.toString());
			flag = false;
		}

		return flag;
	}

	public int getCodigoAlojamiento() {
		String sql = "SELECT cod_alo FROM tb_alojamiento  ORDER BY "
				+ "cod_alo DESC LIMIT 1";
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
