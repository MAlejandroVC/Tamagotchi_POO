package Games;
import Classes.Tamagotchi;
import java.util.Scanner;

public class Gato {
    private int cuadros = 9;
    private String[][] tabla;
    private boolean tamagotchi;
    private boolean master;
    Tamagotchi pet;

    public Gato(Tamagotchi pet)
    {
        tabla = new String[3][3];
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                tabla[x][y] = " ";
            }
        }

        this.pet = pet;
    }

    public void tablero()
    {
        for(int x = 0; x < 3; x++)
        {
            System.out.print("|  ");
            for(int y = 0; y < 3; y++)
            {

                System.out.print(tabla[x][y] + "  | ");
            }
            System.out.println();
        }
    }

    public boolean GanadorAI() {
        if (tabla[0][0].equals("O") && tabla[0][1].equals("O") && tabla[0][2].equals("O")) tamagotchi = true;
        if (tabla[1][0].equals("O") && tabla[1][1].equals("O") && tabla[1][2].equals("O")) tamagotchi = true;
        if (tabla[2][0].equals("O") && tabla[2][1].equals("O") && tabla[2][2].equals("O")) tamagotchi = true;
        if (tabla[0][0].equals("O") && tabla[1][0].equals("O") && tabla[2][0].equals("O")) tamagotchi = true;
        if (tabla[0][1].equals("O") && tabla[1][1].equals("O") && tabla[2][1].equals("O")) tamagotchi = true;
        if (tabla[0][2].equals("O") && tabla[1][2].equals("O") && tabla[2][2].equals("O")) tamagotchi = true;
        if (tabla[0][0].equals("O") && tabla[1][1].equals("O") && tabla[2][2].equals("O")) tamagotchi = true;
        if (tabla[0][2].equals("O") && tabla[1][1].equals("O") && tabla[2][0].equals("O")) tamagotchi = true;
        return tamagotchi;
    }

    public boolean GanadorUser() {
        if (tabla[0][0].equals("X") && tabla[0][1].equals("X") && tabla[0][2].equals("X")) master = true;
        if (tabla[1][0].equals("X") && tabla[1][1].equals("X") && tabla[1][2].equals("X")) master = true;
        if (tabla[2][0].equals("X") && tabla[2][1].equals("X") && tabla[2][2].equals("X")) master = true;
        if (tabla[0][0].equals("X") && tabla[1][0].equals("X") && tabla[2][0].equals("X")) master = true;
        if (tabla[0][1].equals("X") && tabla[1][1].equals("X") && tabla[2][1].equals("X")) master = true;
        if (tabla[0][2].equals("X") && tabla[1][2].equals("X") && tabla[2][2].equals("X")) master = true;
        if (tabla[0][0].equals("X") && tabla[1][1].equals("X") && tabla[2][2].equals("X")) master = true;
        if (tabla[0][2].equals("X") && tabla[1][1].equals("X") && tabla[2][0].equals("X")) master = true;
        return master;
    }

    public void Jugar() {

        int masterX;
        int masterY;
        int tamagotchiX;
        int tamagotchiY;

        while (cuadros > 0 && !master && !tamagotchi) {
            boolean Ocupado;
            boolean masterXError;
            boolean masterYError;

            do {
                if (cuadros > 0) {
                    Scanner reader = new Scanner(System.in);
                    do {
                        System.out.print("Fila (0 - 2) ");
                        masterX = Integer.parseInt(reader.nextLine());
                        if (masterX < 0 || masterX > 2){
                            System.out.println("Input no valido, ingrese un valor correcto");
                        }
                    } while (masterX < 0 || masterX > 2);

                    do {
                        System.out.println("Columna (0 - 2)");
                        masterY = Integer.parseInt(reader.nextLine());
                        if (masterY < 0 || masterY > 2){
                            System.out.println("Input no valido, ingrese un valor correcto");
                        }
                    } while (masterY < 0 || masterY > 2);

                    if (!tabla[masterX][masterY].equals("O") && !tabla[masterX][masterY].equals("X")) {
                        tabla[masterX][masterY] = "X";
                        cuadros = cuadros - 1;
                        Ocupado = true;
                    } else {
                        Ocupado = false;
                        System.out.println("Espcio ocupado");
                    }
                } else {
                    Ocupado = true;
                }
            } while (!Ocupado);

            tablero();
            master = GanadorUser();
            tamagotchi = GanadorAI();

            System.out.println("Turno de " + pet.getName());

            do {
                if (cuadros > 0) {
                    tamagotchiX = (int) (Math.random() * 3);
                    tamagotchiY = (int) (Math.random() * 3);
                    if (!tabla[tamagotchiX][tamagotchiY].equals("O") && !tabla[tamagotchiX][tamagotchiY].equals("X")) {
                        tabla[tamagotchiX][tamagotchiY] = "O";
                        cuadros = cuadros - 1;
                        Ocupado = true;
                    } else {
                        Ocupado = false;
                    }
                } else {
                    Ocupado = true;
                }
            } while (!Ocupado);
            tablero();
            tamagotchi = GanadorAI();
            master = GanadorUser();
        }
    }

    public void Ganador() {
        if (master) {
            System.out.println("Ganador Master");
        } else if (tamagotchi) {
            System.out.println("Ganodor" + pet.getName());
        } else if (cuadros == 0) {
            System.out.println("Empate");
        }
        Scanner scan = new Scanner(System.in);
        System.out.print("Presione Enter para continuar");
        String vacio = scan.nextLine();
    }

    public static void main(Tamagotchi pet) {
        Gato gato = new Gato(pet);
        gato.Jugar();
        gato.Ganador();
    }
}

