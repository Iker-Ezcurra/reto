package view;

import java.sql.SQLException;
import java.util.Scanner;

import modelo.Sucursal;
import repositorios.RepositorioServicio;

public class MenuServicios {
	
	//Dada una sucursal, muestra los servicios disponibles en ella
	public static void mostrar(Sucursal sucursal) throws SQLException {
		Scanner sc = new Scanner (System.in);
		int op;
		do {
			System.out.println("\n--- Servicios disponibles ---");
			RepositorioServicio.serviciosPorSucursal(sucursal);
			System.out.println();
			System.out.println("0. Volver atras");
			op = sc.nextInt();
			if (op != 0) {
				System.out.println("Opcion incorrecta");
			}
		} while (op != 0);
	}
	
}