package Dominio;

/*
    Autores:
    Mateo Franciulli 310956
    Ivan Castelli 306188
 */

public class Minitablero {
    private char[][] tableroChico;
    private char ganador;

    public Minitablero() {
        tableroChico = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tableroChico[i][j] = '-';  // Inicializamos con posiciones vacias
            }
        }
        ganador = '-';
    }

    
    public char getPosicion(int fila, int col) {
        return tableroChico[fila][col];
    }

   // Realizar una jugada en este minitablero
    public boolean realizarJugada(int fila, int columna, char jugador) {
        boolean seHizo = false;
        if (tableroChico[fila][columna] == '-' ) {  // Si no hay otra ficha, hace la jugada
            tableroChico[fila][columna] = jugador;
            
            
            if (ganador == '-') { 
                verificarGanador(); //Solo se llama al metodo si aun no se gano este minitablero
            }
           
            seHizo = true;
        }
        return seHizo;
    }
    
    
    
    public boolean realizarJugadaMagica(int filaMini, int colMini, char jugador) {
        boolean realizada = true;
        // Verificar si la posicion es valida
        if (filaMini < 0 || filaMini >= 3 || colMini < 0 || colMini >= 3) {
            realizada = false;
        }

        // Limpiar el minitablero
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tableroChico[i][j] = '-';  
            }
        }
        
        ganador = '-';
        
        // Colocamos la ficha del jugador en la posición indicada
        tableroChico[filaMini][colMini] = jugador;
        
        return realizada;  
    }

    public boolean esPosicionVacia(int fila, int columna) {
    
        return tableroChico[fila][columna] == '-';
    
    }
    public boolean hayPosicionesDisponibles() {
       boolean hayVacia = false;
        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 3; col++) {
                if (esPosicionVacia(fila, col)) {
                    hayVacia = true;
                }
            }
        }
        return hayVacia;
    }
    
    public void verificarGanador() {
        for (int i = 0; i < 3; i++) {
           
            // Filas
            if (tableroChico[i][0] != '-' && tableroChico[i][0] == tableroChico[i][1] && tableroChico[i][1] == tableroChico[i][2]) {
                ganador = tableroChico[i][0];
            }
            // Columnas
            if (tableroChico[0][i] != '-' && tableroChico[0][i] == tableroChico[1][i] && tableroChico[1][i] == tableroChico[2][i]) {
                ganador = tableroChico[0][i];
            }
        }
        // Diagonales
        if (tableroChico[0][0] != '-' && tableroChico[0][0] == tableroChico[1][1] && tableroChico[1][1] == tableroChico[2][2]) {
            ganador = tableroChico[0][0];
        }
        if (tableroChico[0][2] != '-' && tableroChico[0][2] == tableroChico[1][1] && tableroChico[1][1] == tableroChico[2][0]) {
            ganador = tableroChico[0][2];
        }
    } 
    
    public char getGanador() {
        return ganador;
    }

    public boolean tieneGanador() {
        return ganador != '-';
    }
    
    


}