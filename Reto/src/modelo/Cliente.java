package modelo;

public class Cliente {
	
	//atributos
	private String usuario;
	private String contraseina;
	private String DNI;
	private String nombre;
	private String direccion;
	private int numTelf;

	//constructor
	public Cliente(String usuario, String contraseina, String DNI, String nombre, String direccion, int numTelf) {
		this.usuario = usuario;
		this.contraseina = contraseina;
		this.DNI = DNI;
		this.nombre = nombre;
		this.direccion = direccion;
		this.numTelf = numTelf;
	}
	
	public Cliente ( String DNI, String nombre, String direccion, int numTelf) {
		this.DNI = DNI;
		this.nombre = nombre;
		this.direccion = direccion;
		this.numTelf = numTelf;
	}
	
	
	public Cliente (String usuario, String contraseina) {
		this.usuario=usuario;
		this.contraseina=contraseina;
	}
	
	public Cliente () {}

	//getters & setters
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseina() {
		return contraseina;
	}

	public void setContraseina(String contraseina) {
		this.contraseina = contraseina;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getNumTel() {
		return numTelf;
	}

	public void setNumTel(int numTelf) {
		this.numTelf = numTelf;
	}
	
}