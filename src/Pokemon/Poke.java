package Pokemon;
import Classes.Tamagotchi;

public class Poke {
    Tamagotchi pet;
    public Poke(Tamagotchi pet) {
        this.pet = pet;
    }

    public static void printMenu(){
        System.out.println(" ~~~~~~~~~~~~~~ Pokémon ~~~~~~~~~~~~~~  ");
        System.out.println(" 1. Entrenamiento                       ");
        System.out.println(" 2. Torre  lvl 1                        ");
        System.out.println(" 3. Torre  lvl 5                        ");
        System.out.println(" 4. Torre  lvl 10                       ");
        System.out.println(" 5. Torre ∞                             ");
        System.out.println(" 0. Salir del juego                     ");
    }

    public static void Torre1(Tamagotchi pet){

    }

}
