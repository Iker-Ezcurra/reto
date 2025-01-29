package modelo;

public class PerroGato extends Animal {

	//atributos
	private String raza;
	
	//Constructor
	public PerroGato(String CodigoChip, String nombre, String sexo, int edad, String raza) {
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