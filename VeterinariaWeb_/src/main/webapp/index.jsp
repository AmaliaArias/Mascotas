<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Gestión de Mascotas</title>

  <link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style>
  body {
    font-family: 'Quicksand', sans-serif;
    background: linear-gradient(to right, #FFDEE9, #B5FFFC);
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }

  .form-container {
    width: 350px;
    padding: 20px;
    border: 2px solid #ccc;
    border-radius: 10px;
    background-color: white;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  }

  .form-container h2 {
    text-align: center;
    color: #2196F3;
    margin-bottom: 20px;
    font-size: 20px;
  }

  table {
    width: 100%;
    border-collapse: collapse;
  }

  td {
    padding: 6px;
    vertical-align: top;
  }

  label {
    font-weight: bold;
    font-size: 14px;
    color: #444;
    display: block;
    margin-bottom: 6px;
  }

  input[type="text"],
  input[type="date"],
  input[type="number"] {
    width: 100%;
    padding: 10px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 6px;
    box-sizing: border-box;
    margin-bottom: 15px;
  }

  input[type="submit"],
  input[type="reset"] {
    background-color: #2196F3; /* azul suave */
    color: white;
    border: none;
    padding: 10px;
    border-radius: 6px;
    width: 100%;
    font-size: 14px;
    font-weight: bold;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  input[type="submit"]:hover,
  input[type="reset"]:hover {
    background-color: #1976D2;
  }

  .pdf-button {
    display: block;
    width: 100%;
    margin-top: 12px;
    padding: 10px;
    background-color: #4CAF50; /* verde vibrante */
    color: white;
    text-align: center;
    font-weight: bold;
    font-size: 14px;
    text-decoration: none;
    border-radius: 6px;
    box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.1);
    transition: background-color 0.3s ease;
  }

  .pdf-button:hover {
    background-color: #45a049;
    text-decoration: underline;
  }

  .logout-button {
    display: block;
    width: 100%;
    margin-top: 12px;
    padding: 10px;
    background-color: #f44336; /* rojo suave */
    color: white;
    text-align: center;
    font-weight: bold;
    font-size: 14px;
    text-decoration: none;
    border-radius: 6px;
    box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.1);
    transition: background-color 0.3s ease;
  }

  .logout-button:hover {
    background-color: #d32f2f;
    text-decoration: underline;
  }
</style>


</head>

<body>
  <div class="form-container">
    <h2>Gestión Integral de Mascotas</h2>

    <!-- Registro -->
    <form action="RegistrarMascotas" method="post">
      <table>
        <tr><td><label for="fechaingreso">Fecha de ingreso:</label></td>
            <td><input type="date" id="fechaingreso" name="fechaingreso" required></td></tr>

        <tr><td><label for="nombre">Nombre:</label></td>
            <td><input type="text" id="nombre" name="nombre" required></td></tr>

        <tr><td><label for="tipo">Tipo (Perro, gato, Loro):</label></td>
            <td><input type="text" id="tipo" name="tipo" required></td></tr>

        <tr><td><label for="genero">Género:</label></td>
            <td><input type="text" id="genero" name="genero" required></td></tr>

        <tr><td><label for="raza">Raza:</label></td>
            <td><input type="text" id="raza" name="raza" required></td></tr>

        <tr><td><label for="vacuna">N° Vacunas:</label></td>
            <td><input type="number" id="vacuna" name="vacuna" required></td></tr>

        <tr><td colspan="2"><input type="submit" value="Registrar Mascota"></td></tr>
        <tr><td colspan="2"><input type="reset" value="Limpiar"></td></tr>
        <tr><td colspan="2">
          <a href="DescargarPDF" target="_blank" class="pdf-button">📄 Descargar PDF de Mascota</a>
        </td></tr>
      </table>
    </form>

    <!-- Eliminar -->
    <!-- Eliminar con confirmación -->
<form action="EliminarMascota" method="post" onsubmit="return confirmarEliminacion();">
  <table>
    <tr>
      <td><label for="codigomascota">Código Mascota:</label></td>
      <td><input type="number" id="codigomascota" name="codigomascota" required></td>
    </tr>
    <tr>
      <td colspan="2"><input type="submit" value="Eliminar Mascota"></td>
    </tr>
  </table>
</form>

<script>
  function confirmarEliminacion() {
    return confirm("⚠️ ¿Estás seguro de que deseas eliminar esta mascota?");
  }
</script>


    <!-- Buscar -->
    <form action="BuscarMascota" method="post">
      <table>
        <tr><td><label for="buscarCodigo">Código Mascota:</label></td>
            <td><input type="text" id="buscarCodigo" name="codigomascota" required></td></tr>
        <tr><td colspan="2"><input type="submit" value="Buscar Mascota"></td></tr>
      </table>
    </form>

    <!-- Actualizar -->
    <form action="ActualizarMascota" method="post">
      <table>
        <tr><td><label for="actualizarCodigo">Código Mascota:</label></td>
            <td><input type="text" id="actualizarCodigo" name="codigomascota" required></td></tr>
        <tr><td colspan="2"><input type="submit" value="Actualizar Mascota"></td></tr>
      </table>
    </form>

    <!-- Cerrar sesión -->
    <form action="CerrarSesion" method="get">
      <table>
        <tr><td colspan="2"><input type="submit" value="Cerrar Sesión"></td></tr>
      </table>
    </form>
    
    <form action="EnviarCorreoServlet" method="post">
 		 <label for="correo">Correo electrónico:</label>
 	 	<input type="email" name="correo" id="correo" required>
  		<button type="submit">Enviar certificado</button>
	</form>
    
  </div>
</body>
</html>
