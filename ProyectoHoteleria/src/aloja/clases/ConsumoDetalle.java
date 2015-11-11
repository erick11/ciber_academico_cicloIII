package aloja.clases;

public class ConsumoDetalle {
	//Declaracion de Atributos
	private int cod_con;
	private int cod_pro;
	private int can_det;
	private double pre_uni;
	private double sub_tot;
	
	//Metodos Constructores
	public ConsumoDetalle() {
		
	}
	
	public ConsumoDetalle(int cod_con, int cod_pro, int can_det,
			double pre_uni, double sub_tot) {
		this.cod_con = cod_con;
		this.cod_pro = cod_pro;
		this.can_det = can_det;
		this.pre_uni = pre_uni;
		this.sub_tot = sub_tot;
	}


	//Metodos Getter and Setter
	public int getCod_con() {
		return cod_con;
	}
	public void setCod_con(int cod_con) {
		this.cod_con = cod_con;
	}
	public int getCod_pro() {
		return cod_pro;
	}
	public void setCod_pro(int cod_pro) {
		this.cod_pro = cod_pro;
	}
	public int getCan_det() {
		return can_det;
	}
	public void setCan_det(int can_det) {
		this.can_det = can_det;
	}
	public double getPre_uni() {
		return pre_uni;
	}
	public void setPre_uni(double pre_uni) {
		this.pre_uni = pre_uni;
	}
	public double getSub_tot() {
		return sub_tot;
	}
	public void setSub_tot(double sub_tot) {
		this.sub_tot = sub_tot;
	}
	
	
}
