
package Dominio;

/*
    Autores:
    Mateo Franciulli 310956
    Ivan Castelli 306188
 */

import java.util.Scanner;
import Dominio.*;
import java.util.Random;

public class Juego {
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private boolean turnoJugador1;  // True si es el turno del jugador 1, false si es el del jugador 2


    public Juego(Jugador j1, Jugador j2) {
        this.jugador1 = j1;
        this.jugador2 = j2;
        this.tablero = new Tablero();
        this.turnoJugador1 = true;  // Empieza el jugador 1
    }

    public void iniciarJuego() {
    Scanner scanner = new Scanner(System.in);
    boolean jugar = true;
    char ganador = tablero.verificarGanadorTableroGrande();
    String jugadaAnterior = ""; // Para guardar la jugada de la parte grande del tablero
    int filaMini = 5;
    int colMini= 4;
    
    while (jugar && (tablero.verificarGanadorTableroGrande() == '-')) {
        
        tablero.mostrarTableroGrande(filaMini, colMini);
        Jugador jugadorActual = turnoJugador1 ? jugador1 : jugador2;
        char ficha = turnoJugador1 ? 'X' : 'O';
        System.out.println("Turno de " + jugadorActual.getAlias() + " (Juega " + ficha + ")");
        
        // Verifica si es la primera jugada 
        if (jugadaAnterior.isEmpty()) {
            
            
            System.out.print("Ingrese una jugada completa (ej. A1 A2): ");
           
            boolean seHizoPrimera = false;
            while(!seHizoPrimera){
           
           try{
            String jugada = scanner.nextLine().toUpperCase();
            
            
            if (jugada.equalsIgnoreCase("X")) {
             
                System.out.println(jugadorActual.getAlias() + " ha abandonado la partida.");
                jugar = false;
                reiniciarJugadaMagica(jugador1, jugador2);
                seHizoPrimera = true;
                if(!turnoJugador1){
                    jugador1.incrementarPartidasGanadas();
                }else{
                    jugador2.incrementarPartidasGanadas();
                }
                
                
               
                continue;
             
            }                        
            
            
            String[] partes = jugada.split(" ");
            
            if (partes.length != 2) {
                System.out.println("Jugada inválida. Debe ser en formato 'A1 B2'.");
                continue;
            }

            jugadaAnterior = partes[1];  // Guardo donde  se jugara la siguiente
            int filaTablero = partes[0].charAt(0) - 'A';  // Letra a numero
            int colTablero = Character.getNumericValue(partes[0].charAt(1)) - 1;
             filaMini = partes[1].charAt(0) - 'A';
             colMini = Character.getNumericValue(partes[1].charAt(1)) - 1;

            
            if (tablero.realizarJugada(filaTablero, colTablero, filaMini, colMini, ficha)) {
                // Cambiar turno solo si la jugada fue válida
                turnoJugador1 = !turnoJugador1;
                ganador = tablero.verificarGanadorTableroGrande();
                if (ganador != '-') {
                    jugadorActual.incrementarPartidasGanadas(); // le sumo una victoria   
                }
            } else {
                System.out.println("Jugada no válida, intente de nuevo.");
                filaMini=filaTablero;
                colMini=colTablero;
            }
           seHizoPrimera = true;
           
        }catch(Exception e){
            System.out.println("Jugada no válida, intente de nuevo.(ej. A3) ");
            }
            }
        } else {  //Luego de la primera jugada
            
            
            
                System.out.print("Ingrese una jugada en el minitablero (ej. A3): ");
               
            try{
               String jugadaMiniTablero = scanner.nextLine().toUpperCase();


                if (jugadaMiniTablero.equalsIgnoreCase("X")) {
                    System.out.println(jugadorActual.getAlias() + " ha abandonado la partida.");
                    reiniciarJugadaMagica(jugador1, jugador2);
                    jugar = false;

                    if(!turnoJugador1){
                        jugador1.incrementarPartidasGanadas();
                    }else{
                        jugador2.incrementarPartidasGanadas();
                    }
                    continue;

                }

                 if (jugadaMiniTablero.equalsIgnoreCase("M")) {
                   int  filaTablero = jugadaAnterior.charAt(0) - 'A';
                   int  colTablero = Character.getNumericValue(jugadaAnterior.charAt(1)) - 1;

                    // Realizar la jugada mágica
                    String nuevaPosicion = jugadaMagica(jugadorActual, filaTablero, colTablero, ficha);
                    if (nuevaPosicion != null) {
                        jugadaAnterior = nuevaPosicion;  // Actualizar la posicion para el siguiente turno
                    } else {
                        turnoJugador1 = !turnoJugador1;
                    }
                    ganador = tablero.verificarGanadorTableroGrande();
                    turnoJugador1 = !turnoJugador1;
                    continue;
                }



                String jugada = jugadaAnterior + " " + jugadaMiniTablero;  // Juntar la jugada anterior a la nueva
                String[] partes = jugada.split(" ");



                if (partes.length != 2 || !Character.isLetter(jugadaMiniTablero.charAt(0)) || !Character.isDigit(jugadaMiniTablero.charAt(1))) {
                    System.out.println("Jugada inválida. Debe ser en formato 'A1 B2'.");
                    continue;
                }


                int filaTablero = partes[0].charAt(0) - 'A';  // Letra a número
                int colTablero = Character.getNumericValue(partes[0].charAt(1)) - 1;
                 filaMini = partes[1].charAt(0) - 'A';
                 colMini = Character.getNumericValue(partes[1].charAt(1)) - 1;


                if (tablero.realizarJugada(filaTablero, colTablero, filaMini, colMini, ficha)) {                
                    turnoJugador1 = !turnoJugador1;
                    ganador = tablero.verificarGanadorTableroGrande();
                    if (ganador != '-') {
                        jugadorActual.incrementarPartidasGanadas(); // le sumo una victoria   
                    }
                    jugadaAnterior = jugadaMiniTablero;
                } else {
                    System.out.println("Jugada no válida, intente de nuevo.");
                    filaMini=filaTablero;
                    colMini=colTablero;
                }

                if (ganador != '-') {
                    System.out.println("Ganador: " + ganador);
                    //Si juego termina reinicio jugadas magicas
                    reiniciarJugadaMagica(jugador1, jugador2);
                }


            }catch(Exception e){
                System.out.println("Jugada no válida, intente de nuevo.(ej. A3) ");
            }
        
        } 
                
                
    }
    
}
    
