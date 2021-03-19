package Classes.Species;
import Classes.SPECIAL;

public class Species{
    public static final int
            GOD = 0, //SOLO PARA PRUEBAS
            REPTIL = 1,
            CANINO = 2,
            FELINO = 3,
            AVE = 4,
            ACUATICO = 5,
            MAGICO = 6;

    public static SPECIAL low_end(int species_id){
        switch(species_id){
            case GOD:
                return new SPECIAL(10,10,10,10,10,10,10);
            case REPTIL:
                return new SPECIAL(2,4,5,1,2,3,1);
            case CANINO:
                return new SPECIAL(4,5,2,6,3,3,1);
            case FELINO:
                return new SPECIAL(2,5,1,2,5,6,6);
            case AVE:
                return new SPECIAL(1,4,1,3,2,5,1);
            case ACUATICO:
                return new SPECIAL(1,2,3,2,2,4,1);
            case MAGICO:
                return new SPECIAL(4,2,5,2,4,3,3);
            default:
                return new SPECIAL(0,0,0,0,0,0,0);
        }
    }

    public static SPECIAL high_end(int species_id){
        switch(species_id){
            case GOD:
                return new SPECIAL(10,10,10,10,10,10,10);
            case REPTIL:
                return new SPECIAL(7,7,10,5,5,6,9);
            case CANINO:
                return new SPECIAL(8,9,6,10,7,7,9);
            case FELINO:
                return new SPECIAL(8,9,5,5,9,10,10);
            case AVE:
                return new SPECIAL(5,10,5,8,6,10,9);
            case ACUATICO:
                return new SPECIAL(10,5,8,8,7,9,9);
            case MAGICO:
                return new SPECIAL(10,7,10,8,10,8,10);
            default:
                return new SPECIAL(9,9,9,9,9,9,9);
        }
    }
}
