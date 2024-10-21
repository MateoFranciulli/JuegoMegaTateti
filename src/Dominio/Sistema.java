package Dominio;

/*
    Autores:
    Mateo Franciulli 310956
    Ivan Castelli 306188
 */
import static Dominio.Jugador.registrarJugador;
import static Interfaz.Menu.mostrarRanking;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Sistema {
    public static ArrayList<Jugador> jugadores = new ArrayList<>();

    public static ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public static void setJugadores(ArrayList<Jugador> jugadores) {
        Sistema.jugadores = jugadores;
    }
    
  

 public static void iniciarJuego() {
    Scanner scanner = new Scanner(System.in);

    boolean correcto = false;
    boolean ok = false;
    int jugador1Index = -1;
    int jugador2Index = -1;

    //Jugador 1
    while (!ok) {
        try {
            // Mostrar jugadores 
            System.out.println("Seleccione el Jugador 1:");
            for (int i = 0; i < jugadores.size(); i++) {
                System.out.println((i + 1) + ". " + jugadores.get(i).getAlias());
            }

            jugador1Index = scanner.nextInt() - 1;
            scanner.nextLine(); // Limpiar 

            // Verificar 
            if (jugador1Index < 0 || jugador1Index >= jugadores.size()) {
                System.out.println("Índice incorrecto, seleccione un jugador válido.");
            } else {
                ok = true; //Es válido y salgo
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida, por favor ingrese un número.");
            scanner.nextLine(); // Limpiar buffer de entrada
        }
    }

    //Jugador 2
    while (!correcto) {
        try {
            //Muestro jugadores
            System.out.println("Seleccione el Jugador 2:");
            for (int i = 0; i < jugadores.size(); i++) {
                System.out.println((i + 1) + ". " + jugadores.get(i).getAlias());
            }

            jugador2Index = scanner.nextInt() - 1;
            scanner.nextLine(); // Limpiar

            // Verifico
            if (jugador2Index < 0 || jugador2Index >= jugadores.size()) {
                System.out.println("Índice incorrecto, seleccione un jugador válido.");
            } else if (jugador1Index == jugador2Index) {
                System.out.println("No puedes seleccionar el mismo jugador dos veces.");
            } else {
                correcto = true; // Es válido y salgo
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida, por favor ingrese un número.");
            scanner.nextLine(); // Limpiar
        }
    }

    // Asigno jugadores 
    Jugador jugador1 = jugadores.get(jugador1Index);
    Jugador jugador2 = jugadores.get(jugador2Index);

    // Crear el juego y llamarlo
    Juego juego = new Juego(jugador1, jugador2);
    juego.iniciarJuego(); // Llama al método de la clase Juego
}

    
    public static void iniciarJuegoContraComputadora() {
    Scanner scanner = new Scanner(System.in);
    int jugadorHumanoIndex = -1;
    boolean ok = false;
    
    while (!ok) {
        try {
            // Mostrar jugadores 
            System.out.println("Seleccione el Jugador que jugará contra la Computadora:");
            for (int i = 0; i < jugadores.size(); i++) {
                System.out.println((i + 1) + ". " + jugadores.get(i).getAlias());
            }

            jugadorHumanoIndex = scanner.nextInt() - 1;
            scanner.nextLine(); // Limpiar 

            if (jugadorHumanoIndex < 0 || jugadorHumanoIndex >= jugadores.size()) {
                System.out.println("Índice incorrecto, seleccione un jugador válido.");
            } else {
                ok = true; // Entrada válida
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida, por favor ingrese un número.");
            scanner.nextLine(); // Limpiar el buffer en caso de excepción
        }
    }

    // Asignar jugador y compu
    Jugador jugadorHumano = jugadores.get(jugadorHumanoIndex);
    Jugador computadora = new Jugador("Computadora", 0, "AI");

    // Crear el juego y llamarlo
    Juego juego = new Juego(jugadorHumano, computadora);
    juego.jugarContraComputadora();
}

 
    
    
    
    public static void opcionMenu() {
        Scanner scanner = new Scanner(System.in);
       
        
        
        while (true) {
            System.out.println("\n---------------------------");
            System.out.println("\n!Bienvenidos al Gran Tateti!");
            System.out.println("\n---------------------------");
            System.out.println("1. Registrar Jugador");
            System.out.println("2. Jugar entre 2 personas");
            System.out.println("3. Jugar contra la Computadora");
            System.out.println("4. Mostrar Ranking");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            
           
           boolean ok = false;
           int opcion= -1;
           while(!ok){
            try{
                 opcion = scanner.nextInt();
                 scanner.nextLine();  // Consumir el salto de línea
                 ok=true;
             }catch (InputMismatchException e){
                 System.out.println("Opcion no válida,intente con otro valor numérico");
                 scanner.next();    //Limpio para que no se loopee 
             }
           }
            switch (opcion) {
                case 1:
                    registrarJugador(scanner);
                    break;
                case 2:
                    if (jugadores.size() >= 2) {
                      
                       iniciarJuego();
                    } else {
                        System.out.println("Debe haber al menos 2 jugadores registrados.");
                    }
                    break;
                case 3:
                    if (jugadores.size() >= 1) {
                       iniciarJuegoContraComputadora();
                    } else {
                        System.out.println("Debe haber al menos 1 jugador registrado.");
                    }
                    break;
                case 4:
                    mostrarRanking();
                    break;
                case 5:
                    System.out.println("Gracias por jugar.");
                    return;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        }
    }
    }

