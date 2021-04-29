package Games;
import Classes.Tamagotchi;
import java.util.Scanner;
import java.util.stream.IntStream;

public class C0necta4 {
    Tamagotchi pet;

    public C0necta4(Tamagotchi pet) {
        this.pet = pet;
    }

    public static void main(Tamagotchi pet) {
        Scanner in = new Scanner(System.in);

        char[][] tablero = new char[6][7];

        //initialize array
        for (int fila = 0; fila < tablero.length; fila++){
            for (int col = 0; col < tablero[0].length; col++){
                tablero[fila][col] = ' ';
            }
        }

        int turno = 1;
        char jugador = 'J';
        boolean ganador = false;

        while (!ganador && turno <= 42){
            boolean jvalido;
            int jugada;
            do {
                if (jugador == 'J') {
                    printTablero(tablero);

                    System.out.print("Jugador " + jugador + ", Escoge una columna ");
                    jugada = in.nextInt();

                    jvalido = Validar(jugada,tablero);
                }
                else{

                        jugada = (int) Math.floor(Math.random()*(7));
                        jvalido = Validar(jugada,tablero);
                }


            }while (!jvalido);


            for (int row = tablero.length-1; row >= 0; row--){
                if(tablero[row][jugada] == ' '){
                    tablero[row][jugada] = jugador;
                    break;
                }
            }

            ganador = Ganador(jugador,tablero);

            if (jugador == 'J' && !ganador){
                jugador = pet.getName().charAt(0);
            }
            else if(jugador == pet.getName().charAt(0) && !ganador) {
                jugador = 'J';
            }
            turno++;
        }

        printTablero(tablero);

        if (ganador){
            if (jugador == 'J')
            {
                System.out.println("Gano jugador");
            }
            else
            {
                System.out.println("Gano" +  pet.getName());
            }
        }else{
            System.out.println("Empate");
        }

    }

    public static void printTablero(char[][] grid){
        System.out.println(" 0 1 2 3 4 5 6");
        System.out.println("---------------");
        for (char[] chars : grid) {
            System.out.print("|");
            for (int col = 0; col < grid[0].length; col++) {
                System.out.print(chars[col]);
                System.out.print("|");
            }
            System.out.println();
            System.out.println("---------------");
        }
        System.out.println(" 0 1 2 3 4 5 6");
        System.out.println();
    }

    public static boolean Validar(int column, char[][] grid){
        return column >= 0 && column <= grid[0].length && grid[0][column] == ' ';
    }

    public static boolean Ganador(char jugador, char[][] tablero){

        for(int row = 0; row<tablero.length; row++){
            for (int col = 0;col < tablero[0].length - 3;col++){
                if (tablero[row][col] == jugador   &&
                        tablero[row][col+1] == jugador &&
                        tablero[row][col+2] == jugador &&
                        tablero[row][col+3] == jugador){
                    return true;
                }
            }
        }

        for(int row = 0; row < tablero.length - 3; row++){
            for(int col = 0; col < tablero[0].length; col++){
                if (tablero[row][col] == jugador   &&
                        tablero[row+1][col] == jugador &&
                        tablero[row+2][col] == jugador &&
                        tablero[row+3][col] == jugador){
                    return true;
                }
            }
        }

        for(int row = 3; row < tablero.length; row++){
            for(int col = 0; col < tablero[0].length - 3; col++){
                if (tablero[row][col] == jugador   &&
                        tablero[row-1][col+1] == jugador &&
                        tablero[row-2][col+2] == jugador &&
                        tablero[row-3][col+3] == jugador){
                    return true;
                }
            }
        }

        for(int row = 0; row < tablero.length - 3; row++){
            for(int col = 0; col < tablero[0].length - 3; col++){
                if (tablero[row][col] == jugador   &&
                        tablero[row+1][col+1] == jugador &&
                        tablero[row+2][col+2] == jugador &&
                        tablero[row+3][col+3] == jugador){
                    return true;
                }
            }
        }
        return false;
    }
}
