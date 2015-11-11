package aloja.clases;

public class Empleado {

	// Declaracion de Atributos
	private int cod_emp;
	private String nom_emp;
	private String ape_pat;
	private String ape_mat;
	private int tip_emp;
	private String usu_emp;
	private String pas_emp;
	
	private String des_tip;

	// Metodo Constructor
	public Empleado() {

	}

	public Empleado(int cod_emp, String nom_emp, String ape_pat,
			String ape_mat, int tip_emp, String usu_emp, String pas_emp) {
		this.cod_emp = cod_emp;
		this.nom_emp = nom_emp;
		this.ape_pat = ape_pat;
		this.ape_mat = ape_mat;
		this.tip_emp = tip_emp;
		this.usu_emp = usu_emp;
		this.pas_emp = pas_emp;
	}

	//Metodos Getter and Setter
	public int getCod_emp() {
		return cod_emp;
	}

	public void setCod_emp(int cod_emp) {
		this.cod_emp = cod_emp;
	}

	public String getNom_emp() {
		return nom_emp;
	}

	public void setNom_emp(String nom_emp) {
		this.nom_emp = nom_emp;
	}

	public String getApe_pat() {
		return ape_pat;
	}

	public void setApe_pat(String ape_pat) {
		this.ape_pat = ape_pat;
	}

	public String getApe_mat() {
		return ape_mat;
	}

	public void setApe_mat(String ape_mat) {
		this.ape_mat = ape_mat;
	}

	public int getTip_emp() {
		return tip_emp;
	}

	public void setTip_emp(int tip_emp) {
		this.tip_emp = tip_emp;
	}

	public String getUsu_emp() {
		return usu_emp;
	}

	public void setUsu_emp(String usu_emp) {
		this.usu_emp = usu_emp;
	}

	public String getPas_emp() {
		return pas_emp;
	}

	public void setPas_emp(String pas_emp) {
		this.pas_emp = pas_emp;
	}

	public String getDes_tip() {
		switch (getTip_emp()) {
		case 0: 
			des_tip = "Administrador";
			break;
		default:
			des_tip = "Empleado";
			break;
		}
		return des_tip;
	}

	public void setDes_tip(String des_tip) {
		this.des_tip = des_tip;
	}
	
	

}
