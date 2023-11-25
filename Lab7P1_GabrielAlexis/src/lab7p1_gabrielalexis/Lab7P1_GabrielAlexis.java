/*fila 3 asiento 9*/
package lab7p1_gabrielalexis;

import java.util.Scanner;
import java.util.Random;

public class Lab7P1_GabrielAlexis {

    static Scanner input = new Scanner(System.in);
    static Random rand = new Random();

    public static void imprimir(char matriz[][]) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (j == 0) {
                    System.out.print("[ " + matriz[i][j] + " , ");
                } else if (j == matriz.length - 1) {
                    System.out.print(matriz[i][j] + " ]");
                } else {
                    System.out.print(matriz[i][j] + " , ");
                }
            }
            System.out.println("");
        }

    }

    public static void imprimirInt(int matriz[][]) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(" [" + matriz[i][j] + "]");
            }
            System.out.println("");
        }

    }

    public static char[][] generalTablero(char[][] matriz) {
        char temporal[][] = matriz;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((temporal[i][j] != 'X' && temporal[i][j] != '0')) {
                    temporal[i][j] = ' ';

                }

            }
        }
        return temporal;
    }

    public static boolean verificarPosicionValida(char[][] matriz, int posx, int posy) {
        boolean esValida = false;
        if ((posx > -1 && posx < 3) && (posy > -1 && posy < 3) && ((matriz[posx][posy] != 'X') && (matriz[posx][posy] != '0'))) {
            esValida = true;
        }
        return esValida;

    }

    public static boolean verificarEmpate(char[][] matriz) {
        boolean hayEmpate = true;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == ' ') {
                    hayEmpate = false; // Revisa que todavia haya espacios en blanco
                }
            }
        }
        return hayEmpate;
    }

    public static boolean verificarVictoria(char[][] matriz, char signo) {
        // verifica filas
        for (int i = 0; i < 3; i++) {
            if (matriz[i][0] == signo && matriz[i][1] == signo && matriz[i][2] == signo) {
                return true;
            }
        }

        //verifica las columnas
        for (int i = 0; i < 3; i++) {
            if (matriz[0][i] == signo && matriz[1][i] == signo && matriz[2][i] == signo) {
                return true;
            }
        }

        //verifica diagonales
        if (matriz[0][0] == signo && matriz[1][1] == signo && matriz[2][2] == signo) {
            return true;
        }
        if (matriz[0][2] == signo && matriz[1][1] == signo && matriz[2][0] == signo) {
            return true;
        }

        return false;
    }

    public static int[][] generarIntMatrizAleatoria(int filas, int columnas) {
        int temporal[][] = new int[filas][columnas];
        int num;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                num = rand.nextInt(1, 99);
                temporal[i][j] = num;
            }
        }
        return temporal;
    }

public static void encontrarPuntoDeSilla(int[][] matriz) {
    boolean seEncontroPunto = false;
    for (int i = 0; i < matriz.length; i++) {
        int indiceColumna = encuentraPosMinCol(matriz[i]);
        int minFila = matriz[i][indiceColumna];

        if (esPuntoDeSilla(matriz, i, indiceColumna, minFila)) {
            System.out.println("Punto de silla encontrado: " + minFila + " en la posicion (" + i + ", " + indiceColumna + ")");
            seEncontroPunto = true;
        }
    }
    if (!seEncontroPunto) {
        System.out.println("No se encontraron puntos de silla.");
    }
}

    // Encuentra la posicion del numero mas pequeño de la fila
    public static int encuentraPosMinCol(int[] fila) {
        int indiceColumna = 0;
        for (int j = 1; j < fila.length; j++) {
            if (fila[j] < fila[indiceColumna]) {
                indiceColumna = j;
            }
        }
        return indiceColumna;
    }

    // Revisa si el numero minimo de la fila tambien es el numero mayor de su columna
    public static boolean esPuntoDeSilla(int[][] matriz, int fila, int columna, int num) {
        boolean esPunto=true;
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][columna] > num) {
                return false;
            }
        }
        return esPunto;
    }


    public static void main(String[] args) {
        System.out.println("Elige una opcion\n1. Tres en raya \n2. Puntos de silla\n3. Salir\n");
        int opt = input.nextInt();
        while (opt != 3) {
            switch (opt) {
                case 1:
                    System.out.println("-----Bienvenido a tres en raya------");
                    System.out.println("Tablero actual");
                    char tablero[][] = new char[3][3];
                    boolean prueba;
                    char signo = 'X';
                    imprimir(tablero);
                    for (int i = 1; verificarVictoria(tablero, signo) != true; i++) {
                        int x = 0, y = 0;

                        System.out.println("Es el turno de: " + signo);
                        if (i % 2 != 0) {
                            signo = 'X';
                            System.out.println("Ingrese la posision X: ");
                            x = input.nextInt();
                            System.out.println("Ingrese la posision Y: ");
                            y = input.nextInt();
                            // si la posicion no es valida la vuelva a pedir hasta que sea valida
                            while (!verificarPosicionValida(tablero, x, y)) {
                                System.out.println("Posicion no valida. Ingrese la posision X: ");
                                x = input.nextInt();
                                System.out.println("Ingrese la posision Y: ");
                                y = input.nextInt();
                            }
                        } else if (i % 2 == 0) {

                            signo = '0';
                            boolean posicionValida = false;

                            // revisa que la maquina este eligiendo una posicion valida
                            while (!posicionValida) {
                                x = rand.nextInt(0, 2);
                                y = rand.nextInt(0, 2);

                                if (verificarPosicionValida(tablero, x, y)) {
                                    posicionValida = true;
                                    System.out.println("La maquina ha elegido la posicion " + x + " " + y);

                                }
                            }
                        }
                        if (verificarPosicionValida(tablero, x, y)) {
                            tablero[x][y] = signo;

                            tablero = generalTablero(tablero);
                            imprimir(tablero);

                            if (verificarEmpate(tablero)) {
                                System.out.println("¡Empate!");
                            } else if (verificarVictoria(tablero, signo)) {
                                System.out.println("Ha ganado " + signo + "!.");
                            }
                        }
                    }

                    break;
                case 2:
                    System.out.println("Ingrese el tamaño de las filas: ");
                    int filas = input.nextInt();
                    System.out.println("Ingrese el tamaño de las filas: ");
                    int columnas = input.nextInt();
                    int puntosSilla[][] = new int[filas][columnas];
                    System.out.println("Matriz generada: ");
                    imprimirInt(puntosSilla = generarIntMatrizAleatoria(filas, columnas));
                    encontrarPuntoDeSilla(puntosSilla);
                
                    break;
                case 3:
                    System.out.println("Fin del programa");
                    break;
                default:

                    throw new AssertionError();
            }
            System.out.println("Elige una opcion\n1. Tres en raya \n2. Puntos de silla\n3. Salir\n");
            opt = input.nextInt();
        }

    }

}
