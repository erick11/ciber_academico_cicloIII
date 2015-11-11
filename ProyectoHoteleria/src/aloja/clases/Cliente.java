package aloja.clases;

public class Cliente {

	// Declaracion de Atributos
	private int cod_cli;
	private String nom_cli;
	private String ape_pat;
	private String ape_mat;
	private String tel_cli;
	private String dni_cli;

	// Metodos Constructores
	public Cliente() {

	}

	public Cliente(int cod_cli, String nom_cli, String ape_pat, String ape_mat,
			String tel_cli, String dni_cli) {
		this.cod_cli = cod_cli;
		this.nom_cli = nom_cli;
		this.ape_pat = ape_pat;
		this.ape_mat = ape_mat;
		this.tel_cli = tel_cli;
		this.dni_cli = dni_cli;
	}

	//Metodos Getter and Setter
	public int getCod_cli() {
		return cod_cli;
	}

	public void setCod_cli(int cod_cli) {
		this.cod_cli = cod_cli;
	}

	public String getNom_cli() {
		return nom_cli;
	}

	public void setNom_cli(String nom_cli) {
		this.nom_cli = nom_cli;
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

	public String getTel_cli() {
		return tel_cli;
	}

	public void setTel_cli(String tel_cli) {
		this.tel_cli = tel_cli;
	}

	public String getDni_cli() {
		return dni_cli;
	}

	public void setDni_cli(String dni_cli) {
		this.dni_cli = dni_cli;
	}

}
