

package Interfaz;
import Dominio.Sistema;
import Dominio.Jugador;


/*
    Autores:
    Mateo Franciulli 310956
    Ivan Castelli 306188
 */
public class Menu  {
   


    public Menu() {
   } 
   
   
    
    public static void mostrarRanking() {
            System.out.println("\n--- Ranking ---");

            // Orden de jugadores de mas partidas ganadas a menos
            Sistema.jugadores.sort((j1, j2) -> Integer.compare(j2.getPartidasGanadas(), j1.getPartidasGanadas()));

            if (Sistema.jugadores.isEmpty()) {
                System.out.println("No hay jugadores registrados.");
            } else {
                System.out.println("Ranking de jugadores:");
                for (Jugador jugador : Sistema.jugadores) {
                    String cantPartidas = "";
                    for(int i= 0; i<jugador.getPartidasGanadas(); i++){
                        cantPartidas += "# ";
                    }
                    System.out.println(jugador.getAlias() + "| " + cantPartidas);
                }
            }
        }
    
    public static void mostrarMatriz(){
        String datos[][]=new String [3][3];
        //lleno la matriz de espacios vacios
        for (int i = 0; i < datos.length; i++) {
            for (int j = 0; j < datos[0].length; j++) {
                datos[i][j]=" ";
            }  
        }
  
    //le doy la forma a la matriz del tablero chico
    for (int i = 0; i < datos.length; i++) {
        for (int j = 0; j < datos.length; j++) {
            //dibujo los separadores 
            if (j<datos[0].length-1) {
                System.out.print(datos[i][j]+"|");  
                }else{
                    System.out.print(datos[i][j]);
                }   
        }
        System.out.println(""); //aca se separan las filas
        //dibujo entre donde pongo la ficha
        if (i<datos.length-1) {
            System.out.println("-+-+-");
        }
    }
  } 
    public static void animacionTaTeTi() throws InterruptedException {
        
        char[][] tablero = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        int mov = 0;
        char jugadorActual = 'X';

        while (mov < 9) {
            limpiarConsola();            
            imprimirTableroAnimacion(tablero);
            hacerMovimientoAnimacion(tablero, jugadorActual, mov);          
            jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';            
            mov++;
            
            Thread.sleep(175);//7/40 segundo entre animacion y animacion
        }
        
        limpiarConsola(); 
        imprimirTableroAnimacion(tablero);
        Thread.sleep(425); //Demoro 17/40 segundos antes de entrar al juego
        limpiarConsola(); 
    }

    
    public static void imprimirTableroAnimacion(char[][] tablero) {
        System.out.println("Bienvenido:");
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + tablero[i][0] + " | " + tablero[i][1] + " | " + tablero[i][2]);
            if (i < 2) {
                System.out.println("---|---|---");
            }
        }
        System.out.println();
    }

    
    public static void hacerMovimientoAnimacion(char[][] tablero, char jugador, int numeroDeMov) {
        int row = numeroDeMov / 3;
        int col = numeroDeMov % 3;
        tablero[row][col] = jugador;
    }
    
    
    public static void limpiarConsola() {        
      
        for (int i = 0; i < 19; i++) {
            System.out.println();
        }
    
    }
} 

   







    


