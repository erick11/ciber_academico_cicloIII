package aloja.clases;

public class Consumo {

	// Declaracion de Atributos
	private int cod_con;
	private int cod_cli;
	private String fec_ped;
	private int ser_con;
	private double tot_con;
	private int est_con;

	//Metodos Construtores
	public Consumo() {
		// TODO Auto-generated constructor stub
	}

	public Consumo(int cod_con, int cod_cli, String fec_ped, int ser_con,
			double tot_con, int est_con) {
		this.cod_con = cod_con;
		this.cod_cli = cod_cli;
		this.fec_ped = fec_ped;
		this.ser_con = ser_con;
		this.tot_con = tot_con;
		this.est_con = est_con;
	}

	// Metodos Getter and Setter
	public int getCod_con() {
		return cod_con;
	}

	public void setCod_con(int cod_con) {
		this.cod_con = cod_con;
	}

	public int getCod_cli() {
		return cod_cli;
	}

	public void setCod_cli(int cod_cli) {
		this.cod_cli = cod_cli;
	}

	public String getFec_ped() {
		return fec_ped;
	}

	public void setFec_ped(String fec_ped) {
		this.fec_ped = fec_ped;
	}

	public int getSer_con() {
		return ser_con;
	}

	public void setSer_con(int ser_con) {
		this.ser_con = ser_con;
	}

	public double getTot_con() {
		return tot_con;
	}

	public void setTot_con(double tot_con) {
		this.tot_con = tot_con;
	}

	public int getEst_con() {
		return est_con;
	}

	public void setEst_con(int est_con) {
		this.est_con = est_con;
	}

}
