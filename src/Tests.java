import Classes.SPECIAL;
import Classes.Species.Species;
import Classes.Species.Subspecies;
import Classes.Tamagotchi;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Tests {
    public static void main(String[] args) {
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
                if(breader.readLine().equals("TamagotchiSave"))
                    for(int i=0; i<16; i++)
                        System.out.println(i + ". " + breader.readLine());
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,
                        "No se pudo abrir el archivo",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
