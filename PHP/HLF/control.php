<?php
    // Recupera los datos necesarios para trabajar con la funcion "comprobarSiGolpe".
    session_start();
    $posBarco = $_SESSION['barcoSession'];
    $uHit = $_POST['misil'];

    // Require del fichero "index.php" ya que necesito acceder a datos contenidos en él.
    require_once './gen.php';

    // Función encargada de recoger la posición del barco y la posición la cual el jugador ha seleccionado para comprobar si son las mismas.
    function comprobarSiGolpe($posBarco, $uHit){
        echo 'Has golpeado en: ' . $uHit . '<br/>';
        //Este echo está hecho con razones de debug --- Mantener comentado durante los juegos.
        //echo 'Localización del barco: ' . $posBarco . '<br/>';

        if($uHit == $posBarco){
            echo '¡Enhorabuena! Has hundido el barco.' . '<br/>';
        } else {
            echo 'Has fallado, vuelve a intentarlo.' . '<br/>';
            echo '<a href="./index.php">Volver</a>';
        };
    };

    // Llamada a la función de comprobación de casilleros.
    comprobarSiGolpe($posBarco, $uHit);
?>