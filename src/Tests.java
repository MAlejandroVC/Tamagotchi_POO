import Classes.SPECIAL;
import Classes.Species.Species;
import Classes.Tamagotchi;

public class Tests {
    public static void main(String[] args) {
        //SPECIAL special1 = new SPECIAL(5,8,9,3,6,3,10);
        Tamagotchi test1 = new Tamagotchi("Juancho", Species.FELINO);
        Tamagotchi test2 = new Tamagotchi("Filomena", Species.FELINO);

        System.out.println(test1);
        System.out.println(test2);

        Tamagotchi test3;
        int hijos = 0;
        for(int i = 0; i<100_000 ; i++){
            test3 = Tamagotchi.procrear(test1, test2);
            if(test3 != null) {
                //System.out.println(test3);
                hijos++;
            }
        }
        System.out.println("Porcentaje de procreado: " + hijos/1000.0);
        System.out.println("Porcentaje esperado:     " + (test1.getDna().getCha()*test2.getDna().getCha()));
    }
}
