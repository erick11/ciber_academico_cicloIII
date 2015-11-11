package aloja.clases;

public class Producto {

	// Declaracion de Atributos
	private int cod_pro;
	private String des_pro;
	private double pre_pro;
	private int sto_pro;

	// Sobre Carga de Constructores
	public Producto() {

	}

	public Producto(int cod_pro, String des_pro, double pre_pro, int sto_pro) {
		this.cod_pro = cod_pro;
		this.des_pro = des_pro;
		this.pre_pro = pre_pro;
		this.sto_pro = sto_pro;
	}

	// Metodos Getter and Setter
	public int getCod_pro() {
		return cod_pro;
	}

	public void setCod_pro(int cod_pro) {
		this.cod_pro = cod_pro;
	}

	public String getDes_pro() {
		return des_pro;
	}

	public void setDes_pro(String des_pro) {
		this.des_pro = des_pro;
	}

	public double getPre_pro() {
		return pre_pro;
	}

	public void setPre_pro(double pre_pro) {
		this.pre_pro = pre_pro;
	}

	public int getSto_pro() {
		return sto_pro;
	}

	public void setSto_pro(int sto_pro) {
		this.sto_pro = sto_pro;
	}

}
