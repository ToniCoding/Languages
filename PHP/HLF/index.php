<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hundir La Flota</title>
</head>
<body>
    <form action="./control.php" method="post">
        ¿Dónde vas a golpear?&nbsp;<br/><input type='text' name='misil'>
        <input type='submit' value='Comprobar'>
    </form>
    <?php
        // Permite visualizar la session donde se almacena la localización del barco. Usado para hacer debug --- Mantener comentado durante los juegos.
        //session_start();
        //$posicionBarco = $_SESSION['barcoSession'];
        //echo $posicionBarco;
    ?>
</body>
</html>