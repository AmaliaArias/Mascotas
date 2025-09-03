package TiendaMacotasServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Controlador.Conexion;

/**
 * Servlet implementation class BuscarMascota
 */
@WebServlet("/BuscarMascota")
public class BuscarMascota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarMascota() {
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
            response.getWriter().println("Código de mascota no proporcionado.");
            return;
        }

        int codigomascota = Integer.parseInt(cdgMact);

        try {
            Connection conn = Conexion.getConnection();
            String sql = "SELECT * FROM tbl_mascotas WHERE codigomascota = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigomascota);
            ResultSet rs = stmt.executeQuery();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (rs.next()) {
                String fechaingreso = rs.getString("fechaingreso");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                String genero = rs.getString("genero");
                String raza = rs.getString("raza");
                String vacuna = rs.getString("vacuna");

                out.println("<html><head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<title>Datos de Mascota</title>");
                out.println("<style>");
                out.println("  body {");
                out.println("    font-family: Arial, sans-serif;");
                out.println("    background: linear-gradient(to right, #FFDEE9, #B5FFFC);");
                out.println("    display: flex;");
                out.println("    justify-content: center;");
                out.println("    align-items: center;");
                out.println("    height: 100vh;");
                out.println("    margin: 0;");
                out.println("  }");
                out.println("  .card {");
                out.println("    background-color: white;");
                out.println("    padding: 30px;");
                out.println("    border-radius: 10px;");
                out.println("    box-shadow: 0 4px 8px rgba(0,0,0,0.2);");
                out.println("    width: 350px;");
                out.println("    text-align: center;");
                out.println("  }");
                out.println("  .card h2 {");
                out.println("    margin-bottom: 20px;");
                out.println("    color: #333;");
                out.println("  }");
                out.println("  .card p {");
                out.println("    margin: 10px 0;");
                out.println("    color: #555;");
                out.println("    font-weight: bold;");
                out.println("  }");
                out.println("  .back-link {");
                out.println("    display: block;");
                out.println("    margin-top: 20px;");
                out.println("    padding: 10px;");
                out.println("    background-color: #2196F3;");
                out.println("    color: white;");
                out.println("    border-radius: 6px;");
                out.println("    text-decoration: none;");
                out.println("    font-weight: bold;");
                out.println("    transition: background-color 0.3s ease;");
                out.println("  }");
                out.println("  .back-link:hover {");
                out.println("    background-color: #1976D2;");
                out.println("  }");
                
                out.println("</style>");
                
                
                out.println("</head><body>");
                out.println("<div class='card'>");
                out.println("<h2>Mascota encontrada</h2>");
                out.println("<p>Código: " + codigomascota + "</p>");
                out.println("<p>Fecha de ingreso: " + fechaingreso + "</p>");
                out.println("<p>Nombre: " + nombre + "</p>");
                out.println("<p>Tipo: " + tipo + "</p>");
                out.println("<p>Género: " + genero + "</p>");
                out.println("<p>Raza: " + raza + "</p>");
                out.println("<p>Vacuna: " + vacuna + "</p>");
                out.println("<a href='index.jsp' class='back-link'>Volver</a>");
                out.println("</div>");
                out.println("</body></html>");

            }


            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error al buscar la mascota.");
        }
		
		
		
		doGet(request, response);
	}

}
