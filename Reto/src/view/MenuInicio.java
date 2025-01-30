package view;

import java.sql.SQLException;
import java.util.Scanner;

import modelo.Cliente;
import repositorios.RepositorioCliente;

public class MenuInicio {
	
	public static Cliente mostrar() throws SQLException {
		Scanner teclado = new Scanner(System.in);
		int opcion;
		boolean fin = false;
		Cliente cliente = new Cliente();
		while (!fin) {
			System.out.println("\n--- Bienvenido a MAIPet ---");
			System.out.println();
			System.out.println("Escoge una de las opciones: ");
			System.out.println("1. Crear cuenta");
			System.out.println("2. Iniciar sesión");
			opcion = teclado.nextInt();
			if (opcion == 1) {
				System.out.println("\n--- Estás registrandote ---");
				if (RepositorioCliente.registrar()) {
					System.out.println("Cuenta creada");
					System.out.println("Inicia sesion en ella");
				} else {
					System.out.println("No se ha podido crear la cuenta");
				}
			} else if (opcion == 2) {
				System.out.println("\n--- Estás iniciando sesión ---");
				cliente = RepositorioCliente.inicioSesion();
				if (cliente!=null) {
					System.out.println("Has iniciado sesion");
					fin=true;
				} else {
					System.out.println("El usuario no es correcto");
				}
			} else {
				System.out.println("Opción inválida");
			}
		}
		return cliente;
	}
	
}