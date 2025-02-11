package view;

import java.sql.SQLException;
import java.util.Scanner;

import modelo.Animal;
import modelo.Ave;
import modelo.Cliente;
import modelo.Peludo;
import modelo.Reptil;

public class InstanciarPorTeclado {
	
	static Scanner teclado = new Scanner(System.in);

	//Se piden por pantalla los datos de un animal y al final se instancia uno con esos datos
	public static Animal Animal() throws SQLException {
		boolean fin = false;
		
		System.out.println("Codigo del chip del animal:");
		String codigoChip = teclado.next();
		System.out.println("Nombre: ");
		String nombre = teclado.next();
		System.out.println("Sexo (M/H): ");
		System.out.println("M(macho) / H(Hembra)");
		String sexo = teclado.next();
		System.out.println("Edad: ");
		int edad = teclado.nextInt();
		
		Animal animal = new Animal(codigoChip, nombre, sexo, edad);
		
		while (!fin) {
			System.out.println("¿Que tipo de animal es?");
			System.out.println("1. Peludo");
			System.out.println("2. Ave");
			System.out.println("3. Reptil");
			System.out.println("4. Otro");
			
			int opcion = teclado.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Raza:");
				String raza = teclado.next();
				animal = new Peludo(codigoChip, nombre, sexo, edad, raza);
				fin = true;
				break;
			case 2:
				System.out.println("Especie: ");
				String especie = teclado.next();
				animal = new Ave(codigoChip, nombre, sexo, edad, especie);
				fin = true;
				break;
			case 3:
				System.out.println("Dieta:");
				String dieta = teclado.next();
				animal = new Reptil(codigoChip, nombre, sexo, edad, dieta);
				fin = true;
				break;
			case 4:
				fin = true;
				break;
			default:
				System.out.println("Opcion incorrecta");
			}
		}
		
		return animal;
	}
	
	//Se piden por pantalla todos los datos de un cliente y al final se instancia uno con esos datos
	public static Cliente ClienteRegistro() throws SQLException {
		System.out.println("Usuario: ");
		String usuario = teclado.next();
		System.out.println("Contraseña: ");
		String contraseina = teclado.next();
		System.out.println("DNI: ");
		String DNI = teclado.next();
		System.out.println("Nombre: ");
		String nombre = teclado.next();
		System.out.println("Dirección: ");
		String direccion = teclado.next();
		System.out.println("Número de teléfono: ");
		int numTelf = teclado.nextInt();
		
		Cliente cliente = new Cliente(usuario, contraseina, DNI, nombre, direccion, numTelf);
		
		return cliente;
	}
	
	//Se pide por pantalla solo el usuario y contraseña de un cliente y se instancia uno con esos datos
	public static Cliente ClienteInicioSesion() {
		System.out.println("Usuario: ");
		String usuario = teclado.next();
		System.out.println("Contraseña: ");
		String contraseina = teclado.next();
		
		Cliente cliente = new Cliente(usuario, contraseina);
		
		return cliente;
	}
	
}