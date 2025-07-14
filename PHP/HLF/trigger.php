<?php
    // Requires del fichero "index.php" para utilizar una única vez el generador de cuadrículas y la asignación del barco.
    require_once "gen.php";
    
    // Inicia una única vez las funciones necesarias.
    generarCuadricula($cuadriculas, $letras);
    asignarCuadricula($posiciones);
?>

<a href="./index.php">Comenzar juego</a>