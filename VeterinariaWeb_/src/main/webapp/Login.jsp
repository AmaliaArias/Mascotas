<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>

<style>
body {
	font-family: Arial, sans-serif;
	background: linear-gradient(to right, #FFDEE9, #B5FFFC);
	/* fondo actualizado */
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

form {
	background-color: white;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
	width: 300px;
}

label {
	display: block;
	margin-bottom: 8px;
	font-weight: bold;
	color: #444;
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 6px;
	box-sizing: border-box;
}

button {
	width: 100%;
	padding: 10px;
	background-color: #2196F3; /* azul suave */
	color: white;
	border: none;
	border-radius: 6px;
	font-size: 16px;
	font-weight: bold;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

button:hover {
	background-color: #1976D2;
}

.register-link {
	display: block;
	text-align: center;
	margin-top: 15px;
	padding: 10px;
	background-color: #4CAF50; /* verde para destacar */
	color: white;
	border-radius: 6px;
	font-size: 16px;
	text-decoration: none;
	font-weight: bold;
	transition: background-color 0.3s ease;
}

.register-link:hover {
	background-color: #45a049;
}

.logout-link {
	display: block;
	text-align: center;
	margin-top: 10px;
	padding: 10px;
	background-color: #f44336; /* rojo suave */
	color: white;
	border-radius: 6px;
	font-size: 16px;
	text-decoration: none;
	font-weight: bold;
	transition: background-color 0.3s ease;
}

.logout-link:hover {
	background-color: #d32f2f;
}
</style>


</head>
<body>

	<!-- Contenedor principal que centra el formulario en la página -->
	<div class="container">
		<form action="LoginTiendaMascota" method="post">
			<!-- Formulario que envía los datos al servlet llamado "LoginTiendaMascota" usando el método POST -->
			<h2>Iniciar Sesión</h2>
			<h5>Por favor ingrese sus datos para iniciar sesión. Sí aún no
				tienes una cuenta puedes registarte</h5>

			<label>Cédula:</label>
			<!-- Etiqueta para el campo de cédula -->
			<input type="text" name="cedula" required>
			<!-- Campo de texto para ingresar la cédula, obligatorio -->
			<label>Contraseña:</label> <input type="password" name="contraseña"
				id="contraseña" required>


			<!-- Checkbox para mostrar la contraseña al hacer clic -->
			<label class="checkbox-label"> <input type="checkbox"
				onclick="mostrarContraseña()"> Mostrar contraseña
			</label>

			<button type="submit">Iniciar sesión</button>
			<!-- Botón para enviar el formulario -->
		</form>

		<a href="Registro.jsp" class="register-link">Registrarse</a>
		<!-- Enlace para ir a la página de registro -->
	</div>

	<!-- Script para mostrar/ocultar contraseña -->
	<script>
  function mostrarContraseña() {
    const campo = document.getElementById("contraseña");
    campo.type = campo.type === "password" ? "text" : "password";
  }
</script>

</body>
</html>