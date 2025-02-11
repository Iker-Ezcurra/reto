package view;

import java.sql.SQLException;
import java.util.Scanner;

import modelo.Cliente;
import repositorios.RepositorioAdmin;
import repositorios.RepositorioServicio;
import repositorios.RepositorioSucursal;

public class MenuAdmin {

	public static void mostrar(Cliente cliente) throws SQLException {
		Scanner teclado = new Scanner(System.in);
		
		boolean valido = false;
		boolean fin = false;
		boolean fin2 = false;
		
		System.out.println("\n--- MENU ADMIN ---");
		System.out.println();
		System.out.println("1. Consultar citas");
		System.out.println("2. Consultar cantidad de ...");
		System.out.println("3. Consultar ingresos");
		System.out.println("4. Cambiar cuenta");
		System.out.println("5. Finalizar aplicación");
		System.out.println();
		
		int opcion = teclado.nextInt();
		
		switch(opcion) {
		case 1:
			fin = false;
			while (!fin) {
				System.out.println("\n¿Que citas quieres ver?");
				System.out.println();
				System.out.println("1. Todas las citas");
				System.out.println("2. Citas por servicio");
				System.out.println("3. Citas por oficina");
				System.out.println();
				System.out.println("0. Volver atrás");
				opcion = teclado.nextInt();
				switch(opcion) {
				case 1:
					RepositorioAdmin.consultarCitas();
					break;
				case 2:
					fin2 = false;
					while (!fin2) {
						System.out.println("\nEscoge un servicio: ");
						System.out.println("1. Hotel");
						System.out.println("2. Cheque médico");
						System.out.println("3. Peluquería");
						System.out.println("4. Recorte de plumas");
						System.out.println("5. Terapia UV");
						System.out.println();
						System.out.println("0. Volver atrás");
						int codServ = teclado.nextInt();
						if (codServ > 0 && codServ < 6) {
							RepositorioAdmin.consultarCitasServicio(codServ);
						} else if (codServ == 0) {
							fin2 = true;
						} else {
							System.out.println("Opción inválida");
						}
					}
					break;
				case 3:
					fin2 = false;
					while (!fin2) {
						System.out.println("\nEscoge una sucursal: ");
						System.out.println("1. Irun");
						System.out.println("2. Donostia");
						System.out.println("3. Renteria");
						System.out.println("4. Bilbao");
						System.out.println();
						System.out.println("0. Volver atrás");
						int codOf = teclado.nextInt();
						if (codOf > 0 && codOf < 5) {
							RepositorioAdmin.consultarCitasOficina(codOf);
						} else  if (codOf == 0){
							fin2 = true;
						} else {
							System.out.println("Opción inválida");
						}
					}
					break;
				case 0:
					fin = true;
					break;
				default:
					System.out.println("Opción inválida");
					break;
				}
			}
			break;
		case 2:
			fin = false;
			while (!fin) {
				System.out.println("\nConsultar cantidad de ...");
				System.out.println();
				System.out.println("1. Citas");
				System.out.println("2. Clientes");
				System.out.println();
				System.out.println("0. Volver atrás");
				opcion = teclado.nextInt();
				switch(opcion) {
				case 1:
					RepositorioAdmin.consultarCantidadCitas();
					break;
				case 2:
					RepositorioAdmin.consultarCantidadClientes();
					break;
				case 0:
					fin = true;
					break;
				default:
					System.out.println("Opción inválida");
					break;
				}
			}
			break;
		case 3:
			fin = false;
			while (!fin) {
				System.out.println("\nConsultar ingresos");
				System.out.println();
				System.out.println("1. Generales");
				System.out.println("2. Por servicio");
				System.out.println("3. Por oficina");
				System.out.println();
				System.out.println("0. Volver atrás");
				opcion = teclado.nextInt();
				switch(opcion) {
				case 1:
					RepositorioAdmin.consultarIngresos();
					break;
				case 2:
					fin2 = false;
					while (!fin2) {
						System.out.println("\nElige el servicios");
						int opciones = RepositorioServicio.servicios();
						System.out.println();
						System.out.println("0. Volver atrás");
						int servicio = teclado.nextInt();
						if (servicio > 0 && servicio < (opciones + 1)) {
							RepositorioAdmin.consultarIngresosServicio(servicio);
						} else if (servicio == 0) {
							fin2 = true;
						} else {
							System.out.println("Opción inválida");
						}
					}
					break;
				case 3:
					while (!fin2) {
						System.out.println("\nElige la sucursal");
						int opciones = RepositorioSucursal.sucursales();
						System.out.println();
						System.out.println("0. Volver atrás");
						int oficina = teclado.nextInt();
						if (oficina > 0 && oficina < (opciones + 1)) {
							RepositorioAdmin.consultarIngresosOficina(oficina);
						} else if (oficina == 0) {
							fin2 = true;
						} else {
							System.out.println("Opción inválida");
						}
					}
					break;
				case 0:
					fin = true;
					break;
				default:
					System.out.println("Opción inválida");
					break;
				}
			}
			break;
		case 4:
			MenuPrincipal.mostrar(MenuInicio.mostrar());
			System.exit(0);
			break;
		case 5:
			fin = false;
			while (!valido) {
				System.out.println("¿Seguro que quieres cerrar la aplicación? (Y/N)");
				String cerrar = teclado.next();
				if (cerrar.equalsIgnoreCase("Y")) {
					valido = true;
					System.out.println("Cerrando aplicación");
					System.out.println("¡MAIPet le desea un buen día!");
					System.exit(0);
				} else if (cerrar.equalsIgnoreCase("N")) {
					valido = true;
					System.out.println("Se ha cancelado la operacion de cerrar la aplicación");
				} else {
					System.out.println("Opción inválida");
				}
			}
			break;
		default:
			System.out.println("Opción inválida");
			break;
		}
	}
	
	
}