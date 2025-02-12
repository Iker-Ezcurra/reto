package view;

import java.sql.SQLException;
import java.util.Scanner;

import modelo.Sucursal;
import repositorios.RepositorioSucursal;

public class MenuOficinas {
	
	//Da a escoger entre las oficinas instancia la elegida y devuelve su código
	public static int mostrar() throws SQLException {
		int opcion = 0;
		
		do {
			Scanner sc = new Scanner (System.in);
			System.out.println("\n--- Selector de oficinas ---");
			System.out.println();
			System.out.println("Escoge una de las opciones: ");
			System.out.println("1. Irun");
			System.out.println("2. Donostia");
			System.out.println("3. Renteria");
			System.out.println("4. Bilbao");
			System.out.println();
			System.out.println("5. Volver atras");
			opcion = sc.nextInt();
			if (opcion > 0 && opcion < 5) {
				Sucursal sucursal = RepositorioSucursal.contruirSucursal(opcion);
				MenuServicios.mostrar(sucursal);
			} else if(opcion == 5) {
				
			} else {
				System.out.println("Opción inválida");
			}
		} while (opcion != 5);
		 
		return opcion;
	}
	
}