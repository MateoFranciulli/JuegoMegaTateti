package Dominio;

/*
    Autores:
    Mateo Franciulli 310956
    Ivan Castelli 306188
 */

public class Tablero {
    private Minitablero[][] tableroGrande;

    public Tablero() {
        tableroGrande = new Minitablero[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tableroGrande[i][j] = new Minitablero(); // Inicializamos cada minitablero
            }
        }
    }
 
  
    public void mostrarTableroGrande(int filaTablero, int columnaTablero) {
    String colorVerde = "\u001B[42m"; // Fondo verde
    String colorAmarillo = "\u001B[43m"; // Fondo amarillo
    String resetColor = "\u001B[0m";  // Restablecer color
    String colorRojo = "\u001B[31m";  // Para X
    String colorAzul = "\u001B[34m";  // Para O

   //recorro el mega tablero
    for (int filaMegaTateti = 0; filaMegaTateti < 3; filaMegaTateti++) {
        //linea arriba de cada tablero
        if (filaMegaTateti == filaTablero || filaMegaTateti == filaTablero+1) {
            if (columnaTablero == 0) {
                System.out.println(colorAmarillo + "*******" + resetColor + colorVerde + "************" + resetColor);
            } else if (columnaTablero == 1) {
                System.out.println(colorVerde + "******" + resetColor + colorAmarillo + "*******" + resetColor + colorVerde + "******" + resetColor);
            } else if (columnaTablero == 2) {
                System.out.println(colorVerde + "************" + resetColor + colorAmarillo + "*******" + resetColor);
            }
        } else {
            System.out.println(colorVerde + "*******************" + resetColor);
        }

        // Recorro cada fila del tablero chico
        for (int fila = 0; fila < 3; fila++) {
            for (int columnaMegaTateti = 0; columnaMegaTateti < 3; columnaMegaTateti++) {
                if (columnaMegaTateti > 0) {
                    System.out.print(""); // No imprimo asterisco aquí para evitar el duplicado
                }

                // asteriscos izquierda
                if ( filaMegaTateti == filaTablero && columnaMegaTateti == columnaTablero) {
                    System.out.print(colorAmarillo + "*" + resetColor);
                }else if (columnaMegaTateti - 1 == columnaTablero && filaMegaTateti == filaTablero) {
                    System.out.print(colorAmarillo + "*" + resetColor);
                } else {
                    System.out.print(colorVerde + "*" + resetColor);
                }
                Minitablero minitablero = tableroGrande[filaMegaTateti][columnaMegaTateti];  
                char ganadorMinitablero = minitablero.getGanador();
                // Estructura interna
                for (int col = 0; col < 3; col++) {
                     minitablero = tableroGrande[filaMegaTateti][columnaMegaTateti];
                    char ficha = minitablero.getPosicion(fila, col);
                    
                    if (ficha == '-') {
                        ficha = ' ';
                    }
                    String fichaConColor = " ";
                 if (ficha != ' ') {
                        if (ganadorMinitablero != '-') {
                            // Si hay un ganador, pintar todas las fichas con el color del ganador
                            if (ganadorMinitablero == 'X') {
                                fichaConColor = colorRojo + ficha + resetColor; // Pintar de rojo para el ganador "X"
                            } else if (ganadorMinitablero == 'O') {
                                fichaConColor = colorAzul + ficha + resetColor; // Pintar de azul para el ganador "O"
                            }
                        } else {
                            // Si no hay ganador, asignar color de la ficha
                            if (ficha == 'X') {
                                fichaConColor = colorRojo + ficha + resetColor; // Pintar de rojo para "X"
                            } else if (ficha == 'O') {
                                fichaConColor = colorAzul + ficha + resetColor; // Pintar de azul para "O"
                            }
                        }
                    }

                    if (col < 2) {
                        System.out.print(fichaConColor + "|");
                    } else {
                        System.out.print(fichaConColor);
                    }
                }
            }

           // final de la fila
            if (filaMegaTateti == filaTablero && columnaTablero == 2) { // amarillo si está en el borde derecho
                System.out.println(colorAmarillo + "*" + resetColor);
            } else {
                System.out.println(colorVerde + "*" + resetColor);
            }
            
              // separadores
            if (fila < 2) {
                for (int columnaMegaTateti = 0; columnaMegaTateti < 3; columnaMegaTateti++) {
                    if (columnaMegaTateti > 0) {
                        System.out.print(""); // separo
                    }
                    if (filaMegaTateti == filaTablero && columnaMegaTateti == columnaTablero) {
                        System.out.print(colorAmarillo + "*" + resetColor + "-+-+-");
                    } else if (filaMegaTateti == filaTablero && columnaMegaTateti == columnaTablero+1) {
                        System.out.print(colorAmarillo + "*" + resetColor + "-+-+-");
                    }else {
                        System.out.print(colorVerde + "*" + resetColor + "-+-+-");
                    }
                }
                // separador de la derecha
                if (filaMegaTateti == filaTablero && columnaTablero == 2) {
                    System.out.println(colorAmarillo + "*" + resetColor);
                } else {
                    System.out.println(colorVerde + "*" + resetColor);
                }
            }
        }
    }

    // ultima linea de asteriscos
    for (int filaMegaTateti = 2; filaMegaTateti < 3; filaMegaTateti++) {
        if (filaMegaTateti == filaTablero && columnaTablero == 0) {
            System.out.println(colorAmarillo + "*******" + resetColor + colorVerde + "************" + resetColor);
        } else if (filaMegaTateti == filaTablero && columnaTablero == 1) {
            System.out.println(colorVerde + "******" + resetColor + colorAmarillo + "*******" + resetColor + colorVerde + "******" + resetColor);
        } else if (filaMegaTateti == filaTablero && columnaTablero == 2) {
            System.out.println(colorVerde + "************" + resetColor + colorAmarillo + "*******" + resetColor);
        } else {
            System.out.println(colorVerde + "*******************" + resetColor);
        }
    }
}
    
    
 
    // Realizar una jugada en un minitablero específico
    public boolean realizarJugada(int filaTablero, int colTablero, int filaMini, int colMini, char jugador) {
        return tableroGrande[filaTablero][colTablero].realizarJugada(filaMini, colMini, jugador);
    }

     
    public boolean realizarJugadaMagica(int filaTablero, int colTablero, int filaMini, int colMini, char ficha) {
    
    return tableroGrande[filaTablero][colTablero].realizarJugadaMagica(filaMini, colMini, ficha);
}
    
    
    public boolean esPosicionVacia(int filaTablero, int colTablero, int filaMini, int colMini) {
    return tableroGrande[filaTablero][colTablero].esPosicionVacia(filaMini, colMini);
}
    
    public boolean verificarEmpateTableroGrande() {
        boolean empate = true;
       
        if (verificarGanadorTableroGrande() != '-') {
            empate = false;
        }
        
        // Recorrer todos los minitableros
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                
                if (tableroGrande[i][j].getGanador() == '-' && tableroGrande[i][j].hayPosicionesDisponibles()) {
                    empate = false;
                }
            }
        }
        
        return empate;
    }
    
    
    public char verificarGanadorTableroGrande() {
        for (int i = 0; i < 3; i++) {
            // Filas
            if (tableroGrande[i][0].getGanador() != '-' &&
                tableroGrande[i][0].getGanador() == tableroGrande[i][1].getGanador() &&
                tableroGrande[i][1].getGanador() == tableroGrande[i][2].getGanador()) {
               
                return tableroGrande[i][0].getGanador();
                
                
            }
            // columnas
            if (tableroGrande[0][i].getGanador() != '-' &&
                tableroGrande[0][i].getGanador() == tableroGrande[1][i].getGanador() &&
                tableroGrande[1][i].getGanador() == tableroGrande[2][i].getGanador()) {
                return tableroGrande[0][i].getGanador();
            }
        }
        // Diagonales
        if (tableroGrande[0][0].getGanador() != '-' &&
            tableroGrande[0][0].getGanador() == tableroGrande[1][1].getGanador() &&
            tableroGrande[1][1].getGanador() == tableroGrande[2][2].getGanador()) {
            return tableroGrande[0][0].getGanador();
        }
        if (tableroGrande[0][2].getGanador() != '-' &&
            tableroGrande[0][2].getGanador() == tableroGrande[1][1].getGanador() &&
            tableroGrande[1][1].getGanador() == tableroGrande[2][0].getGanador()) {
            return tableroGrande[0][2].getGanador();
        }
        return '-'; // No hay ganador 
    }

    // Verificar si un minitablero tiene ganador
    public boolean minitableroGanado(int filaTablero, int colTablero) {
        return tableroGrande[filaTablero][colTablero].tieneGanador();
    }
    
    
}