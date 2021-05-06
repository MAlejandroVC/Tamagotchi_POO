import Classes.Species.Species;
import Classes.Species.Subspecies;
import Classes.Tamagotchi;
import Games.BlackJack;
import Games.C0necta4;
import Games.Gato;
import Games.Roshambo;

import javax.swing.*;
import java.io.*;

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
                            8. Importa Tamagotchis                \s
                            9. Exporta Tamagotchis                \s
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
                    totalPets = load(myPets, totalPets);
                    break;
                case "9":
                    save(myPets, totalPets);
                    break;
                case "0":
                    save(myPets, totalPets);
                    playing = false;
                    break;
                case "-1":
                    devTools(myPets, currentPet, totalPets);
                    break;
                default:
                    errorMessage();
            }
            //update
            for(int i=0; i<totalPets; i++)
                myPets[i].update();
            //procrear
            procrear(myPets, totalPets);
        }
    }

    public static void newPet(Tamagotchi[] array, int total){
        Species species = Species.selectSpecies();
        Subspecies subspecies = Subspecies.selectSubspecies(species);
        String name = JOptionPane.showInputDialog(null,
                "Ingresa un nombre",
                "Crear un Tamagotchi nuevo",
                JOptionPane.QUESTION_MESSAGE);
        array[total] = new Tamagotchi(name, species, subspecies);
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
                        "Edad:      " + pet.getAge()+ "\n" +
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
                            " ~~~~~~~~~~~~ MENU COMIDA ~~~~~~~~~~~~  \n"+
                            " 1. Manzana                       (10)  \n"+
                            " 2. Galleta                       (10)  \n"+
                            " 3. Sandwich                      (25)  \n"+
                            " 4. Sopa                          (30)  \n"+
                            " 5. Carne asada                   (50)  \n"+
                            " 6. Hamburguesa                   (60)  \n"+
                            " 7. Pastel                        (75)  \n"+
                            " 8. Tacos                         (90)  \n"+
                            " 9. Lox meat pie                 (100)  \n"+
                            " 0. Salir                               \n",
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

        System.out.println(" ~~~~~~~~~~~~~~~ JUEGOS ~~~~~~~~~~~~~~~ ");
        System.out.println(" 1. BlackJack           ");
        System.out.println(" 2. Gato                ");
        System.out.println(" 3. Pelota              ");
        System.out.println(" 4. Connect 4               ");
        System.out.println(" 5. Roshambo            ");
        System.out.println(" 0. Cancelar            ");

        String op;
        Scanner reader = new Scanner(System.in);
        op = reader.nextLine();
        switch(op){
            case "1":
                BlackJack.main(pet);
                pet.play(20);
                break;
            case "2":
                Gato.main(pet);
                pet.play(5);
                break;
            case "3":

                break;
            case "4":
                C0necta4.main(pet);
                break;
            case "5":
                Roshambo.main(pet);
                pet.play(5);
                break;
            case "6":
                break;
            case "0":
                break;
        }

    }

    public static void explorar(Tamagotchi pet){
        System.out.println(pet.getName() + "esta explorando");
    }

    public static void pelear(){
        System.out.println("Pelea");
    }

    public static int load(Tamagotchi[] myPets, int totalPets){
        BufferedReader breader;
        String name;
        String path = JOptionPane.showInputDialog(null,
                "Dirección del juego: \n" +
                        "ej: C:\\Users\\Lenovo\\IdeaProjects\\Tamagotchi_POO\\src",
                "Cargar Tamagotchis",
                JOptionPane.PLAIN_MESSAGE);
        while(true){
            name = JOptionPane.showInputDialog(null,
                    "Nombre del Tamagotchi:",
                    "Cargar Tamagotchis",
                    JOptionPane.PLAIN_MESSAGE);
            if(name==null)
                break;
            try{
                breader = new BufferedReader(new FileReader(path + "\\saves\\" + name + ".txt"));
                if(breader.readLine().equals("TamagotchiSave")) {
                    myPets[totalPets] = new Tamagotchi(
                            ""+breader.readLine(),
                            ""+breader.readLine(),
                            ""+breader.readLine(),
                            ""+breader.readLine(),
                            ""+breader.readLine(),
                            ""+breader.readLine(),
                            ""+breader.readLine(),
                            ""+breader.readLine(),
                            ""+breader.readLine(),
                            ""+breader.readLine(),
                            ""+breader.readLine(),
                            ""+breader.readLine(),
                            ""+breader.readLine(),
                            ""+breader.readLine(),
                            ""+breader.readLine(),
                            ""+breader.readLine());
                    totalPets++;
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,
                        "No se pudo abrir el archivo",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return totalPets;
    }

    public static void save(Tamagotchi[] myPets, int totalPets){
        BufferedWriter bwriter;
        Tamagotchi pet;
        String path = JOptionPane.showInputDialog(null,
                "Dirección del juego: \n" +
                        "ej: C:\\Users\\Lenovo\\IdeaProjects\\Tamagotchi_POO\\src",
                "Guardar Tamagotchis",
                JOptionPane.PLAIN_MESSAGE);
        try{
            for(int i=0; i<totalPets; i++){
                pet = myPets[i];
                bwriter = new BufferedWriter(new FileWriter(path + "\\saves\\" + pet.getName() +".txt"));
                bwriter.write("TamagotchiSave\n");
                bwriter.write(""+pet.getName()+"\n");
                bwriter.write(""+pet.getSpecies()+"\n");
                bwriter.write(""+pet.getSubspecies()+"\n");
                bwriter.write(""+pet.getLvl()+"\n");
                bwriter.write(""+pet.getMaxHunger()+"\n");
                bwriter.write(""+pet.getMaxHp()+"\n");
                bwriter.write(""+pet.getHp()+"\n");
                bwriter.write(""+pet.getHunger()+"\n");
                bwriter.write(""+pet.getHappiness()+"\n");
                bwriter.write(""+pet.getDna().getStr()+"\n");
                bwriter.write(""+pet.getDna().getPer()+"\n");
                bwriter.write(""+pet.getDna().getEnd()+"\n");
                bwriter.write(""+pet.getDna().getCha()+"\n");
                bwriter.write(""+pet.getDna().getInt()+"\n");
                bwriter.write(""+pet.getDna().getAgl()+"\n");
                bwriter.write(""+pet.getDna().getLck()+"\n");
                bwriter.close();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "No se pudo crear el archivo",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void skip(Tamagotchi[] myPets, int totalPets){
        int turns = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Ingresa cuantos turnos quieres saltar",
                "Skip",
                JOptionPane.QUESTION_MESSAGE))-1;
        for(int i=0; i<turns; i++){
            for(int j=0; j<totalPets; j++) {
                myPets[j].update();
                procrear(myPets, totalPets);
            }
        }
    }

    public static void procrear(Tamagotchi[] myPets, int totalPets){
        if(totalPets > 1) {
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
    }

    public static void devTools(Tamagotchi[] myPets, int currentPet,int totalPets){
        String op, op2, op3;
        boolean dev = true;
        Tamagotchi pet = myPets[currentPet];
        while(dev){
            op = JOptionPane.showInputDialog(null,
                    """
                            ~~~~~~~~~~~~~ DEV MENU ~~~~~~~~~~~~~  \s
                            1. Cambiar de nivel                   \s
                            2. Cambiar SPECIAL                    \s
                            3. Curar tamagotchi                   \s
                            4. Cambiar tamagotchi                 \s
                            5. Skip                               \s
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
                    JOptionPane.showMessageDialog(null,
                            "Tamagotchi al 100",
                            "Tamagotchi Feliz",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "4":
                    currentPet = select(myPets, totalPets);
                    view(myPets[currentPet]);
                    break;
                case "5":
                    skip(myPets, totalPets);
                    break;
                case "0":
                    dev = false;
                    break;
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