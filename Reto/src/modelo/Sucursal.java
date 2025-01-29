package modelo;

public class Sucursal {

	//atributos
	private int codigo;
	private String direccion;
	private String localidad;
	
	//constructor
	public Sucursal(int codigo, String direccion, String localidad) {
		super();
		this.codigo = codigo;
		this.direccion = direccion;
		this.localidad = localidad;
	}

	//getters & setters
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
}