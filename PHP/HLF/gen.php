<?php
    // Ignora todos los errores que no sean críticos. Usado sobretodo para algún warning que sale por información incompleta de primeras.
    error_reporting(E_ERROR | E_PARSE);
    
    // Variable que almacena la cantidad de cuadrículas que habrá.
    $cuadriculas = 100;

    // Variable que almacena las letras que serán utilizadas para crear la cuadrícula junto a los números.
    $letras = array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J');

    // Función que genera e inicializa las variables de cuadrículas.
    function generarCuadricula($cuadriculas, $letras) {
        global $posiciones;
        $posiciones = array();
        $numActual = 1;
        $siguiente = 0;

        while(count($posiciones) < $cuadriculas){
            if($numActual == 11) {
                $numActual = 1;
                $siguiente += 1;
            };
            array_push($posiciones, $letras[$siguiente] . $numActual);
            $numActual++;
        };
    };

    // Función que asigna una posición al barco y la guarda en una cookie.
    function asignarCuadricula($posiciones) {
        global $posBarco;
        $posBarco = $posiciones[rand(0, 99)];
        echo "¡Las cuadrículas han sido generadas con éxito!<br/>La posición del barco ha sido asignada de manera exitosa.<br/>";

        // Crea una sesión en el servidor y almacena en ella la posición del barco.
        session_start();
        $_SESSION['barcoSession'] = $posBarco;
    };
?>