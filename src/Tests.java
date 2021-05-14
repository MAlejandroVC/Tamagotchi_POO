import Classes.AES;
import Classes.MyPets;
import Classes.SPECIAL;
import Classes.Species.Species;
import Classes.Species.Subspecies;
import Classes.Tamagotchi;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class Tests {
    public static void main(String[] args) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                Font.MONOSPACED, Font.PLAIN, 12)));

        String[] options = {"Encriptar", "Decriptar"};
        int option;
        String name;

        String path = JOptionPane.showInputDialog(null,
                "Dirección del juego: \n" +
                        "ej: C:\\Users\\Lenovo\\IdeaProjects\\Tamagotchi_POO\\src",
                "Cargar Tamagotchis",
                JOptionPane.PLAIN_MESSAGE);

        while(true) {
            name = JOptionPane.showInputDialog(null,
                    "Nombre del Tamagotchi:",
                    "CryptTest",
                    JOptionPane.PLAIN_MESSAGE);
            if(name == null)
                return;

            option = JOptionPane.showOptionDialog(null,
                    "Escoge",
                    "CryptTest",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch(option){
                case 0: //encriptar
                    MyPets.encrypt(path + "\\saves\\" + name +".txt");
                    break;
                case 1: //decriptar
                    MyPets.decrypt(path + "\\saves\\" + name +".txt");
                    break;
                default:
                    return;
            }
        }
    }
}
