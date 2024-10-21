
package Interfaz;

/*
    Autores:
    Mateo Franciulli 310956
    Ivan Castelli 306188
 */





import static Dominio.Sistema.opcionMenu;
import static Interfaz.Menu.animacionTaTeTi;


public class MegaTateti {


public static void main (String[] args) throws InterruptedException  {
            
    animacionTaTeTi();
    
    opcionMenu();
    
    
}

   public static void mostrarSuperTablero() {
    String color = "\u001B[42m";
    String resetColor = "\u001B[0m";  

    String datos[][] = new String[3][3];
    // Lleno la matriz base de espacios vacíos
    for (int i = 0; i < datos.length; i++) {
        for (int j = 0; j < datos[0].length; j++) {
            datos[i][j] = " ";
        }
    }

    // Hago la matriz grande, poniendo 3 veces las chicas
    for (int filaMegaTateti = 0; filaMegaTateti < 3; filaMegaTateti++) {
        System.out.println(color + "*******************" + resetColor); 
        // Línea de asteriscos antes de cada fila de tableros chicos

        for (int fila = 0; fila < 3; fila++) { // Recorro cada fila de un tablero
            System.out.print(color+"*"+resetColor); // Asterisco al inicio de la fila
            
            for (int columnaMegaTateti = 0; columnaMegaTateti < 3; columnaMegaTateti++) {
                // Pongo los espacios en blanco o las barras
                for (int j = 0; j < 3; j++) {
                    if (j < datos[0].length - 1) {
                        System.out.print(datos[fila][j] + "|");
                    } else {
                        System.out.print(datos[fila][j]);
                    }
                }
                // Espacio entre los tableros
                if (columnaMegaTateti < 2) {
                    System.out.print(color+"*"+resetColor); // Espacio entre tableros
                }
            }
            
            System.out.println(color+"*"+resetColor); // Asterisco al final de la fila
            // Dibujo separadores entre las filas de cada tablero
            if (fila < 2) {
                System.out.println(color+"*"+resetColor+"-+-+-"+color+"*"+resetColor+
                        "-+-+-"+color+"*"+resetColor+"-+-+-"+color+"*"+resetColor);
            }
        }
    }
    System.out.println(color + "*******************" + resetColor); // Cierro la matriz
}
                       
  
    
}