package modelo;

public class Ave extends Animal {

	//atributos
	private String especie;
	
	//constructor
	public Ave(String CodigoChip, String nombre, String sexo, int edad, String especie) {
		super(CodigoChip, nombre, sexo, edad);
		this.especie = especie;
	}

	//getters & setters
	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
}