package modelo;

public class Peludo extends Animal {

	//atributos
	private String raza;
	
	//Constructor
	public Peludo(String CodigoChip, String nombre, String sexo, int edad, String raza) {
		super(CodigoChip, nombre, sexo, edad);
		this.raza = raza;
	}

	//getters & setters
	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}
	
}