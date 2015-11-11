package aloja.clases;

public class AlojamientoDetalle {

	//Declaracion de Atributos
	private int cod_alo;
	private int cod_hab;

	//Metodos Constructores
	public AlojamientoDetalle() {
		// TODO Auto-generated constructor stub
	}

	public AlojamientoDetalle(int cod_alo, int cod_hab) {
		this.cod_alo = cod_alo;
		this.cod_hab = cod_hab;
	}

	//Metodos Getter and Setter
	public int getCod_alo() {
		return cod_alo;
	}

	public void setCod_alo(int cod_alo) {
		this.cod_alo = cod_alo;
	}

	public int getCod_hab() {
		return cod_hab;
	}

	public void setCod_hab(int cod_hab) {
		this.cod_hab = cod_hab;
	}
}
