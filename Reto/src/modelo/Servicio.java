package modelo;

public class Servicio {

	//atributos
	private int codigo;
	private String descripcion;
	private int coste;

	//constructor
	public Servicio(int codigo, String descripcion, int coste) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.coste=coste;
	}
	
	public Servicio(String descripcion, int coste) {
		this.descripcion = descripcion;
		this.coste=coste;
	}
	
	public Servicio(int codigo) {
		this.codigo = codigo;
	}
	
	public Servicio() {}
	
	//getters & setters
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getCoste() {
		return coste;
	}
	
	public void setCoste(int coste) {
		this.coste=coste;
	}

	@Override
	public String toString() {
		return descripcion + "\t" + "\t" + coste + "€";
	}
	
	

}