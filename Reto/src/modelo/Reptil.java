package modelo;

public class Reptil extends Animal{

	//atributos
	private String dieta;
	
	//constructor
	public Reptil(String CodigoChip, String nombre, String sexo, int edad,  String dieta) {
		super(CodigoChip, nombre, sexo, edad);
		this.dieta = dieta;
	}
	
	public Reptil() {}

	//getters & setters
	public String getDieta() {
		return dieta;
	}

	public void setDieta(String dieta) {
		this.dieta = dieta;
	}
	
}