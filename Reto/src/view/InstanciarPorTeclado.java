package view;

import java.sql.SQLException;
import java.util.Scanner;

import modelo.Animal;
import modelo.Ave;
import modelo.Cliente;
import modelo.Peludo;
import modelo.Reptil;
import repositorios.RepositorioAnimal;

public class InstanciarPorTeclado {
	
	static Scanner teclado = new Scanner(System.in);

	public static Animal Animal() throws SQLException {
		
		System.out.println("Codigo del chip del animal");
		String codigoChip = teclado.nextLine();
		System.out.println("Nombre: ");
		String nombre = teclado.nextLine();
		System.out.println("Sexo (M/H): ");
		System.out.println("M(macho) / H(Hembra)");
		String sexo = teclado.nextLine();
		System.out.println("Edad: ");
		int edad = teclado.nextInt();
		
		Animal animal = new Animal(codigoChip, nombre, sexo, edad);
		
		System.out.println("¿Que tipo de animal es?");
		System.out.println("1. Perro/Gato");
		System.out.println("2. Ave");
		System.out.println("3. Reptil");
		System.out.println("4. Otro");
		int opcion = teclado.nextInt();
		switch (opcion) {
		case 1:
			System.out.println("Raza:");
			String raza = teclado.nextLine();
			animal = new Peludo(codigoChip, nombre, sexo, edad, raza);
			break;
		case 2:
			System.out.println("Especie: ");
			String especie = teclado.nextLine();
			animal = new Ave(codigoChip, nombre, sexo, edad, especie);
			break;
		case 3:
			System.out.println("Dieta:");
			String dieta = teclado.nextLine();
			animal = new Reptil(codigoChip, nombre, sexo, edad, dieta);
			break;
		case 4:
			
			break;
		default:
			System.out.println("Opcion incorrecta");
		}
		
		return animal;
	}
	
	public static Cliente Cliente() throws SQLException {
		System.out.println("Usuario: ");
		String usuario = teclado.nextLine();
		System.out.println("Contraseña: ");
		String contraseina = teclado.nextLine();
		System.out.println("DNI: ");
		String DNI = teclado.nextLine();
		System.out.println("Nombre: ");
		String nombre = teclado.nextLine();
		System.out.println("Dirección: ");
		String direccion = teclado.nextLine();
		System.out.println("Número de teléfono: ");
		int numTelf = teclado.nextInt();
		
		Cliente cliente = new Cliente(usuario, contraseina, DNI, nombre, direccion, numTelf);
		return cliente;
	}
	
	public static Cliente ClienteInicioSesion() {
		System.out.println("Usuario: ");
		String usuario = teclado.next();
		System.out.println("Contraseña: ");
		String contraseina = teclado.next();
		Cliente cliente = new Cliente(usuario, contraseina);
		return cliente;
	}
	
}