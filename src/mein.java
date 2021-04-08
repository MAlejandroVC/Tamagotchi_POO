import Classes.Species.Species;
import Classes.Tamagotchi;

import java.util.Scanner;

public class mein {
    public static void main(String[] args) {
        int totalPets = 0;
        int currentPet = 0;
        Tamagotchi[] myPets = new Tamagotchi[64];

        //aqui va a ser el loop del juego
        String op;
        Scanner reader = new Scanner(System.in);
        boolean playing = true;
        while (playing){
            printMenu();
            op = reader.nextLine();
            switch(op){
                case "1":
                    newPet(myPets, totalPets);
                    totalPets++;
                    break;
                case "2":
                    currentPet = select(myPets, totalPets);
                    break;
                case "3":
                    jugar(myPets[currentPet]);
                    break;
                case "4":
                    explorar(myPets[currentPet]);
                    break;
                case "5":
                    pelear();
                    break;
                case "6":
                    load();
                    break;
                case "7":
                    skip(myPets, totalPets);
                    break;
                case "0":
                    save();
                    playing = false;
                    break;
            }
            //update
            for(int i=0; i<totalPets; i++)
                myPets[i].update();
            //procrear
            if(totalPets > 1) { //total pets
                for (int i = 0; i < totalPets - 1; i++) { //i
                    for (int j = i + 1; j < totalPets; j++) { //j
                        if(totalPets > 63)
                            break;
                        System.out.println(""+i+"v"+j);
                        myPets[totalPets] = Tamagotchi.procrear(myPets[i], myPets[j]);
                        if (myPets[totalPets] != null) {
                            totalPets++;
                            //System.out.println("procrearon " + totalPets);
                        }
                    }
                }
            }
        }
    }

    public static void printMenu(){
        System.out.println(" ~~~~~~~~~~~~~~~ MENU ~~~~~~~~~~~~~~~   ");
        System.out.println(" 1. Crear un Tamagotchi nuevo           ");
        System.out.println(" 2. Selecciona un Tamagotchi            ");
        System.out.println(" 3. Jugar con tu Tamagotchi             ");
        System.out.println(" 4. Manda el Tamagotchi a explorar      ");
        System.out.println(" 5. Pelea contra otro Tamagotchi        ");
        System.out.println(" 6. Importa de un archivo               ");
        System.out.println(" 7. Skip                                ");
        System.out.println(" 0. Salir del juego                     ");
    }

    public static void newPet(Tamagotchi[] array, int total){
        System.out.println("Selecciona la especie: ");
        System.out.println(Species.menu());
        Scanner reader = new Scanner(System.in);
        int op = Integer.parseInt(reader.nextLine());
        if(op<0 || op>6)
            op = 1;
        System.out.println("Ingresa un nombre: ");
        String name = reader.nextLine();
        array[total] = new Tamagotchi(name, op);
    }

    public static int select(Tamagotchi[] array, int total){
        if(total == 0) {
            System.out.println("No hay mascotas, crea una nueva o carga de un archivo");
            return 0;
        }
        Scanner reader = new Scanner(System.in);
        for(int i=0; i<total; i++)
            System.out.println((i+1) + ". " + array[i].getName());
        System.out.println("0. Cancelar");
        int op = Integer.parseInt(reader.nextLine());
        if(op<1 || op>total)
            return 0;
        op--;
        System.out.println(array[op]);
        return op;
    }

    public static void jugar(Tamagotchi pet){
        System.out.println(pet.getName() + "esta jugando");
    }

    public static void explorar(Tamagotchi pet){
        System.out.println(pet.getName() + "esta explorando");
    }

    public static void pelear(){
        System.out.println("Pelea");
    }

    public static void load(){
        System.out.println("Loading from file");
    }

    public static void skip(Tamagotchi[] myPets, int totalPets){
        Scanner reader = new Scanner(System.in);
        System.out.print("Ingresa cuantos turnos quieres saltar: ");
        int turns = Integer.parseInt(reader.nextLine())-1;
        for(int i=0; i<turns; i++){
            for(int j=0; j<totalPets; j++)
                myPets[j].update();
        }
    }

    public static void save(){
        System.out.println("Saving...");
        System.out.println("Save complete");
    }
}
