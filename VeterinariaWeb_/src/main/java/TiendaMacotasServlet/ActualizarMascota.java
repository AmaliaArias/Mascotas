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

import Controlador.Conexion;

/**
 * Servlet implementation class ActualizarMascota
 */
@WebServlet("/ActualizarMascota")
public class ActualizarMascota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarMascota() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        	
            String codStr = request.getParameter("codigomascota");   // Obtiene el parámetro "codigomascota" enviado en la URL
            
            // Variables para almacenar los datos de la mascota
            String fechaingreso = "";
            String nombre = "";
            String tipo = "";
            String genero = "";
            String raza = "";
            String vacuna = "";

            if (codStr != null && !codStr.isEmpty()) {  // Verifica si se recibió un código válido
                try {
                    int codigomascota = Integer.parseInt(codStr);
                    Connection conn = Conexion.getConnection();
                    
                    // Consulta SQL para obtener los datos de la mascota
                    String sql = "SELECT * FROM tbl_mascotas WHERE codigomascota = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, codigomascota);
                    var rs = stmt.executeQuery();

                    
                    // Si se encuentra la mascota, se guardan sus datos
                    if (rs.next()) {
                        fechaingreso = rs.getString("fechaingreso");
                        nombre = rs.getString("nombre");
                        tipo = rs.getString("tipo");
                        genero = rs.getString("genero");
                        raza = rs.getString("raza");
                        vacuna = rs.getString("vacuna");
                    }

                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            
            //  estilos
            out.println("<html><head><title>Actualizar Mascota</title>");
            
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f2f2f2; }");
            out.println(".form-container {");
            out.println("  background-color: #fff;");
            out.println("  padding: 20px;");
            out.println("  margin: 50px auto;");
            out.println("  width: 400px;");
            out.println("  border-radius: 10px;");
            out.println("  box-shadow: 0 0 10px rgba(0,0,0,0.1);");
            out.println("}");
            out.println("input[type='text'], input[type='date'] {");
            out.println("  width: 100%;");
            out.println("  padding: 8px;");
            out.println("  margin: 5px 0 15px 0;");
            out.println("  border: 1px solid #ccc;");
            out.println("  border-radius: 4px;");
            out.println("}");
            out.println("button {");
            out.println("  background-color: #4CAF50;");
            out.println("  color: white;");
            out.println("  padding: 10px 15px;");
            out.println("  border: none;");
            out.println("  border-radius: 4px;");
            out.println("  cursor: pointer;");
            out.println("}");
            out.println("button:hover { background-color: #45a049; }");
            out.println("</style></head><body>");
            
         // Formulario con los datos precargados
            out.println("<div class='form-container'>");
            out.println("<h2 style='text-align:center;'>Actualizar Mascota</h2>");
            out.println("<form method='post' action='ActualizarMascota'>");
            out.println("Código: <input type='text' name='codigomascota' value='" + (codStr != null ? codStr : "") + "' required><br>");
            out.println("Fecha Ingreso: <input type='date' name='fechaingreso' value='" + fechaingreso + "'><br>");
            out.println("Nombre: <input type='text' name='nombre' value='" + nombre + "'><br>");
            out.println("Tipo: <input type='text' name='tipo' value='" + tipo + "'><br>");
            out.println("Género: <input type='text' name='genero' value='" + genero + "'><br>");
            out.println("Raza: <input type='text' name='raza' value='" + raza + "'><br>");
            out.println("Vacuna: <input type='text' name='vacuna' value='" + vacuna + "'><br>");
            out.println("<button type='submit'>Actualizar</button>");
            out.println("</form>");
            out.println("</div>");

            out.println("</body></html>");

        

    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 // Obtiene los datos enviados desde el formulario
		String codStr = request.getParameter("codigomascota");
		String fechaingreso = request.getParameter("fechaingreso");
        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        String genero = request.getParameter("genero");
        String raza = request.getParameter("raza");
        String vacuna = request.getParameter("vacuna");

     // Verifica que el código no esté vacío
        if (codStr == null || codStr.isEmpty()) {
            response.getWriter().println("Código de mascota no proporcionado.");
            return;
        }

        int codigomascota = Integer.parseInt(codStr);

        try {
            Connection conn = Conexion.getConnection(); // Tu clase de conexión
            String sql = "UPDATE tbl_mascotas SET fechaingreso = ?, nombre = ?, tipo = ?, genero = ?, raza = ?, vacuna = ? WHERE codigomascota = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
         // Asigna los valores a la consulta
            stmt.setString(1, fechaingreso);
            stmt.setString(2, nombre);
            stmt.setString(3, tipo);
            stmt.setString(4, genero);
            stmt.setString(5, raza);
            stmt.setString(6, vacuna);
            stmt.setInt(7, codigomascota);

         // Ejecuta la actualización
            int filas = stmt.executeUpdate();

            stmt.close();
            conn.close();

         // Si se actualizó correctamente, redirige al index
            if (filas > 0) {
                response.sendRedirect("index.jsp"); // Redirige al listado o página principal
            } else {  // Si no se encontró la mascota, muestra mensaje
                response.getWriter().println("No se encontró ninguna mascota con ese código.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("");
        }
		
		
		doGet(request, response);
	}

}
