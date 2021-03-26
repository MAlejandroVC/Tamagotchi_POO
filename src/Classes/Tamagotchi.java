package Classes;

import Classes.Species.Species;
import java.util.Calendar;

public class Tamagotchi {
    private String name;
    private final int species;
    protected SPECIAL dna;
    private int age; //minutos vivo
    private int lvl; //0-10
    private int happiness; //0-100
    private int hunger; //0-500
    private int hp; //0-250
    private final int max_hunger; //50*End
    private final int max_hp; //25*hp
    private boolean isHungry;
    private boolean isDepressed;

    private final Calendar born = Calendar.getInstance();
    private Calendar last_fed;
    private Calendar last_play;

    public Tamagotchi(String name, int species, SPECIAL dna){ //constructor de hijo
        this.name = name;
        this.species = species;
        this.dna = dna;

        age = 0;
        lvl = 0;
        max_hunger = 50*dna.End;
        hunger = max_hunger;
        max_hp = 25*dna.End;
        hp = max_hp;
        happiness = 50;

        last_fed = born;
        last_play = born;

        isHungry = false;
        isDepressed = false;
    }

    public Tamagotchi(String name, int species){ //para new tamagotchi, sale del aire
        this.name = name;
        this.species = species;
        this.dna = new SPECIAL(Species.high_end(species), Species.low_end(species));

        age = 0;
        lvl = 0;
        max_hunger = 50*dna.End;
        hunger = max_hunger;
        max_hp = 25*dna.End;
        hp = max_hp;
        happiness = 50;

        last_fed = born;
        last_play = born;

        isHungry = false;
        isDepressed = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age){
        this.age = age;
    }

    public int getLvl() {
        return lvl;
    }

    public void lvlUp() {
        this.lvl++;
    }

    public int getHunger() {
        return hunger;
    }

    private void setHunger(int hunger) {
        isHungry = false;
        this.hunger = hunger;
        if(this.hunger > max_hunger)
            this.hunger = max_hunger;
        if(this.hunger < 0) {
            this.hunger = 0;
            isHungry = true;
            System.out.println(this.name + "tiene hambre");
        }
    }

    public int getHp() {
        return hp;
    }

    private void setHp(int hp) {
        this.hp += hp;
        if(this.hp > max_hp)
            this.hp = max_hp;
        if(this.hp <= 0)
            kill();
    }

    public int getHappiness() {
        return happiness;
    }

    private void setHappiness(int happiness) {
        isDepressed = false;
        this.happiness = happiness;
        if(this.happiness > 100)
            this.happiness = 100;
        if(this.happiness < 0){
            this.happiness = 0;
            isDepressed = true;
            System.out.println(this.name + "esta deprimido");
        }
    }

    public SPECIAL getDna(){
        return this.dna;
    }

    public void update(){
        Calendar time = Calendar.getInstance();
        setAge((int) (time.getTimeInMillis() - born.getTimeInMillis())/60_000);
        setHunger((int) (time.getTimeInMillis() - last_fed.getTimeInMillis())/60_000);
        setHappiness((int) (time.getTimeInMillis() - last_play.getTimeInMillis())/60_000);
        if(isHungry)
            setHp(-5);
    }

    public void kill(){
        System.out.println(this.name + "ha muerto!");
    }

    public void feed(int food){
        last_fed = Calendar.getInstance();
        setHunger(this.hunger+food);
    }

    public void play(int fun){
        last_play = Calendar.getInstance();
        setHappiness(this.happiness+fun);
    }

    public static Tamagotchi procrear(Tamagotchi pareja1, Tamagotchi pareja2){
        if(pareja1.species != pareja2.species)
            return null;
        boolean isAtractive1 = false;
        boolean isAtractive2 = false;
        if(Math.random()*100 < 10*pareja1.dna.getCha())
            isAtractive1 = true;
        if(Math.random()*100 < 10*pareja2.dna.getCha())
            isAtractive2 = true;
        if(isAtractive1 && isAtractive2)
            return new Tamagotchi("no-name", pareja1.species, new SPECIAL(pareja1.dna, pareja2.dna));
        return null;
    }

    @Override
    public String toString() {
        return "Tamagotchi{" +
                "name='" + name + "'" + "\t" +
                ", species=" + species +
                ", dna=" + dna +
                ", lvl=" + lvl +
                ", born=" + born.getTime() +
                '}';
    }
}