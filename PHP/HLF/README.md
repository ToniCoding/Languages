# HLF - Hundir La Flota (Proyecto en español)

## Descripción corta
Hundir La Flota es un minijuego programado en PHP el cual se basa en el clásico juego de mesa de *"Hundir La Flota"*. Para inicializar el juego en un servidor local
tendrás que ejecutar primeramente el archivo *"trigger.php"* que sirve para que se genere el tablero virtual de 100 casillas y se le asigne una de ellas al
barco *(de momento sólo hay uno en todo el mapa)*. Tras ello, simplemente acude a *"index.php"* para comenzar a jugar.

## Descripción larga
###### Inicializar el juego
Antes de comenzar a jugar, se necesita ejecutar el archivo *"trigger.php"* que es el que llamará al archivo *"gen.php"* que contiene las funciones que hacen posible generar las cien casillas necesarias para el juego. El archivo *"trigger.php"* también llamará a la función residente en *"gen.php"* que permite al programa leer las casillas
generadas y asignar, de manera aleatoria, una al barco el cual hay que destruir.

###### Comenzar a jugar
Tras haber realizado el proceso de inicialización del juego, todos los usuarios podrán acceder al archivo "index.php" en el cual hay un formulario en el
cual podrán empezar a introducir casillas para intentar hundir el barco. La sintaxis de la casilla debe ser: *letra[A-J]número[1-10]*. De lo contrario el programa
simplemente ignorará lo que haya introducido el jugador y lanzará un mensaje como si se hubiese **equivocado** de casilla.

###### Limitaciones
- El proceso de inicialización *no* es automático.
- Las casillas introducidas por el jugador deben contener la letra **MAYÚSCULA** no la minúscula dado que el programa aún *no está preparado para convertir el input
del usuario a mayúscula*. 
