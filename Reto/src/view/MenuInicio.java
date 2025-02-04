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
				cliente = InstanciarPorTeclado.ClienteRegistro();
				if (RepositorioCliente.comprobarCliente(cliente)) {
					System.out.println("Este usuario esta ya en uso");
				} else {
					RepositorioCliente.insertar(cliente);
				    fin = true;
				    System.out.println("Cuenta creada con éxito");
				}
			} else if (opcion == 2) {
				System.out.println("\n--- Estás iniciando sesión ---");
				cliente = InstanciarPorTeclado.ClienteInicioSesion();
				if (RepositorioCliente.comprobarClienteUsuarioConstraseina(cliente)) {
					cliente = RepositorioCliente.construir(cliente.getUsuario());
					System.out.println("Has iniciado sesion");
					fin=true;
				} else {
					System.out.println("El usuario o contraseña no es correcto");
				}
			} else {
				System.out.println("Opción inválida");
			}
		}
		return cliente;
	}
	
}