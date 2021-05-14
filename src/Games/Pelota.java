package Games;

import Classes.Tamagotchi;

import javax.swing.*;

public class Pelota {
    public static void main(Tamagotchi pet) {
        String[] options = {"Arrojar pelota", "Dejar de jugar pelota"};
        int option;

        while (true){
            option = JOptionPane.showOptionDialog(null,
                    " ~~~ PELOTA ~~~ \n" +
                            pet.getName(),
                    "Pelota",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (option == 2)
                break;
            JOptionPane.showMessageDialog(null,
                    "Arrojas la pelota",
                    "Pelota",
                    JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null,
                    pet.getName() + " trae la pelota",
                    "Pelota",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        JOptionPane.showMessageDialog(null,
                "Guardas la pelota",
                "Pelota",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
