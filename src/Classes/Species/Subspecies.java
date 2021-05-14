package Classes.Species;

import javax.swing.*;

public enum Subspecies {
    GOD,
    BALLENA, CAMARON, GOLDFISH, PULPO, TIBURON,
    COTORRO, TUCAN, PINGUINO, QUETZAL, AGUILA,
    GOLDEN, HUSKY, PASTOR, SHITZU, LOBO,
    TIGRE, LEON, LEOPARDO, PANTERA, GATITO,
    COCODRILO, TORTUGA, COMODO, IGUANA, CAMALEON,
    CTHULHU, DRAGON, KRAKEN, GODZILLA, UNICORNIO;

    public static Subspecies randomSubspecies(Subspecies a, Subspecies b){
        if(Math.random()*100 < 50)
            return a;
        return b;
    }

    public static Subspecies selectSubspecies(Species species){
        int op;
        switch(species){
            case ACUATICO:
                op = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Ballena    = 1\n" +
                                "Camaron    = 2\n" +
                                "Goldfish   = 3\n" +
                                "Pulpo      = 4\n" +
                                "Tiburon    = 5",
                        "Selecciona una subespecie",
                        JOptionPane.QUESTION_MESSAGE));
                return switchOP(op, BALLENA, CAMARON, GOLDFISH, PULPO, TIBURON);
            case AVE:
                op = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Cotorro    = 1\n" +
                                "Tucan      = 2\n" +
                                "Pinguino   = 3\n" +
                                "Quetzal    = 4\n" +
                                "Aguila     = 5",
                        "Selecciona una subespecie",
                        JOptionPane.QUESTION_MESSAGE));
                return switchOP(op, COTORRO, TUCAN, PINGUINO, QUETZAL, AGUILA);
            case CANINO:
                op = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Golden   = 1\n" +
                                "Husky    = 2\n" +
                                "Pastor   = 3\n" +
                                "Shitzu   = 4\n" +
                                "Lobo     = 5",
                        "Selecciona una subespecie",
                        JOptionPane.QUESTION_MESSAGE));
                return switchOP(op, GOLDEN, HUSKY, PASTOR, SHITZU, LOBO);
            case FELINO:
                op = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Tigre      = 1\n" +
                                "Leon       = 2\n" +
                                "Leopardo   = 3\n" +
                                "Pantera    = 4\n" +
                                "Gatito     = 5",
                        "Selecciona una subespecie",
                        JOptionPane.QUESTION_MESSAGE));
                return switchOP(op, TIGRE, LEON, LEOPARDO, PANTERA, GATITO);
            case REPTIL:
                op = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Cocodrilo   = 1\n" +
                                "Tortuga     = 2\n" +
                                "Comodo      = 3\n" +
                                "Iguana      = 4\n" +
                                "Camaleon    = 5",
                        "Selecciona una subespecie",
                        JOptionPane.QUESTION_MESSAGE));
                return switchOP(op, COCODRILO, TORTUGA, COMODO, IGUANA, CAMALEON);
            case MAGICO:
                op = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Cthulhu     = 1\n" +
                                "Dragon      = 2\n" +
                                "Kraken      = 3\n" +
                                "Godzilla    = 4\n" +
                                "Unicornio   = 5",
                        "Selecciona una subespecie",
                        JOptionPane.QUESTION_MESSAGE));
                return switchOP(op, CTHULHU, DRAGON, KRAKEN, GODZILLA, UNICORNIO);
            default: return GOD;
        }
    }

    private static Subspecies switchOP(int op, Subspecies sub1, Subspecies sub2, Subspecies sub3, Subspecies sub4, Subspecies sub5) {
        while (true) {
            switch(op){
                case 1: return sub1;
                case 2: return sub2;
                case 3: return sub3;
                case 4: return sub4;
                case 5: return sub5;
                default: op = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Opción inválida \n  Vuelve a intnetar (1-5)",
                        "Invalido",
                        JOptionPane.WARNING_MESSAGE));
            }
        }
    }

    public static Subspecies selectSubspecies(String subspecies){
        switch(subspecies){
            case "BALLENA": return BALLENA;
            case "CAMARON": return CAMARON;
            case "GOLDFISH": return GOLDFISH;
            case "PULPO": return PULPO;
            case "TIBURON": return TIBURON;

            case "COTORRO": return COTORRO;
            case "TUCAN": return TUCAN;
            case "PINGUINO": return PINGUINO;
            case "QUETZAL": return QUETZAL;
            case "AGUILA": return AGUILA;

            case "GOLDEN": return GOLDEN;
            case "HUSKY": return HUSKY;
            case "PASTOR": return PASTOR;
            case "SHITZU": return SHITZU;
            case "LOBO": return LOBO;

            case "TIGRE": return TIGRE;
            case "LEON": return LEON;
            case "LEOPARDO": return LEOPARDO;
            case "PANTERA": return PANTERA;
            case "GATITO": return GATITO;

            case "COCODRILO": return COCODRILO;
            case "TORTUGA": return TORTUGA;
            case "COMODO": return COMODO;
            case "IGUANA": return IGUANA;
            case "CAMALEON": return CAMALEON;

            case "CTHULHU": return CTHULHU;
            case "DRAGON": return DRAGON;
            case "KRAKEN": return KRAKEN;
            case "GODZILLA": return GODZILLA;
            case "UNICORNIO": return UNICORNIO;

            default: return GOD;
        }
    }
}
