package Games;
import Classes.Tamagotchi;

import javax.swing.*;

public class Roshambo {
    String[] options = {"Piedra", "Papel", "Tijeras"};
    private int ganador;
    Tamagotchi pet;

    public Roshambo(Tamagotchi pet) {
        this.pet = pet;
    }

    public void Jugar() {
        String op;
        int masterChoice;
        int tamagotchiChoice = (int) Math.floor(Math.random()*(3)+1);

        op = (String) JOptionPane.showInputDialog(null,
                "Escoje una opcion:",
                "Roshambo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        switch(op){
            case "Piedra":
                masterChoice = 0;
                break;
            case "Papel":
                masterChoice = 1;
                break;
            default:
                masterChoice = 2;
        }

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
                JOptionPane.showMessageDialog(null,
                        " ~~~ GAME OVER ~~~ \n" +
                                "Ganador: Jugador",
                        "Ganaste!",
                        JOptionPane.PLAIN_MESSAGE);
                break;
            case 1:
                JOptionPane.showMessageDialog(null,
                        " ~~~ GAME OVER ~~~ \n" +
                                "Ganador: " + pet.getName(),
                        "Perdiste",
                        JOptionPane.PLAIN_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null,
                        " ~~~ GAME OVER ~~~ \n" +
                                "Ganador: Empate",
                        "Empate",
                        JOptionPane.PLAIN_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null,
                        " ~~~ GAME OVER ~~~ \n" +
                                "Ganador: Inaccesible",
                        "Inaccesible",
                        JOptionPane.WARNING_MESSAGE);
                break;

        }
    }

    public static void main(Tamagotchi pet) {
        Roshambo piedra = new Roshambo(pet);
        piedra.Jugar();
        piedra.Ganador();
    }
}
