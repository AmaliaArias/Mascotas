package TiendaMacotasServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import Controlador.Conexion;

/**
 * Servlet implementation class RegistrarMascotas
 */
@WebServlet("/RegistrarMascotas")
public class RegistrarMascotas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrarMascotas() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fechaingreso = request.getParameter("fechaingreso");
		String nombre = request.getParameter("nombre");
		String tipo = request.getParameter("tipo");
		String genero = request.getParameter("genero");
		String raza = request.getParameter("raza");
		String vacuna = request.getParameter("vacuna");

		Integer.parseInt(vacuna);

		try (Connection conn = Conexion.getConnection()) {

			String sql = "INSERT INTO tbl_mascotas (fechaingreso, nombre, tipo, genero, raza, vacuna) VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, fechaingreso);
			ps.setString(2, nombre);
			ps.setString(3, tipo);
			ps.setString(4, genero);
			ps.setString(5, raza);
			ps.setString(6, vacuna);

			int filas = ps.executeUpdate();

			if (filas > 0) {
				response.getWriter().print("Mascota registrada exitosamente.");
			} else {
				response.getWriter().print("No se pudo registrar la mascota.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("Error al registrar la mascota.");
		}
	}
}