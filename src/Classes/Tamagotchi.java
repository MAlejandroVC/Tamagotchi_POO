package Classes;

import Classes.Species.Species;
import Classes.Species.Subspecies;

import javax.swing.*;
import java.util.Calendar;

public class Tamagotchi {
    private String name;
    private final Species species;
    private final Subspecies subspecies;
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

    public Tamagotchi(String name, Species species, Subspecies subspecies, SPECIAL dna){ //constructor de hijo
        this.name = name;
        this.species = species;
        this.subspecies = subspecies;
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

    public Tamagotchi(String name, Species species, Subspecies subspecies){ //para new tamagotchi, sale del aire
        this.name = name;
        this.species = species;
        this.subspecies = subspecies;
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

    public Species getSpecies(){
        return this.species;
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

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void lvlUp() {
        if(this.lvl < 10)
            this.lvl++;
    }

    public int getHunger() {
        return hunger;
    }

    public int getMaxHunger(){
        return max_hunger;
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

    public int getMaxHp(){
        return max_hp;
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
        feed(-(int) (time.getTimeInMillis() - last_fed.getTimeInMillis())/60_000);
        play(-(int) (time.getTimeInMillis() - last_play.getTimeInMillis())/60_000);
        if(isHungry)
            setHp(-5);
    }

    public void kill(){
        System.out.println(this.name + "ha muerto!");
        JOptionPane.showMessageDialog(null,
                this.name + "ha muerto!",
                "Uh Oh!",
                JOptionPane.WARNING_MESSAGE);
    }

    public void feed(int food){
        last_fed = Calendar.getInstance();
        setHunger(this.hunger+food);
    }

    public void play(int fun){
        last_play = Calendar.getInstance();
        setHappiness(this.happiness+fun);
    }

    public void heal(int healing){
        setHp(getHp()+healing);
    }

    public static Tamagotchi procrear(Tamagotchi pareja1, Tamagotchi pareja2){
        if(pareja1.species != pareja2.species)
            return null;
        if(pareja1.getLvl()<5 || pareja2.getLvl()<5)
            return null;
        boolean isAtractive1 = false;
        boolean isAtractive2 = false;
        if(Math.random()*100 < 5*pareja1.dna.getCha())
            isAtractive1 = true;
        if(Math.random()*100 < 5*pareja2.dna.getCha())
            isAtractive2 = true;
        if(isAtractive1 && isAtractive2)
            return new Tamagotchi(
                    JOptionPane.showInputDialog(null,
                            "Ponle nombre a tu nuevo tamagotchi",
                            "Nació un Tamagotchi!",
                            JOptionPane.PLAIN_MESSAGE),
                    pareja1.species,
                    Subspecies.randomSubspecies(pareja1.subspecies, pareja2.subspecies),
                    new SPECIAL(pareja1.dna, pareja2.dna));
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

    public String stats() { //hacer que se vea mejor
        return "Tamagotchi{" +
                "name='" + name + "'" +
                ", species=" + species +
                ", dna=" + dna +
                ", age=" + age +
                ", lvl=" + lvl +
                ", happiness=" + happiness +
                ", hunger=" + hunger +
                ", hp=" + hp +
                ", max_hunger=" + max_hunger +
                ", max_hp=" + max_hp +
                ", isHungry=" + isHungry +
                ", isDepressed=" + isDepressed +
                ", born=" + born.getTime() +
                ", last_fed=" + last_fed.getTime() +
                ", last_play=" + last_play.getTime() +
                '}';
    }
}
