package Classes.Species;
import Classes.SPECIAL;

import javax.swing.*;

public enum Species{
    GOD,
    ACUATICO,
    AVE,
    CANINO,
    FELINO,
    MAGICO,
    REPTIL;

    public static SPECIAL low_end(Species species){
        switch(species){
            case GOD:
                return new SPECIAL(10,10,10,10,10,10,10);
            case ACUATICO:
                return new SPECIAL(1,2,3,2,2,4,1);
            case AVE:
                return new SPECIAL(1,4,1,3,2,5,1);
            case CANINO:
                return new SPECIAL(4,5,2,6,3,3,1);
            case FELINO:
                return new SPECIAL(2,5,1,2,5,6,6);
            case REPTIL:
                return new SPECIAL(2,4,5,1,2,3,1);
            case MAGICO:
                return new SPECIAL(4,2,5,2,4,3,3);
            default:
                return new SPECIAL(0,0,0,0,0,0,0);
        }
    }

    public static SPECIAL high_end(Species species_id){
        switch(species_id){
            case GOD:
                return new SPECIAL(10,10,10,10,10,10,10);
            case ACUATICO:
                return new SPECIAL(10,5,8,8,7,9,9);
            case AVE:
                return new SPECIAL(5,10,5,8,6,10,9);
            case CANINO:
                return new SPECIAL(8,9,6,10,7,7,9);
            case FELINO:
                return new SPECIAL(8,9,5,5,9,10,10);
            case REPTIL:
                return new SPECIAL(7,7,10,5,5,6,9);
            case MAGICO:
                return new SPECIAL(10,7,10,8,10,8,10);
            default:
                return new SPECIAL(9,9,9,9,9,9,9);
        }
    }

    public static Species selectSpecies(){
        int op = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Acuatico   = 1\n" +
                        "Ave        = 2\n" +
                        "Canino     = 3\n" +
                        "Felino     = 4\n" +
                        "Reptil     = 5",
                "Selecciona la especie",
                JOptionPane.QUESTION_MESSAGE));
        switch(op){
            case -1:
                return GOD;
            case 1:
                return ACUATICO;
            case 2:
                return AVE;
            case 3:
                return CANINO;
            case 4:
                return FELINO;
            default:
                return REPTIL;
        }
    }
}
