package Classes;

import Classes.Species.Species;
import Classes.Species.Subspecies;
import Games.*;
import Games.Poke;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;

public class MyPets {
    private ArrayList<Tamagotchi> pets = new ArrayList<>();
    private Tamagotchi currentPet = null;

    public int getTotalPets(){
        return pets.size();
    }

    public Tamagotchi getCurrentPet(){
        return currentPet;
    }

    public void newPet(){
        Species species = Species.selectSpecies();
        Subspecies subspecies = Subspecies.selectSubspecies(species);
        String name = JOptionPane.showInputDialog(null,
                "Ingresa un nombre",
                "Crear un Tamagotchi nuevo",
                JOptionPane.QUESTION_MESSAGE);
        pets.add(new Tamagotchi(name, species, subspecies));
    }

    public void select(){
        if(getTotalPets() == 0) {
            JOptionPane.showMessageDialog(null,
                    "No hay mascotas, crea una nueva o carga de un archivo",
                    "Tamagotchis no encontrados",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        StringBuilder strMascotas = new StringBuilder();
        for(int i=0; i<getTotalPets(); i++)
            strMascotas.append(i + 1).append(". ").append(pets.get(i).getName()).append("\n");
        strMascotas.append("0. Cancelar");
        int op = Integer.parseInt(JOptionPane.showInputDialog(null,
                strMascotas,
                "Selecciona una mascota",
                JOptionPane.PLAIN_MESSAGE));
        if(op<1 || op>getTotalPets())
            return;
        currentPet = pets.get(op-1);
    }

    public void viewCurrent(){
        JOptionPane.showMessageDialog(null,
                "Nombre:    " + currentPet.getName() + "\n" +
                        "Nivel:     " + currentPet.getLvl()+ "\n" +
                        "Salud:     " + currentPet.getHp() + "/" + currentPet.getMaxHp()+ "\n" +
                        "Hambre:    " + currentPet.getHunger() + "/" + currentPet.getMaxHunger()+ "\n" +
                        "Felicidad: " + currentPet.getHappiness() + "/100"+ "\n" +
                        "Edad:      " + currentPet.getAge()+ "\n" +
                        "Especie:   " + currentPet.getSpecies()+ "\n" +
                        "SPECIAL:   " + currentPet.getDna(),
                "Ver a mi Tamagotchi",
                JOptionPane.PLAIN_MESSAGE);
    }

    public void comer(){
        String op;
        //Scanner reader = new Scanner(System.in);
        boolean eating = true;
        while(eating) {
            op = JOptionPane.showInputDialog(null,
                    " ~~~ " + currentPet.getName() + ": " + currentPet.getHunger() + "/" + currentPet.getMaxHunger() + " ~~~ \n"+
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
                    currentPet.feed(10);
                    break;
                case "3":
                    currentPet.feed(25);
                    break;
                case "4":
                    currentPet.feed(30);
                    break;
                case "5":
                    currentPet.feed(50);
                    break;
                case "6":
                    currentPet.feed(60);
                    break;
                case "7":
                    currentPet.feed(75);
                    break;
                case "8":
                    currentPet.feed(90);
                    break;
                case "9":
                    currentPet.feed(100);
                    break;
                case "0":
                    eating = false;
                    break;
                default:
                    errorMessage();
            }
        }
    }

    public void jugar(){
        String op = JOptionPane.showInputDialog(null,
                " ~~~~~~~~~~~~~~~ JUEGOS ~~~~~~~~~~~~~~~ \n" +
                        " 1. BlackJack                           \n" +
                        " 2. Gato                                \n" +
                        " 3. Pelota                              \n" +
                        " 4. Connect 4                           \n" +
                        " 5. Roshambo                            \n" +
                        " 0. Cancelar                            ",
                "Juegos",
                JOptionPane.PLAIN_MESSAGE);
        switch(op){
            case "1":
                JackBlack.main(currentPet);
                currentPet.play(25);
                break;
            case "2":
                Gato.main(currentPet);
                currentPet.play(10);
                break;
            case "3":
                Pelota.main(currentPet);
                currentPet.play(5);
                break;
            case "4":
                C0necta4.main(currentPet);
                currentPet.play(15);
                break;
            case "5":
                Roshambo.main(currentPet);
                currentPet.play(10);
                break;
            case "0":
                break;
        }
    }

    public void pelear(){
        while(true) {
            int op = Poke.printMenu();
            switch (op) {
                case 1: //entrenamiento
                    Poke.train(currentPet);
                    break;
                case 2: //torre lvl 1
                    Poke.torre(currentPet, 1);
                    break;
                case 3: //torre lvl 5
                    if(currentPet.getLvl() < 5){
                        JOptionPane.showMessageDialog(null,
                                "Tienes que ser nivel 5 para entrar",
                                "Nivel insuficiente",
                                JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                    Poke.torre(currentPet, 5);
                    break;
                case 4: //torre lvl 10
                    if(currentPet.getLvl() < 10){
                        JOptionPane.showMessageDialog(null,
                                "Tienes que ser nivel 10 para entrar",
                                "Nivel insuficiente",
                                JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                    Poke.torre(currentPet, 10);
                    break;
                case 5: //torre ∞
                    if(currentPet.getLvl() < 10){
                        JOptionPane.showMessageDialog(null,
                                "Tienes que ser nivel 10 para entrar",
                                "Nivel insuficiente",
                                JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                    Poke.infinite(currentPet);
                    break;
                case 0: //salir
                    return;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Selección invalida",
                            "Invalido",
                            JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void load(){
        BufferedReader breader;
        String name;
        String path = JOptionPane.showInputDialog(null,
                "Dirección de saves: \n" +
                        "ej: C:\\Users\\Lenovo\\IdeaProjects\\Tamagotchi_POO\\src\\saves",
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
                decrypt(path + "\\" + name + ".txt");
                breader = new BufferedReader(new FileReader(path + "\\" + name + ".txt"));
                if(breader.readLine().equals("TamagotchiSave")) {
                    pets.add(new Tamagotchi(
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
                            ""+breader.readLine()));
                    JOptionPane.showMessageDialog(null,
                            "Tamagotchi " + pets.get(getTotalPets()-1).getName() + " agregado",
                            "Importado exitoso",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                encrypt(path + "\\" + name +".txt");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,
                        "No se pudo abrir el archivo",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void save(){
        BufferedWriter bwriter;
        Tamagotchi pet;
        String path = JOptionPane.showInputDialog(null,
                "Dirección de save: \n" +
                        "ej: C:\\Users\\Lenovo\\IdeaProjects\\Tamagotchi_POO\\src\\saves",
                "Guardar Tamagotchis",
                JOptionPane.PLAIN_MESSAGE);
        try{
            for(int i=0; i<getTotalPets(); i++){
                pet = pets.get(i);
                bwriter = new BufferedWriter(new FileWriter(path + "\\" + pet.getName() +".txt"));
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
                encrypt(path + "\\" + pet.getName() +".txt");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "No se pudo crear el archivo",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void skip(){
        int turns = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Ingresa cuantos turnos quieres saltar",
                "Skip",
                JOptionPane.QUESTION_MESSAGE))-1;
        for(int i=0; i<turns; i++) {
            for (Tamagotchi pet : pets)
                pet.update();
            procrear();
        }
    }

    public void update(){
        if(currentPet == null && getTotalPets() > 0)
            currentPet = pets.get(getTotalPets()-1);

        for (Tamagotchi pet : pets)
            pet.update();
    }

    public void procrear(){
        Tamagotchi tmp = null;
        if(getTotalPets() > 1) {
            for (int i = 0; i < getTotalPets() - 1; i++) { //i
                for (int j = i + 1; j < getTotalPets(); j++) { //j
                    tmp = Tamagotchi.procrear(pets.get(i), pets.get(j));
                    if (tmp != null)
                        pets.add(tmp);
                }
            }
        }
    }

    public void errorMessage(){
        JOptionPane.showMessageDialog(null,
                "Por favor, seleccione una opción válida",
                "Opción invalida",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void encrypt(String path){
        final String secretKey = "Llave?secreta?secretiosa";
        String[] lines = new String[17];
        try{
            BufferedReader breader;
            breader = new BufferedReader(new FileReader(path));
            for(int i=0; i<17; i++)
                lines[i]=  breader.readLine();

            BufferedWriter bwriter;
            bwriter = new BufferedWriter(new FileWriter(path));
            for(int i=0; i<17; i++)
                bwriter.write(Objects.requireNonNull(AES.encrypt(lines[i], secretKey))+"\n");
            bwriter.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "No se pudo encriptar el archivo",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void decrypt(String path){
        final String secretKey = "Llave?secreta?secretiosa";
        String[] lines = new String[17];
        try{
            BufferedReader breaderd;
            breaderd = new BufferedReader(new FileReader(path));
            for(int i=0; i<17; i++)
                lines[i]=  breaderd.readLine();

            System.out.println();

            BufferedWriter bwriterd;
            bwriterd = new BufferedWriter(new FileWriter(path));
            for(int i=0; i<17; i++)
                bwriterd.write(Objects.requireNonNull(AES.decrypt(lines[i], secretKey))+"\n");
            bwriterd.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "No se pudo decriptar el archivo",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
