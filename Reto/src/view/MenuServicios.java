package view;

import java.sql.SQLException;
import java.util.Scanner;

import repositorios.RepositorioServicio;

public class MenuServicios {
	
	public static void mostrar(int sucursal) throws SQLException {
		Scanner sc = new Scanner (System.in);
		int op;
		do {
			System.out.println("\n--- Servicios disponibles ---");
			RepositorioServicio.serviciosPorSucursal(sucursal);
			System.out.println();
			System.out.println("1. Volver atras");
			op = sc.nextInt();
			if (op!=1) {
				System.out.println("Opcion incorrecta");
			}
		} while (op !=1);
	}
	
}