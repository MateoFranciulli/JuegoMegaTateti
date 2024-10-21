
package Dominio;
/*
    Autores:
    Mateo Franciulli 310956
    Ivan Castelli 306188
 */

import java.util.InputMismatchException;
import java.util.Scanner;
import Dominio.Sistema;

public class Jugador {
    
    private String nombre;
    private int edad;
    private String alias;
    private int partidasGanadas;
    private boolean jugadaMagicaUsada;
    
    public Jugador(String nombre, int edad, String alias) {
        
        this.nombre = nombre;
        this.edad = edad;
        this.alias = alias;
        this.partidasGanadas = 0 ;
        this.jugadaMagicaUsada = false;
            
    }
    
    
    
    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void incrementarPartidasGanadas() {
        this.partidasGanadas += 1;
    }
    public String getAlias() {
        return alias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public boolean haUsadoJugadaMagica() {
        return jugadaMagicaUsada;
    }

    public void usarJugadaMagica() {
        this.jugadaMagicaUsada = true;
    }
    
    public void resetJugadaMagica(){
        this.jugadaMagicaUsada = false;
    }
    

    
    public static void registrarJugador(Scanner scanner) {
        System.out.println("\n--- Registrar Jugador ---");
        
        String nombre="";
       
        while (nombre.equals("") && nombre.trim().length()== 0) {
            System.out.print("Ingrese el nombre del jugador: ");
            nombre = scanner.nextLine();
            
            // Validación para asegurarse de que solo se ingresen letras
            if (!nombre.matches("[a-zA-Z ]+")) { // esta validacion fue hecha con ayuda de chat GPT (link:https://chatgpt.com/share/66fb3ee0-30e0-800d-a8cb-438856f220ca)
                System.out.println("Nombre solo admite letras, ingrese un nombre válido");
                nombre = ""; // reinicio el valor de nombre si no cumple
            }
        } 
        
        int edad = -1;
        while (edad == -1){
            
            try {
                System.out.print("Ingrese la edad del jugador: ");
                edad = scanner.nextInt();
                if (edad < 0) {
                    System.out.println("La edad no puede ser negativa. Por favor, ingresa una edad válida.");
                    edad = -1;  // Reinicio la edad a -1 para seguir pidiendo datos
                }
            } catch (InputMismatchException e) {
                System.out.println("La edad tiene que ser un valor numérico.");
                scanner.next();  // Limpio el scanner sino se loopea
            }
        } 
       
        scanner.nextLine();  // Consumir el salto de linea
        String alias = "";
        System.out.print("Ingrese el alias del jugador: ");                        
        alias += scanner.nextLine();
        boolean aliasUnico=false;
        if(!Sistema.jugadores.isEmpty()){ // Si hay mas de un jugador checkeo que no exista el alias, y lo añado
            do{            
                if(!(alias.trim().length()== 0)){                    
                
                    for (Jugador jugador : Sistema.jugadores) {

                        if (jugador.getAlias().equalsIgnoreCase(alias)) {  //Checkeo que no se repita
                            System.out.println("Alias ya registrado. Por favor, elige uno único.");
                            System.out.print("Ingrese el alias del jugador: ");
                            alias="";
                            alias += scanner.nextLine();

                        }else{
                            aliasUnico = true;
                        }

                    }
                }else{
                    System.out.println("Alias con caracteres vacío.Por favor, elige otro ");
                    alias="";
                    scanner.nextLine();
                }
                    
            }while(!aliasUnico);
                                         
            Sistema.jugadores.add(new Jugador(nombre, edad, alias));
            System.out.println("Jugador registrado con éxito.");
            
         
            
        }else{  //Si es el primer jugador a registrar
            Sistema.jugadores.add(new Jugador(nombre, edad, alias));
            System.out.println("Jugador registrado con éxito.");
            
       

        }
       
    }
}

