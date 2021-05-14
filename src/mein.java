import Classes.MyPets;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class mein {
    public static void main(String[] args) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(Font.MONOSPACED, Font.PLAIN, 12)));
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font(Font.MONOSPACED, Font.PLAIN, 12)));
        MyPets myPets = new MyPets();

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
                            6. Pelea contra otro Tamagotchi       \s
                            7. Importa Tamagotchis                \s
                            8. Exporta Tamagotchis                \s
                            0. Salir del juego                    \s""".indent(1),
                    "Menú Principal",
                    JOptionPane.QUESTION_MESSAGE);
            switch(op){
                case "1":
                    myPets.newPet();
                    break;
                case "2":
                    myPets.select();
                    break;
                case "3":
                    myPets.viewCurrent();
                    break;
                case "4":
                    myPets.comer();
                    break;
                case "5":
                    myPets.jugar();
                    break;
                case "6":
                    myPets.pelear();
                    break;
                case "7":
                    myPets.load();
                    break;
                case "8":
                    myPets.save();
                    break;
                case "0":
                    myPets.save();
                    playing = false;
                    break;
                case "-1":
                    devTools(myPets);
                    break;
                default:
                    errorMessage();
            }
            //update
            myPets.update();
            //procrear
            myPets.procrear();
        }
    }

    public static void devTools(MyPets myPets){
        String op, op2, op3;
        boolean dev = true;
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
                    myPets.getCurrentPet().setLvl(Integer.parseInt(op2));
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
                            myPets.getCurrentPet().getDna().setStr(Integer.parseInt(op3));
                            break;
                        case "P":
                            op3 = JOptionPane.showInputDialog(null,
                                    "Ingresa el nuevo PER",
                                    "Cambiar de SPECIAL",
                                    JOptionPane.QUESTION_MESSAGE);
                            myPets.getCurrentPet().getDna().setPer(Integer.parseInt(op3));
                            break;
                        case "E":
                            op3 = JOptionPane.showInputDialog(null,
                                    "Ingresa el nuevo END",
                                    "Cambiar de SPECIAL",
                                    JOptionPane.QUESTION_MESSAGE);
                            myPets.getCurrentPet().getDna().setEnd(Integer.parseInt(op3));
                            break;
                        case "C":
                            op3 = JOptionPane.showInputDialog(null,
                                    "Ingresa el nuevo CHA",
                                    "Cambiar de SPECIAL",
                                    JOptionPane.QUESTION_MESSAGE);
                            myPets.getCurrentPet().getDna().setCha(Integer.parseInt(op3));
                            break;
                        case "I":
                            op3 = JOptionPane.showInputDialog(null,
                                    "Ingresa el nuevo INT",
                                    "Cambiar de SPECIAL",
                                    JOptionPane.QUESTION_MESSAGE);
                            myPets.getCurrentPet().getDna().setInt(Integer.parseInt(op3));
                            break;
                        case "A":
                            op3 = JOptionPane.showInputDialog(null,
                                    "Ingresa el nuevo AGL",
                                    "Cambiar de SPECIAL",
                                    JOptionPane.QUESTION_MESSAGE);
                            myPets.getCurrentPet().getDna().setAgl(Integer.parseInt(op3));
                            break;
                        case "L":
                            op3 = JOptionPane.showInputDialog(null,
                                    "Ingresa el nuevo LCK",
                                    "Cambiar de SPECIAL",
                                    JOptionPane.QUESTION_MESSAGE);
                            myPets.getCurrentPet().getDna().setLck(Integer.parseInt(op3));
                            break;
                    }
                    break;
                case "3":
                    myPets.getCurrentPet().heal(myPets.getCurrentPet().getMaxHp());
                    myPets.getCurrentPet().feed(myPets.getCurrentPet().getMaxHunger());
                    myPets.getCurrentPet().play(100);
                    JOptionPane.showMessageDialog(null,
                            "Tamagotchi al 100",
                            "Tamagotchi Feliz",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "4":
                    myPets.select();
                    myPets.viewCurrent();
                    break;
                case "5":
                    myPets.skip();
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