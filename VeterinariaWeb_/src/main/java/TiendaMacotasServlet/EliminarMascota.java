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
 * Servlet implementation class EliminarMascota
 */
@WebServlet("/EliminarMascota")
public class EliminarMascota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarMascota() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cdgMact = request.getParameter("codigomascota");

		 if (cdgMact == null || cdgMact.isEmpty()) {
	            response.getWriter().println("Codigo de mascota no proporcionado.");
	            return;
	        }

	        int codigomascota = Integer.parseInt(cdgMact);

	        try {
	            Connection conn = Conexion.getConnection(); // 
	            String sql = "DELETE FROM tbl_mascotas WHERE codigomascota = ?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, codigomascota);
	            int filas = stmt.executeUpdate();

	            stmt.close();
	            conn.close();

	            if (filas > 0) {
	                response.sendRedirect("index.jsp");
	            } else {
	                response.getWriter().println("No se encontró ninguna mascota con ese codigo.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.getWriter().println("Error al eliminar la mascota.");
	        }
		
		doGet(request, response);
	}

}
