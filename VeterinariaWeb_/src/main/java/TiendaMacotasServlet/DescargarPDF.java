package TiendaMacotasServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Controlador.Conexion;

@WebServlet("/DescargarPDF")
public class DescargarPDF extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf"); // Define el tipo de contenido como PDF

        try (Connection connection = Conexion.getConnection()) { // establece conexión con la base de datos

            if (connection == null) { // Verifica si la conexión fue exitosa
                throw new ServletException("No se pudo establecer la conexión con la base de datos.");
            }

            Document document = new Document(); // Crea un nuevo documento PDF
            PdfWriter.getInstance(document, response.getOutputStream());  // Inicializa el escritor de PDF para enviar el archivo directamente al navegador
            document.open();

         // Ejecuta la consulta SQL para obtener los datos de las mascotas
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT fechaingreso, nombre, tipo, genero, raza, vacuna FROM tbl_mascotas")) {

            	document.add(new Paragraph("Lista de mascotas"));
            	document.add(new Paragraph(" ")); // 

            	// Crear una tabla con 6 columnas
            	PdfPTable table = new PdfPTable(6);
            	table.setWidthPercentage(100); // Ancho

            	// Agregar encabezados
            	table.addCell("Fecha Ingreso");
            	table.addCell("Nombre");
            	table.addCell("Tipo");
            	table.addCell("Género");
            	table.addCell("Raza");
            	table.addCell("Vacuna");

            	// Llenar la tabla con los datos
            	while (rs.next()) {
            	    table.addCell(rs.getString("fechaingreso"));
            	    table.addCell(rs.getString("nombre"));
            	    table.addCell(rs.getString("tipo"));
            	    table.addCell(rs.getString("genero"));
            	    table.addCell(rs.getString("raza"));
            	    table.addCell(String.valueOf(rs.getInt("vacuna")));
            	}

            	// Agregar la tabla al documento
            	document.add(table);


                document.close();

            }

        } catch (SQLException e) {
            throw new ServletException("Error al acceder a la base de datos", e);
        } catch (Exception e) {
            throw new ServletException("Error al generar el PDF", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
