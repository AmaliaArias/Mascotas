package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	public static final String url = "jdbc:mysql://localhost:3306/bd_tiendamacota";
	public static final String usuario = "root";
	public static final String contraseña = "2556229";

	public static Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, usuario, contraseña);
		} catch (Exception e) {
			System.out.println("No conectado a la base datos" + e.getMessage());
		}

		return conn;

	}

}
