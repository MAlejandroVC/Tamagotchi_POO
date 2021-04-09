import Classes.Species.Species;
import Classes.Species.Subspecies;
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
                    view(myPets[currentPet]);
                    break;
                case "4":
                    jugar(myPets[currentPet]);
                    break;
                case "5":
                    explorar(myPets[currentPet]);
                    break;
                case "6":
                    pelear();
                    break;
                case "7":
                    load();
                    break;
                case "8":
                    skip(myPets, totalPets);
                    break;
                case "0":
                    save();
                    playing = false;
                    break;
                case "-1":
                    devTools(myPets[currentPet]);
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
                        myPets[totalPets] = Tamagotchi.procrear(myPets[i], myPets[j]);
                        if (myPets[totalPets] != null){
                            totalPets++;
                            System.out.println("Nació un tamagotchi nuevo! Ve a conocerlo");
                        }
                    }
                }
            }
            //
        }
    }

    public static void printMenu(){
        System.out.println(" ~~~~~~~~~~~~~~~ MENU ~~~~~~~~~~~~~~~   ");
        System.out.println(" 1. Crear un Tamagotchi nuevo           ");
        System.out.println(" 2. Selecciona un Tamagotchi            ");
        System.out.println(" 3. Ver a mi Tamagotchi                 ");
        System.out.println(" 4. Jugar con tu Tamagotchi             ");
        System.out.println(" 5. Manda el Tamagotchi a explorar      ");
        System.out.println(" 6. Pelea contra otro Tamagotchi        ");
        System.out.println(" 7. Importa de un archivo               ");
        System.out.println(" 8. Skip                                ");
        System.out.println(" 0. Salir del juego                     ");
    }

    public static void newPet(Tamagotchi[] array, int total){
        Species species = Species.selectSpecies();
        System.out.println("Ingresa un nombre: ");
        Scanner reader = new Scanner(System.in);
        String name = reader.nextLine();
        array[total] = new Tamagotchi(name, species);
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
        //System.out.println(array[op]);
        return op;
    }

    public static void view(Tamagotchi pet){
        //Show image
        System.out.println("Nombre:    " + pet.getName());
        System.out.println("Nivel:     " + pet.getLvl());
        System.out.println("Salud:     " + pet.getHp() + "/" + pet.getMaxHp());
        System.out.println("Hambre:    " + pet.getHunger() + "/" + pet.getMaxHunger());
        System.out.println("Felicidad: " + pet.getHappiness() + "/100");
        System.out.println("Edad       " + pet.getAge());
        System.out.println("Especie:   " + pet.getSpecies());
        System.out.println("SPECIAL:   " + pet.getDna());
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

    public static void devTools(Tamagotchi pet){
        String op;
        Scanner reader = new Scanner(System.in);
        boolean dev = true;
        while(dev){
            System.out.println(" ~~~~~~~~~~~~~ DEV MENU ~~~~~~~~~~~~~   ");
            System.out.println(" 1. Cambiar de nivel                    ");
            System.out.println(" 2. Cambiar SPECIAL                     ");
            System.out.println(" 3. Cambiar salud                       ");
            System.out.println(" 0. Salir de DevTools                   ");

            op = reader.nextLine();
            switch(op){
                case "1":
                    System.out.print("Ingresa el nivel nuevo: ");
                    pet.setLvl(Integer.parseInt(reader.nextLine()));
                    break;
                case "2":
                    System.out.print("Cual SPECIAL vas a cambiar? ");
                    switch(reader.nextLine()){
                        case "S":
                            System.out.println("Ingresa el nuevo Str: ");
                            pet.getDna().setStr(Integer.parseInt(reader.nextLine()));
                            break;
                        case "P":
                            System.out.println("Ingresa el nuevo Per: ");
                            pet.getDna().setPer(Integer.parseInt(reader.nextLine()));
                            break;
                        case "E":
                            System.out.println("Ingresa el nuevo End: ");
                            pet.getDna().setEnd(Integer.parseInt(reader.nextLine()));
                            break;
                        case "C":
                            System.out.println("Ingresa el nuevo Cha: ");
                            pet.getDna().setCha(Integer.parseInt(reader.nextLine()));
                            break;
                        case "I":
                            System.out.println("Ingresa el nuevo Int: ");
                            pet.getDna().setInt(Integer.parseInt(reader.nextLine()));
                            break;
                        case "A":
                            System.out.println("Ingresa el nuevo Agl: ");
                            pet.getDna().setAgl(Integer.parseInt(reader.nextLine()));
                            break;
                        case "L":
                            System.out.println("Ingresa el nuevo Lck: ");
                            pet.getDna().setLck(Integer.parseInt(reader.nextLine()));
                            break;
                    }
                    break;
                case "3":
                    pet.heal(pet.getMaxHp());
                    pet.feed(pet.getMaxHunger());
                    pet.play(100);
                    break;
                case "0":
                    dev = false;
            }
        }
    }
}