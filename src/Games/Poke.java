package Games;
import Classes.Tamagotchi;

import javax.swing.*;

public class Poke {
    Tamagotchi pet;
    public Poke(Tamagotchi pet) {
        this.pet = pet;
    }

    public static int printMenu(){
        return Integer.parseInt(JOptionPane.showInputDialog(null,
                """
                        ~~~~~~~~~~~~~~ Peleas ~~~~~~~~~~~~~~ \s
                        1. Entrenamiento                     \s
                        2. Torre  lvl 1                      \s
                        3. Torre  lvl 5                      \s
                        4. Torre  lvl 10                     \s
                        5. Torre ∞                           \s
                        0. Salir del juego                   \s""".indent(1),
                "Pelear",
                JOptionPane.PLAIN_MESSAGE));
    }

    public static void torre(Tamagotchi pet, int towerLvl){
        Tamagotchi[] minions = new Tamagotchi[towerLvl];
        for(int i=0; i<towerLvl; i++)
            minions[i] = new Tamagotchi("Minion"+(i+1), towerLvl);
        Tamagotchi boss = new Tamagotchi("Boss", pet.getLvl());

        for(Tamagotchi minion: minions){
            if(!fight(pet, minion))
                return;
        }
        if(!fight(pet, boss))
            return;

        if(pet.getHp() > 0)
            JOptionPane.showMessageDialog(null,
                    " ~~~ FELICIDADES ~~~ \n" +
                            "Completaste la torre nivel " + towerLvl,
                    "Torre Completada",
                    JOptionPane.PLAIN_MESSAGE);
    }

    public static void infinite(Tamagotchi pet){
        Tamagotchi enemy;
        int lvl = 0;

        do{
            lvl++;
            enemy = new Tamagotchi("Enemy"+lvl, lvl);
        }while(fight(pet, enemy));

        JOptionPane.showMessageDialog(null,
                "Completaste: " + (lvl-1) + " niveles",
                "Modo Infinito terminado",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void train(Tamagotchi pet){
        Tamagotchi challenger = new Tamagotchi("Challenger", pet.getLvl());

        if(!fight(pet, challenger))
            return;

        if(pet.getHp() > 0)
            JOptionPane.showMessageDialog(null,
                    " ~~~ FELICIDADES ~~~ \n" +
                            "Le ganaste a Challenger",
                    "Entrenamiento completado",
                    JOptionPane.PLAIN_MESSAGE);
    }

    private static boolean fight(Tamagotchi pet, Tamagotchi oponent){
        String[] options = {"Pelear", "Escapar"};
        int op;

        while(oponent.getHp() > 0 && pet.getHp() > 0){
            op = JOptionPane.showOptionDialog(null,
                    oponent.getName() + " HP: " + oponent.getHp() + "/" + oponent.getMaxHp() + "\n" +
                            "SPECIAL: " + oponent.getDna() + "\n" +
                            pet.getName() + " HP: " + pet.getHp() + "/" + pet.getMaxHp() + "\n" +
                            "SPECIAL: " + pet.getDna() + "\n",
                    "Pelear",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);
            if(op == 1){
                JOptionPane.showMessageDialog(null,
                        "Has escapado de la pelea",
                        "Escapar",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            if(pet.getHp() > 0) oponent.damage(defend(oponent, attack(pet)));
            if(oponent.getHp() > 0) pet.damage(defend(pet, attack(oponent)));
        }
        return pet.getHp() > 0;
    }

    private static int attack(Tamagotchi attacker){
        String buffs = "";
        int dmg = 5*attacker.getDna().getStr();
        if(Math.random()*100 < 5*attacker.getDna().getLck()){
            dmg *= 2;
            buffs = attacker.getName() + " realizó un ataque crítico! (x2) \n";
        }
        JOptionPane.showMessageDialog(null,
                buffs + attacker.getName() + " realizó " + dmg + " daño",
                "Daño inflingido",
                JOptionPane.INFORMATION_MESSAGE);
        return dmg;
    }

    private static int defend(Tamagotchi defender, int dmg){
        String buffs = "";
        //esquivar
        if(Math.random()*100 < 2.5*defender.getDna().getAgl()){
            dmg = 0;
            buffs = defender.getName() + " esquivó el ataque! (x0) \n";
        }
        //bloquear
        else if(Math.random()*100 < 7.5*defender.getDna().getPer()){
            dmg /= 2;
            buffs = defender.getName() + " bloqueó el ataque! (÷2) \n";
        }
        JOptionPane.showMessageDialog(null,
                buffs + defender.getName() + " recibió " + dmg + " daño",
                "Daño recibido",
                JOptionPane.INFORMATION_MESSAGE);
        return dmg;
    }

}
