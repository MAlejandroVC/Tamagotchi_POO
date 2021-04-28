import Classes.SPECIAL;
import Classes.Species.Species;
import Classes.Species.Subspecies;
import Classes.Tamagotchi;

public class Tests {
    public static void main(String[] args) {
        //SPECIAL special1 = new SPECIAL(5,8,9,3,6,3,10);
        Tamagotchi test1 = new Tamagotchi("Juancho", Species.CANINO, Subspecies.HUSKY);
        Tamagotchi test2 = new Tamagotchi("Filomena", Species.CANINO, Subspecies.SHITZU);

        test1.setLvl(5);
        test2.setLvl(5);

        System.out.println(test1);
        System.out.println(test2);

        Tamagotchi test3;
        int hijos = 0;
        for(int i = 0; i<100 ; i++){
            test3 = Tamagotchi.procrear(test1, test2);
            if(test3 != null) {
                //System.out.println(test3);
                hijos++;
            }
        }
        System.out.println("Porcentaje de procreado: " + hijos);
        System.out.println("Porcentaje esperado:     " + (test1.getDna().getCha()*test2.getDna().getCha())/4);
    }
}
