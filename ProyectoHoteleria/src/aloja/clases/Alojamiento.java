package aloja.clases;

public class Alojamiento {

	// Declaracion de Atributos
	private int cod_alo;
	private int cod_cli;
	private int cod_emp;
	private String fec_lle;
	private String hor_lle;
	private String fec_sal;
	private String hor_sal;
	private int est_alo;

	// Metodos Constructores
	public Alojamiento() {
		// TODO Auto-generated constructor stub
	}

	public Alojamiento(int cod_alo, int cod_cli, int cod_emp, int num_hab,
			String fec_lle, String hor_lle, String fec_sal, String hor_sal,
			int est_alo) {
		this.cod_alo = cod_alo;
		this.cod_cli = cod_cli;
		this.cod_emp = cod_emp;
		this.fec_lle = fec_lle;
		this.hor_lle = hor_lle;
		this.fec_sal = fec_sal;
		this.hor_sal = hor_sal;
		this.est_alo = est_alo;
	}

	// Metodos Getter and Setter
	public int getCod_alo() {
		return cod_alo;
	}

	public void setCod_alo(int cod_alo) {
		this.cod_alo = cod_alo;
	}

	public int getCod_cli() {
		return cod_cli;
	}

	public void setCod_cli(int cod_cli) {
		this.cod_cli = cod_cli;
	}

	public int getCod_emp() {
		return cod_emp;
	}

	public void setCod_emp(int cod_emp) {
		this.cod_emp = cod_emp;
	}

	public String getFec_lle() {
		return fec_lle;
	}

	public void setFec_lle(String fec_lle) {
		this.fec_lle = fec_lle;
	}

	public String getHor_lle() {
		return hor_lle;
	}

	public void setHor_lle(String hor_lle) {
		this.hor_lle = hor_lle;
	}

	public String getFec_sal() {
		return fec_sal;
	}

	public void setFec_sal(String fec_sal) {
		this.fec_sal = fec_sal;
	}

	public String getHor_sal() {
		return hor_sal;
	}

	public void setHor_sal(String hor_sal) {
		this.hor_sal = hor_sal;
	}

	public int getEst_alo() {
		return est_alo;
	}

	public void setEst_alo(int est_alo) {
		this.est_alo = est_alo;
	}

}
