package Games;

import Classes.Tamagotchi;

import javax.swing.*;

public class Gato {
    private int cuadros = 9;
    private String[][] tabla;
    private boolean tamagotchi;
    private boolean master;
    Tamagotchi pet;

    JTextField xField = new JTextField(5);
    JTextField yField = new JTextField(5);
    JPanel myPanel = new JPanel();

    public Gato(Tamagotchi pet) {
        tabla = new String[3][3];
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                tabla[x][y] = " ";
            }
        }
        this.pet = pet;

        myPanel.add(new JLabel("Fil(0-2):"));
        myPanel.add(xField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Col(0-2):"));
        myPanel.add(yField);
    }

    public String tablero() {
        String tablero = "";
        for(int x = 0; x < 3; x++) {
            tablero += "| ";
            for(int y = 0; y < 3; y++) {
                tablero += tabla[x][y] + " | ";
            }
            tablero += "\n";
        }
        return tablero;
    }

    public boolean GanadorAI() {
        if (tabla[0][0].equals("O") && tabla[0][1].equals("O") && tabla[0][2].equals("O")) tamagotchi = true;
        if (tabla[1][0].equals("O") && tabla[1][1].equals("O") && tabla[1][2].equals("O")) tamagotchi = true;
        if (tabla[2][0].equals("O") && tabla[2][1].equals("O") && tabla[2][2].equals("O")) tamagotchi = true;
        if (tabla[0][0].equals("O") && tabla[1][0].equals("O") && tabla[2][0].equals("O")) tamagotchi = true;
        if (tabla[0][1].equals("O") && tabla[1][1].equals("O") && tabla[2][1].equals("O")) tamagotchi = true;
        if (tabla[0][2].equals("O") && tabla[1][2].equals("O") && tabla[2][2].equals("O")) tamagotchi = true;
        if (tabla[0][0].equals("O") && tabla[1][1].equals("O") && tabla[2][2].equals("O")) tamagotchi = true;
        if (tabla[0][2].equals("O") && tabla[1][1].equals("O") && tabla[2][0].equals("O")) tamagotchi = true;
        return tamagotchi;
    }

    public boolean GanadorUser() {
        if (tabla[0][0].equals("X") && tabla[0][1].equals("X") && tabla[0][2].equals("X")) master = true;
        if (tabla[1][0].equals("X") && tabla[1][1].equals("X") && tabla[1][2].equals("X")) master = true;
        if (tabla[2][0].equals("X") && tabla[2][1].equals("X") && tabla[2][2].equals("X")) master = true;
        if (tabla[0][0].equals("X") && tabla[1][0].equals("X") && tabla[2][0].equals("X")) master = true;
        if (tabla[0][1].equals("X") && tabla[1][1].equals("X") && tabla[2][1].equals("X")) master = true;
        if (tabla[0][2].equals("X") && tabla[1][2].equals("X") && tabla[2][2].equals("X")) master = true;
        if (tabla[0][0].equals("X") && tabla[1][1].equals("X") && tabla[2][2].equals("X")) master = true;
        if (tabla[0][2].equals("X") && tabla[1][1].equals("X") && tabla[2][0].equals("X")) master = true;
        return master;
    }

    public void Jugar() {
        int masterX;
        int masterY;
        int tamagotchiX;
        int tamagotchiY;

        JOptionPane.showMessageDialog(null,
            tablero(),
            "Gato",
            JOptionPane.PLAIN_MESSAGE);

        while (cuadros > 0 && !master && !tamagotchi) {
            boolean Ocupado;
            boolean masterXError;
            boolean masterYError;

            do {
                if (cuadros > 0) {
                    do {
                        JOptionPane.showMessageDialog(null,
                                myPanel,
                                "Tu turno",
                                JOptionPane.PLAIN_MESSAGE);

                        masterX = Integer.parseInt(xField.getText());
                        masterY = Integer.parseInt(yField.getText());

                        if(masterX < 0 || masterX > 2 || masterY < 0 || masterY > 2)
                            JOptionPane.showMessageDialog(null,
                                    "Input invalido, intenta otra vez",
                                    "ERROR",
                                    JOptionPane.WARNING_MESSAGE);

                    } while (masterX < 0 || masterX > 2 || masterY < 0 || masterY > 2);

                    if (!tabla[masterX][masterY].equals("O") && !tabla[masterX][masterY].equals("X")) {
                        tabla[masterX][masterY] = "X";
                        cuadros = cuadros - 1;
                        Ocupado = true;
                    } else {
                        Ocupado = false;
                        JOptionPane.showMessageDialog(null,
                                "Espacio ocupado",
                                "ERROR",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    Ocupado = true;
                }
            } while (!Ocupado);

            tablero();
            master = GanadorUser();
            tamagotchi = GanadorAI();

            do {
                if (cuadros > 0) {
                    tamagotchiX = (int) (Math.random() * 3);
                    tamagotchiY = (int) (Math.random() * 3);
                    if (!tabla[tamagotchiX][tamagotchiY].equals("O") && !tabla[tamagotchiX][tamagotchiY].equals("X")) {
                        tabla[tamagotchiX][tamagotchiY] = "O";
                        cuadros = cuadros - 1;
                        Ocupado = true;
                    } else {
                        Ocupado = false;
                    }
                } else {
                    Ocupado = true;
                }
            } while (!Ocupado);
            JOptionPane.showMessageDialog(null,
                    " ~~~ Turno de " + pet.getName() + " ~~~ \n" + tablero(),
                    "Turno de " + pet.getName(),
                    JOptionPane.PLAIN_MESSAGE
                    );
            tamagotchi = GanadorAI();
            master = GanadorUser();
        }
    }

    public void Ganador() {
        if (master) {
            JOptionPane.showMessageDialog(null,
                    " ~~~ GAME OVER ~~~ \nGanador: Jugador",
                    "Ganaste!",
                    JOptionPane.PLAIN_MESSAGE);
        } else if (tamagotchi) {
            JOptionPane.showMessageDialog(null,
                    " ~~~ GAME OVER ~~~ \nGanodor" + pet.getName(),
                    "Perdiste",
                    JOptionPane.PLAIN_MESSAGE);
        } else if (cuadros == 0) {
            JOptionPane.showMessageDialog(null,
                    " ~~~ GAME OVER ~~~ \nEmpate",
                    "Empate",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void main(Tamagotchi pet) {
        Gato gato = new Gato(pet);
        gato.Jugar();
        gato.Ganador();
    }
}
