import Classes.Species.Species;
import Classes.Species.Subspecies;
import Classes.Tamagotchi;

import javax.swing.*;

public class mein {
    public static void main(String[] args) {
        int totalPets = 0;
        int currentPet = 0;
        Tamagotchi[] myPets = new Tamagotchi[64];

        //aqui va a ser el loop del juego
        String op;
        boolean playing = true;
        while (playing){
            op = JOptionPane.showInputDialog(null,
                    """
                            ~~~~~~~~~~~~~~~ MENU ~~~~~~~~~~~~~~~  \s
                            1. Crear un Tamagotchi nuevo          \s
                            2. Selecciona un Tamagotchi           \s
                            3. Ver a mi Tamagotchi                \s
                            4. Alimentar a tu Tamagotchi          \s
                            5. Jugar con tu Tamagotchi            \s
                            6. Manda el Tamagotchi a explorar     \s
                            7. Pelea contra otro Tamagotchi       \s
                            8. Importa de un archivo              \s
                            9. Skip                               \s
                            0. Salir del juego                    \s""".indent(1),
                    "Menú Principal",
                    JOptionPane.QUESTION_MESSAGE);
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
                    comer(myPets[currentPet]);
                    break;
                case "5":
                    jugar(myPets[currentPet]);
                    break;
                case "6":
                    explorar(myPets[currentPet]);
                    break;
                case "7":
                    pelear();
                    break;
                case "8":
                    load();
                    break;
                case "9":
                    skip(myPets, totalPets);
                    break;
                case "0":
                    save();
                    playing = false;
                    break;
                case "-1":
                    devTools(myPets[currentPet]);
                    break;
                default:
                    errorMessage();
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
                            JOptionPane.showMessageDialog(null,
                                    "Nació un tamagotchi nuevo! Ve a conocerlo",
                                    "Nuevo Tamagotchi",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
            //
        }
    }

    public static void newPet(Tamagotchi[] array, int total){
        Species species = Species.selectSpecies();
        String name = JOptionPane.showInputDialog(null,
                "Ingresa un nombre",
                "Crear un Tamagotchi nuevo",
                JOptionPane.QUESTION_MESSAGE);
        array[total] = new Tamagotchi(name, species);
    }

    public static int select(Tamagotchi[] array, int total){
        if(total == 0) {
            JOptionPane.showMessageDialog(null,
                    "No hay mascotas, crea una nueva o carga de un archivo",
                    "Tamagotchis no encontrados",
                    JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        StringBuilder strMascotas = new StringBuilder();
        for(int i=0; i<total; i++)
            strMascotas.append(i + 1).append(". ").append(array[i].getName()).append("\n");
        strMascotas.append("0. Cancelar");
        int op = Integer.parseInt(JOptionPane.showInputDialog(null,
                strMascotas,
                "Selecciona una mascota",
                JOptionPane.PLAIN_MESSAGE));
        if(op<1 || op>total)
            return 0;
        op--;
        return op;
    }

    public static void view(Tamagotchi pet){
        JOptionPane.showMessageDialog(null,
                "Nombre:    " + pet.getName() + "\n" +
                        "Nivel:     " + pet.getLvl()+ "\n" +
                        "Salud:     " + pet.getHp() + "/" + pet.getMaxHp()+ "\n" +
                        "Hambre:    " + pet.getHunger() + "/" + pet.getMaxHunger()+ "\n" +
                        "Felicidad: " + pet.getHappiness() + "/100"+ "\n" +
                        "Edad       " + pet.getAge()+ "\n" +
                        "Especie:   " + pet.getSpecies()+ "\n" +
                        "SPECIAL:   " + pet.getDna(),
                "Ver a mi Tamagotchi",
                JOptionPane.PLAIN_MESSAGE);
    }

    public static void comer(Tamagotchi pet){
        String op;
        //Scanner reader = new Scanner(System.in);
        boolean eating = true;
        while(eating) {
            op = JOptionPane.showInputDialog(null,
                    " ~~~ " + pet.getName() + ": " + pet.getHunger() + "/" + pet.getMaxHunger() + " ~~~ \n"+
                            " ~~~~~~~~~~~~ MENU COMIDA ~~~~~~~~~~~~  "+
                            " 1. Manzana                       (10)  "+
                            " 2. Galleta                       (10)  "+
                            " 3. Sandwich                      (25)  "+
                            " 4. Sopa                          (30)  "+
                            " 5. Carne asada                   (50)  "+
                            " 6. Hamburguesa                   (60)  "+
                            " 7. Pastel                        (75)  "+
                            " 8. Tacos                         (90)  "+
                            " 9. Lox meat pie                 (100)  "+
                            " 0. Salir                               ",
                    "Alimentar Tamagotchi",
                    JOptionPane.QUESTION_MESSAGE);
            switch (op) {
                case "1":
                case "2":
                    pet.feed(10);
                    break;
                case "3":
                    pet.feed(25);
                    break;
                case "4":
                    pet.feed(30);
                    break;
                case "5":
                    pet.feed(50);
                    break;
                case "6":
                    pet.feed(60);
                    break;
                case "7":
                    pet.feed(75);
                    break;
                case "8":
                    pet.feed(90);
                    break;
                case "9":
                    pet.feed(100);
                    break;
                case "0":
                    eating = false;
                    break;
                default:
                    //System.out.println(" !!! OPCION INVALIDA !!! ");
                    errorMessage();
            }
        }
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
        int turns = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Ingresa cuantos turnos quieres saltar",
                "Skip",
                JOptionPane.QUESTION_MESSAGE))-1;
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
        String op, op2, op3;
        boolean dev = true;
        while(dev){
            op = JOptionPane.showInputDialog(null,
                    """
                            ~~~~~~~~~~~~~ DEV MENU ~~~~~~~~~~~~~  \s
                            1. Cambiar de nivel                   \s
                            2. Cambiar SPECIAL                    \s
                            3. Cambiar salud                      \s
                            0. Salir de DevTools                  \s""".indent(1),
                    "Developer Tools",
                    JOptionPane.QUESTION_MESSAGE);

            switch(op){
                case "1":
                    op2 = JOptionPane.showInputDialog(null,
                            "Ingresa el nivel nuevo",
                            "Cambiar de nivel",
                            JOptionPane.QUESTION_MESSAGE);
                    break;
                case "2":
                    //System.out.print("Cual SPECIAL vas a cambiar? ");
                    op2 = JOptionPane.showInputDialog(null,
                            "Cual SPECIAL vas a cambiar?",
                            "Cambiar SPECIAL",
                            JOptionPane.QUESTION_MESSAGE);
                    switch(op2){
                        case "S":
                            op3 = JOptionPane.showInputDialog(null,
                                    "Ingresa el nuevo STR",
                                    "Cambiar de SPECIAL",
                                    JOptionPane.QUESTION_MESSAGE);
                            pet.getDna().setStr(Integer.parseInt(op3));
                            break;
                        case "P":
                            op3 = JOptionPane.showInputDialog(null,
                                    "Ingresa el nuevo PER",
                                    "Cambiar de SPECIAL",
                                    JOptionPane.QUESTION_MESSAGE);
                            pet.getDna().setPer(Integer.parseInt(op3));
                            break;
                        case "E":
                            op3 = JOptionPane.showInputDialog(null,
                                    "Ingresa el nuevo END",
                                    "Cambiar de SPECIAL",
                                    JOptionPane.QUESTION_MESSAGE);
                            pet.getDna().setEnd(Integer.parseInt(op3));
                            break;
                        case "C":
                            op3 = JOptionPane.showInputDialog(null,
                                    "Ingresa el nuevo CHA",
                                    "Cambiar de SPECIAL",
                                    JOptionPane.QUESTION_MESSAGE);
                            pet.getDna().setCha(Integer.parseInt(op3));
                            break;
                        case "I":
                            op3 = JOptionPane.showInputDialog(null,
                                    "Ingresa el nuevo INT",
                                    "Cambiar de SPECIAL",
                                    JOptionPane.QUESTION_MESSAGE);
                            pet.getDna().setInt(Integer.parseInt(op3));
                            break;
                        case "A":
                            op3 = JOptionPane.showInputDialog(null,
                                    "Ingresa el nuevo AGL",
                                    "Cambiar de SPECIAL",
                                    JOptionPane.QUESTION_MESSAGE);
                            pet.getDna().setAgl(Integer.parseInt(op3));
                            break;
                        case "L":
                            op3 = JOptionPane.showInputDialog(null,
                                    "Ingresa el nuevo LCK",
                                    "Cambiar de SPECIAL",
                                    JOptionPane.QUESTION_MESSAGE);
                            pet.getDna().setLck(Integer.parseInt(op3));
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
                default:
                    errorMessage();
            }
        }
    }

    public static void errorMessage(){
        JOptionPane.showMessageDialog(null,
                "Por favor, seleccione una opción válida",
                "Opción invalida",
                JOptionPane.ERROR_MESSAGE);
    }
}