    public void reiniciarJugadaMagica(Jugador j1, Jugador j2){
        j1.resetJugadaMagica() ;
        j2.resetJugadaMagica();
    }
    
    
    public void jugarContraComputadora() {
    Scanner scanner = new Scanner(System.in);
    boolean jugar = true;
    char ganador = tablero.verificarGanadorTableroGrande();
    String jugadaAnterior = ""; 
    Random random = new Random();
    int filaMini = 5;
    int colMini = 5;
    while (jugar && (tablero.verificarGanadorTableroGrande() == '-')) {
        
        tablero.mostrarTableroGrande(filaMini, colMini);
        Jugador jugadorActual = turnoJugador1 ? jugador1 : jugador2;
        char ficha = turnoJugador1 ? 'X' : 'O';
        
        if (turnoJugador1) {
            System.out.println("Turno de " + jugadorActual.getAlias() + " (Juega " + ficha + ")");
                        
            if (jugadaAnterior.isEmpty()) {
                System.out.print("Ingrese una jugada completa (ej. A1 A2): ");
                boolean seHizoPrimera = false;
             while(!seHizoPrimera){   
                try {
                String jugada = scanner.nextLine().toUpperCase();

                if (jugada.equalsIgnoreCase("X")) {
                    System.out.println(jugadorActual.getAlias() + " ha abandonado la partida.");
                    jugar = false;
                    jugador2.incrementarPartidasGanadas();
                    seHizoPrimera = true;
                    continue;
                }
                
                String[] partes = jugada.split(" ");
                
                if (partes.length != 2) {
                    System.out.println("Jugada inválida. Debe ser en formato 'A1 B2'.");
                    continue;
                }

                jugadaAnterior = partes[1];  
                
                int filaTablero = partes[0].charAt(0) - 'A';  // Letra a número
                int colTablero = Character.getNumericValue(partes[0].charAt(1)) - 1;
                 filaMini = partes[1].charAt(0) - 'A';
                 colMini = Character.getNumericValue(partes[1].charAt(1)) - 1;

                if (tablero.realizarJugada(filaTablero, colTablero, filaMini, colMini, ficha)) {
                    turnoJugador1 = !turnoJugador1;  // Cambiar turno solo si la jugada fue valida
                    ganador = tablero.verificarGanadorTableroGrande();
                    if (ganador != '-') {
                        jugadorActual.incrementarPartidasGanadas();  // Sumar victoria al player
                    }
                } else {
                    System.out.println("Jugada no válida, intente de nuevo.");
                    filaMini=filaTablero;
                    colMini=colTablero;
                }
                seHizoPrimera = true;
                    } catch(Exception e){
                        System.out.println("Jugada no válida, intente de nuevo.(ej. A3) ");
                    }
             }
            } else {  // Luego de la primera jugada
                
                System.out.print("Ingrese una jugada en el minitablero (ej. A3): ");
               
                boolean correcto = false;
                
                while(!correcto){
                try{
                
                String jugadaMiniTablero = scanner.nextLine().toUpperCase();

                if (jugadaMiniTablero.equalsIgnoreCase("X")) {
                    System.out.println(jugadorActual.getAlias() + " ha abandonado la partida.");
                    jugar = false;
                    jugador2.incrementarPartidasGanadas();  // La compu gana si el jugador se retira
                    correcto=true;
                    continue;
                }

                String jugada = jugadaAnterior + " " + jugadaMiniTablero;  
                String[] partes = jugada.split(" ");

                if (partes.length != 2 || !Character.isLetter(jugadaMiniTablero.charAt(0)) || !Character.isDigit(jugadaMiniTablero.charAt(1))) {
                    System.out.println("Jugada inválida. Debe ser en formato 'A1 B2'.");
                    continue;
                }

                int filaTablero = partes[0].charAt(0) - 'A';  // Letra a número
                int colTablero = Character.getNumericValue(partes[0].charAt(1)) - 1;
                 filaMini = partes[1].charAt(0) - 'A';
                 colMini = Character.getNumericValue(partes[1].charAt(1)) - 1;

                if (tablero.realizarJugada(filaTablero, colTablero, filaMini, colMini, ficha)) {                
                    turnoJugador1 = !turnoJugador1;
                    ganador = tablero.verificarGanadorTableroGrande();
                    if (ganador != '-') {
                        jugadorActual.incrementarPartidasGanadas();  // Sumar victoria al player
                    }
                    jugadaAnterior = jugadaMiniTablero;
                } else {
                    System.out.println("Jugada no válida, intente de nuevo.");
                    filaMini=filaTablero;
                    colMini=colTablero;
                }
                
                correcto = true;
                }catch (Exception e){
                    System.out.println("Jugada no válida, intente de nuevo.(ej. A3) ");
                }
                }
                if (ganador != '-') {
                    tablero.mostrarTableroGrande(filaMini, colMini);
                    System.out.println("Ganador: " + ganador);
                }
            }
            
                
        } else {
            // Turno de la compu
            System.out.println("Turno de la máquina (Juega " + ficha + ")");
            
            // La máquina hace una jugada aleatoria en el minitablero que corresponde a la última jugada del jugador
            int filaTablero = jugadaAnterior.charAt(0) - 'A';
            int colTablero = Character.getNumericValue(jugadaAnterior.charAt(1)) - 1;

//             filaMini;
//             colMini;
            
            // En caso que no hayan posiciones la máquina tiene que rendirse asi no se loopea
            if(!tablero.esPosicionVacia(filaTablero, colTablero, 0, 0) && !tablero.esPosicionVacia(filaTablero, colTablero, 0, 1) 
                    && !tablero.esPosicionVacia(filaTablero, colTablero, 0, 2) && !tablero.esPosicionVacia(filaTablero, colTablero, 1, 0)
                    && !tablero.esPosicionVacia(filaTablero, colTablero, 1, 1 )&& !tablero.esPosicionVacia(filaTablero, colTablero, 1, 2)
                    && !tablero.esPosicionVacia(filaTablero, colTablero, 2, 0) && !tablero.esPosicionVacia(filaTablero, colTablero, 2, 1)
                    && !tablero.esPosicionVacia(filaTablero, colTablero, 2, 2) ){
                System.out.println("La máquina ha abandonado la partida. No hay movimientos disponibles.");
                jugar = false;
                jugador1.incrementarPartidasGanadas();
                   
                continue;
            }
            
            // Asegurarse de que la máquina haga una jugada válida (en una posición vacía)
            do {
                filaMini = random.nextInt(3);  // Fila aleatoria del minitablero
                colMini = random.nextInt(3);   // Columna aleatoria del minitablero
               
            } while (!tablero.esPosicionVacia(filaTablero, colTablero, filaMini, colMini));
           
            
            
            System.out.println("La máquina jugó: " + "" + (char) ('A' + filaMini) + (colMini + 1));
            
            tablero.realizarJugada(filaTablero, colTablero, filaMini, colMini, ficha);

            // cambiar el turno y verificar ganador
            turnoJugador1 = !turnoJugador1;
            ganador = tablero.verificarGanadorTableroGrande();
            if (ganador != '-') {
                jugador2.incrementarPartidasGanadas();  // Sumar victoria a la compu
                System.out.println("Ganador: " + ganador);
            }

            jugadaAnterior = "" + (char) ('A' + filaMini) + (colMini + 1);  // Guardar la jugada de la compu
        }
    }
    
    if (ganador != '-') {
        tablero.mostrarTableroGrande(filaMini, colMini);
        System.out.println("El juego ha terminado. Ganador: " + ganador);
    }
    
    
}
    
    
    
    
    public String jugadaMagica(Jugador jugadorActual, int filaTablero, int colTablero, char ficha) {
    Scanner scanner = new Scanner(System.in);
    String jugada = null;
    boolean seHizo = false;
    
  while(!seHizo){
    if (jugadorActual.haUsadoJugadaMagica()) {
        System.out.println(jugadorActual.getAlias() + " ya ha usado la jugada mágica.");
        jugada = null; 
        seHizo = true;
        continue;
    }

    
    System.out.print("Ingrese la posición donde desea colocar la ficha en el minitablero (ej. A2): ");
    boolean correcto = false ;
    while(!correcto){
        try{   

        String posicion = scanner.nextLine().toUpperCase();


        if (posicion.length() != 2 || !Character.isLetter(posicion.charAt(0)) || !Character.isDigit(posicion.charAt(1))) {
            System.out.println("Posición inválida. Debe ser en formato 'A2'.");
            jugada = null;
        }

        int filaMini = posicion.charAt(0) - 'A'; 
        int colMini = Character.getNumericValue(posicion.charAt(1)) - 1;  


        if (tablero.realizarJugadaMagica(filaTablero, colTablero, filaMini, colMini, ficha)) {
            System.out.println("Jugada mágica realizada. Todas las fichas del minitablero fueron eliminadas y se añadio la suya en: " + posicion );
            jugadorActual.usarJugadaMagica();  
            jugada = posicion;  // Devolver la  posicion para usarla en el siguiente turno
            seHizo = true;
        } else {
            System.out.println("Jugada mágica no válida.");
            jugada = null; 
        }
        
        correcto = true;
        
      } catch (Exception e){
          System.out.println("Fromato de jugada mágica no válida. Intente otra vez");
      }
    }
    
  
  }
    return jugada;
}
    

       
    }







