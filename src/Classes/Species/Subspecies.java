package Classes.Species;

import java.util.Scanner;

public enum Subspecies {
    GOD,
    BALLENA, CAMARON, GOLDFISH, PULPO, TIBURON,
    COTORRO, TUCAN, PINGUINO, QUETZAL, AGUILA,
    GOLDEN, HUSKY, PASTOR, SHITZU, LOBO,
    TIGRE, LEON, LEOPARDO, PANTERA, GATITO,
    COCODRILO, TORTUGA, COMODO, IGUANA, CAMALEON,
    CTHULHU, DRAGON, KRAKEN, GODZILLA, UNICORNIO;

    public static Subspecies selectSubspecies(String species){
        System.out.println("Selecciona una subespecie: ");
        Scanner reader = new Scanner(System.in);
        int op;
        switch(species){
            case "ACUATICO":
                System.out.println(
                        "Ballena    = 1 \n" +
                        "Camaron    = 2 \n" +
                        "Goldfish   = 3 \n" +
                        "Pulpo      = 4 \n" +
                        "Tiburon    = 5 \n"
                );
                op = Integer.parseInt(reader.nextLine());
                switch(op){
                    case 1: return BALLENA;
                    case 2: return CAMARON;
                    case 3: return GOLDFISH;
                    case 4: return PULPO;
                    default: return TIBURON;
                }
            case "AVE":
                System.out.println(
                        "Cotorro    = 1 \n" +
                        "Tucan      = 2 \n" +
                        "Pinguino   = 3 \n" +
                        "Quetzal    = 4 \n" +
                        "Aguila     = 5 \n"
                );
                op = Integer.parseInt(reader.nextLine());
                switch(op){
                    case 1: return COTORRO;
                    case 2: return TUCAN;
                    case 3: return PINGUINO;
                    case 4: return QUETZAL;
                    default: return AGUILA;
                }
            case "CANINO":
                System.out.println(
                        "Golden   = 1 \n" +
                        "Husky    = 2 \n" +
                        "Pastor   = 3 \n" +
                        "Shitzu   = 4 \n" +
                        "Lobo     = 5 \n"
                );
                op = Integer.parseInt(reader.nextLine());
                switch(op){
                    case 1: return GOLDEN;
                    case 2: return HUSKY;
                    case 3: return PASTOR;
                    case 4: return SHITZU;
                    default: return LOBO;
                }
            case "FELINO":
                System.out.println(
                        "Tigre      = 1 \n" +
                        "Leon       = 2 \n" +
                        "Leopardo   = 3 \n" +
                        "Pantera    = 4 \n" +
                        "Gatito     = 5 \n"
                );
                op = Integer.parseInt(reader.nextLine());
                switch(op){
                    case 1: return TIGRE;
                    case 2: return LEON;
                    case 3: return LEOPARDO;
                    case 4: return PANTERA;
                    default: return GATITO;
                }
            case "REPTIL":
                System.out.println(
                        "Cocodrilo   = 1 \n" +
                        "Tortuga     = 2 \n" +
                        "Comodo      = 3 \n" +
                        "Iguana      = 4 \n" +
                        "Camaleon    = 5 \n"
                );
                op = Integer.parseInt(reader.nextLine());
                switch(op){
                    case 1: return COCODRILO;
                    case 2: return TORTUGA;
                    case 3: return COMODO;
                    case 4: return IGUANA;
                    default: return CAMALEON;
                }
            case "MAGICO":
                System.out.println(
                        "Cthulhu     = 1 \n" +
                        "Dragon      = 2 \n" +
                        "Kraken      = 3 \n" +
                        "Godzilla    = 4 \n" +
                        "Unicornio   = 5 \n"
                );
                op = Integer.parseInt(reader.nextLine());
                switch(op){
                    case 1: return CTHULHU;
                    case 2: return DRAGON;
                    case 3: return KRAKEN;
                    case 4: return GODZILLA;
                    default: return UNICORNIO;
                }
            default: return GOD;
        }
    }
}
