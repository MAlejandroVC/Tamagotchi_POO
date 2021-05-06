package Games;
import Classes.Tamagotchi;
import java.util.Scanner;

public class Roshambo {
    public static final int
            PIEDRA = 0,
            PAPEL = 1,
            TIGERAS = 2;
    private int ganador;
    Tamagotchi pet;

    public Roshambo(Tamagotchi pet) {
        this.pet = pet;
    }

    public void Jugar() {
        int masterChoice;
        int tamagotchiChoice = (int) Math.floor(Math.random()*(3-1+1)+1);

        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("1- Piedra\n2- Papel\n3-Tijeras\nEscoje una opcion\n~>");
            masterChoice = Integer.parseInt(scan.next());
            if (masterChoice < 0 || masterChoice > 2){
                System.out.println("Input no valido, ingrese un valor correcto");
            }
        } while (masterChoice < 0 || masterChoice > 2);


        if (masterChoice == 0 && tamagotchiChoice == 0) ganador = 2;
        if (masterChoice == 0 && tamagotchiChoice == 1) ganador = 1;
        if (masterChoice == 0 && tamagotchiChoice == 2) ganador = 0;

        if (masterChoice == 1 && tamagotchiChoice == 0) ganador = 0;
        if (masterChoice == 1 && tamagotchiChoice == 1) ganador = 2;
        if (masterChoice == 1 && tamagotchiChoice == 2) ganador = 1;

        if (masterChoice == 2 && tamagotchiChoice == 0) ganador = 1;
        if (masterChoice == 2 && tamagotchiChoice == 1) ganador = 0;
        if (masterChoice == 2 && tamagotchiChoice == 2) ganador = 2;
    }

    public void Ganador(){
        switch (ganador){
            case 0:
                System.out.println("Ganador Master");
                break;
            case 1:
                System.out.println("Ganador" + pet.getName());
                break;
            case 2:
                System.out.println("Empate");
                break;
            default:
                System.out.println("Inaccesible");
                break;

        }
        Scanner scan = new Scanner(System.in);
        System.out.print("Presione Enter para continuar");
        String vacio = scan.nextLine();
    }

    public static void main(Tamagotchi pet) {
        Roshambo piedra = new Roshambo(pet);
        piedra.Jugar();
        piedra.Ganador();
    }
}
