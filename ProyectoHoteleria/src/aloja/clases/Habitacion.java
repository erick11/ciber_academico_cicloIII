package aloja.clases;

public class Habitacion {

	// Declaracion de Atributos
	private int num_hab;
	private int cat_hab;
	private int tip_hab;
	private double pre_dia;
	private int est_hab;

	private String des_cat;
	private String des_tip;
	private String des_est;

	// Metodos Constructores
	public Habitacion() {

	}

	public Habitacion(int num_hab, int can_hab, int tip_hab, double pre_dia,
			int est_hab) {
		this.num_hab = num_hab;
		this.cat_hab = can_hab;
		this.tip_hab = tip_hab;
		this.pre_dia = pre_dia;
		this.est_hab = est_hab;
	}

	// Metodos Getter and Setter
	public int getNum_hab() {
		return num_hab;
	}

	public void setNum_hab(int num_hab) {
		this.num_hab = num_hab;
	}

	public int getCat_hab() {
		return cat_hab;
	}

	public void setCat_hab(int can_hab) {
		this.cat_hab = can_hab;
	}

	public int getTip_hab() {
		return tip_hab;
	}

	public void setTip_hab(int tip_hab) {
		this.tip_hab = tip_hab;
	}

	public double getPre_dia() {
		return pre_dia;
	}

	public void setPre_dia(double pre_dia) {
		this.pre_dia = pre_dia;
	}

	public int getEst_hab() {
		return est_hab;
	}

	public void setEst_hab(int est_hab) {
		this.est_hab = est_hab;
	}

	public String getDes_cat() {
		switch (getCat_hab()) {
		case 0:
			des_cat = "A";
			break;
		case 1:
			des_cat = "B";
			break;
		default:
			des_cat = "C";
			break;
		}
		return des_cat;
	}

	public void setDes_cat(String des_cat) {
		this.des_cat = des_cat;
	}

	public String getDes_tip() {
		switch (getTip_hab()) {
		case 0:
			des_tip = "Simple";
			break;
		case 1:
			des_tip = "Doble";
			break;
		case 2:
			des_tip = "Triple";
			break;
		default:
			des_tip = "Matrimonial";
			break;
		}
		return des_tip;
	}

	public void setDes_tip(String des_tip) {
		this.des_tip = des_tip;
	}

	public String getDes_est() {
		switch (est_hab) {
		case 0:
			des_est = "libre";
			break;
		default:
			des_est = "ocupado";
			break;
		}
		return des_est;
	}

	public void setDes_est(String des_est) {
		this.des_est = des_est;
	}

}
