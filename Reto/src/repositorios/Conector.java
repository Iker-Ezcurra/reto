package repositorios;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	
	protected static Connection conexion;
   
	//abrir conexion con la base de datos
    public static void conectar(){
        try{
            //Cargamos el driver, el driver es la libreria que nos permite conectarnos a la BD
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado");        
            try{
            //Establecemos la conexion con la BD            
            //La BD se encuentra en el localhost(en mi ordenador)
            //El usuario es root y la contraseña es 1234
            //La conexion se hace a traves del puerto 3306
            
            //la ruta se debe cambiar para cada dispositivo
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/reto1","root","root");
            //conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/reto1","root","sqlitosetira2p2");
            System.out.println("Conexion establecida");
	        }catch(Exception e){
	            System.out.println("Error en la conexion");
	        }
        }catch(Exception e){
            System.out.println("Error en el driver");
        }
    }
    
    //cerrar conexion con la base de datos
  	public static void desconectar() throws SQLException {
  		conexion.close();
  	}
  	
}