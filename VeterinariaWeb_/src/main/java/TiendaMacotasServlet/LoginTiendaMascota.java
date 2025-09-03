package TiendaMacotasServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Controlador.Conexion;

/**
 * Servlet implementation class LoginTiendaMascota
 */
@WebServlet("/LoginTiendaMascota")
public class LoginTiendaMascota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginTiendaMascota() {
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
		
		// Obtiene los valores ingresados por el usuario en el formulario
		String cedula = request.getParameter("cedula");
        String contraseña = request.getParameter("contraseña");

        try {
            Connection conn = Conexion.getConnection(); // Establece la conexión con la base de datos
            String sql = "SELECT * FROM tbl_usuario WHERE cedula = ? AND contraseña = ?";  // Consulta SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
           
            // Asigna los valores del formulario a los parámetros de la consulta
            stmt.setString(1, cedula);
            stmt.setString(2, contraseña);
            
            ResultSet rs = stmt.executeQuery(); // Ejecuta la consulta y guarda el resultado

            if (rs.next()) {  // Si se encuentra un usuario que coincide con los datos
                HttpSession session = request.getSession(); // Crea una sesión para el usuario
                session.setAttribute("cedula", cedula);
                response.sendRedirect("index.jsp");  // Redirige al usuario a la página principal
            } else {
                response.getWriter().println("Cedula o Contraseña, incorrectos");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error al iniciar sesión.");
        }
    }
	
	}


