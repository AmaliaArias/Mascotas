package TiendaMacotasServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

import Modelo.enviarPDF;

/**
 * Servlet implementation class EnviarCorreoServlet
 */
@WebServlet("/EnviarCorreoServlet")
public class EnviarCorreoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnviarCorreoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. Obtener el correo del formulario
        String correoDestino = request.getParameter("correo");

        // 2. Ubicar el archivo PDF (ajusta la ruta según tu proyecto)
        String rutaPDF = getServletContext().getRealPath("/certificado/CertificadoEjemplo.pdf");
        File archivoPDF = new File(rutaPDF);

        // 3. Enviar el certificado
        enviarPDF.enviarCertificado(correoDestino, archivoPDF);

        // 4. Redirigir o mostrar mensaje
        response.getWriter().println("Certificado enviado a " + correoDestino);
    }

	}


