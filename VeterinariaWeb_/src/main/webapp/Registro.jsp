<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro de Usuario</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background: linear-gradient(to right, #FFDEE9, #B5FFFC);
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
        box-shadow: 0 4px 8px rgba(0,0,0,0.2);
        width: 300px;
    }

    label {
        display: block;
        margin-bottom: 8px;
        font-weight: bold;
    }

    input[type="text"],
    input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }

    button {
        width: 100%;
        padding: 10px;
        background-color: #2196F3;
        color: white;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
    }

    button:hover {
        background-color: #1976D2;
    }
</style>
</head>
<body>

<form action="RegistroUsuario" method="post">
    <h2>Registro</h2>
    <label>Cédula:</label>
    <input type="text" name="cedula" required>
    <label>Nombre Usuario:</label>
    <input type="text" name="nombreUsuario" required>
    <label>Contraseña:</label>
    <input type="password" name="contraseña" required>
    <button type="submit">Registrarse</button>
</form>
</head>
<body>

</body>
</html>